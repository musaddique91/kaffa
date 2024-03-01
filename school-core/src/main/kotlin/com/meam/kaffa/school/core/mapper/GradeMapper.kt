package com.meam.kaffa.school.core.mapper

import com.meam.kaffa.common.dto.school.GradeDTO
import com.meam.kaffa.common.mapper.BaseMapper
import com.meam.kaffa.school.core.entity.Grade
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface GradeMapper: BaseMapper<Grade, GradeDTO> {
}