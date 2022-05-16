package com.example.moviestream_ui_practice.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.loadImage
import kotlinx.android.synthetic.main.item_horizontal_layout.view.imageMovie

class HorizontalAdapter(private val movieList : List<HomeScreenModelClass>, val context: Context): RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movieList[position]
        loadImage(currentMovie.imageHorizontal, holder.image)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.imageMovie
    }
}