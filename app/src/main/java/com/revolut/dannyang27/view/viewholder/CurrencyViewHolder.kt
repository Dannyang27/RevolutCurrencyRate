package com.revolut.dannyang27.view.viewholder

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.revolut.dannyang27.R
import de.hdodenhof.circleimageview.CircleImageView

class CurrencyViewHolder(view: View): RecyclerView.ViewHolder(view){
    val image: CircleImageView = view.findViewById(R.id.viewholder_flagicon)
    val currencyCode: TextView = view.findViewById(R.id.viewholder_code)
    val currencyName: TextView = view.findViewById(R.id.viewholder_name)
    val currencyRate: EditText = view.findViewById(R.id.viewholder_ratevalue)
}