package com.yhw.demo

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import com.yhw.library.adapter.BaseListGridAdapter

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val spinner = findViewById<AppCompatSpinner>(R.id.spinner)
        val dataList = mutableListOf("北京", "重庆", "天津", "上海", "广州", "西安", "石家庄", "呼和浩特", "乌鲁木齐")
        spinner.adapter = MyAdapter(dataList)
    }

    class MyAdapter(dataList: MutableList<String>) : BaseListGridAdapter<String>(dataList) {
        override fun getItemLayoutId(): Int {
            return R.layout.spinner_item_layout
        }

        override fun onBindViewItem(
            position: Int,
            data: String,
            parent: ViewGroup?,
            holder: ViewHolder
        ) {
            holder.setText(R.id.tv_text, data)
        }

    }
}