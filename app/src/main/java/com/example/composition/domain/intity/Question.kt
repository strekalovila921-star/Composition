package com.example.composition.domain.intity

data class Question(
    // Значение суммы, которое отображается в кружке
    val sum: Int,
    // Видимое число, которое отображется в левом квадрате
    val visibleNumber: Int,
    // Варианты ответов
    val options: List<Number>
) {

    val rightAnswer: Int
        get() = sum -  visibleNumber
}