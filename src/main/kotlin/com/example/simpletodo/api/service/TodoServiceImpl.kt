package com.example.simpletodo.api.service

import com.example.simpletodo.api.dto.TodoDto
import com.example.simpletodo.api.exception.TodoNotFoundException
import com.example.simpletodo.api.extention.toDto
import com.example.simpletodo.api.extention.toEntity
import com.example.simpletodo.store.repository.TodoRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {

    override fun getAll(): List<TodoDto> {
        log.info("SERVICE | Get all todos")

        return todoRepository.findAll()
            .map { it.toDto() }
    }

    override fun getById(id: Long): TodoDto {
        log.info("SERVICE | Get todo by id=$id")

        return todoRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw TodoNotFoundException(id)
    }

    override fun getByTitle(title: String): List<TodoDto> {
        log.info("SERVICE | Get todos by title=$title")

        return todoRepository.findByTitleStartsWithIgnoreCaseOrderByTitle(title)
            .map { it.toDto() }
    }

    override fun create(todoDto: TodoDto): TodoDto {
        log.info("SERVICE | Save todo=$todoDto")

        return todoRepository.save(todoDto.toEntity()).toDto()
    }

    override fun update(id: Long, todoDto: TodoDto): TodoDto {
        log.info("SERVICE | Update todo with id=$id")

        log.info("SERVICE | Check for exist todo with id=$id")
        val existingTodo = todoRepository.findByIdOrNull(id)
            ?: throw TodoNotFoundException(id)

        log.info("SERVICE | Update fields to existing todo=$existingTodo")
        existingTodo.title = todoDto.title
        existingTodo.description = todoDto.description
        existingTodo.isCompleted = todoDto.isCompleted

        log.info("SERVICE | Save todo=$existingTodo")
        return todoRepository.save(existingTodo).toDto()
    }

    override fun delete(id: Long) {
        log.info("SERVICE | Delete todo with id=$id")
        todoRepository.deleteById(id)
    }


    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}