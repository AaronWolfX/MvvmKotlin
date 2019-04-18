package com.gmail.aaronsmith.mvvmkotlin.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*


/**
 * @author Aaron Smith
 * @date 2019/4/18
 */
class TextService : Service() {
    var i = 0

    override fun onCreate() {
        super.onCreate()
        Log.e("aaron", "service create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("aaron", "onStartCommand")
        logTime()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun logTime() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                i++
                Log.e("aaron", "current time :" + i)
                logTime()
            }

        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("aaron","destroy")
    }

}