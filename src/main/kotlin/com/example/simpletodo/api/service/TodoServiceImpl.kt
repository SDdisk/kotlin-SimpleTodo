package com.example.simpletodo.api.service

import com.example.simpletodo.api.dto.FilterDto
import com.example.simpletodo.api.dto.PageDto
import com.example.simpletodo.api.dto.TodoDto
import com.example.simpletodo.api.exception.TodoNotFoundException
import com.example.simpletodo.store.entity.Todo
import com.example.simpletodo.store.repository.TodoRepository
import jakarta.persistence.criteria.Predicate
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {

    override fun getAll(page: Pageable, filter: FilterDto): PageDto<TodoDto> {
        log.info("SERVICE | Get all todos")

        val spec = filter.toSpecification()

        return todoRepository.findAll(spec, page)
            .map { it.toDto() } // entity to dto
            .toDto() // page to dto
    }

    override fun getById(id: Long): TodoDto {
        log.info("SERVICE | Get todo by id=$id")

        return todoRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw TodoNotFoundException(id)
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

        log.info("SERVICE | Save updated todo=$existingTodo")
        return todoRepository.save(existingTodo).toDto()
    }

    override fun delete(id: Long) {
        log.info("SERVICE | Delete todo with id=$id")
        todoRepository.deleteById(id)
    }


    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    // entity -> dto
    private fun Todo.toDto(): TodoDto =
        TodoDto(
            this.id,
            this.title,
            this.description,
            this.isCompleted,
            this.createdAt,
            this.updatedAt
        )

    // dto -> entity
    private fun TodoDto.toEntity(): Todo =
        Todo(
            this.id,
            this.title,
            this.description,
            this.isCompleted,
            this.createdAt,
            this.updatedAt
        )

    // page -> pageDto
    private fun <T> Page<T>.toDto(): PageDto<T> =
        PageDto(
            content = this.content,
            currentPage = this.number,
            pageSize = this.size,
            totalElements = this.totalElements,
            totalPages = this.totalPages
        )

    // filter -> specification
    private fun FilterDto.toSpecification(): Specification<Todo> = Specification { root, query, cb ->
        val predicates = mutableListOf<Predicate>()

        this.title?.let { predicates.add(
            cb.like(root.get("title"), "$it%")
        ) }

        this.description?.let { predicates.add(
            cb.like(root.get("description"), "$it%")
        ) }

        this.isCompleted?.let { predicates.add(
            cb.equal(root.get<Boolean>("isCompleted"), it)
        ) }

        cb.and(*predicates.toTypedArray())
    }
}