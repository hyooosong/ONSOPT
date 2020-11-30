package com.example.onsopt_1st.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.onsopt_1st.R


class SettingFragment : Fragment() {
    val part = "default"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView: TextView = view.findViewById(R.id.thirdFM_txt)
        textView.text= part
        return view
    }

}