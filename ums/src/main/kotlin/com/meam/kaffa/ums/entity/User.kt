package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.convertor.KeyValueListConvertor
import com.meam.kaffa.common.entity.BaseEntity
import com.meam.kaffa.common.enumrate.Gender
import com.meam.kaffa.common.model.KeyValue
import jakarta.persistence.*
import lombok.Data
import java.time.LocalDate

@Data
@Entity
data class User(
    val email: String?,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val dateOfBirth: LocalDate?,
    val profilePicture: ByteArray? = null,
    val organizationId: Long,
    @Enumerated(EnumType.STRING) val gender: Gender?,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_address",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "address_id")]
    )
    val address: List<Address> = emptyList(),
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: List<Role> = emptyList(),
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val preferences: Preferences?,
    @Convert(converter = KeyValueListConvertor::class)
    val socialMediaAccounts: List<KeyValue>?,
    @Convert(converter = KeyValueListConvertor::class)
    val customFields: List<KeyValue>?,
    @OneToOne(cascade = [CascadeType.ALL]) val userAuth: UserAuth
//    @ElementCollection
//    @CollectionTable(
//        name = "organization_ids",
//        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
//    )
//    @Column(name = "organization_id")
//    val organizationIds: MutableList<Long> = mutableListOf()
) : BaseEntity()