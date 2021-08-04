package com.example.mymoviesapp.features.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mymoviesapp.base.BaseBottomSheetFragment
import com.example.mymoviesapp.databinding.FragmentBottomSheetFilterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterBottomSheetFragment : BaseBottomSheetFragment() {
    private lateinit var binding: FragmentBottomSheetFilterBinding

    private val genreViewModel: GenreViewModel by viewModels()

    private val adapterGenres by lazy { GenresAdapter(::selectGenre) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetFilterBinding.inflate(inflater, container, false)
        setupObservers()
        setupAdapter()
        setupListeners()
        genreViewModel.getGenresData()
        return binding.root
    }

    private fun setupListeners() {
        binding.clearButton.setOnClickListener {
            dismissWithArguments(mapOf())
        }
    }

    private fun setupAdapter() {
        binding.genreRecyclerView.adapter = adapterGenres
    }

    private fun setupObservers() {
        genreViewModel.getGenres().observe(this, {
            adapterGenres.addGenres(it)
        })
    }

    private fun selectGenre(id: Int) {
        dismissWithArguments(mapOf(ID to id))
    }

    companion object {
        const val ID = "id"
        const val FILTER_BOTTOM_SHEET_FRAGMENT = "FilterBottomSheetFragment"
        fun newInstance(): FilterBottomSheetFragment {
            val args = Bundle()
            val fragment = FilterBottomSheetFragment()
            fragment.arguments = args
            return fragment
        }
    }
}