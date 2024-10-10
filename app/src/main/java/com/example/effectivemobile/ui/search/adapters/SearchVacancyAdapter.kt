package com.example.effectivemobile.ui.search.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.effectivemobile.R
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.databinding.ItemVacancyBinding
import com.example.effectivemobile.ui.search.view_holders.VacancyViewHolder

class SearchVacancyAdapter(
    private val onClick: () -> Unit
) : Adapter<VacancyViewHolder>() {
    private var data: List<Vacancy> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Vacancy>) {
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
            if (vacancy?.lookingNumber != null) {
                val number = vacancy.lookingNumber
                looking.text =
                    looking.resources.getQuantityString(R.plurals.looking_count, number, number)
            } else looking.text = ""
            if (vacancy?.isFavorite!!) {
                isFavorite.setImageResource(R.drawable.favorite_true_icon)
            } else {
                isFavorite.setImageResource(R.drawable.favorites_default_icon)
            }
            name.text = vacancy.title
            icome.text = vacancy.salary.full
            town.text = vacancy.address.town
            company.text = vacancy.company
            expText.text = vacancy.experience.previewText
            viewed.text = vacancy.publishedDate
            respondButton.isClickable = false
            respondButton.isFocusable = false
            root.setOnClickListener{
                onClick()
            }
        }
    }
}