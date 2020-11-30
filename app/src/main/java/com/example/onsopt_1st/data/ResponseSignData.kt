package com.example.onsopt_1st.data

data class ResponseSignData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data : Data
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}