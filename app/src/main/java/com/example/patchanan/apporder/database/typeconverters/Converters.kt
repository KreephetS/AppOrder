package com.example.patchanan.apporder.database.typeconverters

import android.arch.persistence.room.TypeConverter
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.common.model.ProductModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun stringToListProduct(json: String): MutableList<ProductModel> {
        return if (json == "null") {
            mutableListOf()
        } else {
            val gson = Gson()
            val type = object : TypeToken<MutableList<ProductModel>>() {}.type
            gson.fromJson(json, type)
        }
    }

    @TypeConverter
    fun listProductToString(productLists: MutableList<ProductModel>?): String {
        return if (productLists == null) {
            "null"
        } else {
            val gson = Gson()
            val type = object : TypeToken<MutableList<ProductModel>>() {}.type
            gson.toJson(productLists, type)
        }
    }
}