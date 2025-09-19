package com.example.simpletodo.api.service

import com.example.simpletodo.api.dto.FilterDto
import com.example.simpletodo.api.dto.PageDto
import com.example.simpletodo.api.dto.TodoDto
import org.springframework.data.domain.Pageable

interface TodoService {
    fun getAll(page: Pageable, filter: FilterDto): PageDto<TodoDto>
    fun getById(id: Long): TodoDto

    fun create(todoDto: TodoDto): TodoDto
    fun update(id: Long, todoDto: TodoDto): TodoDto
    fun delete(id: Long)
}