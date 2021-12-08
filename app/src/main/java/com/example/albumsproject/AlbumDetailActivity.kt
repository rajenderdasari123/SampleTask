package com.example.albumsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albumsproject.constant.AlbumConstant
import com.example.albumsproject.model.Album
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_albumdetail.*

class AlbumDetailActivity : AppCompatActivity() {

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc. This method also provides you with a
     * Bundle containing the activity's previously frozen state, if there was one.
     *
     * @param savedInstanceState-{@link Bundle}.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumdetail)

        val bundle = intent.extras
        val str: String = bundle?.get(AlbumConstant.LIST_ALBUM).toString()
        val albumData = Gson().fromJson(str, Album::class.java)
        Picasso.get().load(albumData.url).into(img_album)
    }

}