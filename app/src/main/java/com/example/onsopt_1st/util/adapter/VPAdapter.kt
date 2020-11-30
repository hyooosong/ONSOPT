package com.example.onsopt_1st.util.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.onsopt_1st.ui.ProfileFragment
import com.example.onsopt_1st.ui.RCVFragment
import com.example.onsopt_1st.ui.SettingFragment


class VPAdapter (fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> ProfileFragment()
        1 -> RCVFragment()
        2 -> SettingFragment()

        else -> throw IllegalStateException("Unexpected position $position")
    }
    override fun getCount(): Int = 3

}