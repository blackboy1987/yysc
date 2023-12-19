package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.config.Config
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TabRowList
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.HotSearchViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SearchScreen(
    navController: NavHostController,
    softViewModel: SoftViewModel = viewModel(),
    hotSearchViewModel: HotSearchViewModel = viewModel()
) {
    val context = LocalContext.current
    val storeManager = StoreManager(context)
    val coroutineScope = rememberCoroutineScope()
    var hotList by remember { mutableStateOf(listOf<SoftEntity>()) }
    var searchStatus by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        //获取热搜应用
        hotList = softViewModel.orderBy(context, 1, 20, "8")
        // 热门搜索
        hotSearchViewModel.fetchList(context)
        Log.e("LaunchedEffect", "SearchScreen: ${hotSearchViewModel.list.size}")
    }
    val gson = Gson()
    var keywords by remember {
        mutableStateOf("")
    }
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

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    OutlinedTextField(
                        value = keywords,
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
                        },
                        placeholder = {
                            Text(text = "请输入关键词")
                        },
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                    )
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                add(keywords)
                                searchStatus = true
                            }
                        }
                    )
                }
            )
        }
    ) {
        val tabs = listOf("站内", "全网", "广场", "用户")
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        Surface(modifier = Modifier.padding(it)) {

            if (searchStatus) {
                TabRowList(tabs = tabs, selectedTabIndex = selectedTabIndex, onClick = { index ->
                    selectedTabIndex = index
                })
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {

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
                                    coroutineScope.launch {
                                        add(value)
                                        keywords = value
                                        searchStatus = true
                                    }
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
                                        add(value)
                                        keywords = value
                                        searchStatus = true
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

