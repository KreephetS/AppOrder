package com.example.patchanan.apporder.history.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.patchanan.apporder.R
import com.example.patchanan.apporder.database.DBHelper
import com.example.patchanan.apporder.history.view.adapter.HistoryAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_home_user.*


class HistoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerOrder.layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        loadItems()
    }

    private fun loadItems() {
        DBHelper.getAppDatabase(activity!!.applicationContext!!).orderDAO().getOrderAll()
                .subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    recyclerOrder.adapter = HistoryAdapter(it)
                }, {}, {})
    }
}