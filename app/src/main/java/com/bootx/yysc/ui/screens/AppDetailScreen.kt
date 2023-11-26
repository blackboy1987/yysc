package com.bootx.yysc.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.ui.components.ad.RequestBannerAd
import com.bootx.yysc.viewmodel.SoftViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AppDetailScreen(navController: NavHostController, id: String,softViewModel: SoftViewModel= viewModel()) {
    val context = LocalContext.current
    val showTitle = remember {
        mutableStateOf(false)
    }

    val softDetail = remember {
        mutableStateOf<SoftDetailEntity>(SoftDetailEntity())
    }

    LaunchedEffect(Unit){
        softDetail.value = softViewModel.detail("61862")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { if(showTitle.value) Text(text = softDetail.value.name) },
                navigationIcon = {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "")
                },
                actions = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    Icon(imageVector = Icons.Filled.Dehaze,contentDescription = "")
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                TextButton(onClick = { /*TODO*/ }) {
                    Column {
                        Icon(imageVector = Icons.Filled.CurrencyBitcoin, contentDescription = "")
                        Text(text = "投币")
                    }
                }
                Button(modifier = Modifier.weight(1.0f), onClick = { /*TODO*/ }) {
                    Text(text = "下载")
                }
               TextButton(onClick = { /*TODO*/ }) {
                   Column {
                       Icon(imageVector = Icons.Filled.Share, contentDescription = "")
                       Text(text = "分享")
                   }
               }
            }
        }
    ) {

        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                item{
                    ListItem(
                        headlineContent = {
                            Text(
                                text = softDetail.value.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        supportingContent = {
                            Text(
                                text = softDetail.value.fullName,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        leadingContent = {
                            AsyncImage(
                                modifier = Modifier.size(60.dp),
                                model = softDetail.value.logo,
                                contentDescription = ""
                            )
                        }
                    )
                }
                item{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = softDetail.value.score)
                            Text(text = "544条评论")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = softDetail.value.size)
                            Text(text = "大小")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = softDetail.value.downloads)
                            Text(text = "下载")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "1841")
                            Text(text = "748人投币")
                        }
                    }
                }
                item{
                    val tabs = listOf("详情", "讨论", "版本")
                    var selectedTabIndex by remember { mutableIntStateOf(0) }
                    SecondaryTabRow(
                        divider = @Composable {

                        },
                        selectedTabIndex = selectedTabIndex,
                        modifier = Modifier
                            .focusRestorer()
                            .padding(horizontal = 8.dp, vertical = 0.dp),
                        tabs = {
                            tabs.forEachIndexed { index, item ->
                                Tab(selected = selectedTabIndex == index, onClick = {
                                    selectedTabIndex = index
                                }) {
                                    Text(
                                        text = item,
                                        modifier = Modifier.padding(4.dp),
                                    )
                                }
                            }
                        }
                    )
                }
                item{
                   LazyRow(){
                        items(softDetail.value.images){image->
                            AsyncImage(
                                modifier = Modifier
                                    .width(162.dp)
                                    .height(288.dp)
                                    .padding(horizontal = 8.dp, vertical = 16.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.FillBounds,
                                model = image, contentDescription = "")
                        }
                    }
                }
                /*item{
                    Text(text = "更新内容")
                    MyWebView(rememberWebViewState(data = softDetail.value.introduce))
                }
                item{
                    Text(text = "关于应用")
                    MyWebView(rememberWebViewState(data = softDetail.value.introduce))
                }*/
                item{
                    RequestBannerAd(context = context)
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "分享者")
                        },
                        trailingContent={
                            Row {
                                AsyncImage(model = "https://profile-avatar.csdnimg.cn/9848118595564203baa263ac8ec3459a_ozhuimeng123.jpg", contentDescription = "")
                                Text(text = "湯姆")
                            }
                        }
                    )
                    ListItem(
                        headlineContent = {
                            Text(text = "应用详情")
                        },
                        trailingContent={
                            Row {
                                Text(text = "详情")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                    ListItem(
                        headlineContent = {
                            Text(text = "应用版本")
                        },
                        trailingContent={
                            Row {
                                Text(text = "beta0.02(2)")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                    ListItem(
                        headlineContent = {
                            Text(text = "举报它")
                        },
                        trailingContent={
                            Row {
                                Text(text = "去举报")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }
            }
        }
    }

}