package com.revolut.dannyang27.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.revolut.dannyang27.R
import com.revolut.dannyang27.businesslogic.viewmodel.CurrencyViewModel
import com.revolut.dannyang27.view.adapter.CurrencyAdapter

class MainActivity : AppCompatActivity() {
    @BindView(R.id.currency_toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.currency_list) lateinit var currencies: RecyclerView

    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var viewAdapter: CurrencyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        toolbar.title = getString(R.string.rates)
        currencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        currencyViewModel.getCurrencyRate().observe(this, Observer {
            it?.let{
                viewAdapter.updateList(it.rateList!!)
            }
        })

        viewAdapter = CurrencyAdapter(mutableListOf())
        viewManager = LinearLayoutManager(this)

        currencies.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            itemAnimator = null
        }

        currencyViewModel.initCurrencyObserver(this)
    }

    override fun onResume() {
        super.onResume()
        currencyViewModel.startService(this)
    }

    override fun onStop() {
        super.onStop()
        currencyViewModel.stopService(this)
    }
}