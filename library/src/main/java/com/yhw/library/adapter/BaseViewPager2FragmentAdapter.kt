package com.yhw.library.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BaseViewPager2FragmentAdapter(
    fragmentActivity: FragmentActivity,
    private var fragmentList: MutableList<Fragment>
) : FragmentStateAdapter(fragmentActivity), IAdapter<Fragment> {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun refreshAll(dataList: MutableList<Fragment>) {
        this.fragmentList = dataList
        this.notifyDataSetChanged()
    }

    override fun refreshItem(data: Fragment, position: Int) {
        if (position >= itemCount) {
            return
        }
        this.fragmentList[position] = data
        this.notifyItemChanged(position)
    }

    /**
     * 插入数据
     */
    override fun insertItem(data: Fragment, position: Int) {
        if (position > itemCount) {
            return
        }
        this.fragmentList.add(position, data)
        this.notifyItemRangeInserted(position, 1)
        if (position + 1 < itemCount) {
            this.notifyItemRangeChanged(position + 1, itemCount - position - 1)
        }
    }

    /**
     * 首部插入数据
     */
    override fun insertItemToFirst(data: Fragment) {
        this.fragmentList.add(0, data)
        this.notifyItemInserted(0)
        if (itemCount > 1) {
            this.notifyItemRangeChanged(1, itemCount - 1)
        }
    }

    /**
     * 首部插入多条数据
     */
    override fun insertItemToFirst(dataList: MutableList<Fragment>) {
        this.fragmentList.addAll(0, dataList)
        this.notifyItemRangeInserted(0, dataList.size)
        if (itemCount > dataList.size) {
            this.notifyItemRangeChanged(dataList.size, itemCount - dataList.size)
        }
    }

    /**
     * 尾部追加数据
     */
    override fun appendItem(data: Fragment) {
        this.fragmentList.add(data)
        this.notifyItemInserted(itemCount)
    }

    /**
     * 尾部追加多条数据
     */
    override fun appendItem(dataList: MutableList<Fragment>) {
        this.fragmentList.addAll(dataList)
        this.notifyItemRangeInserted(itemCount, dataList.size)
    }

    override fun removeAt(position: Int) {
        if (position >= itemCount) {
            return
        }
        this.fragmentList.removeAt(position)
        this.notifyItemRemoved(position)
        if (itemCount > 0 && position < itemCount) {
            this.notifyItemRangeChanged(position, itemCount - position)
        }
    }

    override fun removeAll(dataList: MutableList<Fragment>) {
        this.fragmentList.removeAll(dataList)
        this.notifyDataSetChanged()
    }

    /**
     * 清除所有数据
     */
    override fun removeAll() {
        this.fragmentList.clear()
        this.notifyDataSetChanged()
    }

}