package com.yhw.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * Demo
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_recyclerview).setOnClickListener{
            startActivity(Intent(this,RecyclerViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_listview).setOnClickListener{
            startActivity(Intent(this,ListViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn_viewpager_fragment).setOnClickListener{
            startActivity(Intent(this,ViewPagerFragmentActivity::class.java))
        }

        findViewById<Button>(R.id.btn_viewpager_view).setOnClickListener{
            startActivity(Intent(this,ViewPagerViewActivity::class.java))
        }
    }
}