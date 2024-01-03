package com.meam.kaffa.common.dto.ums

import com.meam.kaffa.common.dto.admin.organization.AddressDTO
import com.meam.kaffa.common.entity.BaseEntity
import com.meam.kaffa.common.enumrate.Gender
import com.meam.kaffa.common.model.KeyValue
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@Builder
@NoArgsConstructor
data class UserDTO(
    var username: String,
    val email: String?,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val address: List<AddressDTO> = emptyList(),
    val roles: List<RoleDTO> = emptyList(),
    val preferences: PreferencesDTO?,
    val dateOfBirth: LocalDate?,
    val gender: Gender?,
    var userPicture: String?,
    val socialMediaAccounts: List<KeyValue>?,
    val customFields: List<KeyValue>?,
    var organizationId: Long?
) : BaseEntity()