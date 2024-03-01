package com.meam.kaffa.student.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import com.meam.kaffa.common.enumrate.PaymentStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDate

@Entity
data class Installment(
    val studentFeeId: Long,
    val installmentAmount: Double,
    val dueDate: LocalDate,
    val paymentDate: LocalDate?,
    @Enumerated(EnumType.STRING) val status: PaymentStatus
) : BaseIdEntity()
