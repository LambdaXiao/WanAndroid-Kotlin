package com.xiao.wanandroid.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *描述：封装RecycleView的适配器
 *
 */
class KRecyclerAdapter<T>(
    private val layoutResourceId: Int,
    private var itemList: MutableList<T>?,
    private val bindData: (View, T) -> Unit,
    private val itemClick: (View, Int) -> Unit,
    private val longClick: (View, Int) -> Unit
) : RecyclerView.Adapter<KRecyclerAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId, parent, false)
        val viewHolder = ViewHolder(view, bindData)
        viewHolder.itemView.setOnClickListener {
            //单击列表项
            itemClick(viewHolder.itemView, viewHolder.layoutPosition)
        }
        viewHolder.itemView.setOnLongClickListener {
            //长按列表项
            longClick(viewHolder.itemView, viewHolder.layoutPosition)
            true
        }

        return viewHolder
    }

    override fun getItemCount(): Int = itemList?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        itemList?.get(position)?.let { holder.bindItems(it) }
    }

    class ViewHolder<in T>(view: View, val bindData: (View, T) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bindItems(item: T) {
            if (item != null) {
                bindData(itemView, item)
            }
        }
    }

    fun add(item: T) {
        itemList?.add(item)
        itemList?.size?.let { notifyItemInserted(it) }
    }

    fun remove(position: Int) {
        itemList?.removeAt(position)
        notifyItemRemoved(position)
    }

}

/**
 * RecyclerView的扩展函数
 * 实现了 RecyclerView#setLayoutManager 和 RecyclerView#setAdapter 两个方法
 * 并返回 Adapter实例
 */
fun <T> RecyclerView.setCustomAdapter(
    layoutResourceId: Int,
    itemList: MutableList<T>?,
    bindData: (View, T) -> Unit,
    //以下三项都有默认实现体，需要自己处理时可以重写，不需要即可省略
    itemClick: (View, Int) -> Unit = { view, pos ->  },
    longClick: (View, Int) -> Unit = { view, pos ->  },
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): KRecyclerAdapter<T> {
    this.layoutManager = layoutManager
    return KRecyclerAdapter(
        layoutResourceId,
        itemList,
        bindData,
        itemClick,
        longClick
    ).apply { adapter = this }
}