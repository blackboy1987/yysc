package com.bootx.yysc.ui.components

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.CarouselEntity
import com.bootx.yysc.ui.theme.fontSize10
import java.io.File
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperItem(items: List<CarouselEntity>) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = {
        items.size
    })
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top,
    ) { page ->
        Box(

        ) {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
                    .height(160.dp)
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                model = items[page].image,
                contentDescription = ""
            )
            Box(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 24.dp)
            ) {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray),
                    model = items[page].logo,
                    contentDescription = ""
                )
            }
            if(items[page].title1.isNotEmpty()){
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 80.dp, bottom = 40.dp)
                ) {
                    Text(text = items[page].title1, color = Color.White, fontSize = fontSize10)
                }
            }
            if(items[page].title2.isNotEmpty()){
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 80.dp, bottom = 24.dp)
                ) {
                    Text(text = items[page].title2, color = Color.White, fontSize = fontSize10)
                }
            }
            Box(
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 24.dp)
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.Center),
                    onClick = {
                        val manager = DownloadManager.Builder(context as Activity).run {
                            apkUrl(items[page].downloadUrl).smallIcon(R.drawable.qiandao)
                            apkName(items[page].title1+".apk")
                            apkVersionName("v4.2.2")
                            apkSize("82.05MB")
                            apkDescription("更新描述信息(取服务端返回数据)")
                            build()
                        }
                        manager.download()
                    },
                ){
                    Text(text = "下载")
                }
            }
        }
    }
}