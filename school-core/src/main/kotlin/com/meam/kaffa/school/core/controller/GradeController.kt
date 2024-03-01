package com.meam.kaffa.school.core.controller

import com.meam.kaffa.common.dto.school.GradeDTO
import com.meam.kaffa.school.core.service.GradeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("grades")
class GradeController(
    private val gradeService: GradeService
) {

    @GetMapping
    fun getGrades(): List<GradeDTO> {
        return gradeService.getGrades()
    }

    @PostMapping
    fun addGrade(@RequestBody grade: GradeDTO): GradeDTO {
        return gradeService.createGrade(grade = grade)
    }

    @PutMapping("{id}")
    fun updateGrade(@PathVariable id: Long, @RequestBody grade: GradeDTO): GradeDTO {
        return gradeService.updateGrade(id, grade)
    }

}