package com.martintong.kotlindemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.martintong.kotlindemo.module.pictures.MoviesFragment
import com.martintong.kotlindemo.module.pictures.PicturesFragment
import com.martintong.kotlindemo.module.pictures.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_picture -> vp_main.currentItem = 0
                R.id.menu_bnv_movie -> vp_main.currentItem = 1
                R.id.menu_bnv_setting -> vp_main.currentItem = 2
            }
            false
        }
        //bnv_main.disableShiftMode()
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
        setupViewPager()
    }

    /**
     * 初始化ViewPager
     */
    private fun setupViewPager() {
        vp_main.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

            val fragments = arrayListOf(PicturesFragment(), MoviesFragment(), SettingsFragment())

            override fun getItem(index: Int): Fragment {
                return fragments[index]
            }

            override fun getCount(): Int {
                return fragments.size
            }

        }

    }
}

/**
 * BottomNavigationView扩展方法，禁止转移模式
 * 在大于4个item时，默认启动转移模式
 */
@SuppressLint("RestrictedApi")
private fun BottomNavigationView.disableShiftMode() {
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
