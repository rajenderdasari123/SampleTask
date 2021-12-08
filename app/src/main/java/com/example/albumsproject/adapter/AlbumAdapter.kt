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

/**
 * Adapter to populate albumdata information list
 */
class AlbumAdapter(private val listener: AlbumClickListener) :
    RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {

    private var albumData: List<Album>? = null

    /**
     * sets Adapter Data.
     *
     * @param albumData {@link List<Album>}
     */
    fun setUpdateData(albumData: List<Album>) {
        this.albumData = albumData
    }

    /**
     * MyView holder sets the details for its view
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idAlbum = view.id_album
        val ivImage = view.ivImage
        val tvTitle = view.tvTitle
        val constraintlayout = view.constraintlayout
        fun bind(data: Album) {
            idAlbum.setText(data.id)
            tvTitle.setText(data.title)
            Picasso.get().load(data.thumbnailUrl).into(ivImage)
        }
    }

    /**
     * This method calls onCreateViewHolder(ViewGroup, int) to create a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
     *
     * @param parent   {@link ViewGroup}
     * @param viewType int of View type.
     * @return {@link AlbumAdapter.MyViewHolder}.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(view)
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the RecyclerView.ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView.
     *
     * @param holder   {@link AlbumAdapter.MyViewHolder}
     * @param position int
     */
    override fun onBindViewHolder(holder: AlbumAdapter.MyViewHolder, position: Int) {
        holder.bind(albumData?.get(position)!!)
        holder.constraintlayout.setOnClickListener {
            listener.onAlbumItemClick(holder.constraintlayout, position)
            notifyDataSetChanged()
        }
    }

    /**
     * This method Returns the total number of items in the data set held by the adapter.
     *
     * @return int.
     */
    override fun getItemCount(): Int {
        if (albumData == null) return 0
        else return albumData?.size!!
    }

}