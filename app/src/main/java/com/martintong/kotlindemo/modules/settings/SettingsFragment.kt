package com.martintong.kotlindemo.modules.pictures

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.martintong.kotlindemo.App
import com.martintong.kotlindemo.R
import com.martintong.kotlindemo.app.LazyLoadFragment
import com.martintong.kotlindemo.app.Preference
import com.martintong.kotlindemo.utils.findViewOften
import com.martintong.kotlindemo.utils.inflateWithoutRoot
import com.martintong.kotlindemo.utils.loadUrl
import com.martintong.kotlindemo.widgets.ScaleCircleNavigator
import kotlinx.android.synthetic.main.fragment_settings.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import org.jetbrains.anko.runOnUiThread
import java.lang.ref.WeakReference
import java.util.*
import kotlin.concurrent.timerTask

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/12
🐶
🐶 @apiNote
 */

class Scope(val context: Context) {
    var scope: Int by Preference(context)
    var name: String by Preference(context)
}

class SettingsFragment : LazyLoadFragment() {

    var i = 0
    var mTask: TimerTask? = null
    val timer = Timer()
    val scope = Scope(App.instance)
    var lol: Int by Preference(App.instance, "test")

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_settings, container, false)
    }

    override fun loadData() {
        vp_settings_ad.adapter = object : PagerAdapter() {
            val icon = arrayListOf(
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497432790922&di=1303ca009428bb5f8baaed55c44bbf78&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F6a600c338744ebf8d1972717d3f9d72a6059a730.jpg",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2077920764,3543930657&fm=26&gp=0.jpg",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497433497596&di=599036b9e50714d16dc5642ca30363a0&imgtype=0&src=http%3A%2F%2Fnews.jsyks.com%2Fphoto%2Fimg1c.xgo-img.com.cn%2Fpics%2F1712%2F1711568.jpg"
            )

            val viewList = arrayListOf<WeakReference<View>>()

            override fun isViewFromObject(view: View?, obj: Any?): Boolean = view === obj

            override fun getCount(): Int = icon.size

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val convertView =
                        if (viewList.size > 0)
                            viewList.removeAt(0).get() ?: container.inflateWithoutRoot(R.layout.adapter_settings_ad)
                        else
                            container.inflateWithoutRoot(R.layout.adapter_settings_ad)
                convertView.findViewOften<ImageView>(R.id.iv_ad).loadUrl(context, icon[position])
                container.addView(convertView)
                return convertView
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                val view = `object` as View
                container.removeView(view)
                viewList.add(WeakReference(view))
            }

        }
        val circleNavigator = ScaleCircleNavigator(context)
        with(circleNavigator) {
            setCircleCount(vp_settings_ad.adapter.count)
            setNormalCircleColor(Color.LTGRAY)
            setSelectedCircleColor(resources.getColor(R.color.colorPrimary))
            setCircleClickListener(vp_settings_ad::setCurrentItem)
        }
        mi_settings_ad.navigator = circleNavigator
        ViewPagerHelper.bind(mi_settings_ad, vp_settings_ad)
        Preference<Int>(App.instance, "test").delete("lol")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mTask = timerTask { context.runOnUiThread { vp_settings_ad.currentItem = i++ % 3 } }
            timer.schedule(mTask, 0, 3000)
        } else {
            mTask?.cancel()
            timer.purge()
        }
    }

}