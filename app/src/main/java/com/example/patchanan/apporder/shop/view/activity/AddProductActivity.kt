package com.example.patchanan.apporder.shop.view.activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.shop.viewModel.ShopViewModel
import com.example.patchanan.apporder.common.model.ProductModel
import kotlinx.android.synthetic.main.activity_add_product.*
import com.example.patchanan.apporder.common.viewmodel.CommonFunction

class AddProductActivity : AppCompatActivity() {
    private var product = ProductModel()
    private lateinit var shopViewModel: ShopViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        shopViewModel = ViewModelProviders.of(this).get(ShopViewModel::class.java)
        if (intent.hasExtra("itemProduct")) {
            product = intent.getParcelableExtra("itemProduct")
            updateProduct(product)
        } else {
            addProduct(product)
        }
    }

    private fun updateProduct(product: ProductModel?) {
        product?.let { product ->
            etNameProduct.setText(product.nameProduct)
            numProductInBasket.value = product.numProduct
            etCondition.setText(product.condition)
            btnSaveUpdate.setOnClickListener {
                if (etNameProduct.text.toString() != "" && numProductInBasket.value != 0) {
                    setProduct(product)
                    shopViewModel.updateProduct(this, product)
                    finish()
                } else {
                    dialogNotification()
                }
            }
        }
    }

    private fun addProduct(product: ProductModel?) {
        product?.let { product ->
            btnSaveUpdate.setOnClickListener {
                if (etNameProduct.text.toString() != "" && numProductInBasket.value != 0) {
                    setProduct(product)
                    shopViewModel.insertProduct(this, product)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    dialogNotification()
                }
            }
        }
    }

    private fun setProduct(product: ProductModel) {
        product.let {
            it.nameProduct = etNameProduct.text.toString()
            it.numProduct = numProductInBasket.value
            it.condition = etCondition.text.toString()
        }
    }

    private fun dialogNotification() {
        CommonFunction.msgDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลให้ครบถ้วน", false,
                "OK", "No"
        ) { }
        Log.wtf("check null", "ว่างเปล่า")
    }
}
