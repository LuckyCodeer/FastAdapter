package com.yhw.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.yhw.library.adapter.BaseRecyclerAdapter

/**
 * ViewPager + 任意自定义layout
 */
class ViewPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val dataList = mutableListOf<String>()
        for (i in 0..20) {
            dataList.add("new item $i")
        }
        viewPager2.adapter = MyAdapter(dataList)
    }

    class MyAdapter(dataList: MutableList<String>) : BaseRecyclerAdapter<String>(dataList) {
        override fun getItemLayoutId(viewType: Int): Int {
            return R.layout.viewpager2_item_layout
        }

        override fun onBindViewItem(holder: RecyclerViewHolder, position: Int, data: String) {
            holder.setText(R.id.tv_text, data)
        }
    }

}