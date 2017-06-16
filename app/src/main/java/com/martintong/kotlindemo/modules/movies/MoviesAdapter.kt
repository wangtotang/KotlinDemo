package com.martintong.kotlindemo.modules.pictures

import android.content.Context
import android.view.ViewGroup
import com.martintong.kotlindemo.R
import com.martintong.kotlindemo.utils.inflate
import com.martintong.kotlindemo.utils.loadUrl
import com.martintong.kotlindemo.widgets.BaseRecyclerViewAdapter
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/13
🐶
🐶 @apiNote
 */

data class LittleMovie(val title: String, val thumbnail: String, val url: String)

class MoviesAdapter(val mContext: Context, items: ArrayList<LittleMovie>) : BaseRecyclerViewAdapter<LittleMovie>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.adapter_movies))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.findView<JCVideoPlayerStandard>(R.id.video_movies)){
            setUp(items!![position].url, JCVideoPlayer.SCREEN_LAYOUT_LIST, items!![position].title)
            thumbImageView.loadUrl(mContext, items!![position].thumbnail)
        }
    }

}