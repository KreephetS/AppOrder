package com.example.patchanan.apporder.common.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductModel(
        @SerializedName("nameProduct") var nameProduct: String? = null,
        @SerializedName("numProduct") var numProduct: Int = 0,
        @SerializedName("condition") var condition: String? = "-",
        @SerializedName("price") var price: Int? = null,
        @SerializedName("img") var img: String? = null,
        @SerializedName("status") var status: Boolean = false
) : Parcelable {
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
        id = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameProduct)
        parcel.writeInt(numProduct)
        parcel.writeString(condition)
        parcel.writeValue(price)
        parcel.writeString(img)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }


}