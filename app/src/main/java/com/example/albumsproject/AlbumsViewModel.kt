package com.example.albumsproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.albumsproject.di.RetroServiceInterface
import com.example.albumsproject.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mService: RetroServiceInterface
    private lateinit var liveDataList: MutableLiveData<Album>

    init {
        (application as MyApplication).getRetroComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<Album> {
        return liveDataList
    }


    fun makeApicall() {
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<Album?> = mService.getDataFromAPI()
            call.enqueue(object : Callback<Album?> {
                override fun onResponse(call: Call<Album?>, response: Response<Album?>) {
                    if (response.isSuccessful) {
                        liveDataList.postValue(response.body())
                    } else {
                        liveDataList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Album?>, t: Throwable) {
                    liveDataList.postValue(null)
                }

            })

        }
    }
}