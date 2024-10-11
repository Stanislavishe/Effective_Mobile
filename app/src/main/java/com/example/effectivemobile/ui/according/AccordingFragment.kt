package com.example.effectivemobile.ui.according

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
import com.example.effectivemobile.databinding.FragmentAccordingBinding
import com.example.effectivemobile.ui.navigateSave
import com.example.effectivemobile.ui.search.adapters.SearchVacancyAdapter
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AccordingFragment : Fragment() {

    @Inject
    lateinit var accordingViewModelFactory: AccordingViewModelFactory

    private var _binding: FragmentAccordingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccordingViewModel by viewModels { accordingViewModelFactory }

    private val adapter = SearchVacancyAdapter(
        onClick = { onClickVacancy() },
        onClickFavorite = { vacancy, isFavorite -> onClickFavorite(vacancy, isFavorite) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccordingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getVacancyInfo()

        binding.vacancies.adapter = adapter

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.vacancyInfo.onEach {
            val count = it.size
            binding.vacancyCount.text =
                resources.getQuantityString(R.plurals.according_count, count, count)
            adapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach {
            if (it) {
                loading()
            } else {
                notLoading()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onClickVacancy() {
        findNavController().navigateSave(R.id.action_according_to_stub)
    }

    private fun onClickFavorite(vacancy: Vacancy, isFavorite: Boolean) {
        val favoriteVacancy = FavoriteVacancy(
            id = vacancy.id, title = vacancy.title, town = vacancy.address.town,
            company = vacancy.company, salary = vacancy.salary.full,
            exp = vacancy.experience.previewText, publishedDate = vacancy.publishedDate,
            responsibilities = vacancy.responsibilities
        )
        if (isFavorite) {
            viewModel.insertFavorite(favoriteVacancy)
        } else {
            viewModel.deleteFavorite(favoriteVacancy)
        }
    }

    private fun loading() {
        binding.load.visibility = View.VISIBLE
        binding.vacancies.visibility = View.INVISIBLE
        binding.vacancyCount.visibility = View.INVISIBLE
    }

    private fun notLoading() {
        binding.load.visibility = View.GONE
        binding.vacancies.visibility = View.VISIBLE
        binding.vacancyCount.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}