package com.example.onsopt_1st.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.onsopt_1st.R
import com.example.onsopt_1st.util.adapter.VPAdapter
import kotlinx.android.synthetic.main.activity_profile_vp.*
import kotlin.properties.Delegates

class ProfileVPActivity : AppCompatActivity() {
    private lateinit var viewpagerAdapter : VPAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_vp)

        viewpagerAdapter = VPAdapter(supportFragmentManager)
        viewpagerAdapter.fragments= listOf(
            ProfileFragment(),
            RCVFragment(),
            SettingFragment()
        )

        viewpager.adapter = viewpagerAdapter

        // 뷰페이저 슬라이드 시 하단 탭 변경
        viewpager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottom_navi.menu.getItem(position).isChecked = true
            }
        })
        //하단 탭 클릭 시 뷰페이저 변경
        bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()

            when(it.itemId) {
                R.id.account -> index = 0
                R.id.portfolio -> index = 1
                R.id.setting -> index = 2
            }
            viewpager.currentItem = index
            true
        }


    }
}