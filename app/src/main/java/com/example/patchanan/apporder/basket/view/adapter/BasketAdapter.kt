package com.example.patchanan.apporder.basket.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.basket.view.viewHoldel.BasketViewHoldel
import com.example.patchanan.apporder.common.model.ProductModel

class BasketAdapter(private val itemProductInBasket: MutableList<ProductModel>, val callBack: (ProductModel,String) -> Unit) : RecyclerView.Adapter<BasketViewHoldel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHoldel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_product_in_basket, parent, false)
        return BasketViewHoldel(v,callBack)

    }

    override fun getItemCount(): Int {
        return itemProductInBasket.size
    }

    override fun onBindViewHolder(holder: BasketViewHoldel, position: Int) {

        holder.bindItem(itemProductInBasket[position]) {
            removeAt(position)
        }
    }

    private fun removeAt(position: Int) {
        itemProductInBasket.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemProductInBasket.size)
    }
}


