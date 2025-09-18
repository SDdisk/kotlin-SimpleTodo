package com.example.simpletodo.api.service

import com.example.simpletodo.api.dto.TodoDto

interface TodoService {
    fun getAll(): List<TodoDto>
    fun getById(id: Long): TodoDto
    fun getByTitle(title: String): List<TodoDto>

    fun create(todoDto: TodoDto): TodoDto
    fun update(id: Long, todoDto: TodoDto): TodoDto
    fun delete(id: Long)
}