package com.example.onsopt_1st

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_profile_vp.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.properties.Delegates

class ProfileFragment : Fragment(){
    private lateinit var prof_viewpagerAdapter: ProfileVPAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index by Delegates.notNull<Int>()

        prof_viewpagerAdapter = ProfileVPAdapter(childFragmentManager)
        prof_viewpagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment()
        )

        profileVP.adapter = prof_viewpagerAdapter
        tabLayout.setupWithViewPager(profileVP)

        tabLayout.apply {
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
    }
}