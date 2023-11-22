package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.bootx.yysc.ui.components.ad.requestInteractionAd
import com.bootx.yysc.ui.components.ad.requestRewardAd
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "支持奇妙应用",
                        fontSize = fontSize14,
                        color = Color(0xFF505050)
                    )
                },
                navigationIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                SubcomposeAsyncImage(
                    loading = {
                        CircularProgressIndicator() // 圆形进度条
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(240.0f)),
                    model = "https://img.zcool.cn/community/0132255a604c8da80120121f36db45.gif",
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("1")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("次")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "已获得5硬币")
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { requestRewardAd(context,onClose={status->
                    run {
                        Log.i("requestInteractionAd", "SupportScreen: $status")
                    }
                }) }) {
                    Text(text = "观看激励广告")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate(Destinations.SupportRankFrame.route)
                }) {
                    Text(text = "用户排行榜")
                }
            }
            Box{
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 40.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "每次成功观看广告，将会奖励一定额的硬币", fontSize = fontSize12)
                }
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "真心感谢您的支持", fontSize = fontSize12)
                }
            }
        }
    }
}
