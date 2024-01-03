package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Data

@Data
@Entity
data class Preferences(
    val language: String,
    val theme: String,
) : BaseIdEntity()
