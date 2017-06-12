package com.martintong.kotlindemo.module.pictures

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martintong.kotlindemo.R

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/12
🐶
🐶 @apiNote
 */
class MoviesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_movies, container, false)
    }

}