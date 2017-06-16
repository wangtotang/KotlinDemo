package com.martintong.kotlindemo.utils

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/14
🐶
🐶 @apiNote
 */

/**
 * BottomNavigationView扩展方法，禁止转移模式
 * 在大于4个item时，默认启动转移模式
 */
@SuppressLint("RestrictedApi")
inline fun BottomNavigationView.disableShiftMode() {
    val view = getChildAt(0) as BottomNavigationMenuView
    val field = view.javaClass.getDeclaredField("mShiftingMode")
    field.isAccessible = true
    field.setBoolean(view, false)
    field.isAccessible = false
    (0 until view.childCount).map { view.getChildAt(it) as BottomNavigationItemView }
            .forEach {
                it.setShiftingMode(false)
                it.setChecked(it.itemData.isChecked)
            }
}