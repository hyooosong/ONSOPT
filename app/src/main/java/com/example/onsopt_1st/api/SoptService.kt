package com.example.onsopt_1st.api

import android.text.Html
import com.example.onsopt_1st.data.*
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/api/users")
    fun loadUserList(
            @Query("page") page: Int
    ) : Call<ResponseUserData>

    @Headers("Authorization: KakaoAK 0bc50ac9154a008baf92bbf2af8f32df")
    @GET("/v2/search/web")
    fun kakaoSearch (
        @Query("query") query : String,
        @Query("sort") sort : String? = "accuracy",
        @Query("page") page : Int? = 1,
        @Query("size") size : Int? = 10
    ) : Call<ResponseSearchData>
}