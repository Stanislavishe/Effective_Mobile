package com.example.effectivemobile.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.data.entity.FavoriteVacancy
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.databinding.FragmentSearchBinding
import com.example.effectivemobile.ui.navigateSave
import com.example.effectivemobile.ui.search.adapters.RecommendAdapter
import com.example.effectivemobile.ui.search.adapters.SearchVacancyAdapter
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val recommendAdapter = RecommendAdapter {onClickRecommend(it)}
    private val searchVacancyAdapter = SearchVacancyAdapter(
        onClick = {onClickVacancy()},
        onClickFavorite = {vacancy, isFavorite -> onClickFavorite(vacancy, isFavorite) }
    )

    private val viewModel: SearchViewModel by viewModels {searchViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapters()
        viewModel.getRecommends()
        viewModel.getVacancyInfo()

        viewModel.recommends.onEach {
            recommendAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.vacancyInfo.onEach {
            val vacancyList = mutableListOf<Vacancy>()
            if(it.isNotEmpty()) {
                for (i in 0..2){
                    val vacancy = it[i]
                    vacancyList.add(vacancy)
                }
            }
            val size = it.size - 3
            binding.showAll.text = resources.getQuantityString(R.plurals.vacancy_count, size, size)
            searchVacancyAdapter.setData(vacancyList)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach {
            if (it) loading() else notLoading()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.showAll.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_to_according)
        }
    }

    private fun onClickVacancy(){
        findNavController().navigateSave(R.id.action_navigation_search_to_stub)
    }

    private fun onClickFavorite(vacancy: Vacancy, isFavorite: Boolean) {
        val favoriteVacancy = FavoriteVacancy(
            id = vacancy.id, title = vacancy.title, town = vacancy.address.town,
            company = vacancy.company, salary = vacancy.salary.full,
            exp = vacancy.experience.previewText, publishedDate = vacancy.publishedDate,
            responsibilities = vacancy.responsibilities
        )
        if(isFavorite) {
            viewModel.insertFavorite(favoriteVacancy)
        } else {
            viewModel.deleteFavorite(favoriteVacancy)
        }
    }

    private fun onClickRecommend(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun setAdapters(){
        with(binding){
            recommends.adapter = recommendAdapter
            vacancies.adapter = searchVacancyAdapter
        }
    }

    private fun loading(){
        with(binding){
            recommends.visibility = View.INVISIBLE
            titleVacancy.visibility = View.INVISIBLE
            vacancies.visibility = View.INVISIBLE
            load.visibility = View.VISIBLE
            showAll.visibility = View.INVISIBLE
        }
    }

    private fun notLoading(){
        with(binding){
            recommends.visibility = View.VISIBLE
            titleVacancy.visibility = View.VISIBLE
            vacancies.visibility = View.VISIBLE
            showAll.visibility = View.VISIBLE
            load.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}