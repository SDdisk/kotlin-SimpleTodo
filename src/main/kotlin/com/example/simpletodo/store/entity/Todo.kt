package com.example.simpletodo.store.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

/*
    Класс должен быть open:
        Согласно спецификации JPA, все классы и свойства, связанные с JPA, не должны быть final.
    Если final -> Отключается механизм Hibernate: проксирование.
    Нет прокси - нет lazy загрузки.
    Все ассоциации ToOne будут Eager, что скажется на производительности.
 */
@Entity
@Table(name = "todo_table")
open class Todo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,
    open var title: String = "",
    open var description: String = "",
    open var isCompleted: Boolean = false,
) {
    override fun equals(other: Any?): Boolean {
        // та же ссылка, тот же объект -> true
        if (this === other) return true
        // не класс или не наш класс -> false
        if (other !is Todo) return false

        // неизменяемое поле только id
        // сравнение будет только по нему
        return this.id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Todo(id=$id, title=$title, description=$description, isCompleted=$isCompleted)"
}