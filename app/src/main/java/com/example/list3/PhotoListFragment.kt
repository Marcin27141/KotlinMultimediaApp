package com.example.list3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.list3.databinding.FragmentPhotoListBinding

class PhotoListFragment : Fragment() {
    private lateinit var binding: FragmentPhotoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false)

        val dataRepo = DataRepo.getInstance(requireContext())
        val adapter = dataRepo.getSharedList()?.let {
            PhotoListAdapter(requireContext(), it)
        }
        if (adapter == null) {
            Toast.makeText(requireContext(), "Invalid data", Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressed()
        }
        binding.recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        return binding.root
    }
}