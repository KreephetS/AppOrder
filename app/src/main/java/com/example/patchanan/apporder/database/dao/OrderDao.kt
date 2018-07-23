package com.example.patchanan.apporder.database.dao

import android.arch.persistence.room.*

import com.example.patchanan.apporder.common.model.OrderModel
import io.reactivex.Flowable

@Dao
interface OrderDao {

    @Insert
    fun insertOrder(orderModel: OrderModel)

    @Update()
    fun updateOrder(orderModel: OrderModel)

    @Delete
    fun deleteOrder(orderModel: OrderModel)

    @Query("SELECT * FROM `order`")
    fun getOrderAll(): Flowable<MutableList<OrderModel>>

    @Query("DELETE FROM `order` WHERE idOrder = :userId")
    fun deleteByUserId(userId: Long)

//    @Query("UPDATE `order` SET price = :price  WHERE id = :oId")
//    fun updateByUserId(oId: Long, price: Int)
}