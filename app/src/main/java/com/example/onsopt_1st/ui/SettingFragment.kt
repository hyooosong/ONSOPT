package com.example.onsopt_1st.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.onsopt_1st.R
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_dummyUser.setOnClickListener {
            childFragmentManager.beginTransaction().apply{
                add (R.id.frameLayout3, UserListFragment()).commit()
            }
        }

        btn_kakaoAPI.setOnClickListener {
            childFragmentManager.beginTransaction().apply{
                add (R.id.frameLayout3, SearchFragment()).commit()
            }
        }
    }
}