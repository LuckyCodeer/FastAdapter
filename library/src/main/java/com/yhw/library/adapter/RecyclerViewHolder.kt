package com.yhw.library.adapter

import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var viewMap: MutableMap<Int, View> = mutableMapOf()

    fun <T : View?> getView(@IdRes viewId: Int): T {
        var view = viewMap[viewId]
        if (view == null) {
            view = itemView.findViewById<T>(viewId)
            viewMap[viewId] = view!!
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