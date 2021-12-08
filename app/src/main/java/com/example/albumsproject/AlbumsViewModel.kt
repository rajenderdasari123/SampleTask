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
    private lateinit var liveDataList: MutableLiveData<List<Album>>

    init {
        (application as MyApplication).getRetroComponent().inject(this)
        liveDataList = MutableLiveData<List<Album>>()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Album>> {
        return liveDataList
    }


    fun makeApicall() {
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<List<Album?>> = mService.getDataFromAPI()
            call.enqueue(object : Callback<List<Album?>> {
                override fun onResponse(
                    call: Call<List<Album?>>,
                    response: Response<List<Album?>>
                ) {
                    if (response.isSuccessful) {
                        liveDataList.postValue(response.body() as List<Album>?)
                    } else {
                        liveDataList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<Album?>>, t: Throwable) {
                    liveDataList.postValue(null)
                }

            })

        }
    }
}