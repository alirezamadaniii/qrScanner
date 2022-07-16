package com.example.qrscanner

import com.example.qrscanner.model.Class
import com.example.qrscanner.model.CourseList
import com.example.qrscanner.model.Data
import com.example.qrscanner.model.LogIn
import com.example.qrscanner.model.Resulte
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("check.php")
    suspend fun responseUser(@Query("code") code:String, @Query("class") id:String):Response<Resulte>

    @GET("loginApi.php")
    suspend fun loginAdmin (@Query("user") username:String, @Query("password") password:String):Response<LogIn>

    @GET("listAPI.php")
    suspend fun courseList (@Query("type_list") type:String, @Query("user_id") userId:String):Response<CourseList>

    @GET("listAPI.php")
    suspend fun classList (@Query("type_list") type:String, @Query("course_id") courseId:String):Response<Class>
}