package com.example.list3

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.example.list3.databinding.FragmentPrimaryBinding

class PrimaryFragment : Fragment() {
    private lateinit var binding: FragmentPrimaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrimaryBinding.inflate(inflater, container, false);

        val dir1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val dir2 = Environment.getExternalStorageDirectory()
        val dir3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val dir4 = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        binding.directory1.text = dir1.scheme + ":/" + dir1.path
        binding.directory2.text = dir2.absolutePath
        binding.directory3.text = dir3.absolutePath
        binding.directory4.text = dir4?.absolutePath ?: "nothing"
        dir4?.let {
            val uri = FileProvider.getUriForFile(requireContext(), requireContext().packageName + ".provider", it)
            binding.directory5.text = uri.scheme + ":/" + uri.path
        }

        return binding.root
    }

}