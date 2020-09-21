package com.xiao.wanandroid.ui.gzh

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xiao.wanandroid.R
import com.xiao.wanandroid.common.base.BaseFragment
import com.xiao.wanandroid.data.local.entity.User
import com.xiao.wanandroid.data.repository.LocalRepository
import kotlinx.android.synthetic.main.gzh_fragment.view.*

class GzhFragment : BaseFragment() {

    val mViewModel: GzhViewModel by viewModels()


    companion object {
        fun newInstance() = GzhFragment()
    }

    override fun getLayoutId(): Int = R.layout.gzh_fragment

    override fun initView(view: View) {
        view.btn1.setOnClickListener {
            mViewModel.insertData()
        }

        view.btn2.setOnClickListener {
            mViewModel.selectData()
        }

        mViewModel.getList().observe(viewLifecycleOwner, Observer {
            var str = ""
            it.forEach {
                str += it.toString()
            }
            view.text.text = str
        })

        mViewModel.selectData()?.observe(viewLifecycleOwner, Observer {
            var str = ""
            it.forEach {
                str += it.toString()+","
            }
            view.text.text = str
        })
    }

    override fun initData() {

    }


}