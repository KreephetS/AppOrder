package com.example.patchanan.apporder.common.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "order")
data class OrderModel(
        @SerializedName("orderBy") var orderBy: String? = null,
        @SerializedName("order") var order: MutableList<ProductModel> = mutableListOf(),
        @SerializedName("price") var price: Int? = null,
        @SerializedName("date") var date: String? = null,
        @SerializedName("status") var status: Boolean = false
) : Parcelable {
    @SerializedName("idOrder")
    @PrimaryKey(autoGenerate = true)
    var idOrder: Long = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("order"),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
        idOrder = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(orderBy)
        parcel.writeValue(price)
        parcel.writeString(date)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeLong(idOrder)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }

}
