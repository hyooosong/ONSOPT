package com.example.onsopt_1st.util.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.ProjectData
import com.example.onsopt_1st.util.ProjectViewHolder


class ProjectAdapter (private val context: Context) : RecyclerView.Adapter<ProjectViewHolder>() {

    var data = mutableListOf<ProjectData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}