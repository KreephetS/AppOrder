package com.example.patchanan.apporder.shop.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.common.model.ProductModel
import kotlinx.android.synthetic.main.item_row_product.view.*

class ShopViewHolder(itemView: View, val callBack: (ProductModel) -> Unit) : RecyclerView.ViewHolder(itemView.rootView) {

    private lateinit var listener: ShopViewHolder.productToOrder

    init {
        if (itemView.context is ShopViewHolder.productToOrder) {
            listener = itemView.context as ShopViewHolder.productToOrder
        }
    }

    fun bindItem(product: ProductModel) {
        with(product) {
            with(itemView) {
                tvNameProduct.text = nameProduct
                pickerVolumeProduct.value = numProduct
                btnAddBasket.setOnClickListener {
                    if (pickerVolumeProduct.value != 0) {
                        nameProduct = tvNameProduct.text.toString()
                        numProduct = pickerVolumeProduct.value
                        callBack.invoke(product)
                        listener.addNumBasket()
                    }
                }
                Glide.with(imgProduct).load(img).into(imgProduct)
            }
        }
    }

    interface productToOrder {
        fun addNumBasket()
    }
}