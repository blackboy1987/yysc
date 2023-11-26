package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.math.log

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyWebView(state: WebViewState) {
    AndroidView(factory = { context ->
        WebView(context)

    }) { view ->
        when(val content=state.content){
            is WebContent.Url -> {
                val url = content.url
                if(url.isNotEmpty() && url !=view.url){
                    view.loadUrl(url)
                }
            }
            is WebContent.Data -> {
                Log.e("MyWebView111111", "MyWebView111111: ${content.data}", )
                view.loadDataWithBaseURL(
                    content.baseUrl,
                    content.data,
                    "text/html",
                    "utf-8",
                    null,
                )
            }
        }
    }
}

sealed class WebContent() {
    data class Url(val url: String) : WebContent()
    data class Data(val data: String, val baseUrl: String?=null) : WebContent()

    var pageTitle: String? by mutableStateOf(null)

}

class WebViewState(webContent: WebContent) {
    var content by mutableStateOf(webContent)
}

@Composable
fun rememberWebViewState(url: String) = remember(key1 = url) {
    WebViewState(WebContent.Url(url))
}

@Composable
fun rememberWebViewState(data: String, url: String? = null) = remember(key1 = data, key2 = url) {
    WebViewState(WebContent.Data(data, url))
}