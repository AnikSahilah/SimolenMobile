package com.example.landingpageactivity.Utils;

import com.example.landingpageactivity.RouterAPI.ApiClient;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Function {
    public static String parseRupiah(double i){
        // Set locale to Indonesia for Rupiah currency format
        Locale localeID = new Locale("id", "ID");

        // Create NumberFormat with currency style and the specified locale
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        // Set the currency code to IDR (Rupiah)
        Currency currency = Currency.getInstance("IDR");
        formatRupiah.setCurrency(currency);
        return formatRupiah.format(i);
    }
}
