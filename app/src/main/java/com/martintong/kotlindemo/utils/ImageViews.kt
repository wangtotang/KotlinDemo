package com.martintong.kotlindemo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.martintong.kotlindemo.R

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/14
🐶
🐶 @apiNote
 */

inline fun ImageView.loadUrl(context: Context, url: String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_test).into(this)
}