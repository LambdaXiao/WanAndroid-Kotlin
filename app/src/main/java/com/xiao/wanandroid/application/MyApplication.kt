package com.xiao.wanandroid.application

import android.app.Application

/**
 *描述：app的Application
 *
 */
class MyApplication: Application() {

   companion object{
       lateinit var intance:MyApplication
   }
    override fun onCreate() {
        super.onCreate()
        intance = this
    }
}