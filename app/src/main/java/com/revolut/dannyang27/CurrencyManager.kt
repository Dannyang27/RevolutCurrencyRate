package com.revolut.dannyang27

import com.revolut.dannyang27.enum.FlagEnum
import com.revolut.dannyang27.model.CurrencyRate

object CurrencyManager{

    fun getDrawableByName(flagCode: String): Pair<String, Int>{
        return when(flagCode){
            FlagEnum.AUD.name -> Pair(FlagEnum.AUD.currencyName, FlagEnum.AUD.drawable)
            FlagEnum.BGN.name -> Pair(FlagEnum.BGN.currencyName, FlagEnum.BGN.drawable)
            FlagEnum.BRL.name -> Pair(FlagEnum.BRL.currencyName, FlagEnum.BRL.drawable)
            FlagEnum.CAD.name -> Pair(FlagEnum.CAD.currencyName, FlagEnum.CAD.drawable)
            FlagEnum.CHF.name -> Pair(FlagEnum.CHF.currencyName, FlagEnum.CHF.drawable)
            FlagEnum.CNY.name -> Pair(FlagEnum.CNY.currencyName, FlagEnum.CNY.drawable)
            FlagEnum.CZK.name -> Pair(FlagEnum.CZK.currencyName, FlagEnum.CZK.drawable)
            FlagEnum.DKK.name -> Pair(FlagEnum.DKK.currencyName, FlagEnum.DKK.drawable)
            FlagEnum.GBP.name -> Pair(FlagEnum.GBP.currencyName, FlagEnum.GBP.drawable)
            FlagEnum.HKD.name -> Pair(FlagEnum.HKD.currencyName, FlagEnum.HKD.drawable)
            FlagEnum.HRK.name -> Pair(FlagEnum.HRK.currencyName, FlagEnum.HRK.drawable)
            FlagEnum.HUF.name -> Pair(FlagEnum.HUF.currencyName, FlagEnum.HUF.drawable)
            FlagEnum.IDR.name -> Pair(FlagEnum.IDR.currencyName, FlagEnum.IDR.drawable)
            FlagEnum.ILS.name -> Pair(FlagEnum.ILS.currencyName, FlagEnum.ILS.drawable)
            FlagEnum.INR.name -> Pair(FlagEnum.INR.currencyName, FlagEnum.INR.drawable)
            FlagEnum.ISK.name -> Pair(FlagEnum.ISK.currencyName, FlagEnum.ISK.drawable)
            FlagEnum.JPY.name -> Pair(FlagEnum.JPY.currencyName, FlagEnum.JPY.drawable)
            FlagEnum.MXN.name -> Pair(FlagEnum.MXN.currencyName, FlagEnum.MXN.drawable)
            FlagEnum.MYR.name -> Pair(FlagEnum.MYR.currencyName, FlagEnum.MYR.drawable)
            FlagEnum.KRW.name -> Pair(FlagEnum.KRW.currencyName, FlagEnum.KRW.drawable)
            FlagEnum.NOK.name -> Pair(FlagEnum.NOK.currencyName, FlagEnum.NOK.drawable)
            FlagEnum.NZD.name -> Pair(FlagEnum.NZD.currencyName, FlagEnum.NZD.drawable)
            FlagEnum.PHP.name -> Pair(FlagEnum.PHP.currencyName, FlagEnum.PHP.drawable)
            FlagEnum.PLN.name -> Pair(FlagEnum.PLN.currencyName, FlagEnum.PLN.drawable)
            FlagEnum.RON.name -> Pair(FlagEnum.RON.currencyName, FlagEnum.RON.drawable)
            FlagEnum.RUB.name -> Pair(FlagEnum.RUB.currencyName, FlagEnum.RUB.drawable)
            FlagEnum.SEK.name -> Pair(FlagEnum.SEK.currencyName, FlagEnum.SEK.drawable)
            FlagEnum.SGD.name -> Pair(FlagEnum.SGD.currencyName, FlagEnum.SGD.drawable)
            FlagEnum.THB.name -> Pair(FlagEnum.THB.currencyName, FlagEnum.THB.drawable)
            FlagEnum.TRY.name -> Pair(FlagEnum.TRY.currencyName, FlagEnum.TRY.drawable)
            FlagEnum.USD.name -> Pair(FlagEnum.USD.currencyName, FlagEnum.USD.drawable)
            FlagEnum.ZAR.name -> Pair(FlagEnum.ZAR.currencyName, FlagEnum.ZAR.drawable)
            else -> Pair(FlagEnum.EUR.currencyName, FlagEnum.EUR.drawable)
        }
    }

    fun getPairOfCurrencies(currencies: CurrencyRate): MutableList<Pair<String, Double>>{
        val pairs: MutableList<Pair<String, Double>> = mutableListOf()
        pairs.add(Pair("EUR", 1.00))
        pairs.add(Pair("AUD", currencies.AUD!!))
        pairs.add(Pair("BGN", currencies.BGN!!))
        pairs.add(Pair("BRL", currencies.BRL!!))
        pairs.add(Pair("CAD", currencies.CAD!!))
        pairs.add(Pair("CHF", currencies.CHF!!))
        pairs.add(Pair("CNY", currencies.CNY!!))
        pairs.add(Pair("CZK", currencies.CZK!!))
        pairs.add(Pair("DKK", currencies.DKK!!))
        pairs.add(Pair("GBP", currencies.GBP!!))
        pairs.add(Pair("HKD", currencies.HKD!!))
        pairs.add(Pair("HRK", currencies.HRK!!))
        pairs.add(Pair("HUF", currencies.HUF!!))
        pairs.add(Pair("IDR", currencies.IDR!!))
        pairs.add(Pair("ILS", currencies.ILS!!))
        pairs.add(Pair("ISK", currencies.ISK!!))
        pairs.add(Pair("INR", currencies.INR!!))
        pairs.add(Pair("JPY", currencies.JPY!!))
        pairs.add(Pair("KRW", currencies.KRW!!))
        pairs.add(Pair("MXN", currencies.MXN!!))
        pairs.add(Pair("MYR", currencies.MYR!!))
        pairs.add(Pair("NOK", currencies.NOK!!))
        pairs.add(Pair("NZD", currencies.NZD!!))
        pairs.add(Pair("PHP", currencies.PHP!!))
        pairs.add(Pair("PLN", currencies.PLN!!))
        pairs.add(Pair("RON", currencies.RON!!))
        pairs.add(Pair("RUB", currencies.RUB!!))
        pairs.add(Pair("SEK", currencies.SEK!!))
        pairs.add(Pair("SGD", currencies.SGD!!))
        pairs.add(Pair("THB", currencies.THB!!))
        pairs.add(Pair("TRY", currencies.TRY!!))
        pairs.add(Pair("USD", currencies.USD!!))
        pairs.add(Pair("ZAR", currencies.ZAR!!))

        return pairs
    }
}