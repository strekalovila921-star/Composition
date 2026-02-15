package com.example.composition.domain.intity


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
//Настройки игры


@Parcelize
data class GameningSetings(
    // Максимально возможное значение
    val maxSumValue: Int,
    // Минимальное количество правильных ответов
    val minCountOfRightAnswers: Int,
    // Минимальный процент правильных ответов
    val minPercentOfRightAnswer: Int,
    // Время игры в секундах
    val gemeTimerInSecondes: Int
): Parcelable




