package com.meam.kaffa.student.entity

import com.meam.kaffa.common.entity.BaseIdEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "student_fee_details")
open class StudentFeeDetails(
    var studentFeeId: Long,
    val feeTypeCode: String,
    val amount: Double
) : BaseIdEntity()