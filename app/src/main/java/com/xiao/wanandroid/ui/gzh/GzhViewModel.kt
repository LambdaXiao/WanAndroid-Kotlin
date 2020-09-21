package com.xiao.wanandroid.ui.gzh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xiao.wanandroid.data.local.entity.User
import com.xiao.wanandroid.data.repository.LocalRepository
import com.xiao.wanandroid.ext.launchUI
import com.xiao.wanandroid.ext.logD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GzhViewModel : ViewModel() {
    private val list by lazy { MutableLiveData<List<User>>() }

    fun getList(): LiveData<List<User>> {
        return list
    }

    fun insertData() {
        launchUI(block = {
            var id = LocalRepository.findMaxID()
            logD("Test===", "最大ID:$id")
            LocalRepository.insertAll(User(++id, "$id 姓", "$id 名","id"))
        })

    }

    fun selectData(): LiveData<List<User>>? {

            val list = LocalRepository.getAll()
            logD("Test===", "数据长度：${list.value?.size}")
            list.value?.forEach {
                logD("Test===", it.toString())
            }


        return list
    }
}