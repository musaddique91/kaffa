package com.meam.kaffa.ums.service

import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.security.util.TokenHelper
import com.meam.kaffa.ums.entity.Role
import com.meam.kaffa.ums.mapper.RoleMapper
import com.meam.kaffa.ums.repository.PermissionRepository
import com.meam.kaffa.ums.repository.RoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository,
    private val roleMapper: RoleMapper,
    private val tokenHelper: TokenHelper,
    private val permissionRepository: PermissionRepository
) {
    fun getAllRoles() = roleRepository.findByOrganizationId(tokenHelper.organizationId()).map { roleMapper.toDTO(it) }

    fun createRole(roleDTO: RoleDTO): RoleDTO {
        return roleMapper.toEntity(roleDTO)
            .let {
                mapPermissions(it, roleDTO)
                it
            }
            .let { roleRepository.save(it) }
            .let { roleMapper.toDTO(it) }
    }

    private fun mapPermissions(role: Role, roleDTO: RoleDTO) {
        roleDTO.permissions
            .map { it.code }
            .mapNotNull { permissionRepository.findByIdOrNull(it) }
            .let { permissions -> role.permissions = permissions }
    }
}
