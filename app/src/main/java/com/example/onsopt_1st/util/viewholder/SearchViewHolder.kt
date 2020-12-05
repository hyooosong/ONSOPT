package com.example.onsopt_1st.util.viewholder

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.SearchData
import org.jetbrains.annotations.NotNull

class SearchViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.search_title)
    private val url = itemView.findViewById<TextView>(R.id.search_url)
    private val date = itemView.findViewById<TextView>(R.id.search_date)
    private val content = itemView.findViewById<TextView>(R.id.search_content)

    @RequiresApi(Build.VERSION_CODES.O)
    fun onBind(@NotNull data: SearchData, context : Context) {
        title.text = data.title
        url.text = data.url
        content.text = data.contents
        date.text = data.datetime

        itemView.setOnClickListener {
            var url = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
            context.startActivity(url)
        }
    }
}