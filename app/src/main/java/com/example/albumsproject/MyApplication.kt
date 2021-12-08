package com.example.albumsproject

import android.app.Application
import com.example.albumsproject.di.DaggerRetroComponent
import com.example.albumsproject.di.RetroComponent
import com.example.albumsproject.di.RetroModule

class MyApplication : Application() {

    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()
        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent {
        return retroComponent
    }
}