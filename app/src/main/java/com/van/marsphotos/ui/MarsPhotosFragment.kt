package com.van.marsphotos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.van.marsphotos.databinding.FragmentMarsphotosBinding
import com.van.marsphotos.viewmodel.MarsPhotosViewModel

class MarsPhotosFragment : Fragment() {

    private val viewModel: MarsPhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMarsphotosBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.recyclerView.adapter = PhotoGridAdapter()

        return binding.root
    }
}