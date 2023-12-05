package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.theme.fontSize14

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class
)
@Composable
fun FanScreen(navController: NavHostController) {
    val tabs = listOf("我的关注", "我的粉丝")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "我的关注和粉丝")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
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
                            Tab(selected = selectedTabIndex == index, onClick = {
                                selectedTabIndex = index
                            }) {
                                Text(
                                    text = item,
                                    fontSize = fontSize14,
                                    modifier = Modifier.padding(4.dp),
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
                ) {index->
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        items(100){ index->
                            ListItem(
                                leadingContent = {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(CircleShape),
                                        model = "https://www.iprompt.art/images/1.jpg",
                                        contentDescription = "",
                                    )
                                },
                                headlineContent = {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "奇谈君",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        Tag(text = "lv.1")
                                    }
                                },
                                supportingContent = { Text(text = "坐拥91枚硬币") },
                                trailingContent = {
                                    Button(onClick = { /*TODO*/ }) {
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
