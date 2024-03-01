package com.meam.kaffa.common.dto.ums

import com.meam.kaffa.common.dto.AuditDTO
import com.meam.kaffa.common.dto.admin.organization.AddressDTO
import com.meam.kaffa.common.enumrate.Gender
import com.meam.kaffa.common.enumrate.UserType
import com.meam.kaffa.common.model.KeyValue
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@Builder
@NoArgsConstructor
data class UserDTO(
    var id: Long?,
    var username: String,
    var email: String?,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String?,
    var address: List<AddressDTO>? = emptyList(),
    var roles: List<RoleDTO> = emptyList(),
    var preferences: PreferencesDTO? = null,
    var dateOfBirth: LocalDate? =null,
    var gender: Gender?,
    var userPicture: String? = null,
    var socialMediaAccounts: List<KeyValue>? = emptyList(),
    var customFields: MutableList<KeyValue>? = mutableListOf(),
    var organizationId: Long?,
    var enabled: Boolean? = true,
    var authrities: MutableSet<String>? = mutableSetOf(),
    var userType: UserType = UserType.USER,
) : AuditDTO()