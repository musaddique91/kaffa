package com.meam.kaffa.school.core.repository

import com.meam.kaffa.school.core.entity.Division
import org.springframework.data.jpa.repository.JpaRepository

interface DivisionRepository : JpaRepository<Division, Long> {
}
