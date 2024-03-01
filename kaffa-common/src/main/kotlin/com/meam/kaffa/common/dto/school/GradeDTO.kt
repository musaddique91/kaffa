package com.meam.kaffa.common.dto.school

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Builder
import lombok.Data

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GradeDTO(
    var id: Long?,
    var name: String,
    var divisions: MutableList<DivisionDTO>? = null
)
