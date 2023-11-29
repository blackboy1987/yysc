package com.bootx.yysc.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("Accept", "application/vnd.yourapi.v1.full+json")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }

}
