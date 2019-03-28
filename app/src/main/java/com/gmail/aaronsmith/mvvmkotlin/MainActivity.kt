package com.gmail.aaronsmith.mvvmkotlin

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gmail.aaronsmith.mvvmkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        var user = User()
        activityMainBinding.userInfo = user
        user.name = "Aaron"
        user.password = "123456"

        user.name = "Smith"
    }
}
