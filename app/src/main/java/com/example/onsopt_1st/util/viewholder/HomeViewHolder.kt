package com.example.onsopt_1st.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.homeData

class homeViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.userlist_firstname)
    private val subTitle : TextView = itemView.findViewById(R.id.userlist_email)

    fun onBind(data : homeData) {
        title.text=data.title
        subTitle.text=data.subTitle
    }
}