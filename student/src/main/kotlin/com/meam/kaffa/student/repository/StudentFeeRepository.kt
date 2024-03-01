package com.meam.kaffa.student.repository;

import com.meam.kaffa.student.entity.StudentFee
import org.springframework.data.jpa.repository.JpaRepository

interface StudentFeeRepository : JpaRepository<StudentFee, Long> {
    fun findByStudentId(studentId: Long): StudentFee
}