package com.meam.kaffa.school.core.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Data

@Data
@Entity
data class Division(
    var name: String,
) : BaseIdEntity()
