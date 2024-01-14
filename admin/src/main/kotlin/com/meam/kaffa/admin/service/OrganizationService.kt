package com.meam.kaffa.admin.service

import com.meam.kaffa.admin.client.UMSClient
import com.meam.kaffa.admin.entity.Organization
import com.meam.kaffa.admin.exception.OrganizationNotFoundException
import com.meam.kaffa.admin.mapper.OrganizationMapper
import com.meam.kaffa.admin.repository.ModuleRepository
import com.meam.kaffa.admin.repository.OrganizationRepository
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import com.meam.kaffa.common.dto.ums.RoleDTO
import lombok.extern.slf4j.Slf4j
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
class OrganizationService(
    private val organizationRepository: OrganizationRepository,
    private val moduleRepository: ModuleRepository,
    private val organizationMapper: OrganizationMapper,
    private val umsClient: UMSClient
) {

    fun getOrganizations() =
        organizationRepository.findAll()
            .map { organizationMapper.toDTO(it) }
            .toMutableList()

    @Transactional
    fun createOrganization(organizationDTO: OrganizationDTO): OrganizationDTO =
        organizationDTO
            .let { organizationMapper.toEntity(organizationDTO) }
            .let { setModulesToOrganization(organizationDTO, it) }
            .let { organizationRepository.save(it) }
            .let { organization ->
                createAdminRoleForOrganization(organization.id!!)
                organization
            }
            .let { organizationMapper.toDTO(it) }


    fun updateOrganization(organizationId: Long, organizationDTO: OrganizationDTO): OrganizationDTO =
        organizationRepository.findById(organizationId)
            .map { organizationMapper.updateEntity(organizationDTO, it) }
            .map { setModulesToOrganization(organizationDTO, it) }
            .map { organizationRepository.save(it) }
            .map { organizationMapper.toDTO(it) }
            .orElseThrow { OrganizationNotFoundException() }

    fun deleteOrganization(id: Long): Boolean {
        organizationRepository.findById(id)
            .map { organizationRepository.delete(it) }
            .orElseThrow { OrganizationNotFoundException() }
        return true
    }

    private fun setModulesToOrganization(organizationDTO: OrganizationDTO, organization: Organization) =
        organizationDTO.modules
            .mapNotNull { moduleRepository.findByIdOrNull(it.code) }
            .toMutableList()
            .let { organization.modules = it }.run { organization }

    private fun createAdminRoleForOrganization(organizationId: Long) {
        umsClient.createRole(
            RoleDTO(
                name = "admin",
                organizationId = organizationId,
                permissions = umsClient.getPermissions()
            )
        )
    }

    fun getOrganizationById(id: Long): OrganizationDTO {
        return (organizationRepository.findByIdOrNull(id) ?: throw OrganizationNotFoundException())
            .let { organizationMapper.toDTO(it) }
    }
}