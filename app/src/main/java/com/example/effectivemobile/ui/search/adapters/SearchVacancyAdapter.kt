package com.example.effectivemobile.ui.search.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.effectivemobile.R
import com.example.effectivemobile.data.entity.EffectiveDao
import com.example.effectivemobile.data.entity.FavoriteVacancy
import com.example.effectivemobile.data.models.Vacancy
import com.example.effectivemobile.databinding.ItemVacancyBinding
import com.example.effectivemobile.ui.search.SearchViewModel
import com.example.effectivemobile.ui.search.view_holders.VacancyViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchVacancyAdapter(
    private val onClick: () -> Unit,
    private val onClickFavorite: (Vacancy, Boolean) -> Unit
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
            var isFavoriteVacancy = false
            if (vacancy?.lookingNumber != null) {
                val number = vacancy.lookingNumber
                looking.text =
                    looking.resources.getQuantityString(R.plurals.looking_count, number, number)
            } else looking.text = ""
            if (vacancy?.isFavorite!!) {
                isFavoriteVacancy = true
                isFavorite.setImageResource(R.drawable.favorite_true_icon)
                onClickFavorite(vacancy, isFavoriteVacancy)
            } else {
                isFavorite.setImageResource(R.drawable.favorites_default_icon)
            }
            name.text = vacancy.title
            icome.text = vacancy.salary.full
            town.text = vacancy.address.town
            company.text = vacancy.company
            expText.text = vacancy.experience.previewText
            viewed.text = setPublishedDate(vacancy.publishedDate, context = viewed.context)
            respondButton.isClickable = false
            respondButton.isFocusable = false
            root.setOnClickListener{
                onClick()
            }
            isFavorite.setOnClickListener {
                isFavoriteVacancy = !isFavoriteVacancy
                if(isFavoriteVacancy) {
                    isFavorite.setImageResource(R.drawable.favorite_true_icon)
                    onClickFavorite(vacancy, isFavoriteVacancy)
                } else {
                    isFavorite.setImageResource(R.drawable.favorites_default_icon)
                    onClickFavorite(vacancy, isFavoriteVacancy)
                }
            }
        }
    }

    private fun setPublishedDate(publishedDate: String, context: Context): String{
        val month = when(publishedDate.substring(5, 7)){
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
        var day = publishedDate.takeLast(2)
        if(day.startsWith("0")) day = day.takeLast(1)
        return context.getString(R.string.publishedDate, day, month)
    }
}