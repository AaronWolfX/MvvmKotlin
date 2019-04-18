package com.gmail.aaronsmith.mvvmkotlin.Service

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.aaronsmith.mvvmkotlin.R
import kotlinx.android.synthetic.main.activity_service.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import kotlin.math.min

@RuntimePermissions
class ServiceActivity : AppCompatActivity(), View.OnClickListener {
    val MY_PERMISSIONS_REQUEST_CALL_PHONE: Int = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> {
                startService()
            }
        }
    }

    @NeedsPermission(Manifest.permission.FOREGROUND_SERVICE)
    fun startService() {
        val mIntent = Intent(this, TextService::class.java)
        startService(mIntent)
    }
}
