package com.yhw.library.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

/**
 * ListView & GridView 基类Adapter
 * @param T 数据类型
 */
abstract class BaseListGridAdapter<T>(private var dataList: MutableList<T>) :
    BaseAdapter(), IAdapter<T> {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): T {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun insertItemToFirst(data: T) {
        this.dataList.add(0, data)
        this.notifyDataSetChanged()
    }

    override fun insertItemToFirst(dataList: MutableList<T>) {
        this.dataList.addAll(0, dataList)
        this.notifyDataSetChanged()
    }

    override fun insertItem(data: T, position: Int) {
        this.dataList.add(position, data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(data: T) {
        this.dataList.add(data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(dataList: MutableList<T>) {
        this.dataList.addAll(dataList)
        this.notifyDataSetChanged()
    }

    override fun removeAt(position: Int) {
        this.dataList.removeAt(position)
        this.notifyDataSetChanged()
    }

    override fun removeAll(dataList: MutableList<T>) {
        this.dataList.removeAll(dataList)
        this.notifyDataSetChanged()
    }

    override fun removeAll() {
        this.dataList.clear()
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: T, position: Int) {
        this.dataList[position] = data
        this.notifyDataSetChanged()
    }

    override fun refreshAll(dataList: MutableList<T>) {
        this.dataList = dataList
        this.notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent?.context)
        }
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = layoutInflater?.inflate(getItemLayoutId(), parent, false)!!
            viewHolder = ViewHolder(view)
            viewHolder.itemViewType = getItemViewType(position)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        onBindViewItem(
            position,
            dataList[position],
            parent,
            viewHolder,
        )
        return view
    }

    abstract fun getItemLayoutId(): Int

    /**
     * @param position 索引位置
     * @param data item数据
     * @param holder 获取控件
     */
    abstract fun onBindViewItem(
        position: Int,
        data: T,
        parent: ViewGroup?,
        holder: ViewHolder,
    )

    class ViewHolder(var itemView: View) {
        private var viewMap: MutableMap<Int, View> = mutableMapOf()
        var itemViewType = 0

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