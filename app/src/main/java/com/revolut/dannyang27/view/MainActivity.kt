package com.revolut.dannyang27.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.R
import com.revolut.dannyang27.view.adapter.CurrencyAdapter
import com.revolut.dannyang27.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var viewAdapter: CurrencyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var currencyRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currency_toolbar.title = getString(R.string.rates)
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        currencyViewModel.getCurrencyRate().observe(this, Observer {
            viewAdapter.updateList(it.toMutableList())
        })

        viewAdapter = CurrencyAdapter(mutableListOf())
        viewManager = LinearLayoutManager(this)

        currencyRecyclerView = findViewById<RecyclerView>(R.id.currency_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            itemAnimator = null
        }

        currencyViewModel.initCurrencyObserver(this)
    }
}