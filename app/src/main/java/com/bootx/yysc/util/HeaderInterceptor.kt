package com.bootx.yysc.util;

import okhttp3.Interceptor
import okhttp3.Response


/**
 * 项目相关，添加公共header的拦截器
 */
class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
                TODO("Not yet implemented")
        }

}