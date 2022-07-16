package com.example.qrscanner

import android.content.Context
import android.content.SharedPreferences

class DataStore {
    private var sharedPreferences: SharedPreferences? = null



    companion object{
        var editor: SharedPreferences.Editor? = null
        fun data(context: Context, key: String?, value: String?) {
            val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val editor =sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun fetch(context: Context, key: String?): String? {
            val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, "")
        }
    }


}