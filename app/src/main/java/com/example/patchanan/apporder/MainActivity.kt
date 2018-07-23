package com.example.patchanan.apporder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.patchanan.apporder.shop.view.fragment.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Handler
import com.example.patchanan.apporder.basket.view.fragment.BasketFragment
import com.example.patchanan.apporder.basket.view.viewHoldel.BasketViewHoldel
import com.example.patchanan.apporder.common.viewmodel.CommonFunction
import com.example.patchanan.apporder.database.DBHelper
import com.example.patchanan.apporder.shop.view.viewholder.ShopViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), ShopViewHolder.productToOrder, BasketViewHoldel.productToOrder {

    private var mCount: Int = 0

    override fun delNumBasket() {
        mCount--
        ic_basket.text = mCount.toString()
    }

    override fun addNumBasket() {
        mCount++
        ic_basket.text = mCount.toString()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                CommonFunction.loadFragment(supportFragmentManager, ShopFragment(), R.id.content, "shop")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        CommonFunction.replaceFragment(supportFragmentManager, ShopFragment(), R.id.content, "shop")
        loadCountBasket()
        relativeBasket.setOnClickListener {
            CommonFunction.loadFragment(supportFragmentManager, BasketFragment(), R.id.content, "basket")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> {
                    Handler().postDelayed(Runnable { relativeBasket.performClick() }, 500)
                }
            }
        }
    }


    override fun onBackPressed() {
        val current = supportFragmentManager.findFragmentById(R.id.content)
        if (current::class.java != ShopFragment::class.java) {
            CommonFunction.replaceFragment(supportFragmentManager, ShopFragment(), R.id.content, "shop")
        } else {
            CommonFunction.msgDialog(this, "คุณต้องการออกจาก APP หรือไม่", "ออก APP", true, "Yes", "No"
            ) { finish() }
        }
    }

    private fun loadCountBasket() {
        DBHelper.getAppDatabase(this).productDAO().getProductAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    this.mCount = it.size
                    ic_basket.text = mCount.toString()
                }, {}, {})
    }
}
