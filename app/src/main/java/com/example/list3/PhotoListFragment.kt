package com.example.list3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.list3.databinding.FragmentPhotoListBinding

class PhotoListFragment : Fragment() {
    private lateinit var binding: FragmentPhotoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false);
        return binding.root
    }
}