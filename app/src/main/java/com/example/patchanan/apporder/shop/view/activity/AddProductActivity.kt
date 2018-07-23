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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        var product = ProductModel()

        if (intent.hasExtra("itemProduct")) {
            product = intent.getParcelableExtra("itemProduct")

            etNameProduct.setText(product.nameProduct)
            numProductInBasket.value = product.numProduct!!
            etCondition.setText(product.condition)

            btnSaveUpdate.setOnClickListener {
                if (etNameProduct.text.toString() != "" && numProductInBasket.value != 0) {
                    product.nameProduct = etNameProduct.text.toString()
                    product.numProduct = numProductInBasket.value
                    product.condition = etCondition.text.toString()
                    ViewModelProviders.of(this).get(ShopViewModel::class.java).updateProduct(this, product)
                    finish()
                } else {
                    CommonFunction.msgDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลให้ครบถ้วน", false,
                            "OK", "No"
                    ) { }
                    Log.wtf("check null", "ทำไมใส่วะ")
                }
            }
        } else {
            btnSaveUpdate.setOnClickListener {
                if (etNameProduct.text.toString() != "" && numProductInBasket.value != 0) {
                    product.nameProduct = etNameProduct.text.toString()
                    product.numProduct = numProductInBasket.value
                    product.condition = etCondition.text.toString()
                    ViewModelProviders.of(this).get(ShopViewModel::class.java).insertProduct(this, product)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    CommonFunction.msgDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลให้ครบถ้วน", false,
                            "OK", "No"
                    ) { }
                    Log.wtf("check null", "ทำไมใส่วะ")
                }
            }

        }
    }
}
