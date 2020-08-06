package com.xiao.wanandroid.ui.project

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.SharedPreferencesManager
import com.xiao.wanandroid.common.base.BaseViewModelFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProjectFragment : BaseViewModelFragment<ProjectViewModel>() {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun getLayoutId(): Int = R.layout.project_fragment

    override fun initView(view: View) {

    }

    override fun initData() {

        var dd by SharedPreferencesManager("xxx","")
        if(!TextUtils.isEmpty(dd)) {
            println("Test=$dd")
        }else{
            println("Test=11==$dd")
        }
        dd= "www"
//        dd = "www"
//        println("Test=$dd")
//        val aa= dd
//        println("Test=$aa")
    }


    override fun providerVMClass(): Class<ProjectViewModel> = ProjectViewModel::class.java

}