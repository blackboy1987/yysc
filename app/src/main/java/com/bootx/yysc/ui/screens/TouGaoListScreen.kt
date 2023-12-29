package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
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
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.Loading
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.TouGaoListViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TouGaoListScreen(
    navController: NavHostController,
    touGaoListViewModel: TouGaoListViewModel = viewModel()
) {
    val context = LocalContext.current
    var loading by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    fun refresh() = refreshScope.launch {
        touGaoListViewModel.list(
            SharedPreferencesUtils(context).get("token"),
            1,
            20,
            selectedTabIndex
        );
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()

    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            touGaoListViewModel.list(
                SharedPreferencesUtils(context).get("token"),
                touGaoListViewModel.pageNumber,
                20,
                selectedTabIndex
            );
        }
    }
    val tabs = listOf("全部", "已上架", "审核中", "未通过", "草稿箱")
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var sheetStateType by remember {
        mutableStateOf(0)
    }

    var touGaoInfoId by remember {
        mutableStateOf(0)
    }
    var touGaoInfoStatus by remember {
        mutableStateOf(-1)
    }

    LaunchedEffect(Unit) {
        // 加载数据
        touGaoListViewModel.list(
            SharedPreferencesUtils(context).get("token"),
            1,
            20,
            selectedTabIndex
        );
        // 加载统计信息
        touGaoListViewModel.loadInfo(SharedPreferencesUtils(context).get("token"))
    }

    fun update(type: Int) {
        coroutineScope.launch {
            loading = true
            touGaoListViewModel.update(
                SharedPreferencesUtils(context).get("token"),
                touGaoInfoId,
                type
            )
            sheetState.hide()
            sheetStateType = 0
            touGaoListViewModel.list(
                SharedPreferencesUtils(context).get("token"),
                1,
                20,
                selectedTabIndex,
            )
            loading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = {
                    Column {
                        TopBarTitle(text = "应用投稿")
                        Text(
                            text = "欢迎投稿",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    sheetStateType = 0
                    if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Item(
                        title = "${touGaoListViewModel.touGaoInfo.downloads ?: "0"}",
                        title2 = "总下载",
                        modifier = Modifier.weight(1.0f)
                    )
                    MyDivider1()
                    Item(
                        title = "${touGaoListViewModel.touGaoInfo.reviewCount ?: "0"}",
                        title2 = "评价数",
                        modifier = Modifier.weight(1.0f)
                    )
                    MyDivider1()
                    Item(
                        title = "${touGaoListViewModel.touGaoInfo.reviewCount1 ?: "0"}",
                        title2 = "五星好评",
                        modifier = Modifier.weight(1.0f)
                    )
                    MyDivider1()
                    Item(
                        title = "${touGaoListViewModel.touGaoInfo.donationIcon ?: "0"}",
                        title2 = "投币数量",
                        modifier = Modifier.weight(1.0f)
                    )
                }
                SecondaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .focusRestorer()
                        .padding(8.dp),
                    tabs = {
                        tabs.forEachIndexed { index, item ->
                            Tab(
                                selected = selectedTabIndex == index, onClick = {
                                    selectedTabIndex = index
                                    coroutineScope.launch {
                                        lazyListState.animateScrollToItem(1)
                                        touGaoListViewModel.list(
                                            SharedPreferencesUtils(context).get(
                                                "token"
                                            ), 1, 20, index
                                        );
                                    }
                                }) {
                                Text(
                                    text = item,
                                    fontSize = fontSize14,
                                    modifier = Modifier.padding(8.dp),
                                )
                            }
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .pullRefresh(state)
                        .padding(8.dp)
                ) {
                    if (touGaoListViewModel.listLoaded) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    AnimatedContent(
                        targetState = selectedTabIndex,
                        label = "AnimatedContent",
                        transitionSpec = {
                            (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                                slideOutVertically { height -> -height } + fadeOut())
                        },
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = lazyListState,
                        ) {
                            items(touGaoListViewModel.list) { item ->
                                ListItem(
                                    modifier = Modifier.clickable {
                                        coroutineScope.launch {
                                            sheetStateType = 1
                                            touGaoInfoId = item.id
                                            touGaoInfoStatus = item.status
                                            if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                                        }
                                    },
                                    leadingContent = {
                                        SoftIcon4(url = item.logo ?: "")
                                    },
                                    headlineContent = {
                                        Text(text = item.name ?: "")
                                    },
                                    supportingContent = {
                                        Text(
                                            buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                                    )
                                                ) {
                                                    append(item.versionName ?: "")
                                                }
                                                append(" - ")
                                                withStyle(
                                                    style = SpanStyle(
                                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                                                    )
                                                ) {
                                                    append(item.createdDate)
                                                }
                                            }
                                        )
                                    },
                                    trailingContent = {
                                        Text(text = item.statusInfo ?: "")
                                    }
                                )
                            }
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetElevation = 0.dp,
        sheetState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                if (sheetStateType == 0) {
                    Text(
                        text = "应用投稿",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "选择安装包位置",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "手机存储",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    }
                    TextButton(onClick = {
                        navController.navigate(Destinations.TouGaoAppInfoListFrame.route)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "已安装应用",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "取消",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else if (sheetStateType == 1) {
                    Text(
                        text = "内容下载管理器",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextButton(
                        onClick = {
                            sheetStateType = 0
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "更新版本",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    TextButton(onClick = {
                        sheetStateType = 0
                    }, modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "更新信息",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    if (touGaoInfoStatus == 100 || touGaoInfoStatus == 2) {
                        // 草稿，未通过
                        TextButton(onClick = {
                            update(0)
                        }, modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "重新提交",
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    } else if (touGaoInfoStatus == 0 || touGaoInfoStatus == 1) {
                        TextButton(onClick = {
                            // 全部，已上架，审核中
                            update(1)
                        }, modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "取消审核",
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                    TextButton(onClick = {
                        coroutineScope.launch {
                            update(2)
                        }
                    }, modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "删除应用",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        sheetShape = RoundedCornerShape(4.dp),
        scrimColor = ModalBottomSheetDefaults.scrimColor
    ) {

    }

    if (loading) {
        Loading("加载中");
    }
}