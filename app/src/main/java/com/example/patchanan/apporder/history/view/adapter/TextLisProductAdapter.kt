package com.example.patchanan.apporder.history.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.common.model.ProductModel
import com.example.patchanan.apporder.history.view.viewHoldel.HistoryViewHoldel
import com.example.patchanan.apporder.history.view.viewHoldel.TextListProductViewHoldel

class TextLisProductAdapter(val itemProduct: MutableList<ProductModel>?) : RecyclerView.Adapter<TextListProductViewHoldel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextListProductViewHoldel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_text_product, parent, false)
        return TextListProductViewHoldel(v)
    }

    override fun getItemCount(): Int {
        return itemProduct?.size ?: 0
    }

    override fun onBindViewHolder(holder: TextListProductViewHoldel, position: Int) {
        holder.bindItem(itemProduct!![position])
    }

}