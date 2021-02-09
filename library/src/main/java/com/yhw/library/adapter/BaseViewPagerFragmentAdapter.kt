package com.yhw.library.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * ViewPage的基类adapter
 * Fragment
 */
class BaseViewPagerFragmentAdapter(
    fm: FragmentManager,
    private var fragmentList: MutableList<Fragment>
) : FragmentPagerAdapter(fm), IAdapter<Fragment> {
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun insertItem(data: Fragment, position: Int) {
        this.fragmentList.add(position, data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(data: Fragment) {
        this.fragmentList.add(data)
        this.notifyDataSetChanged()
    }

    override fun appendItem(dataList: MutableList<Fragment>) {
        this.fragmentList.addAll(dataList)
        this.notifyDataSetChanged()
    }

    override fun insertItemToFirst(data: Fragment) {
        this.fragmentList.add(0, data)
        this.notifyDataSetChanged()
    }

    override fun insertItemToFirst(dataList: MutableList<Fragment>) {
        this.fragmentList.addAll(0, dataList)
        this.notifyDataSetChanged()
    }

    override fun removeAt(position: Int) {
        this.fragmentList.removeAt(position)
        this.notifyDataSetChanged()
    }

    override fun removeAt(positionList: MutableList<Int>) {
        for (position in positionList) {
            this.fragmentList.removeAt(position)
        }
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: Fragment, position: Int) {
        this.fragmentList[position] = data
        this.notifyDataSetChanged()
    }

    override fun refreshAll(dataList: MutableList<Fragment>) {
        this.fragmentList = dataList
        this.notifyDataSetChanged()
    }

    override fun removeAll() {
        this.fragmentList.clear()
        this.notifyDataSetChanged()
    }

}