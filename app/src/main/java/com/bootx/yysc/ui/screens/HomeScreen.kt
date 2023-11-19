package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.AdData
import com.bootx.yysc.ui.components.ListData
import com.bootx.yysc.ui.components.MyCard
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.components.SwiperItem
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.height16
import com.bootx.yysc.ui.theme.height32
import com.bootx.yysc.ui.theme.height4
import com.bootx.yysc.ui.theme.height8
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape8
import com.bootx.yysc.viewmodel.CarouselViewModel
import com.bootx.yysc.viewmodel.HomeViewModel
import com.bootx.yysc.viewmodel.SoftViewModel

data class ItemList(
    val icon: Int,
    val title: String,
)

var itemList = listOf<ItemList>(
    ItemList(R.drawable.hot, "热门"),
    ItemList(R.drawable.tougao, "投稿"),
    ItemList(R.drawable.fuli, "福利"),
    ItemList(R.drawable.qunzu, "群组"),
    ItemList(R.drawable.qiandao, "签到"),
)

@Composable
fun HomeScreen(
    navController: NavHostController,
    carouselViewModel: CarouselViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    softViewModel: SoftViewModel = viewModel()
) {

    val todayDownloadList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val todayCommentList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val randomList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }


    LaunchedEffect(Unit) {
        //获取轮播数据
        carouselViewModel.fetchList();
        todayDownloadList.value = softViewModel.orderBy(1, 30, "00")
        todayCommentList.value = softViewModel.orderBy(1, 30, "01")
        randomList.value = softViewModel.orderBy(1, 30, "2")
    }


    Surface(
        modifier = Modifier.fillMaxHeight(),
    ) {

        LazyColumn() {
            item {
                if (carouselViewModel.listLoaded) {
                    SwiperItem(carouselViewModel.carousels)
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .height(90.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    itemList.forEachIndexed { index, itemList ->
                        Column(
                            modifier = Modifier
                                .weight(1.0f)
                                .fillMaxHeight()
                                .clickable {

                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                painter = painterResource(id = itemList.icon),
                                contentDescription = "", tint = Color(0xFFfea928)
                            )
                            Spacer(modifier = Modifier.height(height4))
                            Text(
                                text = itemList.title,
                                fontSize = fontSize12
                            )
                        }
                    }
                }
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
                        Text(text = "好评如潮", fontSize = fontSize12)
                        Icon(
                            modifier = Modifier.clickable {
                                navController.navigate("ListFrame/好评如潮/01")
                            },
                            imageVector = Icons.Default.ArrowForwardIos,
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
                                                text = softEntity.score.toString(),
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
                                                .fillMaxWidth(0.9f)
                                                .height(height32),
                                            onClick = { /*TODO*/ },
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
                AdData()
            }
            item{
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
                        todayDownloadList.value.forEachIndexed { index, softEntity ->
                            SoftItem(
                                softEntity
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
