package com.meam.kaffa.common.dto.student

import com.fasterxml.jackson.annotation.JsonInclude
import com.meam.kaffa.common.enumrate.PaymentStatus
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class FeeDTO(
    var id: Long = 0,
    var studentId: Long,
    var amount: Double,
    var dueDate: LocalDate,
    var status: PaymentStatus
)