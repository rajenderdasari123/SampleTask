package com.example.albumsproject.di

import com.example.albumsproject.model.Album
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("photos")
    fun getDataFromAPI(): Call<List<Album?>>

}