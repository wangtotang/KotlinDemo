package com.martintong.kotlindemo.utils

import android.support.v4.app.Fragment
import android.widget.Toast


/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/14
🐶
🐶 @apiNote
 */

inline fun Fragment.toast(message: CharSequence?): Unit =
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()