package com.example.albumsproject.di

import com.example.albumsproject.AlbumsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(albumsViewModel: AlbumsViewModel)
}