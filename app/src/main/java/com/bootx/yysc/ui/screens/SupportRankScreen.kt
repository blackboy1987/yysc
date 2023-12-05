package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportRankScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(
                        text = "激励排行榜",
                    )
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },

                )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                repeat(1000) { index ->
                    val rank = index + 1
                    val avatar = index % 500
                    item {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = "$rank")
                            ListItem(
                                headlineContent = {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(text = "兔子不是老师", fontSize = fontSize14)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "lv.5",
                                            fontSize = fontSize10,
                                            modifier = Modifier
                                                .clip(
                                                    RoundedCornerShape(12.dp)
                                                )
                                                .height(22.dp)
                                                .width(40.dp)
                                                .background(MaterialTheme.colorScheme.primaryContainer),
                                            textAlign = TextAlign.Center,
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "检察官",
                                            fontSize = fontSize10,
                                            modifier = Modifier
                                                .clip(
                                                    RoundedCornerShape(12.dp)
                                                )
                                                .height(22.dp)
                                                .background(MaterialTheme.colorScheme.primaryContainer)
                                                .padding(horizontal = 8.dp, vertical = 0.dp),
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                },
                                supportingContent = {
                                    Text(
                                        text = "支持了88848次",
                                        fontSize = fontSize12,
                                        color = Color(0xFF9b9b9b)
                                    )
                                },
                                leadingContent = {
                                    AsyncImage(
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(120.0f)),
                                        model = "https://api.multiavatar.com/$avatar.png",
                                        contentDescription = ""
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
