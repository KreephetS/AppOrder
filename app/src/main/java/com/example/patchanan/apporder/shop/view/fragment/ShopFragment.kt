package com.example.patchanan.apporder.shop.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.shop.view.activity.AddProductActivity
import com.example.patchanan.apporder.shop.view.adapter.ShopAdapter
import com.example.patchanan.apporder.common.model.ProductModel
import kotlinx.android.synthetic.main.fragment_home_user.*
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.patchanan.apporder.manager.HttpManager
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ShopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_user, container, false)
    }

    private val mContext: Context by lazy {
        activity?.applicationContext!!
    }

    private val shopViewModel by lazy {
        ViewModelProviders.of(this).get(ShopViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView_product.layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        shopViewModel.getProductList(mContext).observe(this, Observer {
            recyclerView_product.adapter = ShopAdapter(it) {
                shopViewModel.insertProduct(this.activity!!, it)
            }
        })

        btnAddProduct.setOnClickListener {
            val intent = Intent(activity, AddProductActivity::class.java)
            activity!!.startActivityForResult(intent, 1)
        }

    }
}