package com.example.patchanan.apporder.basket.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.database.DBHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.patchanan.apporder.basket.adapter.BasketAdapter
import com.example.patchanan.apporder.basket.viewmodel.BasketViewModel
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.shop.view.activity.AddProductActivity
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_basket.*
import java.text.SimpleDateFormat
import java.util.*


class BasketFragment : Fragment() {

    private lateinit var order: OrderModel
    private lateinit var basketAdapter: BasketAdapter
    private val today = Date()
    private val todayString = today.format()
    private val basketViewModel by lazy { ViewModelProviders.of(this).get(BasketViewModel::class.java) }
    private lateinit var listener: BasketFragment.numBasket

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initInstance()
        loadItems()
    }

    private fun loadItems() {
        DBHelper.getAppDatabase(context!!).productDAO().getProductAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    initRecyclerView()
                    basketAdapter.setListProductInBasket(it)
                }, {}, {})
    }

    private fun initRecyclerView() {
        recyclerViewProductInBasket.layoutManager = LinearLayoutManager(context)
        val itemDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerViewProductInBasket.addItemDecoration(itemDividerItemDecoration)
        basketAdapter = BasketAdapter { productModel, s ->
            when (s) {
                "del" -> {

                    ViewModelProviders.of(activity!!)
                            .get(ShopViewModel::class.java)
                            .deleteProduct(context!!, productModel.id) {}

                }
                "update" -> {
                    val intent = Intent(context, AddProductActivity::class.java).apply {
                        putExtra("itemProduct", productModel)
                    }
                    startActivity(intent)
                }
            }
        }
        recyclerViewProductInBasket.adapter = basketAdapter
    }

    private fun initInstance() {
        btnSendOrder.setOnClickListener {
            sendOrder()
            listener.clarNumBasket()
            basketAdapter.clearProductList()
            basketViewModel.clerProdct(context!!)
        }
    }

    private fun sendOrder() {
        DBHelper.getAppDatabase(context!!).productDAO().getProductAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    it?.let {
                        order = OrderModel("phet", it, 0, todayString, false)
                        Log.wtf("Order Model", order.orderBy)
                        basketViewModel.insertOrder(context!!, order)
                    }.run {

                    }
                }, {}, {})
    }

    override fun onResume() {
        super.onResume()
        loadItems()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BasketFragment.numBasket) {
            listener = context
        }
    }

    private fun Date.format(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(this)
    }


    interface numBasket {
        fun clarNumBasket()
    }
}
