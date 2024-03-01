package com.meam.kaffa.school.core.entity

import com.meam.kaffa.common.entity.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import lombok.Data
import java.time.DayOfWeek
import java.time.LocalDateTime

@Data
@Entity
data class Timetable(
    var subject: String,
    var teacherId: Long,
    @ManyToOne(cascade = [CascadeType.PERSIST]) var grade: Grade,
    @ManyToOne(cascade = [CascadeType.PERSIST]) var division: Division,
    var dayOfWeek: DayOfWeek,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
):BaseEntity()
