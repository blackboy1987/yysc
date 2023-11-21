package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.Item0
import com.bootx.yysc.ui.components.item0List
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.padding8

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MineScreen(navController: NavHostController) {
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /*TODO*/ },
                    actions = {
                        Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
                        Icon(imageVector = Icons.Filled.ArtTrack, contentDescription = "")
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it),

                ) {
                Column(
                    modifier = Modifier.padding(padding8)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier.weight(1.0f),
                                text = "我的账户",
                                fontWeight = FontWeight.Bold,
                                fontSize = fontSize14
                            )
                            Text(text = "免费广告权益", fontSize = fontSize10)
                            Icon(
                                tint = Color(0xFFAEAEAE),
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Item(title = "15", title2 = "硬币", modifier = Modifier.weight(1.0f))
                            MyDivider1()
                            Item(title = "15", title2 = "关注", modifier = Modifier.weight(1.0f))
                            MyDivider1()
                            Item(title = "15", title2 = "粉丝", modifier = Modifier.weight(1.0f))
                            MyDivider1()
                            Item(title = "15", title2 = "付费", modifier = Modifier.weight(1.0f))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.padding(padding8)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxWidth(),
                            columns = GridCells.Fixed(4),
                            content = {
                                items(item0List) { item ->
                                    Item0(item)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

        }
    }
}

@Composable
fun Item(title: String, title2: String, modifier: Modifier) {
    Column(
        modifier = Modifier.then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = title2, fontSize = MaterialTheme.typography.titleSmall.fontSize,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun MyDivider1() {
    HorizontalDivider(
        color = Color(0xFFAEAEAE), modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}