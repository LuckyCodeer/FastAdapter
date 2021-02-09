package com.yhw.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView基类Adapter
 */
abstract class BaseRecyclerAdapter<T>(private var mDataList: MutableList<T>) :
    RecyclerView.Adapter<RecyclerViewHolder>(), IAdapter<T> {
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

    override fun refreshAll(dataList: MutableList<T>) {
        this.mDataList = dataList
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: T, position: Int) {
        if (position >= itemCount) {
            return
        }
        this.mDataList[position] = data
        this.notifyItemChanged(position)
    }

    /**
     * 插入数据
     */
    override fun insertItem(data: T, position: Int) {
        if (position > itemCount) {
            return
        }
        this.mDataList.add(position, data)
        this.notifyItemRangeInserted(position, 1)
        if (position + 1 < itemCount) {
            this.notifyItemRangeChanged(position + 1, itemCount - position - 1)
        }
    }

    /**
     * 首部插入数据
     */
    override fun insertItemToFirst(data: T) {
        this.mDataList.add(0, data)
        this.notifyItemInserted(0)
        if (itemCount > 1) {
            this.notifyItemRangeChanged(1, itemCount - 1)
        }
    }

    /**
     * 首部插入多条数据
     */
    override fun insertItemToFirst(dataList: MutableList<T>) {
        this.mDataList.addAll(0, dataList)
        this.notifyItemRangeInserted(0, dataList.size)
        if (itemCount > dataList.size) {
            this.notifyItemRangeChanged(dataList.size, itemCount - dataList.size)
        }
    }

    /**
     * 尾部追加数据
     */
    override fun appendItem(data: T) {
        this.mDataList.add(data)
        this.notifyItemInserted(itemCount)
    }

    /**
     * 尾部追加多条数据
     */
    override fun appendItem(dataList: MutableList<T>) {
        this.mDataList.addAll(dataList)
        this.notifyItemRangeInserted(itemCount, dataList.size)
    }

    override fun removeAt(position: Int) {
        if (position >= itemCount) {
            return
        }
        this.mDataList.removeAt(position)
        this.notifyItemRemoved(position)
        if (itemCount > 0 && position < itemCount) {
            this.notifyItemRangeChanged(position, itemCount - position)
        }
    }

    override fun removeAt(positionList: MutableList<Int>) {
        if (positionList.size >= itemCount) {
            return
        }
        for (i in positionList) {
            this.mDataList.removeAt(i)
        }
        this.notifyDataSetChanged()
    }

    /**
     * 清除所有数据
     */
    override fun removeAll() {
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