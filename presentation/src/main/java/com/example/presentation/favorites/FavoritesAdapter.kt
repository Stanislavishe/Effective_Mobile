package com.example.presentation.favorites

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.entity.models.FavoriteVacancy
import com.example.presentation.R
import com.example.presentation.databinding.ItemVacancyBinding
import com.example.presentation.search.view_holders.VacancyViewHolder

class FavoritesAdapter(
    private val onClick: () -> Unit,
    private val onClickFavorite: (FavoriteVacancy, Boolean) -> Unit
) : Adapter<VacancyViewHolder>() {
    private var data: List<FavoriteVacancy> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<FavoriteVacancy>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VacancyViewHolder(
            ItemVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = data.getOrNull(position)

        with(holder.binding) {
            var isFavoriteVacancy = true

            isFavorite.setImageResource(R.drawable.favorite_true_icon)
            name.text = vacancy?.title
            icome.text = vacancy?.salary
            town.text = vacancy?.town
            company.text = vacancy?.company
            expText.text = vacancy?.exp
            viewed.text = setPublishedDate(vacancy?.publishedDate!!, context = viewed.context)
            respondButton.isClickable = false
            respondButton.isFocusable = false

            root.setOnClickListener {
                onClick()
            }
            isFavorite.setOnClickListener {
                isFavoriteVacancy = !isFavoriteVacancy
                if (isFavoriteVacancy) {
                    isFavorite.setImageResource(R.drawable.favorite_true_icon)
                    onClickFavorite(vacancy, isFavoriteVacancy)
                } else {
                    isFavorite.setImageResource(R.drawable.favorites_default_icon)
                    onClickFavorite(vacancy, isFavoriteVacancy)
                }
            }
        }
    }

    private fun setPublishedDate(publishedDate: String, context: Context): String {
        val month = when (publishedDate.substring(5, 7)) {
            "01" -> context.getString(R.string.january)
            "02" -> context.getString(R.string.february)
            "03" -> context.getString(R.string.march)
            "04" -> context.getString(R.string.april)
            "05" -> context.getString(R.string.may)
            "06" -> context.getString(R.string.june)
            "07" -> context.getString(R.string.july)
            "08" -> context.getString(R.string.august)
            "09" -> context.getString(R.string.september)
            "10" -> context.getString(R.string.october)
            "11" -> context.getString(R.string.november)
            else -> context.getString(R.string.december)
        }
        val day = publishedDate.takeLast(2)
        return context.getString(R.string.publishedDate, day, month)
    }
}