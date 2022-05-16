package com.example.moviestream_ui_practice.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.loadImage
import kotlinx.android.synthetic.main.fragment_image_slider.view.viewPagerImageView
import kotlinx.android.synthetic.main.fragment_image_slider.viewPagerImageView


class ImageSliderFragment(private val image: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage(image,viewPagerImageView)
    }

}