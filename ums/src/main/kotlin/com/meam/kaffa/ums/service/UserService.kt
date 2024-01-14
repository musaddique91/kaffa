package com.meam.kaffa.ums.service

import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.ums.client.AdminClient
import com.meam.kaffa.ums.entity.User
import com.meam.kaffa.ums.mapper.UserMapper
import com.meam.kaffa.ums.repository.RoleRepository
import com.meam.kaffa.ums.repository.UserAuthRepository
import com.meam.kaffa.ums.repository.UserRepository
import lombok.extern.slf4j.Slf4j
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
    private val adminClient: AdminClient
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
                user.roles=it
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

    private fun sendAccountCreatedMail(user: User){
        val organization = adminClient.getOrganizationById(user.organizationId)
    }
}