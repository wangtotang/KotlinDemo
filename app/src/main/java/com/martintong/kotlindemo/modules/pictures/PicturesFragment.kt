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
import kotlinx.android.synthetic.main.fragment_pictures.*


/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/12
🐶
🐶 @apiNote
 */
class PicturesFragment : LazyLoadFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_pictures)
    }

    override fun loadData() {
        with(rv_pictures) {
            setHasFixedSize(true)
            setLinearSpaceDecoration(16)
            layoutManager = LinearLayoutManager(rv_pictures.context)
            adapter = PicturesAdapter(context, arrayListOf(
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497432790922&di=1303ca009428bb5f8baaed55c44bbf78&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F6a600c338744ebf8d1972717d3f9d72a6059a730.jpg",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2077920764,3543930657&fm=26&gp=0.jpg",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497433497596&di=599036b9e50714d16dc5642ca30363a0&imgtype=0&src=http%3A%2F%2Fnews.jsyks.com%2Fphoto%2Fimg1c.xgo-img.com.cn%2Fpics%2F1712%2F1711568.jpg"
            ))
            (adapter as PicturesAdapter).setOnClickListener {
                toast(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}