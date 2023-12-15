package com.example.list3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.list3.databinding.FragmentTakePhotoBinding

class TakePhotoFragment : Fragment() {
    private lateinit var binding: FragmentTakePhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTakePhotoBinding.inflate(inflater, container, false);
        return binding.root
    }
}