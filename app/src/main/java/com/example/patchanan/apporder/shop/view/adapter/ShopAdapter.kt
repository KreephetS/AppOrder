package com.example.patchanan.apporder.shop.view.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.shop.view.viewholder.ShopViewHolder
import com.example.patchanan.apporder.common.model.ProductModel

class ShopAdapter(val callBack: (ProductModel, String, View) -> Unit) : RecyclerView.Adapter<ShopViewHolder>() {

    private val product = mutableListOf<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_product, parent, false)
        return ShopViewHolder(v, callBack)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bindItem(product[position])
    }

    fun setListProduct(list: MutableList<ProductModel>) {
        product.addAll(list)
        notifyDataSetChanged()
    }
}