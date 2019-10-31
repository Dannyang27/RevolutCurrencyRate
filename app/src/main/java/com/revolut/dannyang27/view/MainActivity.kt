package com.revolut.dannyang27.view

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTextChanged
import com.revolut.dannyang27.CurrencyManager
import com.revolut.dannyang27.R
import com.revolut.dannyang27.R2
import com.revolut.dannyang27.extension.isNumeric
import com.revolut.dannyang27.view.adapter.CurrencyAdapter
import com.revolut.dannyang27.viewmodel.CurrencyViewModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    @BindView(R2.id.currency_toolbar) lateinit var toolbar: Toolbar
    @BindView(R2.id.currency_list) lateinit var currencies: RecyclerView
    @BindView(R2.id.currency_flagicon) lateinit var icon: CircleImageView
    @BindView(R2.id.currency_code) lateinit var code: TextView
    @BindView(R2.id.currency_name) lateinit var name: TextView
    @BindView(R2.id.currency_ratevalue) lateinit var rate: EditText

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
            viewAdapter.updateList(it.toMutableList())
        })

        currencyViewModel.getSelectedCurrency().observe(this, Observer {
            val (currencyName, drawable) = CurrencyManager.getDrawableByName(it.code)
            Picasso.get().load(drawable).into(icon)
            code.text = it.code
            name.text = currencyName
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

    @OnTextChanged(R.id.currency_ratevalue, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    fun afterTextChanged(text: Editable?){
        val isNumeric = text.toString().isNumeric()
        if(isNumeric){
            val value = text.toString().toDouble()
            currencyViewModel.updateCurrentValue(value)
            viewAdapter.notifyDataSetChanged()
        }
    }
}