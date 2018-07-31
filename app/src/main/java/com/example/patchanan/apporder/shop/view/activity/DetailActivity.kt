package com.example.patchanan.apporder.shop.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.shop.view.fragment.IMAGE_URL_KEY
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Log.wtf("DetailActivity", "onCreate")
        val url = intent.getStringExtra(IMAGE_URL_KEY)
        detailImage.transitionName = url
        Glide.with(detailImage).load(url).into(detailImage)
        close.setOnClickListener {
            finish()
        }
    }
}
