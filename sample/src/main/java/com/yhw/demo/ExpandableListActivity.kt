package com.yhw.demo

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.yhw.library.adapter.BaseSimpleExpandableListAdapter
import com.yhw.library.data.ExpandableData

class ExpandableListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_list)

        val expandableListView = findViewById<ExpandableListView>(R.id.expandable_listview)

        //组合数据 start
        //这里数据一般来源于后台接口，拿到数据后，按照下面示例去进行组合数据即可
        //请查看此类 ExpandableData<G,C>
        val groupList = mutableListOf("陕西省", "山西省", "四川省", "内蒙古自治区")
        val expandableDataList = mutableListOf<ExpandableData<String, String>>()
        for ((index, group) in groupList.withIndex()) {
            val childList: MutableList<String> = when (index) {
                0 -> mutableListOf("西安市", "榆林市", "延安市", "安康市")
                1 -> mutableListOf("太原市", "大同市", "运城市")
                2 -> mutableListOf("成都市", "自贡市", "攀枝花市")
                3 -> mutableListOf("呼和浩特市", "巴彦淖尔市", "乌海市", "乌兰察布市")
                else -> mutableListOf()
            }

            Log.i("TAG", "child size==> ${childList.size}")
            val expandableData = ExpandableData(group, childList)
            expandableDataList.add(expandableData)
        }
        //组合数据 end

        Log.i("TAG", "expandableDataList $expandableDataList")
        //设置适配器
        val adapter = MyAdapter(expandableDataList)
        expandableListView.setAdapter(adapter)
    }

    class MyAdapter(dataList: MutableList<ExpandableData<String, String>>) :
        BaseSimpleExpandableListAdapter<String, String>(
            dataList
        ) {
        override fun getGroupItemLayoutId(): Int {
            return R.layout.expandable_group_item_layout
        }

        override fun getChildItemLayoutId(): Int {
            return R.layout.expandable_child_item_layout
        }

        override fun onBindGroupViewItem(
            groupPosition: Int,
            groupData: String,
            parent: ViewGroup?,
            holder: ViewHolder,
            isExpanded: Boolean
        ) {
            holder.setText(R.id.tv_text, groupData)
        }

        override fun onBindChildViewItem(
            groupPosition: Int,
            childPosition: Int,
            childData: String,
            parent: ViewGroup?,
            holder: ViewHolder,
            isLastChild: Boolean
        ) {
            holder.setText(R.id.tv_text, childData)
        }

    }
}