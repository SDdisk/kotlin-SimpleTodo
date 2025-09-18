package com.example.simpletodo.api.advice.response

import java.time.LocalDateTime

data class ErrorResponse(
    val code: String,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val details: List<String> = emptyList(),
)