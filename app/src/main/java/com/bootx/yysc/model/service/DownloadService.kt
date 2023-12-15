package com.bootx.yysc.model.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming
import retrofit2.http.Url

interface DownloadService {

    @Streaming
    @GET
    fun download(@Header("token") token: String, @Url url: String): Call<ResponseBody>
}