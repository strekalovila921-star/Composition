package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
import com.example.composition.databinding.FragmentWelcomBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomBinding? = null
    private val binding: FragmentWelcomBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // создаст view и под капотом найдет все view по их id создаст для них ссылки
        _binding = FragmentWelcomBinding.inflate(inflater, container, false)
        // венуть view из binding, обратимся к свойству root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener {
            launchFragment(ChoseLevelFragment.newInscnanse())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ChoseLevelFragment())
            .addToBackStack(null)
            .commit()
    }
}