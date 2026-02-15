package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.ChoseLevelFragmentBinding
import com.example.composition.domain.intity.Level

class ChoseLevelFragment : Fragment() {

    private var _bilding: ChoseLevelFragmentBinding? = null
    private val bilding: ChoseLevelFragmentBinding
        get() = _bilding ?: throw RuntimeException("ChoseLevelFragmentBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bilding = ChoseLevelFragmentBinding.inflate(inflater, container, false)
        return bilding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bilding) {
            btnLevelTest.setOnClickListener {
                loanchGameFragment(Level.TEST)
            }
            btnLevelEasy.setOnClickListener {
                loanchGameFragment(Level.EASY)
            }
            btnLevelNormal.setOnClickListener {
                loanchGameFragment(Level.NORMAL)
            }
            btnLevelHard.setOnClickListener {
                loanchGameFragment(Level.HARD)
            }
        }
    }

    private fun loanchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,
                GameFragment.newInctansFragmentGame(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
        }




    override fun onDestroyView() {
        super.onDestroyView()
        _bilding = null
    }

    companion object {

        const val NAME = ""
        fun newInscnanse(): ChoseLevelFragment {
            return ChoseLevelFragment()
            }
        }
    }
