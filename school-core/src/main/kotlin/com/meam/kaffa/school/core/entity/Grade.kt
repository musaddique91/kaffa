package com.meam.kaffa.school.core.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import lombok.Data

@Data
@Entity
data class Grade(
    var name: String,
    @OneToMany(cascade = [CascadeType.ALL])
    var divisions: MutableList<Division>,
    var organizationId: Long
) : BaseIdEntity()
