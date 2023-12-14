package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.viewmodel.MemberViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(
    navController: NavHostController,
    id: String,
    memberViewModel: MemberViewModel = viewModel()
) {
    val context = LocalContext.current
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        memberViewModel.loadUserInfo(context, "1")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://img.zcool.cn/community/014e3f55444ef80000019ae9290dd5.jpg",
                contentScale = ContentScale.FillBounds,
                contentDescription = ""
            )
        }
        Box(Modifier.background(Color.Transparent)) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ), title = { /*TODO*/ }, navigationIcon = {
                        LeftIcon {
                            navController.popBackStack()
                        }
                    }, actions = {
                        IconButton(onClick = {
                            navController.navigate(Destinations.SearchFrame.route)
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                    })
                }
            ) {
                Surface(
                    Modifier.padding(it),
                    contentColor = Color.Transparent,
                ) {
                    LazyColumn(
                        Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                    ) {
                        item{
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = { /*TODO*/ },
                                leadingContent = {
                                    SoftIcon8(
                                        url = "${memberViewModel.memberInfo.avatar}",
                                    )
                                },
                                trailingContent = {
                                    OutlinedButton(onClick = { /*TODO*/ }) {
                                        Text(text = "取消关注")
                                    }
                                }
                            )
                        }
                        item{
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = { 
                                                  Text(text = "${memberViewModel.memberInfo.username}")
                                    Tag(text = "${memberViewModel.memberInfo.rankName}")
                                },
                            )
                        }
                    }
                }
            }
        }
    }

}