package com.example.simpletodo.api.advice

import com.example.simpletodo.api.advice.response.ErrorResponse
import com.example.simpletodo.api.exception.TodoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(exception = [TodoNotFoundException::class])
    fun handleTodoNotFound(ex: TodoNotFoundException) =
        ErrorResponse(
            code = "entity_not_found",
            message = ex.message ?: "Entity not found"
        )

    @ExceptionHandler(exception = [MethodArgumentNotValidException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidation(ex: MethodArgumentNotValidException) =
        ErrorResponse (
            code = "validation_error",
            message = "Validation failed for ${ex.fieldErrorCount} ${if (ex.fieldErrorCount == 1) "field" else "fields"}",
            details = ex.bindingResult
                .fieldErrors
                .map { "${it.field}: ${it.defaultMessage}" }
        )
}