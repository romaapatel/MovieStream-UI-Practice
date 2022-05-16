package com.example.moviestream_ui_practice.homescreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeImageAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle, private val imageList: List<String>) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageSliderFragment(imageList[position])
    }
}