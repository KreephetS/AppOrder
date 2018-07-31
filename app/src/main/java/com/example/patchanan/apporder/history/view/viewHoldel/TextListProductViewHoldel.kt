package com.example.patchanan.apporder.history.view.viewHoldel

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.patchanan.apporder.common.model.ProductModel
import kotlinx.android.synthetic.main.item_row_text_product.view.*

class TextListProductViewHoldel(itemView: View)
    : RecyclerView.ViewHolder(itemView.rootView) {
    fun bindItem(product: ProductModel) {
        itemView.tvNameProductInHistory.text = product.nameProduct
    }

}