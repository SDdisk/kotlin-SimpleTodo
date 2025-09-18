package com.example.simpletodo.test

import kotlin.streams.toList

fun main() {
    val incomeData = "987Timoha123"
    println(
        process(incomeData)
    )
}

//['9','8','7','T','i','m','o','h','a','1','2','3']

fun process(data: String): String {
    val changedData = data.chars()      .also { println("1. $it") }
        .map { it + 1 }                 .also { println("2. $it") }
        .toList()                       .also { println("3. $it") }
        .map { it.toChar() }            .also { println("4. $it") }
        .joinToString("")     .also { println("5. $it") }
        .reversed()                     .also { println("6. $it") }

    return changedData
}