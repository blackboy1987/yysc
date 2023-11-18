package com.bootx.yysc.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.CarouselEntity
import com.bootx.yysc.ui.theme.fontSize10
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperItem(items: List<CarouselEntity>) {
    Log.e("SwiperItem",items.toString())
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
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
                    .height(160.dp)
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        Log.e("HomeScreen", pagerState.currentPage.toString())
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
                        .height(32.dp)
                        .align(Alignment.Center),
                    onClick = { /*TODO*/ },
                ){
                    Text(text = "下载")
                }
            }
        }
    }
}