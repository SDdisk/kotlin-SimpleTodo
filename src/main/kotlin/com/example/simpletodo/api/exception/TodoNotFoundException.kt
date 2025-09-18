package com.example.simpletodo.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class TodoNotFoundException(message: String) : RuntimeException(message) {
    constructor(id: Long) : this(message = "Todo with id=$id not found")
}