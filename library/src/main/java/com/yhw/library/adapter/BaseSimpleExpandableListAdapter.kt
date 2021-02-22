package com.yhw.library.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.yhw.library.data.ExpandableData

abstract class BaseSimpleExpandableListAdapter<G, C>(private var dataList: MutableList<ExpandableData<G, C>>) :
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return dataList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return dataList[groupPosition].childList.size
    }

    override fun getGroup(groupPosition: Int): G {
        return dataList[groupPosition].group
    }

    override fun getChild(groupPosition: Int, childPosition: Int): C {
        return dataList[groupPosition].childList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(getGroupItemLayoutId(), parent, false)!!
            viewHolder = ViewHolder(view)
            viewHolder.itemViewType = getGroupType(groupPosition)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        onBindGroupViewItem(
            groupPosition,
            dataList[groupPosition].group,
            parent,
            viewHolder,
            isExpanded
        )
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(getChildItemLayoutId(), parent, false)!!
            viewHolder = ViewHolder(view)
            viewHolder.itemViewType = getChildType(groupPosition, childPosition)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        onBindChildViewItem(
            groupPosition,
            childPosition,
            dataList[groupPosition].childList[childPosition],
            parent,
            viewHolder,
            isLastChild
        )
        return view
    }

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

    abstract fun getGroupItemLayoutId(): Int
    abstract fun getChildItemLayoutId(): Int

    /**
     * 分组数据绑定
     * @param groupPosition 索引位置
     * @param groupData 分组数据
     * @param holder 获取控件
     */
    abstract fun onBindGroupViewItem(
        groupPosition: Int,
        groupData: G,
        parent: ViewGroup?,
        holder: ViewHolder,
        isExpanded: Boolean
    )

    /**
     * 子数据绑定
     * @param groupPosition 分组索引位置
     * @param childPosition 子数据索引位置
     * @param childData 子数据
     * @param holder 获取控件
     */
    abstract fun onBindChildViewItem(
        groupPosition: Int,
        childPosition: Int,
        childData: C,
        parent: ViewGroup?,
        holder: ViewHolder,
        isLastChild: Boolean
    )

    fun removeGroupAt(groupPosition: Int) {
        this.dataList.removeAt(groupPosition)
        this.notifyDataSetChanged()
    }

    fun removeChildAt(groupPosition: Int, childPosition: Int) {
        this.dataList[groupPosition].childList.removeAt(childPosition)
        this.notifyDataSetChanged()
    }

    fun removeAll() {
        this.dataList.clear()
        this.notifyDataSetChanged()
    }

    fun refreshGroupItem(group: G, groupPosition: Int) {
        this.dataList[groupPosition].group = group
        this.notifyDataSetChanged()
    }

    fun refreshChildItem(child: C, groupPosition: Int, childPosition: Int) {
        this.dataList[groupPosition].childList[childPosition] = child
        this.notifyDataSetChanged()
    }

    fun refreshAll(dataList: MutableList<ExpandableData<G, C>>) {
        this.dataList = dataList
        this.notifyDataSetChanged()
    }
}