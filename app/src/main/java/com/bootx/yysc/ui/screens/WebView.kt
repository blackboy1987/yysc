package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyWebView() {
    val state = rememberWebViewState("https://www.baidu.com")
    WebView(
        state = state,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewWebView() {
    MyWebView()
}