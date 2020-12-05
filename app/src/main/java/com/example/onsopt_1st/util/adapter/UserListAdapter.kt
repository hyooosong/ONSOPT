package com.example.onsopt_1st.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.ResponseUserData
import com.example.onsopt_1st.data.UserListData
import com.example.onsopt_1st.util.viewholder.UserListViewHolder

class UserListAdapter  (private val context: Context) : RecyclerView.Adapter<UserListViewHolder>() {
    var data = mutableListOf<UserListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_userlist, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}