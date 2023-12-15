package com.example.list3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.list3.databinding.FragmentCameraxPhotoBinding

class CameraXPhotoFragment : Fragment() {
    private lateinit var binding: FragmentCameraxPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCameraxPhotoBinding.inflate(inflater, container, false);
        return binding.root
        }
}