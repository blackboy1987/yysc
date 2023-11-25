package com.bootx.yysc.ui.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.AppVersion
import com.bootx.yysc.ui.components.MyFullDialog
import com.bootx.yysc.viewmodel.HomeViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController,homeViewModel: HomeViewModel= viewModel()) {
    val isShow = remember {
        mutableStateOf(true)
    }
    val rate = remember {
        mutableStateOf(0.0f)
    }
    var appVersion = remember {
        mutableStateOf(AppVersion())
    }
    var context = LocalContext.current
    LaunchedEffect(Unit) {
        var list = homeViewModel.checkUpdate(context)
        if(list.isNotEmpty()){
            appVersion.value = list[0]
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "设置") }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",

                )
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn {
                items(100) { index ->
                    Text(
                        text = "text: $index",
                        modifier = Modifier.clickable { isShow.value = true })
                }
            }
            if (isShow.value) {
                MyFullDialog(isShow = isShow.value) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White),
                    ) {
                        Box {
                            AsyncImage(
                                modifier = Modifier.fillMaxWidth(),
                                model = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/yysc/res/44.png",
                                contentDescription = ""
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(24.dp)
                        ) {
                            Text(text = "V1.0.3")
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(top = 160.dp)
                                .height(150.dp)
                                .align(Alignment.Center),
                        ) {
                            LazyColumn(
                                modifier = Modifier.padding(16.dp)
                            ){
                                item{
                                    Text(text = appVersion.value.memo?:"")
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(vertical = 16.dp)
                        ) {
                            Button(onClick = {
                                val manager = DownloadManager.Builder(context as Activity).run {
                                    apkUrl("https://storedl1.nearme.com.cn/apk/202311/24/3465ff252196df388e6b7180ff3c31c2.apk")
                                        .smallIcon(R.drawable.qiandao)
                                    apkName("appupdate.apk")
                                        .onDownloadListener(object:OnDownloadListener{
                                            override fun cancel() {
                                                Log.e("downloading", "cancel", )
                                            }

                                            override fun done(apk: File) {
                                                Log.e("downloading", "done", )
                                            }

                                            override fun downloading(max: Int, progress: Int) {
                                                val newRate =( progress+0.0f)/max
                                                if(newRate>rate.value){
                                                    rate.value = newRate
                                                }

                                                Log.e("downloading", "downloading: $progress,$max,$newRate,${rate.value}", )
                                            }

                                            override fun error(e: Throwable) {
                                                Log.e("downloading", "error: ${e.toString()}", )
                                            }

                                            override fun start() {
                                                Log.e("downloading", "start", )
                                            }

                                        })
                                    //同时下面三个参数也必须要设置
                                    apkVersionName("v4.2.2")
                                    apkSize("82.05MB")
                                    apkDescription("更新描述信息(取服务端返回数据)")
                                    //省略一些非必须参数...
                                    build()
                                }
                                manager.download()
                            }) {
                                Text(text = "立即更新:${String.format("%.0f",rate.value*100)}%")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { isShow.value = false },
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }

        }
    }
}