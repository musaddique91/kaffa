package com.meam.kaffa.ums.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.common.events.MailNotificationEvent
import com.meam.kaffa.common.events.NotificationType
import com.meam.kaffa.ums.client.AdminClient
import com.meam.kaffa.ums.config.ConfigProperties
import com.meam.kaffa.ums.entity.User
import com.meam.kaffa.ums.mapper.UserMapper
import com.meam.kaffa.ums.repository.RoleRepository
import com.meam.kaffa.ums.repository.UserAuthRepository
import com.meam.kaffa.ums.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Slf4j
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val userAuthRepository: UserAuthRepository,
    private val roleRepository: RoleRepository,
    private val adminClient: AdminClient,
    private val configProperties: ConfigProperties,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val objectMapper: ObjectMapper
) {

    fun create(userDTO: UserDTO): UserDTO {
        val userDTO = userMapper.toEntity(userDTO)
            .let { mapRole(it, userDTO.roles) }
            .let { userRepository.save(it) }
            .let { userMapper.toDTO(it) }
        //send event to notification service
        return userDTO
    }

    private fun mapRole(user: com.meam.kaffa.ums.entity.User, roles: List<RoleDTO>): com.meam.kaffa.ums.entity.User {
        return roles
            .map { it.id }
            .mapNotNull { roleRepository.findByIdOrNull(it) }
            .let {
                user.roles = it
                user
            }
    }

    fun update(id: Long, userDTO: UserDTO): UserDTO {
        return userRepository.findByIdOrNull(id)
            ?.let { userMapper.updateEntity(userDTO, it) }
            ?.let { userRepository.save(it) }
            ?.let { userMapper.toDTO(it) }
            ?: throw UsernameNotFoundException("user not found")
    }

    fun getUser(id: Long): UserDTO {
        return userRepository.findByIdOrNull(id)
            ?.let { userMapper.toDTO(it) }
            ?: throw UsernameNotFoundException("user not found")
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserByUsername(username: String): UserDTO? {
        return userAuthRepository.findByUsername(username)
            ?.let { auth ->
                userRepository.findByUserAuthId(auth.id!!)
                    ?.let { userMapper.toDTO(it) }
                    ?.let { dto ->
                        dto.enabled = auth.enable
                        dto
                    }
            }
    }

    private fun sendAccountCreatedMail(user: User) {
        var userDTO = userMapper.toDTO(user);
        val organization = adminClient.getOrganizationById(user.organizationId)
        var event = MailNotificationEvent(
            source = "USER_PASSWORD",
            to = mutableListOf(user.email!!),
            modalMapData = mutableMapOf(
                "user" to objectMapper.writeValueAsString(userDTO),
                "organization" to objectMapper.writeValueAsString(organization),
            ),
        )
        if (organization.organizationConfig != null) {
            if (organization.organizationConfig?.createUserWithPassword == true) {
                event.modalMapData["password"] = configProperties.defaultPassword
                event.type = NotificationType.NEW_USER_MAIL_WITH_PASSWORD
            } else {
                event.type = NotificationType.NEW_USER_WITH_PASSWORD_RESET_MAIL
            }
        } else {
            event.type = NotificationType.NEW_USER_WITH_PASSWORD_RESET_MAIL
        }
        applicationEventPublisher.publishEvent(event)
    }

}