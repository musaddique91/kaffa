package com.meam.kaffa.school.core.service

import com.meam.kaffa.common.dto.school.GradeDTO
import com.meam.kaffa.common.exceptions.KaffaException
import com.meam.kaffa.school.core.entity.Division
import com.meam.kaffa.school.core.entity.Grade
import com.meam.kaffa.school.core.mapper.GradeMapper
import com.meam.kaffa.school.core.repository.DivisionRepository
import com.meam.kaffa.school.core.repository.GradeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GradeService(
    private val gradeRepository: GradeRepository,
    private val gradeMapper: GradeMapper,
    private val divisionRepository: DivisionRepository
) {
    fun getGrades(): List<GradeDTO> {
        return gradeRepository.findAll()
            .map { GradeDTO(it.id, it.name) }
    }

    fun createGrade(grade: GradeDTO): GradeDTO {
        return grade
            .let { dto ->
                val grade = gradeMapper.toEntity(dto)
                grade.divisions = mutableListOf()
                mapDivision(grade, dto);
                grade
            }
            .let { gradeRepository.save(it) }
            .let { gradeMapper.toDTO(it) }
    }

    private fun mapDivision(grade: Grade, dto: GradeDTO) {
        dto.divisions?.map {
            if (it.id == null) Division(it.name) else divisionRepository.findByIdOrNull(it.id) ?: Division(it.name)
        }
            ?.let { grade.divisions = it.toMutableList() }
    }

    fun updateGrade(gradeId: Long, gradeDTO: GradeDTO): GradeDTO {
        return gradeRepository.findById(gradeId).orElseThrow { KaffaException(404, "Grade not found") }
            .let {
                val dbGrade = gradeMapper.updateEntity(gradeDTO, it)
                mapDivision(dbGrade, gradeDTO);
                dbGrade
            }
            .let { gradeRepository.save(it) }
            .let { gradeMapper.toDTO(it) }
    }
}