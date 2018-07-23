package com.example.patchanan.apporder.shop.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.example.patchanan.apporder.common.model.ProductModel
import com.example.patchanan.apporder.database.DBHelper
import com.example.patchanan.apporder.manager.HttpManager
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopViewModel : ViewModel() {

    fun insertProduct(context: Context, body: ProductModel) {
        body.let {
            Completable.fromCallable { DBHelper.getAppDatabase(context).productDAO().insertProduct(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.wtf("insert product", "insert completed")

                    }, { Log.wtf("insert product", "Insert failed with ${it.message}") })
        }
    }

    fun deleteProduct(context: Context, id: Long, callBack: () -> Unit) {
        Completable.fromCallable { DBHelper.getAppDatabase(context).productDAO().deleteByProductId(id) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.wtf("OrderPFirst", "del completed")
                    callBack()
                }, { Log.wtf("OrderPFirst", "del failed with ${it.message}") })

    }

    fun updateProduct(context: Context, product: ProductModel) {
        Completable.fromCallable { DBHelper.getAppDatabase(context).productDAO().updateProduct(product) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.wtf("OrderPFirst", "update completed")
                }, {
                    Log.wtf("OrderPFirst", "update failed with ${it.message}")
                })
    }

    fun getProductList(context: Context): MutableLiveData<MutableList<ProductModel>> {
        var productModel = MutableLiveData<MutableList<ProductModel>>()
        Log.wtf("getProductList", "get")
        HttpManager(context) {
            Log.wtf("HttpManager", "get 123")
        }.service.getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    productModel.value = it.body()

                }, {
                    Log.wtf("getProduct", "get fai ${it.message}")
                })

        return productModel
    }
}
