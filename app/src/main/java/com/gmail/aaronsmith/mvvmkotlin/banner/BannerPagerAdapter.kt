package com.gmail.aaronsmith.mvvmkotlin.banner

import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


/**
 * @author Aaron Smith
 * @date 2019/4/10
 */
class BannerPagerAdapter : PagerAdapter() {

    var mData: List<Int> = listOf()
    lateinit var viewPager: ViewPager
    lateinit var handle: Handler
    lateinit var currentView:ImageView

    fun addData(
        data: List<Int>,
        viewPager: ViewPager,
        handle: Handler
    ) {
        this.viewPager = viewPager
        mData = data
        this.handle = handle
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 as View
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = ImageView(container.context)
        view.setImageResource(mData.get(position))
        container.addView(view)
        return view
    }

    override fun finishUpdate(container: ViewGroup) {
        super.finishUpdate(container)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        currentView = `object` as ImageView
    }

}