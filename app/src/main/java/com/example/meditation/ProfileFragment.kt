package com.example.meditation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.meditation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var _binding: FragmentProfileBinding
    private val mainVM by viewModels<MainViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.pbProgress.progress +=  mainVM.progress
        Toast.makeText(requireContext(), "OnResume Your progress mainVM ${mainVM.progress} and layout bar ${binding.pbProgress.progress}", Toast.LENGTH_SHORT).show()
    }
}