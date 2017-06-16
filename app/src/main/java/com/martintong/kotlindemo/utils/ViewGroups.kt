package com.martintong.kotlindemo.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/14
🐶
🐶 @apiNote
 */

inline fun ViewGroup.inflate(id: Int): View = LayoutInflater.from(this.context).inflate(id, this, false)

inline fun ViewGroup.inflateWithoutRoot(id: Int): View = LayoutInflater.from(this.context).inflate(id, null)
