package com.example.patchanan.apporder.database

import android.arch.persistence.room.*
import android.content.Context
import com.example.patchanan.apporder.database.dao.OrderDao
import com.example.patchanan.apporder.database.dao.ProductDao
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.common.model.ProductModel
import com.example.patchanan.apporder.database.typeconverters.Converters


@Database(entities = [(OrderModel::class), (ProductModel::class)], version = 1)
@TypeConverters(Converters::class)
abstract class DBHelper : RoomDatabase() {

    companion object {
        fun getAppDatabase(context: Context): DBHelper = Room.databaseBuilder(context, DBHelper::class.java, "db_app").build()
    }

    abstract fun orderDAO(): OrderDao
    abstract fun productDAO(): ProductDao
}