package com.revolut.dannyang27.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            Log.d("REVOLUT", "Eur: 1.00 | Pounds: ${it.GBP}")
        })

        viewAdapter = CurrencyAdapter(mutableListOf("1","1","1","1","1","1"))
        viewManager = LinearLayoutManager(this)

        currencyRecyclerView = findViewById<RecyclerView>(R.id.currency_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        currencyViewModel.initCurrencyObserver(this)
    }
}
