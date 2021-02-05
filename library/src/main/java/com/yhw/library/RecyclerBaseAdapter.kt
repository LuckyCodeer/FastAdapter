package com.yhw.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * 基类adapter
 */
abstract class RecyclerBaseAdapter<T>(private var mDataList: MutableList<T>) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(getItemLayoutId(viewType), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        onBindViewItem(holder, position, mDataList[position])
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    /**
     * 刷新整个列表数据
     */
    fun refreshList(dataList: MutableList<T>) {
        this.mDataList = dataList
        this.notifyDataSetChanged()
    }

    /**
     * 刷新单个条目的数据
     */
    fun refreshItem(position: Int, data: T) {
        if (position >= itemCount) {
            return
        }
        this.mDataList[position] = data
        this.notifyItemChanged(position)
    }

    /**
     * 插入数据
     */
    fun insertItem(data: T, position: Int) {
        if (position >= itemCount) {
            return
        }
        this.mDataList.add(position, data)
        this.notifyItemRangeInserted(position, 1)
        this.notifyItemRangeChanged(position + 1, itemCount - 1)
    }

    /**
     * 首部插入数据
     */
    fun insertItemToFirst(data: T) {
        this.mDataList.add(0, data)
        this.notifyItemInserted(0)
        if (itemCount > 0) {
            this.notifyItemRangeChanged(1, itemCount - 1)
        }
    }

    /**
     * 首部插入多条数据
     */
    fun insertItemToFirst(dataList: MutableList<T>) {
        this.mDataList.addAll(0, dataList)
        this.notifyItemRangeInserted(0, dataList.size)
        if (itemCount > dataList.size) {
            this.notifyItemRangeChanged(dataList.size, itemCount - 1)
        }
    }

    /**
     * 追加数据
     */
    fun appendItem(data: T) {
        this.mDataList.add(data)
        this.notifyItemInserted(itemCount)
    }

    /**
     * 追加数据
     */
    fun appendItem(dataList: MutableList<T>) {
        this.mDataList.addAll(dataList)
        this.notifyItemRangeInserted(itemCount, dataList.size)
    }

    /**
     * 删除某个条目
     */
    fun deleteItem(position: Int) {
        if (position >= itemCount) {
            return
        }
        this.mDataList.removeAt(position)
        this.notifyItemRemoved(position)
        if (itemCount > 0 && position != itemCount - 1) {
            this.notifyItemRangeChanged(position, itemCount - 1)
        }
    }

    /**
     * 批量删除条目
     */
    fun deleteItem(positionList: MutableList<Int>) {
        if (positionList.size >= itemCount) {
            return
        }
        for (i in positionList) {
            this.mDataList.removeAt(i)
            this.notifyItemRemoved(i)
        }
        if (itemCount > 0) {
            this.notifyDataSetChanged()
        }
    }

    /**
     * 清除所有数据
     */
    fun removeAll() {
        this.mDataList.clear()
        this.notifyDataSetChanged()
    }

    /**
     * 布局文件
     */
    abstract fun getItemLayoutId(viewType: Int): Int

    /**
     * 绑定数据
     */
    abstract fun onBindViewItem(holder: RecyclerViewHolder, position: Int, item: T)


}