package com.example.onsopt_1st.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.ProjectData

class ProjectViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.project_title)
    private val date : TextView = itemView.findViewById(R.id.project_date)
    private val content : TextView = itemView.findViewById(R.id.project_content)

    fun onBind(data : ProjectData) {
        title.text=data.title
        date.text=data.date
        content.text=data.content
    }
}