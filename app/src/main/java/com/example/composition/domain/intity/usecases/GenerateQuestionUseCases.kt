package com.example.composition.domain.intity.usecases

import com.example.composition.domain.intity.Question
import com.example.composition.domain.intity.repository.GameRepository

class GenerateQuestionUseCases(private val gameRepository: GameRepository) {

    operator fun invoke(maxValue: Int) : Question{
        return gameRepository.generateQuestion(maxValue,COUNT_OF_OPTIONS)
    }

    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }

}