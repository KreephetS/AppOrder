package com.example.patchanan.apporder.history.view.viewHoldel


import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.history.view.adapter.TextLisProductAdapter
import kotlinx.android.synthetic.main.fragment_basket.*
import kotlinx.android.synthetic.main.item_row_order.view.*

class HistoryViewHoldel(itemView: View)
    : RecyclerView.ViewHolder(itemView.rootView) {
    fun bindItem(order: OrderModel) {
        itemView.tvOrderBy.text = order.orderBy
        itemView.tvDate.text = order.date
        itemView.listProduct.layoutManager = LinearLayoutManager(itemView.context)
        val itemDividerItemDecoration = DividerItemDecoration(itemView.context, LinearLayoutManager.VERTICAL)
        itemView.listProduct.addItemDecoration(itemDividerItemDecoration)
        itemView.listProduct.adapter = TextLisProductAdapter(order.order)
    }

}