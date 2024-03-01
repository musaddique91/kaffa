package com.meam.kaffa.ums.service

import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.ums.config.ConfigProperties
import com.meam.kaffa.ums.entity.UserAuth
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
    private val configProperties: ConfigProperties
) {
    fun create(userDTO: UserDTO): UserDTO {
        val userDTO = userMapper.toEntity(userDTO)
            .let { mapRole(it, userDTO.roles) }
            .let {
                var userAuth: UserAuth = createUserAuth(userDTO)
                it.userAuth = userAuth
                userRepository.save(it)
            }
            .let { userMapper.toDTO(it) }
        return userDTO
    }

    private fun createUserAuth(userDTO: UserDTO): UserAuth {
        return UserAuth(
            username = userDTO.username,
            password = configProperties.defaultPassword,
            enable = true,
        )
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

    fun getUsers(username: String, search: String): List<UserDTO> {
        return emptyList();
    }

}