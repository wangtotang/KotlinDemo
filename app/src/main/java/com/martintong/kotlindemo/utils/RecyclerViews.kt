package com.martintong.kotlindemo.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/14
🐶
🐶 @apiNote
 */

inline fun RecyclerView.setLinearSpaceDecoration(space: Int) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            outRect?.left = space / 2
            outRect?.right = space / 2
            outRect?.top = space
            val itemCount = parent?.adapter?.itemCount
            if (parent?.getChildLayoutPosition(view) == itemCount?.minus(1)) {
                outRect?.bottom = space
            } else {
                outRect?.bottom = 0
            }
        }
    })
}

inline fun RecyclerView.Adapter<RecyclerView.ViewHolder>.getViewHolder(itemView: View) = object : RecyclerView.ViewHolder(itemView) {}