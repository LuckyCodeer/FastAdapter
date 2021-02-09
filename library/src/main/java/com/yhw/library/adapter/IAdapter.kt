package com.yhw.library.adapter

interface IAdapter<T> {
    /**
     * 首部插入单条数据
     */
    fun insertItemToFirst(data: T)

    /**
     * 首部插入多条数据
     */
    fun insertItemToFirst(dataList: MutableList<T>)

    /**
     * 任意位置插入一条数据
     */
    fun insertItem(data: T, position: Int)

    /**
     * 尾部追加一条数据
     */
    fun appendItem(data: T)

    /**
     * 尾部追加多条数据
     */
    fun appendItem(dataList: MutableList<T>)

    /**
     * 移除单条数据
     * @param position 位置索引
     */
    fun removeAt(position: Int)

    /**
     * 移除多个数据
     */
    fun removeAt(positionList: MutableList<Int>)

    /**
     * 移除全部数据
     */
    fun removeAll()

    /**
     * 刷新单条数据
     */
    fun refreshItem(data: T, position: Int)

    /**
     * 刷新全部数据
     */
    fun refreshAll(dataList: MutableList<T>)
}