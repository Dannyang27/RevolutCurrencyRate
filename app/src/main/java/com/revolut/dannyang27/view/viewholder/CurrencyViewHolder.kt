package com.revolut.dannyang27.view.viewholder

import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTextChanged
import com.revolut.dannyang27.R
import com.revolut.dannyang27.businesslogic.extension.isNumeric
import com.revolut.dannyang27.businesslogic.util.CurrencyManager
import com.revolut.dannyang27.model.Currency
import com.revolut.dannyang27.view.MainActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CurrencyViewHolder(view: View): RecyclerView.ViewHolder(view){
    @BindView(R.id.viewholder_flagicon) lateinit var image: CircleImageView
    @BindView(R.id.viewholder_code) lateinit var currencyCode: TextView
    @BindView(R.id.viewholder_name) lateinit var currencyName: TextView
    @BindView(R.id.viewholder_ratevalue) lateinit var currencyRate: EditText

    private var isBase = false

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(currency: Currency){
        val (name, drawable) = CurrencyManager.getDrawableByName(currency.code)
        currencyCode.text = currency.code
        currencyName.text = name
        isBase = currency.isBase

        if(!isBase){
            currencyRate.setText(String.format("%.4f", currency.rate * CurrencyManager.currentValue))
        }

        Picasso.get().load(drawable).into(image)
    }

    @OnTextChanged(R.id.viewholder_ratevalue, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    fun afterTextChanged(text: Editable?){
        if(isBase){
            val isNumeric = text.toString().isNumeric()
            if(isNumeric){
                val value = text.toString().toDouble()
                CurrencyManager.currentValue = value
                MainActivity.notifyRangeExceptFirst()
            }
        }
    }
}