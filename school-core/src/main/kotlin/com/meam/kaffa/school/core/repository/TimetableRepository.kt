package com.meam.kaffa.school.core.repository;

import com.meam.kaffa.school.core.entity.Timetable
import org.springframework.data.jpa.repository.JpaRepository

interface TimetableRepository : JpaRepository<Timetable, Long> {
}