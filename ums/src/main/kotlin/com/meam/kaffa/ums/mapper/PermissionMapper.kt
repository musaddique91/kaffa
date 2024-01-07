package com.meam.kaffa.ums.mapper

import com.meam.kaffa.common.dto.ums.PermissionDTO
import com.meam.kaffa.common.mapper.BaseMapper
import com.meam.kaffa.ums.entity.Permission
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PermissionMapper : BaseMapper<Permission, PermissionDTO>