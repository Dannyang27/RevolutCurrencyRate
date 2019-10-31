package com.revolut.dannyang27.view.viewholder

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.revolut.dannyang27.R2
import de.hdodenhof.circleimageview.CircleImageView

class CurrencyViewHolder(view: View): RecyclerView.ViewHolder(view){
    @BindView(R2.id.viewholder_flagicon) lateinit var image: CircleImageView
    @BindView(R2.id.viewholder_code) lateinit var currencyCode: TextView
    @BindView(R2.id.viewholder_name) lateinit var currencyName: TextView
    @BindView(R2.id.viewholder_ratevalue) lateinit var currencyRate: EditText

    init {
        ButterKnife.bind(this, view)
    }
}