package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize14

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Demo() {
    val tabs = listOf("我的关注", "我的粉丝")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "我的关注和粉丝")
                },
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
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(2) { item ->
                        ListItem(
                            headlineContent = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "abc",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Card(
                                        modifier = Modifier.padding(0.dp),
                                        shape = RoundedCornerShape(8.dp),
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp),
                                            text = "lv.1", fontSize = 10.sp, fontStyle = FontStyle.Italic
                                        )
                                    }
                                }
                            },
                            supportingContent = { Text(text = "坐拥2234枚硬币") },
                        )
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewDemo() {
    Demo()
}