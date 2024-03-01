package com.meam.kaffa.student.repository;

import com.meam.kaffa.student.entity.StudentFeeDetails
import org.springframework.data.jpa.repository.JpaRepository

interface StudentFeeDetailsRepository : JpaRepository<StudentFeeDetails, Long> {
}