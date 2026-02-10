package com.example.composition.data

import com.example.composition.domain.intity.GameningSetings
import com.example.composition.domain.intity.Question
import com.example.composition.domain.intity.Level
import com.example.composition.domain.intity.repository.GameRepository
import java.util.HashSet
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2

    override fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibility = Random.nextInt(MIN_SUM_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibility
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_SUM_VALUE)
        val to = min(maxSumValue, rightAnswer - countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibility, options.toList())
    }

    override fun getGameSettings(level: Level): GameningSetings {
        return when (level) {
            Level.TEST -> {
                GameningSetings(
                    10,
                    3,
                    50,
                    8
                )
            }

            Level.EASY -> {
                GameningSetings(
                    10,
                    10,
                    70,
                    60
                )
            }

            Level.NORMAL -> {
                GameningSetings(
                    10,
                    10,
                    70,
                    60
                )
            }

            Level.HARD -> {
                GameningSetings(
                    30,
                    30,
                    90,
                    40
                )
            }
        }
    }
}
