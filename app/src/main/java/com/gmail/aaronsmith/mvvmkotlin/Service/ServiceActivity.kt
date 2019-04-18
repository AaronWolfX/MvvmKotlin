package com.gmail.aaronsmith.mvvmkotlin.Service

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gmail.aaronsmith.mvvmkotlin.R
import kotlinx.android.synthetic.main.activity_service.*
import kotlin.math.min

class ServiceActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> startService()
        }
    }

    fun startService() {
        val mIntent = Intent(this, TextService::class.java)
        startService(mIntent)
    }
}
