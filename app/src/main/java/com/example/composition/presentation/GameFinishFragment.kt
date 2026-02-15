package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.databinding.FragmentGameFinishBinding
import com.example.composition.domain.intity.GameResult
import kotlinx.parcelize.Parcelize

class GameFinishFragment : Fragment() {

    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryArgs()
                }

                override fun handleOnBackStarted(backEvent: BackEventCompat) {
                    super.handleOnBackStarted(backEvent)
                }

                override fun handleOnBackProgressed(backEvent: BackEventCompat) {
                    super.handleOnBackProgressed(backEvent)
                }
            })

        binding.buttonRetry.setOnClickListener {
            retryArgs()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgsGameFinish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parsArgsGameFinish() {
       requireArguments().getParcelable<GameResult>(KEY_RESULT)?.let { it ->
            gameResult = it
        }
    }

    private fun retryArgs() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {

        private val KEY_RESULT = "game_Result"

        fun newInstanceFameFinishFragment(gameResult: GameResult): GameFinishFragment {
            val bundle = Bundle().apply {
                putParcelable(KEY_RESULT, gameResult)
            }
            val gameFinishFragment = GameFinishFragment()
            gameFinishFragment.arguments = bundle
            return gameFinishFragment
        }
    }
}