package com.example.albumsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.albumsproject.model.Album

class AlbumsActivity : AppCompatActivity() {
    private lateinit var mAlbumsViewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        initViewModel()
    }

    private fun initViewModel() {
        mAlbumsViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        mAlbumsViewModel.getLiveDataObserver().observe(this, object : Observer<Album>{
            override fun onChanged(t: Album?) {
                if (t !== null) {
                    Log.d("Result","")
                  /*  listData = t.items
                    Glide.with(img_product).load(listData?.get(0)!!.owner?.avatar_url)
                        .apply(RequestOptions.centerCropTransform()).into(img_product)
                    variantProductAdaptor.setUpdateData(t.items)
                    variantProductAdaptor.notifyDataSetChanged()*/
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


}