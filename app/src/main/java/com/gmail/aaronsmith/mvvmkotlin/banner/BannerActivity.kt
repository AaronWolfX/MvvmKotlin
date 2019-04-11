package com.gmail.aaronsmith.mvvmkotlin.banner

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import com.gmail.aaronsmith.mvvmkotlin.R
import kotlinx.android.synthetic.main.activity_banner.*

class BannerActivity : AppCompatActivity() {

    val message = 1001
    lateinit var pagerAdapter: BannerPagerAdapter
    var startX = 0.0f
    var scaleStatus = false

    var handle = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            if (msg!!.what == message) {
                val current = viewPager.currentItem
//                Log.e("aaron", "handleMessage:" + current)
                //滑动显示下一个  图片缩小动画  动画完成后再滑动
                val currentView = pagerAdapter.currentView
                val scaleX = ObjectAnimator.ofFloat(currentView, "scaleX", 1.3f, 1f)
                val scaleY = ObjectAnimator.ofFloat(currentView, "scaleY", 1.3f, 1f)
                val animSet = AnimatorSet()
                animSet.play(scaleX).with(scaleY)
                animSet.duration = 300
                animSet.start()
                animSet.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        //缩放动画结束后，开始
                        viewPager.setCurrentItem(current + 1, true)
                    }
                })

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
        initListener()
//        handle.sendEmptyMessageDelayed(message, 3000)
        viewPager.post {
            val currentView = pagerAdapter.currentView ?: return@post
            Log.e("aaronP", "get position:${pagerAdapter.getItemPosition(currentView)}")
            val scaleX = ObjectAnimator.ofFloat(currentView, "scaleX", 1f, 1.3f)
            val scaleY = ObjectAnimator.ofFloat(currentView, "scaleY", 1f, 1.3f)
            val animSet = AnimatorSet()
            animSet.play(scaleX).with(scaleY)
            animSet.duration = 300
            Log.e("aaron", "start animator")
            scaleX.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    //缩放动画结束后，开始
                    handle.sendEmptyMessageDelayed(message, 3000)
                }
            })
            animSet.start()
        }
    }

    private fun initData() {
        pagerAdapter = BannerPagerAdapter()
        pagerAdapter.addData(dataList, viewPager, handle)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = 1
        //设置viewpager滑动速度
        val mField = ViewPager::class.java.getDeclaredField("mScroller")
        mField.isAccessible = true
        val mScroller = FixedSpeedScroller(this, AccelerateInterpolator())
        mScroller.mDuration = 500
        mField.set(viewPager, mScroller)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                if (p0 == 0) {
                    handle.removeCallbacksAndMessages(null)
                    Log.e("aaron", "currentItem:" + viewPager.currentItem)
                    if (viewPager.currentItem == 4) {
                        viewPager.setCurrentItem(1, false)
                    } else if (viewPager.currentItem == 0) {
                        viewPager.setCurrentItem(3, false)
                    }
                    val currentView = pagerAdapter.currentView
                    Log.e("aaronP", "get position:${pagerAdapter.getItemPosition(currentView)}")
                    val scaleX = ObjectAnimator.ofFloat(currentView, "scaleX", 1f, 1.3f)
                    val scaleY = ObjectAnimator.ofFloat(currentView, "scaleY", 1f, 1.3f)
                    val animSet = AnimatorSet()
                    animSet.play(scaleX).with(scaleY)
                    animSet.duration = 300
                    Log.e("aaron", "start animator")
                    scaleX.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            //缩放动画结束后，开始
                            handle.sendEmptyMessageDelayed(message, 3000)
                        }
                    })
                    animSet.start()
                }
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                Log.e("aaronP", "onPageSelected:$p0")
            }

        })

    }

    private fun initListener() {
        viewPager.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.e("aaron", "action down")
                        handle.removeCallbacksAndMessages(null)
                        startX = event.x
                        scaleStatus = false
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.e("aaron", "action up")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.e("aaron", "action move")

                        Log.e("aaron", "action move x = ${event.x}")
                        Log.e("aaron", "action move y = ${event.y}")
                        if (Math.abs(event.x - startX) > 100 && !scaleStatus) {
                            Log.e("aaron", "scale view")
                            scaleStatus = true
                            val currentView = pagerAdapter.currentView
                            val scaleX = ObjectAnimator.ofFloat(currentView, "scaleX", 1.3f, 1f)
                            val scaleY = ObjectAnimator.ofFloat(currentView, "scaleY", 1.3f, 1f)
                            val animSet = AnimatorSet()
                            animSet.play(scaleX).with(scaleY)
                            animSet.duration = 300
                            Log.e("aaron", "start animator")
                            animSet.start()
                        }
                    }
                }


                return false
            }

        })
    }
}
