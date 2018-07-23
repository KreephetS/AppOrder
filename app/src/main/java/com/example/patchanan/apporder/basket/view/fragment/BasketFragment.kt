package com.example.patchanan.apporder.basket.view.fragment

import android.arch.lifecycle.ViewModelProviders
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
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.patchanan.apporder.basket.adapter.BasketAdapter
import com.example.patchanan.apporder.shop.view.activity.AddProductActivity
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewProductInBasket.layoutManager = LinearLayoutManager(activity)
        val itemDividerItemDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        recyclerViewProductInBasket.addItemDecoration(itemDividerItemDecoration)
        loadItems()
    }

    private fun loadItems() {
        DBHelper.getAppDatabase(activity!!.applicationContext!!).productDAO().getProductAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
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
