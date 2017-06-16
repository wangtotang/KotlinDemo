package com.martintong.kotlindemo.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import com.martintong.kotlindemo.utils.findViewOften

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/15
🐶
🐶 @apiNote
 */
abstract class BaseRecyclerViewAdapter<T>() : RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.ViewHolder>() {

    var items: ArrayList<T>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener1?.invoke(itemView, layoutPosition)
                listener2?.invoke(items?.get(layoutPosition))
            }
        }

        fun <T : View> findView(viewId: Int): T {
            return itemView.findViewOften(viewId)
        }
    }

    constructor(items: ArrayList<T>) : this() {
        this.items = items
    }

    override fun getItemCount(): Int = items?.size ?: 0

    protected var listener1: ((v: View, i: Int) -> Unit)? = null

    fun setOnClickListener(listener: (v: View, i: Int) -> Unit) {
        this.listener1 = listener
    }

    protected var listener2: ((T?) -> Unit)? = null

    fun setOnClickListener(listener: (T?) -> Unit) {
        this.listener2 = listener
    }

}