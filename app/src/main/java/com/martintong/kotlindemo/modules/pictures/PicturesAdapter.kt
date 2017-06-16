package com.martintong.kotlindemo.modules.pictures

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.martintong.kotlindemo.R
import com.martintong.kotlindemo.utils.inflate
import com.martintong.kotlindemo.utils.loadUrl
import kotlinx.android.synthetic.main.adapter_pictures.view.*

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/13
🐶
🐶 @apiNote
 */
class PicturesAdapter(val mContext: Context, val items: ArrayList<String>) : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {

    private var listener: ((String) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.iv_picture.loadUrl(mContext, items[position])
        holder.itemView.setOnClickListener { listener?.invoke(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.adapter_pictures))

    fun setOnClickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_picture: ImageView by lazy {
            itemView.iv_picture
        }
    }

}