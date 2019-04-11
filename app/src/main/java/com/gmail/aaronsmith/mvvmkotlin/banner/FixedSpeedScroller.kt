package com.gmail.aaronsmith.mvvmkotlin.banner

import android.content.Context
import android.view.animation.AccelerateInterpolator
import android.widget.Scroller


/**
 * @author Aaron Smith
 * @date 2019/4/11
 */
class FixedSpeedScroller(context: Context?) : Scroller(context) {
    var mDuration = 1500

    constructor(context: Context, interpolator: AccelerateInterpolator) : this(context)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, mDuration)

    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    fun setCustomDuration(time: Int) {
        mDuration = time
    }

    fun getCustomDuration(): Int {
        return mDuration
    }

}