package com.example.composition.domain.intity

//Настройки игры
data class GameningSetings(
    // Максимально возможное значение
    val maxSumValue: Int,
    // Минимальное количество правильных ответов
    val minCountOfRightAnswers: Int,
    // Минимальный процент правильных ответов
    val minPercentOfRightAnswer: Int,
    // Время игры в секундах
    val gemeTimerInSecondes: Int
)