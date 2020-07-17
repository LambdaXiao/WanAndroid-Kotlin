package com.xiao.wanandroid.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment
import com.xiao.wanandroid.common.base.BaseViewModelFragment
import kotlinx.coroutines.*

class DashboardFragment : BaseViewModelFragment<DashboardViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_dashboard

    override fun initView(view: View) {
        val textView: TextView = view.findViewById(R.id.text_dashboard)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

    override fun initData() {

    }

    override fun providerVMClass(): Class<DashboardViewModel> = DashboardViewModel::class.java
}