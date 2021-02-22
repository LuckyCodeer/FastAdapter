package com.yhw.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.yhw.demo.fragment.DemoFragment
import com.yhw.library.adapter.BaseViewPager2FragmentAdapter

/**
 * ViewPager + Fragment
 */
class ViewPager2FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2_fragment)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val fragmentList = mutableListOf<Fragment>()

        for (i in 0..5) {
            fragmentList.add(DemoFragment.newInstance("viewpager2 -> $i"))
        }
        viewPager2.adapter = BaseViewPager2FragmentAdapter(this, fragmentList)
    }
}