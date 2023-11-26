package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.ActivityEntity
import com.bootx.yysc.model.entity.CarouselEntity
import com.bootx.yysc.model.entity.HomeCenterBar
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.AdData
import com.bootx.yysc.ui.components.ListData
import com.bootx.yysc.ui.components.MyCard
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.components.SwiperItem
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.height16
import com.bootx.yysc.ui.theme.height4
import com.bootx.yysc.ui.theme.height8
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape8
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.viewmodel.CarouselViewModel
import com.bootx.yysc.viewmodel.HomeViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import kotlinx.coroutines.launch
import java.io.File

data class ItemList(
    val icon: Int,
    val title: String,
)

@SuppressLint("InvalidColorHexValue")
@Composable
fun HomeScreen(
    navController: NavHostController,
    carouselViewModel: CarouselViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    softViewModel: SoftViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val todayDownloadList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val todayCommentList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val randomList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val homeCenterBarList = remember {
        mutableStateOf(listOf<HomeCenterBar>())
    }

    val activityList = remember {
        mutableStateOf(listOf<ActivityEntity>())
    }


    LaunchedEffect(Unit) {
        //获取轮播数据
        carouselViewModel.fetchList();
        // 中间工具栏
        homeCenterBarList.value = homeViewModel.homeCenterBar();
        // 活动
        activityList.value = homeViewModel.activity();

        todayDownloadList.value = softViewModel.orderBy(1, 30, "00")
        todayCommentList.value = softViewModel.orderBy(1, 30, "01")
        randomList.value = softViewModel.orderBy(1, 30, "2")
    }


    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary),
    ) {

        LazyColumn() {
            item {
                if (carouselViewModel.listLoaded) {
                    SwiperItem(carouselViewModel.carousels)
                }
            }
            item {
                CenterBar(homeCenterBarList.value,navigate = {
                    navController.navigate(it)
                })
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(padding8)
                        .clip(RoundedCornerShape(shape8))
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(padding8)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "好评如潮", fontSize = fontSize14)
                        Icon(
                            modifier = Modifier.clickable {
                                navController.navigate("ListFrame/好评如潮/01")
                            },
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "",
                            tint = Color(0xFFb7b7b7)
                        )
                    }
                    Spacer(modifier = Modifier.height(height16))
                    LazyRow {
                        todayCommentList.value.forEachIndexed { index, softEntity ->
                            run {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .width(100.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        Spacer(modifier = Modifier.height(height8))
                                        AsyncImage(
                                            modifier = Modifier.size(60.dp),
                                            model = softEntity.logo,
                                            contentDescription = ""
                                        )
                                        Spacer(modifier = Modifier.height(height8))
                                        Text(
                                            text = softEntity.name,
                                            maxLines = 1,
                                            fontSize = fontSize12,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        Spacer(modifier = Modifier.height(height8))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(12.dp),
                                                imageVector = Icons.Filled.Star,
                                                contentDescription = "",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                text = softEntity.score,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                color = MaterialTheme.colorScheme.primary,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(height8))
                                        Button(
                                            modifier = Modifier
                                                .fillMaxWidth(0.9f),
                                            onClick = {
                                                coroutineScope.launch {
                                                    download(context,softEntity.id,softViewModel)
                                                }
                                            },
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                                        ) {
                                            Text(
                                                text = "下载",
                                                fontSize = fontSize12,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(height8))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                AdData(activityList.value)
            }
            item {
                MyCard(title = "随心看看", onClick = {
                    navController.navigate("ListFrame/随心看看/2")
                }) {
                    ListData(randomList.value)
                }
                Spacer(modifier = Modifier.height(height16))
            }
            item {
                MyCard(title = "今日下载", onClick = {
                    navController.navigate("ListFrame/今日下载/00")
                }) {
                    Column {
                        todayDownloadList.value.forEachIndexed { _, softEntity ->
                            SoftItem(
                                softEntity,
                                onDownload = {
                                    coroutineScope.launch {
                                        download(context,softEntity.id,softViewModel)
                                    }
                                }
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

}


@Composable
fun CenterBar(list:List<HomeCenterBar>,navigate:(path: String)->Unit){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        list.forEachIndexed { _, itemList ->
            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxHeight()
                    .clickable {
                        when (itemList.name) {
                            "签到" -> navigate(Destinations.SignInFrame.route)
                            "群组" -> navigate(Destinations.QunZuFrame.route)
                            "福利" -> navigate(Destinations.FuLiFrame.route)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AsyncImage(
                    modifier = Modifier.size(48.dp),
                    model = itemList.image,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(height4))
                Text(
                    text = itemList.name,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

suspend fun download(context:Context, id:Int, softViewModel: SoftViewModel) {

    // 接口请求下载地址
    val downloadInfo = softViewModel.download(id)
    var data = downloadInfo.data
    if(downloadInfo.code==0 && data!=null && data.downloadUrl.isNotEmpty()){

        val manager = DownloadManager.Builder(context as Activity).run {
            apkUrl(data.downloadUrl).smallIcon(R.drawable.qiandao)
            apkName(data.name+".apk").onDownloadListener(object: OnDownloadListener{
                override fun cancel() {
                    Log.e("download", "cancel: ", )
                }

                override fun done(apk: File) {
                    Log.e("download", "cancel: ${apk.name}", )
                }

                override fun downloading(max: Int, progress: Int) {
                    Log.e("download", "cancel: ", )
                }

                override fun error(e: Throwable) {
                    Log.e("download", "error: ${e.toString()}", )
                }

                override fun start() {
                    Log.e("download", "start: ", )
                }

            })
            apkVersionName(data.versionName)
            apkSize(data.size)
            apkDescription("更新描述信息(取服务端返回数据)")
            build()
        }
        manager.download()
    }else{
        CommonUtils.toast(context,"暂无下载地址")
    }

}