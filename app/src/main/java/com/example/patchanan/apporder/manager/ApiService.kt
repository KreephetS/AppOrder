package com.example.patchanan.apporder.manager

import android.arch.lifecycle.MutableLiveData
import com.example.patchanan.apporder.common.model.ProductModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("listShop")
    fun getProduct(): Single<Response<MutableList<ProductModel>>>
}