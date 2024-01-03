package com.meam.kaffa.admin.service

import com.meam.kaffa.admin.client.UMSClient
import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.security.util.TokenHelper
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class UserService(
    private val tokenHelper: TokenHelper,
    private val umsClient: UMSClient
) {
    fun create(userDTO: UserDTO): UserDTO {
        if (userDTO.organizationId == null) {
            userDTO.organizationId = tokenHelper.organizationId()
        }
        return umsClient.createUser(userDTO)
    }

    fun update(id: Long, userDTO: UserDTO): UserDTO {
        if (userDTO.organizationId == null) {
            userDTO.organizationId = tokenHelper.organizationId()
        }
        return umsClient.update(id,userDTO)
    }

    fun getUser(id: Long): UserDTO = umsClient.getUser(id)

    fun delete(id: Long) {
        umsClient.deleteUser(id)
    }
}