package com.yhw.library.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * ViewPager 基类adapter
 * view
 */
class BaseViewPagerViewAdapter(private var viewList: MutableList<View>) : PagerAdapter(),
    IAdapter<View> {
    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = viewList[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = viewList[position]
        container.removeView(view)
    }

    override fun insertItemToFirst(data: View) {
        this.viewList.add(0, data)
        this.notifyDataSetChanged()
    }

    override fun insertItemToFirst(dataList: MutableList<View>) {
        this.viewList.addAll(0, dataList)
        this.notifyDataSetChanged()
    }

    override fun insertItem(data: View, position: Int) {
        this.viewList.add(position, data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(data: View) {
        this.viewList.add(data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(dataList: MutableList<View>) {
        this.viewList.addAll(dataList)
        this.notifyDataSetChanged()
    }

    override fun removeAt(position: Int) {
        this.viewList.removeAt(position)
        this.notifyDataSetChanged()
    }

    override fun removeAt(positionList: MutableList<Int>) {
        for (position in positionList) {
            this.viewList.removeAt(position)
        }
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: View, position: Int) {
        this.viewList[position] = data
        this.notifyDataSetChanged()
    }

    override fun refreshAll(dataList: MutableList<View>) {
        this.viewList = dataList
        this.notifyDataSetChanged()
    }

    override fun removeAll() {
        this.viewList.clear()
        this.notifyDataSetChanged()
    }


}