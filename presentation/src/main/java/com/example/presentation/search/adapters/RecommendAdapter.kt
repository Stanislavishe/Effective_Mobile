package com.example.presentation.search.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.entity.models.Offer
import com.example.presentation.R
import com.example.presentation.databinding.ItemRecomendBinding
import com.example.presentation.search.view_holders.RecommendViewHolder

class RecommendAdapter(
    private val onClick: (String) -> Unit
) : Adapter<RecommendViewHolder>() {

    private var data: List<Offer> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Offer>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecommendViewHolder(
            ItemRecomendBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val offer = data.getOrNull(position)

        with(holder.binding) {
            when (offer?.id) {
                NEAR -> image.setImageResource(R.drawable.near_image)
                UP -> image.setImageResource(R.drawable.avatar_image)
                TEMPORARY -> image.setImageResource(R.drawable.temporary_image)
                else -> image.visibility = View.INVISIBLE
            }
            if (offer?.button != null) {
                description.text = offer.title
                up.text = offer.button!!.text
            } else {
                description.maxLines = 3
                description.text = offer?.title
            }
            root.setOnClickListener {
                offer?.let {
                    onClick(offer.link)
                }
            }
        }
    }

    companion object {
        private const val NEAR = "near_vacancies"
        private const val UP = "level_up_resume"
        private const val TEMPORARY = "temporary_job"
    }

}