package com.meam.kaffa.student.entity

import com.meam.kaffa.common.entity.BaseEntity
import com.meam.kaffa.common.enumrate.PaymentStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "student_fee")
data class StudentFee(
    var studentId: Long,
    var originalFee: Double,
    var dueDate: LocalDate,
    @Enumerated(EnumType.STRING) var status: PaymentStatus,
    var discount: Double?,
    var totalFee: Double
) : BaseEntity()