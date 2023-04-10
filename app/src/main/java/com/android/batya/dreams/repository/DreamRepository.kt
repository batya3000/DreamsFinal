package com.android.batya.dreams.repository

import com.android.batya.dreams.model.Dream
import com.android.batya.dreams.model.Tag
import java.util.*
import kotlin.random.Random

class DreamRepository {
    var counter = 0;
    fun generateDream(): Dream {
        val words = listOf("сон", "и", "он", "пришёл", "на", "построить", "я", "пошёл", "слово", "а", "стол", "красный", "зеленый", "синий", "желтый",
            "книга", "мышь", "клавиатура", "монитор", "телефон", "часы", "дверь", "окно", "стул", "столик",
            "банан", "яблоко", "апельсин", "груша", "слива", "виноград", "арбуз", "дыня",
            "кот", "собака", "хомяк", "рыбка",
            "домашнее животное","птица","насекомое","рыба","зверь","пресмыкающееся","млекопитающее",
            "автомобиль","мотоцикл","велосипед","самолет","поезд","корабль",
            "молоко","хлеб","сыр","мясо","рыба","овощи","фрукты",
            "деньги","карта","кошелек","банкомат",
            "учебник","ручка","карандаш","тетрадь", "стоял", "строил", "делал", "ушел", "в", "кроме", "если", "только потому", "там", "увидел", "дала", "ушла",
            "добрый", "быстро", "смело", "сильно", "ходил", "раздал","конь", "утка", "телефон", "вроде", "показалось", "над", "под"
        )
        var randomTitle = ""
        for(i in 0..Random.nextInt(4)) {
            if (i == 0) {
                randomTitle += "${words.random()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} "
            } else {
                randomTitle += "${words.random()} "
            }
        }

        var randomDescription = ""
        val randomTags = mutableListOf<Tag>()
        for(i in 0..Random.nextInt(100)) {
            randomDescription += "${words.random()} "
        }

        when (val randomNum = Random.nextInt(4)) {
            0 -> {}
            else -> {
                for (i in 1..randomNum) {
                    randomTags+=Tag(words.random())
                }
            }
        }
        for(i in 0..Random.nextInt(3)) {
            randomDescription += "${words.random()} "
        }
        return Dream(
            id = "0",
            title = randomTitle,
            description = randomDescription,
            tags = randomTags
        )
    }
    fun getDreams(): List<Dream> {
        val randomNum = Random.nextInt(1, 2)
        val dreams = mutableListOf<Dream>()
        for (i in 1..randomNum) {
            dreams+=generateDream()
        }
        return dreams.toList()
    }
}