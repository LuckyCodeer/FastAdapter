package com.yhw.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.yhw.demo.fragment.DemoFragment
import com.yhw.library.adapter.BaseViewPagerFragmentAdapter

class ViewPagerFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_fragment)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val fragmentList = mutableListOf<Fragment>()
        for (i in 0..4) {
            fragmentList.add(DemoFragment.newInstance("Fragment $i"))
        }
        val adapter = BaseViewPagerFragmentAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = adapter
    }
}