package com.gmail.aaronsmith.mvvmkotlin

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * @author Aaron Smith
 * @date 2019/3/29.
 * kotlin这个没用
 */
class User2 : BaseObservable() {
    @get:Bindable
    var name: String? = null
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    var password: String? = null
        set(password) {
            field = password
            notifyChange()
        }
}
