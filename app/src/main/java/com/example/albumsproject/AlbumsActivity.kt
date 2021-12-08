package com.example.albumsproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsproject.adapter.AlbumAdapter
import com.example.albumsproject.model.Album
import com.google.gson.Gson


class AlbumsActivity : AppCompatActivity(), AlbumClickListener {
    private lateinit var mAlbumsViewModel: AlbumsViewModel
    private lateinit var albumAdapter: AlbumAdapter
    private var listData: List<Album>? = null

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc. This method also provides you with a
     * Bundle containing the activity's previously frozen state, if there was one.
     *
     * @param savedInstanceState-{@link Bundle}.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        initAlbumData()
        initObservers()
    }

    /**
     * This method initializes the Observers.
     */
    private fun initObservers() {
        mAlbumsViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        mAlbumsViewModel.getLiveDataObserver().observe(this, object : Observer<List<Album>> {
            override fun onChanged(t: List<Album>?) {
                if (t !== null) {
                    listData = t
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
            R.id.constraintlayout -> {
                val intent = Intent(this, AlbumDetailActivity::class.java)
                val bundle = Bundle()
                val listAsString = Gson().toJson(listData?.get(positon))
                bundle.putString("data", listAsString)
                intent.putExtras(bundle);
                this.startActivity(intent);
            }
        }
    }
}
