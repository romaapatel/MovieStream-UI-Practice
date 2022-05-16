package com.example.moviestream_ui_practice.homescreen

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestream_ui_practice.R
import com.example.moviestream_ui_practice.utils.imageList
import androidx.core.view.setMargins
import androidx.viewpager2.widget.ViewPager2
import com.example.moviestream_ui_practice.utils.Constant
import kotlinx.android.synthetic.main.fragment_home.movieStreamViewPager
import kotlinx.android.synthetic.main.fragment_home.view.movieStreamViewPager
import kotlinx.android.synthetic.main.fragment_home.view.pageControlLinearLayout
import kotlinx.android.synthetic.main.fragment_home.view.recyclerViewParent

class HomeFragment : Fragment() {

    private var viewList: MutableList<View> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.movieStreamViewPager.adapter = HomeImageAdapter(requireActivity().supportFragmentManager,lifecycle,
            imageList)
        view.recyclerViewParent.layoutManager = LinearLayoutManager(requireContext())
        val adapter = VerticalAdapter(listOf(getString(R.string.most_populer), getString(R.string.latest_movies)),requireContext(), getData)
        view.recyclerViewParent.adapter = adapter
        setPageControl(view)
        view.movieStreamViewPager.registerOnPageChangeCallback(object :
           ViewPager2.OnPageChangeCallback() {
               override fun onPageSelected(position: Int) {
                   updatePageControl()
               }
       })
        return view
    }

    private fun setPageControl(view: View) {
        for (image in imageList) {
            val createdView = View(context)
            setView(createdView, false)
            viewList.add(createdView)
            view.pageControlLinearLayout.addView(createdView)
        }
    }

    private fun setView(view: View, isActive: Boolean) {
        if (isActive) {
            val widthAnimator = ValueAnimator.ofInt(Constant.ZERO, Constant.HUNDRED)
            widthAnimator.duration  = 200
            widthAnimator.interpolator = DecelerateInterpolator()
            widthAnimator.addUpdateListener {
                view.layoutParams.width = it.animatedValue as? Int ?: Constant.HUNDRED
                view.background = ResourcesCompat.getDrawable(resources, R.drawable.page_control_active, null)
                view.requestLayout()
            }
            widthAnimator.start()
        } else {
            view.background = ResourcesCompat.getDrawable(resources, R.drawable.page_control_inactive, null)
            val param = LinearLayout.LayoutParams(Constant.THIRTY, Constant.THIRTY)
            param.setMargins(Constant.TWENTY)
            view.layoutParams = param
        }
    }

    private fun updatePageControl() {
        for (currentPage in Constant.ZERO until viewList.size) {
            if (currentPage == movieStreamViewPager.currentItem) {
                val currentView = viewList[currentPage]
                setView(currentView, true)
            } else {
                val currentView = viewList[currentPage]
                setView(currentView, false)
            }
        }
    }
}