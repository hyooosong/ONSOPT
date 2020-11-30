package com.example.onsopt_1st.util

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.ui.ProfileFragment
import com.example.onsopt_1st.R
import com.example.onsopt_1st.ui.RCVFragment
import com.example.onsopt_1st.ui.SettingFragment
import com.example.onsopt_1st.data.homeData

class homeViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.item_title)
    private val subTitle : TextView = itemView.findViewById(R.id.item_subTitle)

    fun onBind(data : homeData) {
        title.text=data.title
        subTitle.text=data.subTitle
    }
}