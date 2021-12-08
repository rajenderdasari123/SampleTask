package com.example.albumsproject.model

data class Album(var list: List<AlbumData>)
data class AlbumData(
    val albumId: String?,
    val id: String?,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)
