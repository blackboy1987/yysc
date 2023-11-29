package com.bootx.yysc.util

import com.google.gson.Gson
import retrofit2.Converter

class CustomGsonConverterFactory(val gson: Gson) : Converter.Factory() {

}