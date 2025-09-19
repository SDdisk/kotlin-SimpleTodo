package com.example.simpletodo.store.repository

import com.example.simpletodo.store.entity.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByTitleStartsWithIgnoreCaseOrderByTitle(title: String): List<Todo>
    override fun findAll(page: Pageable): Page<Todo>
}