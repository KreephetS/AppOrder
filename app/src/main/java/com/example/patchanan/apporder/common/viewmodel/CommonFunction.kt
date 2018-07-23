package com.example.patchanan.apporder.common.viewmodel

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.patchanan.apporder.basket.view.fragment.BasketFragment

object CommonFunction {
    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int, tag: String) {
        Log.wtf(tag, "1")
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .commit()
    }

    fun loadFragment(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int, tag: String) {
        val current = fragmentManager.findFragmentById(containerId)
        if (current::class.java != fragment::class.java) {
            Log.wtf(tag, "2")
            fragmentManager.beginTransaction()
                    .addToBackStack(tag)
                    .replace(containerId, fragment, tag)
                    .commit()
        }
    }

    fun msgDialog(context: Context, massage: String, title: String, hasTwoBtn: Boolean, positiveBtnName: String, negativeBtnName: String, callback: (() -> Unit)?) {

        val builder1 = AlertDialog.Builder(context as Activity)
        builder1.setMessage(massage)
        builder1.setTitle(title)
        builder1.setCancelable(false)
        builder1.setPositiveButton(positiveBtnName) { dialog, id ->
            callback?.invoke()
            dialog.cancel()
        }
        if (hasTwoBtn) {
            builder1.setNegativeButton(negativeBtnName) { dialog, id ->
                //                callback?.invoke()
                dialog.cancel()
            }
        }

        val alert11 = builder1.create()
        alert11.show()
    }

}
