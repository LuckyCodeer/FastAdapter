package com.yhw.demo

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.yhw.library.adapter.BaseListGridAdapter

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listView = findViewById<ListView>(R.id.list_view)
        val dataList = mutableListOf<String>()
        for (i in 0..100) {
            dataList.add("listView item $i")
        }
        val adapter = MyAdapter(dataList)
        listView.adapter = adapter
    }

    inner class MyAdapter(dataList: MutableList<String>) :
        BaseListGridAdapter<String>(dataList) {
        override fun getItemLayoutId(): Int {
            return R.layout.sample_item_layout
        }

        override fun onBindViewItem(
            position: Int,
            data: String,
            parent: ViewGroup?,
            holder: ViewHolder
        ) {
//            val textView = holder.getView<TextView>(R.id.tv_text)
//            textView.text = data
//            或者
            holder.setText(R.id.tv_text, data)

            val imageView = holder.getView<ImageView>(R.id.iv_image)
            imageView.setImageResource(R.mipmap.ic_launcher_round)
        }

    }

}