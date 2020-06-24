package com.xiao.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment
import com.xiao.wanandroid.repository.remote.network.BaseObserver
import com.xiao.wanandroid.repository.remote.network.BaseResponse
import com.xiao.wanandroid.repository.remote.network.RetrofitManager
import com.xiao.wanandroid.ui.home.bean.BannerBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class HomeFragment(isShow: Boolean) : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        RetrofitManager.instance.getService().getBanner()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(Consumer {t ->  print("ddddddddddddddddddddddddddd")})

        return root
    }
}