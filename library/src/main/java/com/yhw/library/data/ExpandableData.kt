package com.yhw.library.data

/**
 * BaseExpandableListAdapter的数据类
 * @param G 分组类型
 * @param C 子数据类型
 */
data class ExpandableData<G, C>(
    var group: G,
    var childList: MutableList<C>
)
