package com.meam.kaffa.security.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import javax.security.auth.login.CredentialException

@ControllerAdvice
class GlobalexceptionHandler {
//    @ExceptionHandler(value = [AppSysException::class])
//    fun handlerAppSysException(ex: AppSysException, request: WebRequest): ResponseEntity<ErrorDTO> {
//        val errorMessage = ErrorDTO(
//            ex.code,
//            ex.message,
//            request.contextPath,
//            HttpStatus.INTERNAL_SERVER_ERROR.value()
//        )
//        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
//    }

}
