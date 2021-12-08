package com.example.albumsproject

import android.view.View

interface AlbumClickListener {
    fun onAlbumItemClick(view: View, positon: Int)
}