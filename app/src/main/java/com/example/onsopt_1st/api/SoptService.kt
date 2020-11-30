package com.example.onsopt_1st.api

import com.example.onsopt_1st.data.RequestLoginData
import com.example.onsopt_1st.data.RequestSignupData
import com.example.onsopt_1st.data.ResponseSignData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService {
    @Headers("Content-Type:application/json")
    @POST ("/users/signup")
    fun postSignup(
        @Body body : RequestSignupData
    ) : Call<ResponseSignData>

    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseSignData>
}