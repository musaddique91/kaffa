package com.meam.kaffa.admin.mapper

import com.meam.kaffa.admin.entity.Organization
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import com.meam.kaffa.common.mapper.BaseMapper
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrganizationMapper : BaseMapper<Organization, OrganizationDTO>