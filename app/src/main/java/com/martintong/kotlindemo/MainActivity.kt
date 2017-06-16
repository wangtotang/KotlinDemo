package com.martintong.kotlindemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.martintong.kotlindemo.modules.pictures.MoviesFragment
import com.martintong.kotlindemo.modules.pictures.PicturesFragment
import com.martintong.kotlindemo.modules.pictures.SettingsFragment
import com.martintong.kotlindemo.modules.pictures.VideoState
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val movieFragment by lazy {
        MoviesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_picture -> vp_main.setCurrentItem(0, false)
                R.id.menu_bnv_movie -> vp_main.setCurrentItem(1, false)
                R.id.menu_bnv_setting -> vp_main.setCurrentItem(2, false)
            }
            false
        }
        //bnv_main.disableShiftMode()
        setupViewPager()
    }

    /**
     * 初始化ViewPager
     */
    private fun setupViewPager() {
        vp_main.offscreenPageLimit = 3
        vp_main.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            var menuItem: MenuItem? = null

            override fun onPageSelected(position: Int) {
                if (menuItem != null)
                    menuItem?.isChecked = false
                else
                    bnv_main.menu.getItem(0).isChecked = false

                menuItem = bnv_main.menu.getItem(position)
                menuItem?.isChecked = true
            }
        })
        vp_main.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

            val fragments = arrayListOf(PicturesFragment(), movieFragment, SettingsFragment())

            override fun getItem(index: Int): Fragment {
                return fragments[index]
            }

            override fun getCount(): Int {
                return fragments.size
            }

        }

    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        if (movieFragment.userVisibleHint){
            movieFragment.videoState = VideoState.RELEASE
        }
        super.onPause()
    }

}
