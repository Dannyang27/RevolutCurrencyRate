package com.revolut.dannyang27.view.viewholder

import android.preference.PreferenceManager
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.revolut.dannyang27.R
import com.revolut.dannyang27.businesslogic.util.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CurrencyViewHolder(view: View): RecyclerView.ViewHolder(view){
    @BindView(R.id.viewholder_flagicon) lateinit var image: CircleImageView
    @BindView(R.id.viewholder_code) lateinit var currencyCode: TextView
    @BindView(R.id.viewholder_name) lateinit var currencyName: TextView
    @BindView(R.id.viewholder_ratevalue) lateinit var currencyRate: EditText

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(currency: Currency){
        val (name, drawable) = CurrencyManager.getDrawableByName(currency.code)
        currencyCode.text = currency.code
        currencyName.text = name
        if(currency.isBase){
            currencyRate.isFocusable = true
            currencyRate.setText(String.format("%.4f",CurrencyManager.currentValue))

        }else{
            currencyRate.setText(String.format("%.4f", currency.rate * CurrencyManager.currentValue))
        }

        Picasso.get().load(drawable).into(image)
        itemView.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(it.context)
            pref.edit()
                .putString("base", currency.code)
                .apply()

            CurrencyManager.baseCurrency = currency.code
        }
    }
}