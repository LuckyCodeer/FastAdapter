package com.yhw.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.yhw.library.adapter.BaseViewPagerViewAdapter

class ViewPagerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_view)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val viewList = mutableListOf<View>()
        for (i in 0..4){
            val textView = TextView(this)
            textView.text = "View $i"
            viewList.add(textView)
        }
        val adapter = BaseViewPagerViewAdapter(viewList)
        viewPager.adapter = adapter
    }
}