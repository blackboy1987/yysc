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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.GifImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.components.ad.requestRewardAd
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.viewmodel.SupportViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(navController: NavHostController,supportViewModel: SupportViewModel= viewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        supportViewModel.loadAd(context)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle(text = "支持应用")},
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
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
                verticalArrangement = Arrangement.Top,
            ) {
                GifImage(url = "https://img.zcool.cn/community/0132255a604c8da80120121f36db45.gif",height = 300.dp)
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
                            append("${supportViewModel.loadAdEntity.times}")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("次")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "已获得${supportViewModel.loadAdEntity.point}硬币")
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { requestRewardAd(context,onClose={type->
                    Log.i("requestRewardAd", "SupportScreen: ${type}")
                    CommonUtils.toast(context,"${type}")
                    if(type=="loadRewardAdFail"){
                        CommonUtils.toast(context,"视频播放失败")
                    }else if(type=="playRewardVideoCompleted"){
                        coroutineScope.launch {
                            supportViewModel.reward(context)
                            supportViewModel.loadAd(context)
                        }
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
