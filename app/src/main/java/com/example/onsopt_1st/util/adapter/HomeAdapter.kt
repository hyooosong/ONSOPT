package com.example.onsopt_1st.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.ui.DetailActivity
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.homeData
import java.util.*

class homeAdapter (private val context: Context) : RecyclerView.Adapter<homeViewHolder>() {

    var data = mutableListOf<homeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return homeViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data[position].title)
            intent.putExtra("subTitle", data[position].subTitle)
            intent.putExtra("detail", data[position].detail)
            intent.putExtra("date", data[position].date)
            startActivity(holder.itemView.context, intent, null)
        }
    }

    fun onItemMoved(from: Int, to: Int) {
        if(from<to) {
            for(i in from until to)
                Collections.swap(data,i,i+1)
        } else {
            for(i in from downTo to+1)
                Collections.swap(data,i,i-1)
        }
        notifyItemMoved(from, to)
        notifyDataSetChanged()
    }

    fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}
