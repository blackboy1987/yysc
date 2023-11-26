package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.ui.components.ad.RequestBannerAd
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.ShareUtils
import com.bootx.yysc.viewmodel.SoftViewModel
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class, ExperimentalLayoutApi::class, ExperimentalFoundationApi::class
)
@Composable
fun AppDetailScreen(
    navController: NavHostController,
    id: String,
    softViewModel: SoftViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val state1 = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var selectedIndex by remember {
        mutableIntStateOf(1)
    }
    val context = LocalContext.current
    val showTitle = remember {
        mutableStateOf(false)
    }

    val softDetail = remember {
        mutableStateOf<SoftDetailEntity>(SoftDetailEntity())
    }

    LaunchedEffect(Unit) {
        softDetail.value = softViewModel.detail("61862")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = softDetail.value.name) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = ""
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                TextButton(onClick = { /*TODO*/ }) {
                    Column(
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                state.show()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.CurrencyBitcoin, contentDescription = "")
                        Text(text = "投币")
                    }
                }
                Button(modifier = Modifier.weight(1.0f), onClick = { /*TODO*/ }) {
                    Text(text = "下载")
                }
                TextButton(onClick = {
                    val shareAppList = ShareUtils.getShareAppList(context)
                    Log.e("shareAppList", "AppDetailScreen: ${shareAppList.toString()}")
                }) {
                    Column(
                        modifier = Modifier.clickable {
                            ShareUtils.shareText(context,"abc")
                        }
                    )  {
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
                item {
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
                item {
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
                item {
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
                item {
                    LazyRow() {
                        items(softDetail.value.images) { image ->
                            AsyncImage(
                                modifier = Modifier
                                    .width(162.dp)
                                    .height(288.dp)
                                    .padding(horizontal = 8.dp, vertical = 16.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.FillBounds,
                                model = image, contentDescription = ""
                            )
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
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RequestBannerAd(context = context)
                    }
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "分享者")
                        },
                        trailingContent = {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape),
                                    model = "https://profile-avatar.csdnimg.cn/9848118595564203baa263ac8ec3459a_ozhuimeng123.jpg",
                                    contentDescription = ""
                                )
                                Text(text = "湯姆")
                            }
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "应用详情")
                        },
                        trailingContent = {
                            Row(
                                modifier = Modifier.clickable {
                                    navController.navigate(Destinations.AppDetail1Frame.route + "/1234")
                                },
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "详情")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "应用版本")
                        },
                        trailingContent = {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "beta0.02(2)")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "举报它")
                        },
                        trailingContent = {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "去举报")
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                }
                item{
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetState = state, sheetContent = {
            Column {
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    listOf<Int>(1, 5, 10, 20, 66, 99, 999).forEach { count ->
                        ElevatedButton(colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = if (selectedIndex === count) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                        ), modifier = Modifier.padding(4.dp), onClick = {
                            selectedIndex = count
                        }) {
                            Text(text = "${count}枚")
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item {
                        Text(text = "留言信息")
                        OutlinedTextField(
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "选填，留下你想说的话把~") },
                            value = "",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                            ),
                            onValueChange = {

                            })
                    }
                    item {
                        Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                            Text(text = "送上硬币")
                        }
                    }
                }
            }
        }) {

    }

}