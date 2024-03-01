package com.meam.kaffa.ums.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import lombok.Data

@Data
@Entity
data class Preferences(
    var language: String,
    var theme: String,
    var layout: String,
) : BaseIdEntity()
