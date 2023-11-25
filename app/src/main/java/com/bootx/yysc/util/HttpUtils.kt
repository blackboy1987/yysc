package com.bootx.yysc.util

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


object HttpUtils {

    var client = OkHttpClient()

    fun get(url: String): String{
        val getRequest:Request = Request.Builder()
            .url(url)
            .build()
        val response: Response = client.newCall(getRequest).execute()
        return response.body.toString()
    }

    fun get1(url: String){
        val getRequest:Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(getRequest).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException){

            }
            override fun onResponse(call: Call, response: Response){

            }
        })
    }

    fun post(url: String): String{
        val postRequest:Request = Request.Builder()
            .url(url)
            .build()
        val response: Response = client.newCall(postRequest).execute()
        return response.body.toString()
    }

}