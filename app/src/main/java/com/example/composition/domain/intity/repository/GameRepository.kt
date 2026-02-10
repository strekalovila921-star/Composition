package com.example.composition.domain.intity.repository

import com.example.composition.domain.intity.GameningSetings
import com.example.composition.domain.intity.Question
import com.example.composition.domain.intity.Level

interface GameRepository {


    // Генерирует вопрос
    fun generateQuestion(
         maxSumValue: Int, // максимальное значение для генерации
         countOfOptions: Int // количество вариантов ответов для генерации
    ): Question

    // Загружает настройки игры в зависимости от уровня
    fun getGameSettings(level: Level): GameningSetings

}