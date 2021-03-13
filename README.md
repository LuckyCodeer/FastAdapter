# FastAdapter
一个快速进行适配器编写的库，适用于RecyclerView、ListView、GridView、ViewPager、ViewPager2、ExpandableListView、Spinner等，
可大大减少样板代码，简化Adapter的编写。

### 引入依赖

```kotlin
implementation 'com.yhw.library:fastadapter:1.0.2'
```

### 使用方法

#### RecyclerView Adapter用法：
```kotlin
class MyRecyclerAdapter(dataList: MutableList<String>) : BaseRecyclerAdapter<String>(dataList) {

        override fun getItemLayoutId(viewType: Int): Int {
            return R.layout.sample_item_layout
        }

        override fun onBindViewItem(holder: RecyclerViewHolder, position: Int, data: String) {
//          使用
//          val textView = holder.getView<TextView>(R.id.tv_text)
//          textView.text = data   
//          或               
            holder.setText(R.id.tv_text, "$data  $position") //推荐写法

            holder.setImageResource(R.id.iv_image, R.mipmap.ic_launcher)
        }
}
```
**\*其它Adapter具体用法请参考Demo中的代码示例**

#### 首部插入一条数据
```kotlin
adapter.insertItemToFirst("新插入的首部数据")
```
#### 首部插入多条数据
```kotlin
adapter.insertItemToFirst(mutableListOf("批量插入的首部数据A","批量插入的首部数据B", 
                                        "批量插入的首部数据C", "批量插入的首部数据D" ))
```
#### 任意位置插入数据
```kotlin
adapter.insertItem("我是新插入的数据", 4)
```
#### 尾部插入一条数据
```kotlin
adapter.appendItem("我是尾部新追加的数据")
```
#### 尾部插入多条数据
```kotlin
adapter.appendItem(mutableListOf("我是尾部新追加的数据A","我是尾部新追加的数据B", 
                                    "我是尾部新追加的数据C", "我是尾部新追加的数据D"))
```
#### 移除一条数据
```kotlin
adapter.removeAt(4)
```
#### 移除多条数据
```kotlin
adapter.removeAt(checkedList)
```
#### 移除全部数据
```kotlin
adapter.removeAll()
```
#### 更新单条数据
```kotlin
adapter.refreshItem("这是更新过的数据", 4)
```
#### 更新全部数据
```kotlin
adapter.refreshAll(mutableListOf("新数据A", "新数据B", "新数据C", "新数据D", "新数据E", "新数据F", "新数据G", "新数据H"))
```


**\*示例代码采用Kotlin语言编写，如果你的项目是Java，用法与Kotlin基本类似**
