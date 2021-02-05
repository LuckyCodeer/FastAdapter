package com.yhw.recyclerbaseadapter

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yhw.library.RecyclerBaseAdapter
import com.yhw.library.RecyclerViewHolder

/**
 * demo
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataList = mutableListOf<String>()
        for (i in 0..100) {
            dataList.add("this is item")
        }

        val deleteBtn = findViewById<AppCompatButton>(R.id.btn_delete)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(dataList)
        recyclerView.adapter = adapter

        /**
         * 操作数据，增删改
         */
        findViewById<AppCompatButton>(R.id.btn_operate).setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("操作数据")
                .setItems(
                    arrayOf(
                        "首部插入一条数据", "首部插入多条数据", "第五个位置插入一条数据", "尾部追加一条数据", "尾部追加多条数据",
                        "删除第五条数据", "批量删除选中的数据", "清除全部数据", "修改第五条数据", "刷新所有数据"
                    )
                ) { _: DialogInterface, which: Int ->
                    when (which) {
                        0 -> {
                            adapter.insertItemToFirst("新插入的首部数据")
                            recyclerView.smoothScrollToPosition(0)
                        }
                        1 -> {
                            adapter.insertItemToFirst(
                                mutableListOf(
                                    "批量插入的首部数据A",
                                    "批量插入的首部数据B", "批量插入的首部数据C", "批量插入的首部数据D"
                                )
                            )
                            recyclerView.smoothScrollToPosition(0)
                        }
                        2 -> {
                            adapter.insertItem("我是新插入的数据", 4)
                        }
                        3 -> {
                            adapter.appendItem("我是尾部新追加的数据")
                            recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
                        }
                        4 -> {
                            adapter.appendItem(
                                mutableListOf(
                                    "我是尾部新追加的数据A",
                                    "我是尾部新追加的数据B", "我是尾部新追加的数据C", "我是尾部新追加的数据D"
                                )
                            )
                            recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
                        }
                        5 -> {
                            adapter.deleteItem(4)
                        }
                        6 -> {
                            adapter.isShowCheckBox = true
                            adapter.notifyDataSetChanged()
                            deleteBtn.visibility = View.VISIBLE
                        }
                        7 -> {
                            adapter.removeAll()
                        }
                        8 -> {
                            adapter.refreshItem(4, "这是更新过的数据")
                        }
                        9 -> {
                            adapter.refreshList(
                                mutableListOf(
                                    "新数据A", "新数据B", "新数据C", "新数据D", "新数据E", "新数据F", "新数据G", "新数据H"
                                )
                            )
                        }
                    }
                }
                .create()
            dialog.show()
        }

        /**
         * 删除选中的数据
         */
        deleteBtn.setOnClickListener {
            if (adapter.checkedList.size == 0) {
                Toast.makeText(this, "请先选择数据", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            adapter.isShowCheckBox = false
            adapter.deleteItem(adapter.checkedList)
            adapter.checkedList.clear()
            deleteBtn.visibility = View.GONE
        }

    }

    /**
     * 直接继承 RecyclerBaseAdapter
     */
    inner class MyAdapter(mDataList: MutableList<String>) : RecyclerBaseAdapter<String>(
        mDataList
    ) {
        var isShowCheckBox = false
        var checkedList = mutableListOf<Int>()

        override fun getItemLayoutId(viewType: Int): Int {
            return R.layout.sample_item_layout
        }

        override fun onBindViewItem(holder: RecyclerViewHolder, position: Int, item: String) {
            //使用 holder.getView(R.id.tv_text) 或者 setText()
            holder.setText(R.id.tv_text, "$item  $position")
            holder.setImageResource(R.id.iv_image, R.mipmap.ic_launcher)
            val checkBox = holder.getView<AppCompatCheckBox>(R.id.checkbox)
            checkBox.visibility = if (isShowCheckBox) View.VISIBLE else View.GONE

            holder.itemView.setOnClickListener {
                if (isShowCheckBox) {
                    checkBox.isChecked = !checkBox.isChecked
                    if (checkBox.isChecked) {
                        checkedList.add(position)
                    } else {
                        checkedList.remove(position)
                    }
                    return@setOnClickListener
                }
                Toast.makeText(this@MainActivity, "item => $position", Toast.LENGTH_SHORT).show()
            }
        }

    }
}