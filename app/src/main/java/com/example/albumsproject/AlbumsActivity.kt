package com.example.albumsproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsproject.adapter.AlbumAdapter
import com.example.albumsproject.model.Album

class AlbumsActivity : AppCompatActivity(), AlbumClickListener {
    private lateinit var mAlbumsViewModel: AlbumsViewModel
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        initAlbumData()
        initViewModel()
    }

    private fun initViewModel() {
        mAlbumsViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        mAlbumsViewModel.getLiveDataObserver().observe(this, object : Observer<List<Album>> {
            override fun onChanged(t: List<Album>?) {
                if (t !== null) {
                    albumAdapter.setUpdateData(t)
                    albumAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@AlbumsActivity,
                        "Error in getting the data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        mAlbumsViewModel.makeApicall()
    }

    private fun initAlbumData() {
        val albumList = findViewById<RecyclerView>(R.id.albumList)
        albumList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        albumAdapter = AlbumAdapter(this)
        albumList.adapter = albumAdapter
    }

    override fun onAlbumItemClick(view: View, positon: Int) {
        when (view.id) {
            R.id.ivImage -> {
                Toast.makeText(this@AlbumsActivity, "item Clicked  " + positon, Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
}
