package com.meam.kaffa.common.exceptions

import lombok.Builder
import lombok.Data

@Data
@Builder
data class ErrorDTO(val code: String? = "500", val message: String?, val path: String, val httpStatus: Int)
