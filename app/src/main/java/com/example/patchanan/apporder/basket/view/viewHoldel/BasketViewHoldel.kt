package com.example.patchanan.apporder.basket.view.viewHoldel

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.patchanan.apporder.basket.adapter.BasketAdapter
import com.example.patchanan.apporder.common.model.ProductModel
import com.example.patchanan.apporder.common.viewmodel.CommonFunction
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import kotlinx.android.synthetic.main.item_row_product_in_basket.view.*
import javax.security.auth.callback.Callback

class BasketViewHoldel(itemView: View, val callback: (ProductModel, String) -> Unit)
    : RecyclerView.ViewHolder(itemView.rootView) {
    private lateinit var listener: BasketViewHoldel.productToOrder
    fun bindItem(product: ProductModel, callback: () -> Unit) {
        with(product) {
            with(itemView) {
                tvNameProductInBasket.text = nameProduct
                numProductInBasket.value = numProduct
                tvProductCondition.text = condition
                btnDeleteProduct.setOnClickListener {
                    CommonFunction.msgDialog(it.context, "ลบข้อมูล", "คุฌต้องการลบข้อมูลใช้หรือไม่", true, "ใช้", "ไม่"
                    ) {
                        this@BasketViewHoldel.callback(product, "del")
                        listener.delNumBasket()
                        callback.invoke()
                    }
                }
                btnEditProduct.setOnClickListener {
                    this@BasketViewHoldel.callback(product, "update")
                }
            }
        }
    }

    init {
        if (itemView.context is BasketViewHoldel.productToOrder) {
            listener = itemView.context as BasketViewHoldel.productToOrder
        }
    }

    interface productToOrder {
        fun delNumBasket()
    }
}
