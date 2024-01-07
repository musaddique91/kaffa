package com.meam.kaffa.ums.mapper

import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.common.mapper.BaseMapper
import com.meam.kaffa.ums.entity.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper : BaseMapper<User, UserDTO> {
    @Mapping(source = "userAuth.username", target = "username")
    @Mapping(source = "userAuth.enable", target = "enabled")
    @Mapping(target = "userPicture", ignore = true)
    override fun toDTO(user: User): UserDTO

    @Mapping(target = "profilePicture", ignore = true)
    override fun toEntity(dto: UserDTO): User
}