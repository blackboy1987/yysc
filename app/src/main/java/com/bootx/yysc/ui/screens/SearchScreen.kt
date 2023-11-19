package com.bootx.yysc.ui.screens

import android.util.Log
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
import androidx.compose.material3.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.HotSearchViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SearchScreen(navController: NavHostController,softViewModel: SoftViewModel= viewModel(),hotSearchViewModel: HotSearchViewModel= viewModel()) {
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()
    var hotList by remember { mutableStateOf(listOf<SoftEntity>()) }

    LaunchedEffect(Unit) {
        //获取分类列表
        hotList = softViewModel.orderBy(1, 20,"8")
        hotSearchViewModel.fetchList()
    }
    val gson = Gson()
    val keywords = remember {
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
                        modifier = Modifier.height(20.dp),
                        value = keywords.value,
                        onValueChange = {
                            keywords.value = it
                        },
                        trailingIcon = {
                            if (keywords.value.isNotEmpty()) Icon(
                                modifier = Modifier.clickable {
                                    keywords.value = ""
                                    coroutineScope.launch {
                                        storeManager.remove("keywords")
                                    }

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
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        )

                    )
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                add(keywords.value)

                                // 搜索
                            }
                        }
                    )
                }
            )
        }
    ) {
        val tabs = listOf("站内", "全网", "广场","用户")
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        Surface(modifier = Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp,),
            ) {
                item{
                    PrimaryTabRow(
                        selectedTabIndex = selectedTabIndex,
                        modifier = Modifier.focusRestorer(),
                        tabs = {
                            tabs.forEachIndexed{index,item->
                                Tab(selected = selectedTabIndex==index, onClick = {
                                    selectedTabIndex = index
                                }) {
                                    Text(text = item)
                                } }
                        }
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "搜索记录")
                        Text(text = "清空", modifier = Modifier.clickable {
                            clear()
                        })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        get().filter { text -> text.isNotEmpty() }.forEach { s ->
                            Card(
                                modifier = Modifier.padding(4.dp),
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
                }
                item{
                    Spacer(modifier = Modifier.height(24.dp))
                }
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "热门搜索")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if(hotSearchViewModel.listLoaded){
                            hotSearchViewModel.list.forEach {
                                Card(
                                    modifier = Modifier.padding(4.dp),
                                ) {
                                    Log.e("SearchScreen", "SearchScreen: ${it.toString()}", )
                                    Text(
                                        text = it.name,
                                        fontSize = fontSize12,
                                        maxLines = 1,
                                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(24.dp))
                }
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "热搜应用")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(){
                        items(hotList){
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


