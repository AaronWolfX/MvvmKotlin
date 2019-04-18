package com.gmail.aaronsmith.mvvmkotlin

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.aaronsmith.mvvmkotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        var user = User2()
        activityMainBinding.userInfo = user
        user.name = "Aaron"
        user.password = "123"

        change.setOnClickListener {
            Log.e("aaron", "smith")
            user.name = "Smith"
            user.password = "333"
        }

        user.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.e("aaron", "propertyChanged")
                Log.e("aaron", "propertyId:" + propertyId)
                Log.e("aaron", "nameId:" + com.gmail.aaronsmith.mvvmkotlin.BR.name)
                Log.e("aaron", "passwordId:" + com.gmail.aaronsmith.mvvmkotlin.BR.password)
                if (propertyId == BR.name) {
                    Log.e("aaron", "name change")
                } else if (propertyId == BR.password) {
                    Log.e("aaron", "password change")
                }
            }

        })
    }

    override fun onStop() {
        super.onStop()
        Log.e("aaron","activity stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("aaron","activity destroy")
    }

}
