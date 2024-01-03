package com.meam.kaffa.ums.service

import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.ums.mapper.UserMapper
import com.meam.kaffa.ums.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Slf4j
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {

    fun create(userDTO: UserDTO): UserDTO {
        val userDTO = userMapper.toEntity(userDTO)
            .let { userRepository.save(it) }
            .let { userMapper.toDTO(it) }
        //send event to notification service
        return userDTO
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
}