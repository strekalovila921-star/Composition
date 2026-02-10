package com.example.composition.domain.intity.usecases

import com.example.composition.domain.intity.GameningSetings
import com.example.composition.domain.intity.Level
import com.example.composition.domain.intity.repository.GameRepository

class GetGameSettingsUseCases(private val gameRepository: GameRepository) {

    operator fun invoke(level: Level): GameningSetings {
        return gameRepository.getGameSettings(level)
    }
}