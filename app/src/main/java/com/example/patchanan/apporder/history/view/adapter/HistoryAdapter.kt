package com.example.patchanan.apporder.history.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.history.view.viewHoldel.HistoryViewHoldel

class HistoryAdapter(private val itemOrder: MutableList<OrderModel>?) : RecyclerView.Adapter<HistoryViewHoldel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHoldel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_order, parent, false)
        return HistoryViewHoldel(v)
    }

    override fun getItemCount(): Int {
        return itemOrder?.size ?: 0
    }

    override fun onBindViewHolder(holder: HistoryViewHoldel, position: Int) {
        holder.bindItem(itemOrder!![position])
    }

}