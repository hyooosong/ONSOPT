package com.example.onsopt_1st.data

data class RequestSearchData(
        val query : String,
        val sort : String,
        val page : Int,
        val size : Int
)