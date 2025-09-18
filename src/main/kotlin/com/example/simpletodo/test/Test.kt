package com.example.simpletodo.test

import com.example.simpletodo.store.entity.Todo
import com.example.simpletodo.store.repository.TodoRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Test(
    private val repository: TodoRepository,
) {
    @GetMapping("/create")
    @ResponseBody
    fun create(): String {
        println(
            "Введи Y для создания нового объекта в базу данных:"
        )
        val answer = readln()
        val entity = Todo(
            null,
            "test",
            "teswAA",
            false
        )

        if (answer == "Y") {
            val saved = repository.save(entity)
            return "saved $saved"
        }

        return "not saved $entity"
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    fun get(@PathVariable id: Long?): String {
        if (id == null) return "wrong id"
        println("getting $id todo")
        val found = repository.findById(id)
        return if (found.isPresent) "found: ${found.get()}" else "not found by id:$id"
    }
}