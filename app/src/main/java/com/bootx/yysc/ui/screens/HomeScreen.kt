package com.bootx.yysc.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.AdData
import com.bootx.yysc.ui.components.MyCard
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.theme.backgroundColor
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.height16
import com.bootx.yysc.ui.theme.height32
import com.bootx.yysc.ui.theme.height4
import com.bootx.yysc.ui.theme.height8
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.primaryFontColor
import com.bootx.yysc.ui.theme.shape8

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val pagerState = rememberPagerState { 10 }
    val toolbarHeight = 48.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    Surface(
        modifier = Modifier.fillMaxHeight(),
        color = backgroundColor,
    ) {
        val paddingOffset =
            toolbarHeight + with(LocalDensity.current) { toolbarOffsetHeightPx.value.toDp() }
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            contentPadding = PaddingValues(top = paddingOffset)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(20) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(4.dp)
                            .background(if (it % 2 == 0) Color.Black else Color.Yellow),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.toString(),
                            color = if (it % 2 != 0) Color.Black else Color.Yellow
                        )
                    }
                }
            }
        }
        LazyColumn() {
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
                                color = primaryFontColor,
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
                        Text(text = "好评如潮", fontSize = fontSize12, color = primaryFontColor)
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "",
                            tint = Color(0xFFb7b7b7)
                        )
                    }
                    Spacer(modifier = Modifier.height(height16))
                    LazyRow {
                        items(100) {
                            Column(
                                modifier = Modifier
                                    .width(100.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Spacer(modifier = Modifier.height(height8))
                                AsyncImage(
                                    modifier = Modifier.size(80.dp),
                                    model = "https://pp.myapp.com/ma_icon/0/icon_93301_1699860270/256",
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.height(height8))
                                Text(
                                    text = "WiFi万能钥匙",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.height(height8))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = "9.9",
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
                                    Text(text = "下载", fontSize = fontSize12, color = Color.White)
                                }
                                Spacer(modifier = Modifier.height(height8))
                            }
                        }
                    }
                }
            }
            item {
                AdData()
            }
            item {
                MyCard("好评如潮") {
                    LazyRow {
                        items(100) {
                            Column(
                                modifier = Modifier.width(100.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Spacer(modifier = Modifier.height(height8))
                                AsyncImage(
                                    modifier = Modifier.size(80.dp),
                                    model = "https://pp.myapp.com/ma_icon/0/icon_93301_1699860270/256",
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.height(height8))
                                Text(
                                    text = "WiFi万能钥匙",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Spacer(modifier = Modifier.height(height8))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = "9.9",
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
                                    Text(text = "下载", fontSize = fontSize12, color = Color.White)
                                }
                                Spacer(modifier = Modifier.height(height8))
                            }
                        }
                    }
                }
            }
            item {
                MyCard(title = "热门下载") {
                    Column {
                        val soft = SoftEntity(
                            id = 3,
                            name = "abc",
                            size = "12.34M",
                            memo = "memo",
                            logo = "logo",
                            updateDate = "2023=11-17 23:59:59",
                            score = 3,
                            downloadUrl = "downloadUrl",
                            images = "",
                        )
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                        SoftItem(soft)
                    }
                }
            }
            item{
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}
