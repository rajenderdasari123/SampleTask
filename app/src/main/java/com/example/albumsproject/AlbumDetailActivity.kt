package com.example.albumsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albumsproject.model.Album
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_albumdetail.*

class AlbumDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumdetail)

        val bundle = intent.extras
        val str: String = bundle?.get("data").toString()
        val albumData = Gson().fromJson(str, Album::class.java)
        Picasso.get().load(albumData.url).into(img_album)
    }

}