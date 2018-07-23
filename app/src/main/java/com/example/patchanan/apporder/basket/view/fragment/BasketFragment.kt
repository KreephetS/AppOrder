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
import android.widget.Toast
import com.example.patchanan.apporder.basket.adapter.BasketAdapter
import com.example.patchanan.apporder.basket.viewmodel.BasketViewModel
import com.example.patchanan.apporder.common.model.OrderModel
import com.example.patchanan.apporder.common.model.ProductModel
import com.example.patchanan.apporder.common.viewmodel.CommonFunction
import com.example.patchanan.apporder.shop.view.activity.AddProductActivity
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {
    lateinit var order: OrderModel
    var product = mutableListOf<ProductModel>()
    private val mContext: Context by lazy {
        activity?.applicationContext!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewProductInBasket.layoutManager = LinearLayoutManager(activity)
        val itemDividerItemDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        recyclerViewProductInBasket.addItemDecoration(itemDividerItemDecoration)
        loadItems()
        btnSendOrder.setOnClickListener {
            order = OrderModel("phet", product, 0, "23/07/2018", false)
            ViewModelProviders.of(this).get(BasketViewModel::class.java).insertOrder(mContext, order)
        }
    }

    private fun loadItems() {
        DBHelper.getAppDatabase(activity!!.applicationContext!!).productDAO().getProductAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    product = it
                    recyclerViewProductInBasket.adapter = BasketAdapter(it) { productModel, s ->
                        when (s) {
                            "del" -> {

                                ViewModelProviders.of(activity!!)
                                        .get(ShopViewModel::class.java)
                                        .deleteProduct(activity!!, productModel.id) {}

                            }
                            "update" -> {
                                val intent = Intent(activity, AddProductActivity::class.java).apply {
                                    putExtra("itemProduct", productModel)
                                }
                                startActivity(intent)
                            }
                        }
                    }
                }, {}, {})
    }

    override fun onResume() {
        super.onResume()
        loadItems()
    }

}
