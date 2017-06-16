package com.martintong.kotlindemo.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/15
🐶
🐶 @apiNote
 */
abstract class LazyLoadFragment : Fragment() {

    var isViewCreated = false
    var isInit = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isInit) return
        if (isVisibleToUser && isViewCreated && !isInit) {
            loadData()
            isInit = true
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        isViewCreated = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (userVisibleHint) {
            loadData()
            isInit = true
        }
    }

    open fun initViews(){}

    abstract fun loadData()

    override fun onDestroyView() {
        super.onDestroyView()
        isInit = false
        isViewCreated = false
    }

}