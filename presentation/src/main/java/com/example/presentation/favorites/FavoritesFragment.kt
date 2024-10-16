package com.example.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.entity.FavoriteVacancy
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.databinding.FragmentFavoritesBinding
import com.example.effectivemobile.ui.navigateSave
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    @Inject
    lateinit var favoritesViewModelFactory: FavoritesViewModelFactory

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter = FavoritesAdapter(
        onClick = {onClickVacancy()},
        onClickFavorite = {vacancy, isFavorite -> onClickFavorite(vacancy, isFavorite) }
    )

    private val viewModel: FavoritesViewModel by viewModels{ favoritesViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.vacancies.adapter = adapter

        viewModel.setFavorites()

        viewModel.favorites.onEach {
            adapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach {
            if (it) loading() else notLoading()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickFavorite(favoriteVacancy: FavoriteVacancy, isFavorite: Boolean) {
        if(isFavorite) {
            viewModel.insertFavorite(favoriteVacancy)
        } else {
            viewModel.deleteFavorite(favoriteVacancy)
        }
    }

    private fun onClickVacancy(){
        findNavController().navigateSave(R.id.action_navigation_favorites_to_stub)
    }

    private fun loading(){
        with(binding){
            vacancyCount.visibility = View.INVISIBLE
            vacancies.visibility = View.INVISIBLE
            load.visibility = View.VISIBLE
        }
    }

    private fun notLoading(){
        with(binding){
            vacancyCount.visibility = View.VISIBLE
            vacancies.visibility = View.VISIBLE
            load.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}