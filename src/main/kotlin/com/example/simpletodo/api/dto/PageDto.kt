package com.example.simpletodo.api.dto

data class PageDto<T>(
    val content: List<T>,
    val currentPage: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int
)