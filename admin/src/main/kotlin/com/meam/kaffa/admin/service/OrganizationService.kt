package com.meam.kaffa.admin.service

import com.meam.kaffa.admin.entity.Organization
import com.meam.kaffa.admin.exception.OrganizationNotFoundException
import com.meam.kaffa.admin.mapper.OrganizationMapper
import com.meam.kaffa.admin.repository.ModuleRepository
import com.meam.kaffa.admin.repository.OrganizationRepository
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import lombok.extern.slf4j.Slf4j
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Slf4j
class OrganizationService(
    private val organizationRepository: OrganizationRepository,
    private val moduleRepository: ModuleRepository,
    private val organizationMapper: OrganizationMapper
) {

    fun getOrganizations() =
        organizationRepository.findAll()
            .map { organizationMapper.toDTO(it) }
            .toMutableList()

    fun createOrganization(organizationDTO: OrganizationDTO) =
        organizationDTO
            .let { organizationMapper.toEntity(organizationDTO) }
            .let { setModulesToOrganization(organizationDTO, it) }
            .let { organizationRepository.save(it) }
            .let { organizationMapper.toDTO(it) }


    fun updateOrganization(organizationId: Long, organizationDTO: OrganizationDTO) =
        organizationRepository.findById(organizationId)
            .map { organizationMapper.updateEntity(organizationDTO, it) }
            .map { setModulesToOrganization(organizationDTO, it) }
            .map { organizationRepository.save(it) }
            .map { organizationMapper.toDTO(it) }
            .orElseThrow { OrganizationNotFoundException() }

    fun deleteOrganization(id: Long) =
        organizationRepository.findById(id)
            .map { organizationRepository.delete(it) }
            .orElseThrow { OrganizationNotFoundException() }
            .run { true }

    private fun setModulesToOrganization(organizationDTO: OrganizationDTO, organization: Organization) =
        organizationDTO.modules
            .mapNotNull { moduleRepository.findByIdOrNull(it.code) }
            .toMutableList()
            .let { organization.modules = it }.run { organization }

    private fun createAdminRoleForOrganization(organizationId: Long) {

    }
}