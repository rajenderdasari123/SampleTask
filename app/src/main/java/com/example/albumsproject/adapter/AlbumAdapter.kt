package com.example.albumsproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsproject.AlbumClickListener
import com.example.albumsproject.R
import com.example.albumsproject.model.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(private val listener: AlbumClickListener) :
    RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {

    private var albumData: List<Album>? = null

    fun setUpdateData(albumData: List<Album>) {
        this.albumData = albumData
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idAlbum = view.id_album
        val ivImage = view.ivImage
        val tvTitle = view.tvTitle
        fun bind(data: Album) {
            idAlbum.setText(data.id)
            tvTitle.setText(data.title)
            Picasso.get().load(data.thumbnailUrl).into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.MyViewHolder, position: Int) {
        holder.bind(albumData?.get(position)!!)
        holder.ivImage.setOnClickListener {
            listener.onAlbumItemClick(holder.ivImage, position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        if (albumData == null) return 0
        else return albumData?.size!!
    }

}