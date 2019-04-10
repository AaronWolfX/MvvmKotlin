package com.gmail.aaronsmith.mvvmkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.Log
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity() {

    val message = 1001

    var handle = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            if (msg!!.what == message) {
                var current = viewPager.currentItem
//                Log.e("aaron", "handleMessage:" + current)
                viewPager.setCurrentItem(current + 1, true)
            }
            return false
        }

    })

    var dataList = listOf(
        R.mipmap.no6,
        R.mipmap.no1,
        R.mipmap.no5,
        R.mipmap.no6,
        R.mipmap.no1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        initData()
        handle.sendEmptyMessageDelayed(message, 3000)
    }

    private fun initData() {
        var pagerAdapter = BannerPagerAdapter()
        pagerAdapter.addData(dataList, viewPager, handle)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = 1

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                if (p0 == 0) {
                    Log.e("aaron", "currentItem:" + viewPager.currentItem)
                    if (viewPager.currentItem == 4) {
                        viewPager.setCurrentItem(1, false)
                    } else if (viewPager.currentItem == 0) {
                        viewPager.setCurrentItem(3, false)
                    }
                    handle.sendEmptyMessageDelayed(message, 3000)
                }
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {

            }

        })
    }
}
