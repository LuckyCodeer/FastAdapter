package com.yhw.library.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView基类Adapter
 * @param T 数据类型
 */
abstract class BaseRecyclerAdapter<T>(private var dataList: MutableList<T>) :
    RecyclerView.Adapter<BaseRecyclerAdapter.RecyclerViewHolder>(), IAdapter<T> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(getItemLayoutId(viewType), parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        onBindViewItem(holder, position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun refreshAll(dataList: MutableList<T>) {
        this.dataList = dataList
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: T, position: Int) {
        if (position >= itemCount) {
            return
        }
        this.dataList[position] = data
        this.notifyItemChanged(position)
    }

    /**
     * 插入数据
     */
    override fun insertItem(data: T, position: Int) {
        if (position > itemCount) {
            return
        }
        this.dataList.add(position, data)
        this.notifyItemRangeInserted(position, 1)
        if (position + 1 < itemCount) {
            this.notifyItemRangeChanged(position + 1, itemCount - position - 1)
        }
    }

    /**
     * 首部插入数据
     */
    override fun insertItemToFirst(data: T) {
        this.dataList.add(0, data)
        this.notifyItemInserted(0)
        if (itemCount > 1) {
            this.notifyItemRangeChanged(1, itemCount - 1)
        }
    }

    /**
     * 首部插入多条数据
     */
    override fun insertItemToFirst(dataList: MutableList<T>) {
        this.dataList.addAll(0, dataList)
        this.notifyItemRangeInserted(0, dataList.size)
        if (itemCount > dataList.size) {
            this.notifyItemRangeChanged(dataList.size, itemCount - dataList.size)
        }
    }

    /**
     * 尾部追加数据
     */
    override fun appendItem(data: T) {
        this.dataList.add(data)
        this.notifyItemInserted(itemCount)
    }

    /**
     * 尾部追加多条数据
     */
    override fun appendItem(dataList: MutableList<T>) {
        this.dataList.addAll(dataList)
        this.notifyItemRangeInserted(itemCount, dataList.size)
    }

    override fun removeAt(position: Int) {
        if (position >= itemCount) {
            return
        }
        this.dataList.removeAt(position)
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
            this.dataList.removeAt(i)
        }
        this.notifyDataSetChanged()
    }

    /**
     * 清除所有数据
     */
    override fun removeAll() {
        this.dataList.clear()
        this.notifyDataSetChanged()
    }


    /**
     * 布局文件
     */
    abstract fun getItemLayoutId(viewType: Int): Int

    /**
     * 绑定数据
     */
    abstract fun onBindViewItem(holder: RecyclerViewHolder, position: Int, data: T)


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var viewMap: MutableMap<Int, View> = mutableMapOf()

        fun <T : View> getView(@IdRes viewId: Int): T {
            var view = viewMap[viewId]
            if (view == null) {
                view = itemView.findViewById(viewId)
                viewMap[viewId] = view
            }
            return view as T
        }

        fun setText(@IdRes viewId: Int, text: String) {
            val textView: TextView = getView(viewId)
            textView.text = text
        }

        fun setText(@IdRes viewId: Int, @StringRes textId: Int) {
            val textView: TextView = getView(viewId)
            textView.setText(textId)
        }

        /**
         * 设置文本
         * 如果文本为null或者空字符串则隐藏控件
         */
        fun setTextIfEmptyHide(@IdRes viewId: Int, text: String) {
            val textView: TextView = getView(viewId)
            if (TextUtils.isEmpty(text)) {
                textView.visibility = View.GONE
            } else {
                textView.visibility = View.VISIBLE
                textView.text = text
            }
        }

        fun setImageResource(@IdRes viewId: Int, @DrawableRes resId: Int) {
            val imageView: ImageView = getView(viewId)
            imageView.setImageResource(resId)
        }

        fun setChecked(@IdRes viewId: Int, isChecked: Boolean) {
            val checkBox: CheckBox = getView(viewId)
            checkBox.isChecked = isChecked
        }

    }
}