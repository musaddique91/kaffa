package com.meam.kaffa.ums.service

import com.meam.kaffa.ums.mapper.PermissionMapper
import com.meam.kaffa.ums.repository.PermissionRepository
import org.springframework.stereotype.Service

@Service
class DomainService(
    private val permissionRepository: PermissionRepository,
    private val permissionMapper: PermissionMapper
) {

    fun getPermissions() = permissionRepository.findAll().map { permissionMapper.toDTO(it) }
}