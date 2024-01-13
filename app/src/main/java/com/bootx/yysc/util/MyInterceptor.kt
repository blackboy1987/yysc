package com.bootx.yysc.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.nio.charset.Charset


class MyInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        Log.e("MyInterceptor", "请求地址: $url")
        var response: Response = chain.proceed(request)
        val body = response.body
        val source = body!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        var charset = Charset.defaultCharset()
        val contentType = body.contentType()
        if (contentType != null) {
            charset = contentType.charset(charset)
        }
        val string: String = buffer.clone().readString(charset)
        val responseBody = ResponseBody.create(contentType, string)
        response = response.newBuilder().body(responseBody).build()
        Log.e("MyInterceptor", "响应数据: $string")
        return response;
    }

}
