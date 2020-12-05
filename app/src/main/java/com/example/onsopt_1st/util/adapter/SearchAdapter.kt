package com.example.onsopt_1st.util.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.SearchData
import com.example.onsopt_1st.util.viewholder.SearchViewHolder

class SearchAdapter (private val context: Context) : RecyclerView.Adapter<SearchViewHolder>() {
    var data = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(data[position],context)
    }
}