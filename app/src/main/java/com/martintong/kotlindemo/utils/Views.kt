package com.martintong.kotlindemo.utils

import android.util.SparseArray
import android.view.View

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/15
🐶
🐶 @apiNote
 */

inline fun <T : View> View.findViewOften(viewId: Int): T {
    var viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
    tag = viewHolder
    var childView: View? = viewHolder.get(viewId)
    if (null == childView) {
        childView = findViewById(viewId)
        viewHolder.put(viewId, childView)
    }
    return childView as T
}