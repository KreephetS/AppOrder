package com.example.patchanan.apporder.shop.view.viewholder

import android.app.Activity
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dk.animation.circle.CircleAnimationUtil
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.common.model.ProductModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_row_product.view.*

class ShopViewHolder(itemView: View, val callBack: (ProductModel, String, View) -> Unit) : RecyclerView.ViewHolder(itemView.rootView) {

    private lateinit var listener: ShopViewHolder.productToOrder

    init {
        if (itemView.context is ShopViewHolder.productToOrder) {
            listener = itemView.context as ShopViewHolder.productToOrder
        }
    }

    fun bindItem(product: ProductModel) {
        Glide.with(itemView.imgProduct).load(product.img).into(itemView.imgProduct)
        Glide.with(itemView.imgBg).load(product.img).into(itemView.imgBg)
        val img = Glide.with(itemView.imgBg).load(product.img).into(itemView.imgBg).view
        itemView.tvNameProduct.text = product.nameProduct
        itemView.pickerVolumeProduct.value = product.numProduct
        itemView.btnAddBasket.setOnClickListener {
            product.nameProduct = itemView.tvNameProduct.text.toString()
            product.numProduct = itemView.pickerVolumeProduct.value
            callBack.invoke(product, "insert", itemView.imgProduct)
            listener.addNumBasket(img)
        }
        itemView.imgProduct.transitionName = product.img
        itemView.imgProduct.setOnClickListener {
            callBack.invoke(product, "showDetail", itemView.imgProduct)
        }
    }

    interface productToOrder {
        fun addNumBasket(img: ImageView)
    }
}