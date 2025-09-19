package com.example.simpletodo.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class FilterDto(

    @field:NotBlank(message = "title cannot be empty")
    @field:Size(max = 250, message = "title should not exceed 250 characters")
    val title: String? = null,

    @field:NotNull(message = "description cannot be null")
    @field:Size(max = 1000, message = "description should not exceed 250 characters")
    val description: String? = null,

    @field:NotNull(message = "isCompleted cannot be null")
    val isCompleted: Boolean? = null,
)