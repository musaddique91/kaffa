package com.meam.kaffa.student.mapper

import com.meam.kaffa.common.dto.student.StudentDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface StudentMapper {
    fun toUserDTO(studentDTO: StudentDTO): UserDTO
    fun toStudentDTO(userDTO: UserDTO): StudentDTO
}