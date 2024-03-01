package com.meam.kaffa.school.core.repository

import com.meam.kaffa.school.core.entity.Grade
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository : JpaRepository<Grade, Long> {
}
