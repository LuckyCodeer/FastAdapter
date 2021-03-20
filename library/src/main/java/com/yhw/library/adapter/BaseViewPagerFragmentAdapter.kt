package com.yhw.library.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * ViewPage的基类adapter
 * Fragment
 */
class BaseViewPagerFragmentAdapter : FragmentStatePagerAdapter, IAdapter<Fragment> {
    private var fragmentList: MutableList<Fragment>

    constructor(
        fm: FragmentManager,
        fragmentList: MutableList<Fragment>
    ) : super(fm) {
        this.fragmentList = fragmentList
    }

    /**
     * @param behavior FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT,
     * FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
     */
    constructor(
        fm: FragmentManager,
        behavior: Int,
        fragmentList: MutableList<Fragment>
    ) : super(fm, behavior) {
        this.fragmentList = fragmentList
    }


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

    override fun removeAll(dataList: MutableList<Fragment>) {
        this.fragmentList.removeAll(dataList)
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