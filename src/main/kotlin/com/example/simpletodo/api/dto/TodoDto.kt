package com.example.simpletodo.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant

data class TodoDto(
    val id: Long? = null,

    @field:NotBlank(message = "title cannot be empty")
    @field:Size(max = 250, message = "title should not exceed 250 characters")
    val title: String,

    @field:NotNull(message = "description cannot be null")
    @field:Size(max = 1000, message = "description should not exceed 250 characters")
    val description: String,

    @field:NotNull(message = "isCompleted cannot be null")
    val isCompleted: Boolean,

    val createdAt: Instant?,

    val updatedAt: Instant?,
)