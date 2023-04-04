package com.android.batya.dreams.repository

import com.android.batya.dreams.model.Dream

class DreamRepository {
    fun getDreams(): List<Dream> {
        return listOf(
            Dream(
                id = "1",
                title = "Один",
                description = "Длинное описание. ".repeat(5),
                date = "25 марта"
            ),
            Dream(
                id = "2",
                title = "Два",
                description = "Описание. ".repeat(5),
                date = "30 декабря"
            ),
            Dream(
                id = "3",
                title = "Три",
                description = "Текст... Описание. ".repeat(5),
                date = "11 февраля"
            ),
            Dream(
                id = "4",
                title = "Четыре",
                description = "Длинное описание. ".repeat(5),
                date = "1 ноября"
            ),
            Dream(
                id = "5",
                title = "Пять",
                description = "Длинное описание. ".repeat(5),
                date = "2 декабря"
            ),
            Dream(
                id = "6",
                title = "Шесть",
                description = "Длинное описание. ".repeat(5),
                date = "6 июня"
            ),
            Dream(
                id = "1",
                title = "Один",
                description = "Длинное описание. ".repeat(5),
                date = "25 марта"
            ),
            Dream(
                id = "2",
                title = "Два",
                description = "Описание. ".repeat(5),
                date = "30 декабря"
            ),
            Dream(
                id = "3",
                title = "Три",
                description = "Текст... Описание. ".repeat(5),
                date = "11 февраля"
            ),
            Dream(
                id = "4",
                title = "Четыре",
                description = "Длинное описание. ".repeat(5),
                date = "1 ноября"
            ),
            Dream(
                id = "5",
                title = "Пять",
                description = "Длинное описание. ".repeat(5),
                date = "2 декабря"
            ),
            Dream(
                id = "6",
                title = "Шесть",
                description = "Длинное описание. ".repeat(5),
                date = "6 июня"
            ),
            Dream(
                id = "1",
                title = "Один",
                description = "Длинное описание. ".repeat(5),
                date = "25 марта"
            ),
            Dream(
                id = "2",
                title = "Два",
                description = "Описание. ".repeat(5),
                date = "30 декабря"
            ),
            Dream(
                id = "3",
                title = "Три",
                description = "Текст... Описание. ".repeat(5),
                date = "11 февраля"
            ),
            Dream(
                id = "4",
                title = "Четыре",
                description = "Длинное описание. ".repeat(5),
                date = "1 ноября"
            ),
            Dream(
                id = "5",
                title = "Пять",
                description = "Длинное описание. ".repeat(5),
                date = "2 декабря"
            ),
            Dream(
                id = "6",
                title = "Шесть",
                description = "Длинное описание. ".repeat(5),
                date = "6 июня"
            ),
        )
    }
}