package com.xiao.wanandroid.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.xiao.wanandroid.common.GlobalViewModel

/**
 *描述：app的Application
 * ViewModelStoreOwner 就是为了 让整个项目持有一份 被观察者接口，为了完成全局共享
 */
class MyApplication : Application(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore

    companion object {
        lateinit var intance: MyApplication

        // 贯穿整个项目的,初始化全局共享（MyApplication传入的ViewModelStore是同一个）
        lateinit var mGlobalViewModel: GlobalViewModel
    }

    override fun onCreate() {
        super.onCreate()
        intance = this

        mAppViewModelStore = ViewModelStore()
        mGlobalViewModel = ViewModelProvider(this).get(GlobalViewModel::class.java)
        // todo，这里可以完成一系列的初始化工作
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
}