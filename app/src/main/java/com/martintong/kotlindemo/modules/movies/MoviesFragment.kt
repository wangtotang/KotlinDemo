package com.martintong.kotlindemo.modules.pictures

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martintong.kotlindemo.R
import com.martintong.kotlindemo.app.LazyLoadFragment
import com.martintong.kotlindemo.utils.inflate
import com.martintong.kotlindemo.utils.setLinearSpaceDecoration
import com.martintong.kotlindemo.utils.toast
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlin.properties.Delegates

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/12
🐶
🐶 @apiNote
 */

enum class VideoState { STOP, PLAY, RELEASE }

class MoviesFragment : LazyLoadFragment() {

    var videoState by Delegates.observable(VideoState.STOP) { default, old, new ->
        when (new) {
            VideoState.STOP -> JCVideoPlayer.backPress()
            VideoState.RELEASE -> JCVideoPlayer.releaseAllVideos()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_movies)
    }

    override fun loadData() {
        with(rv_movies){
            setHasFixedSize(true)
            setLinearSpaceDecoration(16)
            layoutManager = LinearLayoutManager(context)
            adapter = MoviesAdapter(context, arrayListOf(
                    LittleMovie("123", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2077920764,3543930657&fm=26&gp=0.jpg", "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4")))
            (adapter as MoviesAdapter).setOnClickListener { item ->
                toast(item?.title)
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser) {
            videoState = VideoState.RELEASE
        }
    }

}
