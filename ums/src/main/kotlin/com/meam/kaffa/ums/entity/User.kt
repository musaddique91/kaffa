package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.convertor.KeyValueListConvertor
import com.meam.kaffa.common.dto.ums.ContactNumbers
import com.meam.kaffa.common.entity.BaseEntity
import com.meam.kaffa.common.enumrate.Gender
import com.meam.kaffa.common.enumrate.UserType
import com.meam.kaffa.common.model.KeyValue
import jakarta.persistence.*
import lombok.Data
import java.time.LocalDate

@Data
@Entity
data class User(
    var userType: UserType = UserType.USER,
    var email: String?,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String?,
    var dateOfBirth: LocalDate?,
    @Lob
    var profilePicture: ByteArray? = null,
    var organizationId: Long,
    @Enumerated(EnumType.STRING) var gender: Gender?,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_address",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "address_id")]
    )
    var address: List<Address> = emptyList(),
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: List<Role> = emptyList(),
    @OneToOne(cascade = [CascadeType.ALL])
    var preferences: Preferences? = null,
    @Convert(converter = KeyValueListConvertor::class)
    var socialMediaAccounts: List<KeyValue>?,
    @Convert(converter = KeyValueListConvertor::class)
    var customFields: MutableList<KeyValue>?,
    @OneToOne(cascade = [CascadeType.ALL]) var userAuth: UserAuth,
    @Convert(converter = KeyValueListConvertor::class)
    var contactNumbers: ContactNumbers? = null
) : BaseEntity()