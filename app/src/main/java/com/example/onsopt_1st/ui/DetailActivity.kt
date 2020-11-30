package com.example.onsopt_1st.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onsopt_1st.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        this.detailTitle.text = intent.getStringExtra("title")
        this.detailSubTitle.text = intent.getStringExtra("subTitle")
        this.detail.text = intent.getStringExtra("detail")
        this.date.text = intent.getStringExtra("date")
    }
}