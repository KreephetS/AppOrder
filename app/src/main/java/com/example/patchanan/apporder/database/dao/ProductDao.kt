package com.example.patchanan.apporder.database.dao

import android.arch.persistence.room.*
import com.example.patchanan.apporder.common.model.ProductModel
import io.reactivex.Flowable

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(product: ProductModel)

    @Update()
    fun updateProduct(orderModel: ProductModel)

    @Delete
    fun deleteProduct(orderModel: ProductModel)

    @Query("SELECT * FROM `product`")
    fun getProductAll(): Flowable<MutableList<ProductModel>>

    @Query("DELETE FROM `product` WHERE id = :id")
    fun deleteByProductId(id: Long)

    @Query("DELETE FROM `product`")
    fun deleteAllProduct()

//    @Query("UPDATE `order` SET price = :price  WHERE id = :oId")
//    fun updateByUserId(oId: Long, price: Int)
}