package com.example.composition.presentation

import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composition.R
import com.example.composition.data.GameRepositoryImpl
import com.example.composition.domain.intity.GameResult
import com.example.composition.domain.intity.GameningSetings
import com.example.composition.domain.intity.Level
import com.example.composition.domain.intity.Question
import com.example.composition.domain.intity.usecases.GenerateQuestionUseCases
import com.example.composition.domain.intity.usecases.GetGameSettingsUseCases
import java.util.concurrent.CountDownLatch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameRepositoryImpl

    private val generateQuestionUseCases = GenerateQuestionUseCases(repository)
    private val getGameSettingsUseCases = GetGameSettingsUseCases(repository)

    private val context = application
    private lateinit var level: Level
    private lateinit var gameningSetings: GameningSetings

    // Состояние игры
    private var countOfRightAnswer = 0 // Количество правильных ответов
    private var countOfQustions = 0 // Количество вопросов

    private var timer: CountDownTimer? = null

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _questing = MutableLiveData<Question>()
    val questing: LiveData<Question>
        get() = _questing

    // процент правильных ответов
    private val _percentOfRightAnswer = MutableLiveData<Int>()
    val percentOfRightAnswer: LiveData<Int>
        get() = _percentOfRightAnswer

    // прогресс по ответам
    private val _progressOfRightAnswer = MutableLiveData<String>()
    val progressOfRightAnswer: LiveData<String>
        get() = _progressOfRightAnswer

    private val _enoughCountOfRightAnswer = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswer: LiveData<Boolean>
        get() = _enoughCountOfRightAnswer

    private val _enoughPercentOfRightAnswer = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswer: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswer

    // Минимальный процент
    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    //Обновление прогресса
    private fun updateProgress() {
        val procent = calculateProgressOfRightAnswer()
        _percentOfRightAnswer.value = procent
        _progressOfRightAnswer.value = String.format(
            context.resources.getString(R.string.score_percentage),
            countOfRightAnswer,
            gameningSetings.minCountOfRightAnswers
        )
        _enoughCountOfRightAnswer.value =
            countOfRightAnswer >= gameningSetings.minCountOfRightAnswers
        _enoughPercentOfRightAnswer.value = procent >= gameningSetings.minCountOfRightAnswers
    }

    // вычисляет процент правильных ответов
    private fun calculateProgressOfRightAnswer(): Int {
        return ((countOfRightAnswer / countOfQustions.toDouble()) * 100).toInt()

    }

    fun startGame(level: Level) {
        getGameSettings(level)
        startTimer()
        generateQuestings()
    }

    // Логика ответа
    private fun chooseAnswer(numbers: Int) {
        checAnswer(numbers) // проверка овтета на вопрос
        updateProgress() // обновление =
        generateQuestings() // генерация нового вопроса

    }

    //Проверка ответа
    private fun checAnswer(numbers: Int) {
        val rightAnswer = questing.value?.rightAnswer
        if (numbers == rightAnswer) {
            countOfRightAnswer++
        }
        countOfQustions++
    }

    private fun generateQuestings() {
        _questing.value = generateQuestionUseCases(gameningSetings.maxSumValue)
    }

    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameningSetings = getGameSettingsUseCases(level)
        _minPercent.value = gameningSetings.minPercentOfRightAnswer
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameningSetings.gemeTimerInSecondes * MIN_SECOND,
            MIN_SECOND
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val second = millisUntilFinished / MIN_SECOND
        val minutes = second / SECOND_IN_MINUTES
        val lestSecond = second - (minutes * SECOND_IN_MINUTES)
        return String.format("%02d:%02d", minutes, lestSecond)
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughCountOfRightAnswer.value == true &&
                    enoughPercentOfRightAnswer.value == true,
            countOfRightAnswer,
            countOfQustions,
            gameningSetings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MIN_SECOND = 100L
        private const val SECOND_IN_MINUTES = 60
    }
}