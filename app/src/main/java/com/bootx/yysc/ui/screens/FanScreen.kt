package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.FanViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class
)
@Composable
fun FanScreen(navController: NavHostController,type:Int,fanViewModel: FanViewModel= viewModel()) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("我的关注", "我的粉丝")
    var selectedTabIndex by remember { mutableIntStateOf(type) }

    LaunchedEffect(Unit){
        // 加载粉丝列表
        fanViewModel.list(SharedPreferencesUtils(context).get("token"),selectedTabIndex)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "我的关注和粉丝${selectedTabIndex}")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                SecondaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.focusRestorer(),
                    tabs = {
                        tabs.forEachIndexed { index, item ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = {
                                    selectedTabIndex = index
                                }) {
                                Text(
                                    text = item,
                                    fontSize = fontSize14,
                                    modifier = Modifier.padding(16.dp),
                                )
                            }
                        }
                    }
                )
                AnimatedContent(
                    targetState = selectedTabIndex,
                    label = "AnimatedContent",
                    transitionSpec = {
                        (slideInVertically { height -> height } + fadeIn()).togetherWith(
                            slideOutVertically { height -> -height } + fadeOut())
                    },
                ) { index ->
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(fanViewModel.list) { item ->
                            ListItem(
                                leadingContent = {
                                    SoftIcon6(url = "${item.avatar}")
                                },
                                headlineContent = {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "${item.username}",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        Tag(text = "lv.1")
                                    }
                                },
                                supportingContent = { Text(text = "坐拥${item.point}枚硬币") },
                                trailingContent = {
                                    Button(onClick = {
                                        coroutineScope.launch {
                                            fanViewModel.delete(SharedPreferencesUtils(context).get("token"),item.id,selectedTabIndex)
                                        }
                                    }) {
                                        Text(text = "取关")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
