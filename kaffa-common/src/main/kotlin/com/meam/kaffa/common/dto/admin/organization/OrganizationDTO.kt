package com.meam.kaffa.common.dto.admin.organization

import com.meam.kaffa.common.dto.admin.module.ModuleDTO
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
class OrganizationDTO(
    val id: Long?,
    val name: String,
    val description: String?,
    val address: List<AddressDTO> = emptyList(),
    val modules: List<ModuleDTO> = emptyList(),
    val organizationConfig: OrganizationConfigDTO? = null
)
