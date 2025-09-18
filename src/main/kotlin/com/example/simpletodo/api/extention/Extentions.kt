package com.example.simpletodo.api.extention

import com.example.simpletodo.api.dto.TodoDto
import com.example.simpletodo.store.entity.Todo

// dto -> entity
// entity -> dto

fun Todo.toDto(): TodoDto =
    TodoDto(
        this.id,
        this.title,
        this.description,
        this.isCompleted
    )

fun TodoDto.toEntity(): Todo =
    Todo(
        this.id,
        this.title,
        this.description,
        this.isCompleted
    )