package com.example.onsopt_1st.util.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.onsopt_1st.ui.OtherFragment
import com.example.onsopt_1st.ui.ProjectFragment

class ProfileVPAdapter (fm : FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> ProjectFragment()
        1 -> OtherFragment()

        else -> throw IllegalStateException("Unexpected position $position")
    }
    override fun getCount(): Int = 2
}