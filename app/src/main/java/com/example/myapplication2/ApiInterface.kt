package com.example.myapplication2

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
     @GET("posts")
     fun getData() : Call <List<ApplicationDataItem>>


}