package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.intity.GameResult
import com.example.composition.domain.intity.Level

class GameFragment : Fragment() {


    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parsArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun loanshGameFinish(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishFragment.newInstanceFameFinishFragment(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "game_fragment"
        private const val KEY_LEVEL = "Level"
        fun newInctansFragmentGame(level: Level): GameFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_LEVEL,level)
            val fragmentGame = GameFragment()
            fragmentGame.arguments = bundle
            return fragmentGame
        }
    }
}