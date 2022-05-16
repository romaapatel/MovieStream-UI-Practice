package com.example.moviestream_ui_practice.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestream_ui_practice.R
import kotlinx.android.synthetic.main.item_vertical_layout.view.recyclerViewChild
import kotlinx.android.synthetic.main.item_vertical_layout.view.tvMovieTitle

class VerticalAdapter (private val movieArray : List<String>, val context: Context, private val movies: List<HomeScreenModelClass>): RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vertical_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = movieArray[position]
        holder.movieTitle.text = title

        val filteredList: MutableList<HomeScreenModelClass> = mutableListOf()
        for (item in movies) {
            if (item.lang == title) {
                filteredList.add(item)
            }
        }
        holder.recyclerChild.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        holder.recyclerChild.adapter = HorizontalAdapter(filteredList, context)
    }

    override fun getItemCount(): Int {
        return movieArray.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val recyclerChild : RecyclerView = itemView.recyclerViewChild
        val movieTitle : TextView = itemView.tvMovieTitle
    }
}