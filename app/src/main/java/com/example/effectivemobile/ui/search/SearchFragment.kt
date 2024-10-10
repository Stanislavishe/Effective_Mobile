package com.example.effectivemobile.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.databinding.FragmentSearchBinding
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

    private val recommendAdapter = RecommendAdapter {}
    private val searchVacancyAdapter = SearchVacancyAdapter()

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
            searchVacancyAdapter.setData(vacancyList)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setAdapters(){
        with(binding){
            recommends.adapter = recommendAdapter
            vacancies.adapter = searchVacancyAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}