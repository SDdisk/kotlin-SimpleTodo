package com.example.simpletodo.api.advice

import com.example.simpletodo.api.exception.TodoNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(exception = [TodoNotFoundException::class])
    fun handleTodoNotFound(ex: TodoNotFoundException): ErrorResponse {
        logHandle(ex)
        return ErrorResponse(
            code = "entity_not_found",
            message = ex.message ?: "Entity not found"
        )
    }

    @ExceptionHandler(exception = [MethodArgumentNotValidException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidation(ex: MethodArgumentNotValidException): ErrorResponse {
        logHandle(ex)
        return ErrorResponse (
            code = "validation_error",
            message = "Validation failed for ${ex.fieldErrorCount} ${if (ex.fieldErrorCount == 1) "field" else "fields"}",
            details = ex.bindingResult.fieldErrors
                .map { "${it.field}: ${it.defaultMessage}" }
        )
    }

    private fun logHandle(ex: Exception) {
        log.error("\"EXCEPTION HANDLER | Catch: '${ex.javaClass.simpleName}', message: '${ex.message}'")
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    data class ErrorResponse(
        val code: String,
        val message: String,
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val details: List<String> = emptyList(),
    )
}