package com.meam.kaffa.student.specification

import com.meam.kaffa.common.enumrate.PaymentStatus
import com.meam.kaffa.student.entity.StudentFee
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate

class StudentFeeSpecifications {
    companion object {
        fun byCriteria(
            studentId: Long?,
            status: PaymentStatus?,
            startDate: LocalDate?,
            endDate: LocalDate?
        ): Specification<StudentFee> = Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()
            studentId?.let {
                predicates.add(cb.equal(root.get<Long>("studentId"), it))
            }
            status?.let {
                predicates.add(cb.equal(root.get<PaymentStatus>("status"), it))
            }
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("dueDate"), startDate, endDate))
            } else if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dueDate"), startDate))
            } else if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dueDate"), endDate))
            }
            cb.and(*predicates.toTypedArray())
        }
    }
}