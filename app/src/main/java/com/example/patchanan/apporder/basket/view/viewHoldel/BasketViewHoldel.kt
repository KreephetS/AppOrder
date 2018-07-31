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

class BasketViewHoldel(itemView: View, val callbackFragment: (ProductModel, String) -> Unit)
    : RecyclerView.ViewHolder(itemView.rootView) {
    private lateinit var listener: BasketViewHoldel.productToOrder
    fun bindItem(product: ProductModel, callbackAdapter: () -> Unit) {

        itemView.tvNameProductInBasket.text = product.nameProduct
        itemView.numProductInBasket.value = product.numProduct
        itemView.tvProductCondition.text = product.condition
        itemView.btnDeleteProduct.setOnClickListener {
            CommonFunction.msgDialog(it.context, "ลบข้อมูล",
                    "คุฌต้องการลบข้อมูลใช้หรือไม่", true, "ใช้", "ไม่"
            ) {
                listener.delNumBasket()
                callbackAdapter.invoke()
                callbackFragment(product, "del")
            }
        }
        itemView.btnEditProduct.setOnClickListener {
            callbackFragment(product, "update")
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
