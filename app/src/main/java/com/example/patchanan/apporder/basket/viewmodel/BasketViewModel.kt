package com.example.patchanan.apporder.basket.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.example.patchanan.apporder.common.model.OrderModel

import com.example.patchanan.apporder.database.DBHelper
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BasketViewModel : ViewModel() {
    fun insertOrder(context: Context, body: OrderModel) {
        body.let {
            Completable.fromCallable { DBHelper.getAppDatabase(context).orderDAO().insertOrder(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.wtf("insert order", "insert completed")

                    }, { Log.wtf("insert order", "Insert failed with ${it.message}") })
        }
    }
}