package com.example.onsopt_1st

import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class homeViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.item_title)
    private val subTitle : TextView = itemView.findViewById(R.id.item_subTitle)

    fun onBind(data : homeData) {
        title.text=data.title
        subTitle.text=data.subTitle
    }

}
