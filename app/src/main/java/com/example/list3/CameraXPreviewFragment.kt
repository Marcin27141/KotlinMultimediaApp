package com.example.list3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.list3.databinding.FragmentCameraxPreviewBinding

class CameraXPreviewFragment : Fragment() {
    private lateinit var binding: FragmentCameraxPreviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraxPreviewBinding.inflate(inflater, container, false);
        return binding.root
    }
}