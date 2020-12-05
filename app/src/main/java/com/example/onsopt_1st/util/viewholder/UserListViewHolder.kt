package com.example.onsopt_1st.util.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onsopt_1st.R
import com.example.onsopt_1st.data.ResponseUserData
import com.example.onsopt_1st.data.UserListData

class UserListViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    private val image = itemView.findViewById<ImageView>(R.id.userlist_image)
    private val firstname = itemView.findViewById<TextView>(R.id.userlist_firstname)
    private val lastname = itemView.findViewById<TextView>(R.id.userlist_lastname)
    private val email = itemView.findViewById<TextView>(R.id.userlist_email)

    fun onBind(data : UserListData) {
        firstname.text=data.firstname
        lastname.text=data.lastname
        email.text=data.email
        Glide.with(itemView).load(data.Image).into(image)
    }
}