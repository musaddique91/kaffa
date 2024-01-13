package com.meam.kaffa.ums.mapper

import com.meam.kaffa.common.dto.ums.RoleDTO
import com.meam.kaffa.common.mapper.BaseMapper
import com.meam.kaffa.ums.entity.Role
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RoleMapper : BaseMapper<Role, RoleDTO>