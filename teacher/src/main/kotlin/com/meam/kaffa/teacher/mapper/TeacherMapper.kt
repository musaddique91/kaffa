package com.meam.kaffa.teacher.mapper

import com.meam.kaffa.common.dto.teacher.TeacherDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TeacherMapper {
    fun toUserDTO(teacherDTO: TeacherDTO): UserDTO
    fun toTeacherDTO(userDTO: UserDTO): TeacherDTO
}