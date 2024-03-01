package com.meam.kaffa.student.repository;

import com.meam.kaffa.student.entity.Installment
import org.springframework.data.jpa.repository.JpaRepository

interface InstallmentRepository : JpaRepository<Installment, Long> {
}