package com.gmail.aaronsmith.mvvmkotlin.Service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.webkit.PermissionRequest
import com.gmail.aaronsmith.mvvmkotlin.R
import java.util.*


/**
 * @author Aaron Smith
 * @date 2019/4/18
 */
class TextService : Service() {
    var i = 0

    override fun onCreate() {
        super.onCreate()
        foregroundRun()
        Log.e("aaron", "service create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("aaron", "onStartCommand")
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e("aaron", "destroy")

    }

    fun foregroundRun() {
        val pIntent = PendingIntent.getActivity(this, 0, Intent(this, ServiceActivity::class.java), 0)
        val notification = Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setTicker("ina-ina")
            .setContentTitle("lllll")
            .setContentText("aaaaa")
            .setContentIntent(pIntent)
            .build()

        startForeground(0x1989, notification)
    }

}