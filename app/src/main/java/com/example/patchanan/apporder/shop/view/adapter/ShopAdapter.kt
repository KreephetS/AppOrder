package com.example.patchanan.apporder.shop.view.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.shop.view.viewholder.ShopViewHolder
import com.example.patchanan.apporder.common.model.ProductModel

class ShopAdapter(private val product: MutableList<ProductModel>?, val callBack: (ProductModel) -> Unit) :
        RecyclerView.Adapter<ShopViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_product, parent, false)
        return ShopViewHolder(v, callBack)
    }

    override fun getItemCount(): Int {
        return product!!.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bindItem(product!![position])
    }
}