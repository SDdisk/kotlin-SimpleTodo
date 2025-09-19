package com.example.simpletodo.api.service

import com.example.simpletodo.api.dto.PageDto
import com.example.simpletodo.api.dto.TodoDto
import org.springframework.data.domain.Pageable

interface TodoService {
    // TODO: понять, что вернуть: Page<TodoDto> или List<TodoDto>
    fun getAll(page: Pageable): PageDto<TodoDto>
    fun getById(id: Long): TodoDto
    //fun getByTitle(title: String): List<TodoDto>

    fun create(todoDto: TodoDto): TodoDto
    fun update(id: Long, todoDto: TodoDto): TodoDto
    fun delete(id: Long)
}