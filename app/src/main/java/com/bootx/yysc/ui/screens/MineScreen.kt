package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "硬币")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "关注")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "粉丝")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "付费")
                            }
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "硬币")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "关注")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "粉丝")
                            }
                            Divider(
                                color = Color(0xFFAEAEAE), modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1.0f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "15")
                                Text(text = "付费")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

        }
    }
}