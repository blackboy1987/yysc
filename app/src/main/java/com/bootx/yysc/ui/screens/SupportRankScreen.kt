package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.viewmodel.SupportRankViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportRankScreen(navController: NavHostController,supportRankViewModel: SupportRankViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit){
        supportRankViewModel.rank(context,1)
    }
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
                itemsIndexed(supportRankViewModel.rankEntityList){index,item->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "${index+1}")
                        ListItem(
                            headlineContent = {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(text = "${item.username}", fontSize = fontSize14)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Tag(text = "${item.rankName}")
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Tag(text = "检察官")
                                }
                            },
                            supportingContent = {
                                Text(
                                    text = "支持了${item.times}次",
                                    fontSize = fontSize12,
                                    color = Color(0xFF9b9b9b)
                                )
                            },
                            leadingContent = {
                                SoftIcon6(url = "${item.avatar}")
                            },
                        )
                    }
                }
            }
        }
    }
}
