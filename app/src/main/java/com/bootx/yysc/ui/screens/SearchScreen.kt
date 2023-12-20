package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.config.Config
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.SearchData
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.SoftIcon8_8
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.components.TabRowList
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.components.ad.RequestBannerAd
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.HotSearchViewModel
import com.bootx.yysc.viewmodel.SearchViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    softViewModel: SoftViewModel = viewModel(),
    hotSearchViewModel: HotSearchViewModel = viewModel(),
    searchViewModel: SearchViewModel = viewModel()
) {
    val context = LocalContext.current
    val storeManager = StoreManager(context)
    val coroutineScope = rememberCoroutineScope()
    var hotList by remember { mutableStateOf(listOf<SoftEntity>()) }
    var searchStatus by remember { mutableStateOf(false) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var keywords by remember {
        mutableStateOf("")
    }

    fun refresh() = refreshScope.launch {
        refreshing = true
        searchViewModel.search(context, keywords, selectedTabIndex, 1)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            if (searchViewModel.hasMore) {
                searchViewModel.search(
                    context,
                    keywords,
                    selectedTabIndex,
                    searchViewModel.pageNumber
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        //获取热搜应用
        hotList = softViewModel.orderBy(context, 1, 20, "8")
        // 热门搜索
        hotSearchViewModel.fetchList(context)

        searchViewModel.type(context)
    }
    val gson = Gson()

    val searchResult = storeManager.get("keywords").collectAsState(initial = "[]")

    fun add(value: String) {
        val type = object : TypeToken<List<String>>() {}.type
        val list: List<String> = try {
            Gson().fromJson(searchResult.value, type)
        } catch (e: Exception) {
            listOf()
        }
        val newList = list.filter { text -> text != value }
        val softList = mutableListOf<String>()
        softList.add(value)
        softList.addAll(newList)
        coroutineScope.launch {
            storeManager.save("keywords", gson.toJson(softList))
        }
    }

    fun get(): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        val list: List<String> = try {
            Gson().fromJson(searchResult.value, type)
        } catch (e: Exception) {
            listOf()
        }
        return list
    }

    fun clear() {
        coroutineScope.launch {
            storeManager.save("keywords", gson.toJson(listOf<String>()))
        }
    }

    fun search(keyword: String) {
        keywords = keyword
        add(keywords)
        searchStatus = true
        coroutineScope.launch {
            searchViewModel.search(context, keywords, selectedTabIndex, 1)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = keywords,
                        textStyle = TextStyle.Default,
                        onValueChange = {
                            keywords = it
                        },
                        trailingIcon = {
                            if (keywords.isNotEmpty()) Icon(
                                modifier = Modifier.clickable {
                                    keywords = ""
                                    searchStatus = false
                                },
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                            if (keywords.isNotEmpty()) IconButton(onClick = {
                                keywords = ""
                                searchStatus = false
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "请输入关键词")
                        },
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        )
                    )
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (keywords.isNotEmpty()) {
                            search(keywords)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                        )
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {

            if (searchStatus) {
                TabRowList(
                    tabs = searchViewModel.typeList,
                    selectedTabIndex = selectedTabIndex,
                    onClick = { index ->
                        selectedTabIndex = index
                        coroutineScope.launch {
                            searchViewModel.search(context, keywords, index, 1)
                            lazyListState.animateScrollToItem(0)
                        }
                    })
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 64.dp)
                        .fillMaxHeight()
                        .padding(top = 16.dp)
                        .pullRefresh(state),
                ) {
                    LazyColumn(
                        state = lazyListState,
                    ) {
                        if (selectedTabIndex == 0) {
                            items(searchViewModel.list) { item ->
                                SoftItem1(item = item)
                            }
                        } else if (selectedTabIndex == 1) {
                            items(searchViewModel.list) { item ->
                                UserItem(item = item, onClick = { type ->
                                    coroutineScope.launch {
                                        if (type == 0) {
                                            // 关注
                                            searchViewModel.userType(context, item.id, type)
                                        } else if (type == 1) {
                                            // 取消关注
                                            searchViewModel.userType(context, item.id, type)
                                        } else if (type == 2) {
                                            // 用户详细页面
                                            navController.navigate(Destinations.MemberFrame.route + "/${item.id}")
                                        }
                                    }
                                })
                            }
                        } else if (selectedTabIndex == 2) {
                            items(200) {
                                SoftItem(item = SoftEntity())
                            }
                        } else if (selectedTabIndex == 3) {
                            items(searchViewModel.list) { item ->
                                UserItem(item = item, onClick = { type ->
                                    coroutineScope.launch {
                                        if (type == 0) {
                                            // 关注
                                            searchViewModel.userType(context, item.id, type)
                                        } else if (type == 1) {
                                            // 取消关注
                                            searchViewModel.userType(context, item.id, type)
                                        } else if (type == 2) {
                                            // 用户详细页面
                                            navController.navigate(Destinations.MemberFrame.route + "/${item.id}")
                                        }
                                    }
                                })
                            }
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                    RequestBannerAd(context = context)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    val get = get().filter { text -> text.isNotEmpty() }
                    if (get.isNotEmpty()) {
                        item {
                            History(
                                title = "搜索记录",
                                allowClear = true,
                                list = get,
                                clear = { clear() },
                                onSearch = { value ->
                                    search(value)
                                })
                        }
                    }
                    if (hotSearchViewModel.list.size > 0) {
                        item {
                            History(
                                title = "热门搜索",
                                allowClear = false,
                                list = hotSearchViewModel.list.map { item -> item.name },
                                clear = { clear() },
                                onSearch = { value ->
                                    coroutineScope.launch {
                                        search(value)
                                    }
                                })
                        }
                    }
                    if (hotList.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "热搜应用")
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow() {
                                items(hotList) {
                                    Card(
                                        modifier = Modifier
                                            .width(100.dp)
                                            .background(Color.Transparent),
                                        colors = CardColors(
                                            containerColor = Color.Transparent,
                                            contentColor = CardDefaults.cardColors().contentColor,
                                            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
                                            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
                                        )
                                    ) {
                                        AsyncImage(
                                            model = it.logo,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .padding(
                                                    start = 0.dp,
                                                    top = 8.dp,
                                                    end = 8.dp,
                                                    bottom = 8.dp,
                                                )
                                                .size(80.dp)
                                                .align(Alignment.CenterHorizontally)
                                        )
                                        Text(
                                            text = it.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun History(
    title: String,
    allowClear: Boolean = false,
    list: List<String>,
    clear: () -> Unit,
    onSearch: (keyword: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        if (allowClear) {
            Text(text = "清空", modifier = Modifier
                .padding(4.dp)
                .clickable {
                    clear()
                })
        }
    }
    FlowRow(
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Center
    ) {
        list.forEach { s ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onSearch(s)
                    },
            ) {
                Text(
                    text = s,
                    fontSize = fontSize12,
                    maxLines = 1,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun Item2(item: SearchData) {
    Column {
        Text(text = "主标题", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.typography.bodySmall.color,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                ) {
                    append("3.0 M")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.typography.bodySmall.color,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                ) {
                    append(" * ")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.typography.bodySmall.color,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                ) {
                    append("2023-04-23")
                }
            }
        )
    }
}


@Composable
fun UserItem(item: SearchData, onClick: (type: Int) -> Unit) {
    ListItem(modifier = Modifier.clickable {
        onClick(2)
    }, headlineContent = {
        Text(text = "${item.username}")
    }, supportingContent = {
        Text(text = "坐拥${item.point}枚硬币")
    }, leadingContent = {
        SoftIcon6(url = "${item.avatar}")
    }, trailingContent = {
        if (item.isConcern > 0) {
            OutlinedButton(onClick = {
                onClick(1)
            }) {
                Text(text = "取关")
            }
        } else {
            OutlinedButton(onClick = {
                onClick(0)
            }) {
                Text(text = "关注")
            }
        }

    })
}

@Composable
fun SoftItem1(item: SearchData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SoftIcon8_8(url = "${item.logo}")
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(start = 16.dp)
        ) {
            Text(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                text = "${item.name}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Row(
                modifier = Modifier.padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier.padding(end = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = ""
                    )
                    Text(
                        text = "${item.score}",
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                }
                Text(
                    text = "${item.versionName}",
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Tag(text = "官方")
                Text(
                    text = "${item.size}",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
                Text(text = "${item.memo}", fontSize = MaterialTheme.typography.labelSmall.fontSize)
            }
        }
        OutlinedButton(onClick = {}) {
            Text(text = "下载")
        }
    }
}