    package com.example.composition.domain.intity

    import android.os.Parcelable
    import kotlinx.android.parcel.Parcelize
    @Parcelize
    data class GameResult(
        // Значение победа или поражение true and false
        val winner: Boolean,
        // Количество правильных ответов
        val countOfRightAnswers: Int,
        // Общее количество вопросов на которые ответил пользователь
        val countOfQuestions: Int,
        // Настройки игры
        val gameSettings: GameningSetings

    ) : Parcelable