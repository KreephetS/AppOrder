package com.example.patchanan.apporder.shop.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
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
import com.example.patchanan.apporder.shop.view.activity.DetailActivity
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val IMAGE_URL_KEY = "url"

class ShopFragment : Fragment() {

    private lateinit var productListAdapter: ShopAdapter
    private lateinit var shopViewModel: ShopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        shopViewModel = ViewModelProviders.of(this).get(ShopViewModel::class.java)
        btnAddProduct.setOnClickListener {
            val intent = Intent(context!!, AddProductActivity::class.java)
            activity?.startActivityForResult(intent, 1)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearGridLayoutManager = GridLayoutManager(context!!, 2, GridLayoutManager.VERTICAL, false)
        recyclerView_product.layoutManager = linearGridLayoutManager

        productListAdapter = ShopAdapter { product, action, img ->
            when (action) {
                "insert" -> {
                    product.let {
                        shopViewModel.insertProduct(context!!, product)
                    }
                }
                "showDetail" -> {
                    goToDetails(product.img!!, img)
                }
            }
        }
        recyclerView_product.adapter = productListAdapter
        loadProductList()
    }

    private fun loadProductList() {
        shopViewModel.getProductList(context!!).observe(this, Observer {
            it?.let {
                productListAdapter.setListProduct(it)
            }
        })
    }

    fun goToDetails(url: String, imageView: View) {
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity!!, imageView, imageView.transitionName).toBundle()
        Intent(context, DetailActivity::class.java)
                .putExtra(IMAGE_URL_KEY, url)
                .let {
                    startActivity(it)
                }
    }
}