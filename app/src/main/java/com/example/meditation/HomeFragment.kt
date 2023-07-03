package com.example.meditation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meditation.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var _binding: FragmentHomeBinding
    private val mainVM by viewModels<MainViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Timer
        binding.btnRun.setOnClickListener {
            object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvMeditate.setText("Seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    binding.tvMeditate.setText("Done")
                    mainVM.progress += 25
                    Toast.makeText(requireContext(), "Your progress ${mainVM.progress}", Toast.LENGTH_SHORT).show()

                }
            }.start()
        }

        return binding.root
    }
}