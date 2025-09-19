package com.example.simpletodo.api.controller

import com.example.simpletodo.api.dto.FilterDto
import com.example.simpletodo.api.dto.PageDto
import com.example.simpletodo.api.dto.TodoDto
import com.example.simpletodo.api.service.TodoService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TodoController(
    private val todoService: TodoService,
) {

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(@PageableDefault(page = 0, size = 5, sort = ["id"], direction = Sort.Direction.DESC) page: Pageable, filter: FilterDto): PageDto<TodoDto> {
        log.info("CONTROLLER | path: '.../todos', method: 'GET', page:'number=${page.pageNumber}, size=${page.pageSize}, sortBy=${page.sort}'")

        return todoService.getAll(page, filter)
    }

    @GetMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: Long): TodoDto {
        log.info("CONTROLLER | path: '.../todo/$id', method: 'GET'")

        return todoService.getById(id)
    }

    @PostMapping("/create_todo")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid todoDto: TodoDto): TodoDto {
        log.info("CONTROLLER | path: '.../create_todo', method: 'POST', body: '$todoDto'")

        return todoService.create(todoDto)
    }

    @PutMapping("/update_todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody @Valid todoDto: TodoDto): TodoDto {
        log.info("CONTROLLER | path: '.../update_todo/$id', method: 'PUT', body: '$todoDto'")

        return todoService.update(id, todoDto)
    }

    @DeleteMapping("/delete_todo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        log.info("CONTROLLER | path: '.../delete_todo/$id', method: 'DELETE'")

        todoService.delete(id)
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}