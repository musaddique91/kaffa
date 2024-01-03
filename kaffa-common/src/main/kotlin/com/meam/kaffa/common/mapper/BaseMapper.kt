package com.meam.kaffa.common.mapper

import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

interface BaseMapper<Entity, DTO> {
    fun toDTO(entity: Entity): DTO

    fun toEntity(dto: DTO): Entity

    @Mapping(target = "id", ignore = true)
    fun updateEntity(dto: DTO, @MappingTarget entity: Entity): Entity

    fun updateDTO(entity: Entity, @MappingTarget dto: DTO): DTO
}