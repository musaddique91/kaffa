package com.meam.kaffa.admin.exception

import com.meam.kaffa.common.exceptions.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [OrganizationNotFoundException::class])
    fun handlerAppSysException(ex: OrganizationNotFoundException, request: WebRequest): ResponseEntity<ErrorDTO> {
        val errorMessage = ErrorDTO(
            ex.code,
            ex.message,
            request.contextPath,
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}