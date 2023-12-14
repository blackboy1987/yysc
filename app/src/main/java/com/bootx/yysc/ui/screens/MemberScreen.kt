package com.bootx.yysc.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.viewmodel.MemberViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberScreen(navController: NavHostController, id: String,memberViewModel: MemberViewModel= viewModel()) {
    val context = LocalContext.current
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        memberViewModel.loadUserInfo(context,"1")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(
                model = "https://img.zcool.cn/community/014e3f55444ef80000019ae9290dd5.jpg",
                contentScale = ContentScale.FillBounds,
                contentDescription = ""
            )
        }
        Box(modifier = Modifier.fillMaxSize()){
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {

                        },
                        navigationIcon = {
                            LeftIcon {
                                navController.popBackStack()
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                navController.navigate(Destinations.SearchFrame.route)
                            }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            }
                            IconButton(onClick = {
                                showDropdownMenu = true
                            }) {
                                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                            }

                            data class Item(
                                val key: String,
                                val title: String,
                            )

                            val list = listOf(
                                Item(
                                    key = "1",
                                    title = "复制昵称"
                                ),
                                Item(
                                    key = "3",
                                    title = "复制账号"
                                ),
                            )
                            DropdownMenu(
                                offset = DpOffset(0.dp, (-48).dp),
                                expanded = showDropdownMenu,
                                onDismissRequest = {},
                                content = {
                                    list.forEach { item ->
                                        DropdownMenuItem(
                                            onClick = {
                                                showDropdownMenu = false
                                                CommonUtils.copy(context,"abc")
                                            },
                                            text = {
                                                Text(text = item.title)
                                            }
                                        )
                                    }
                                },
                            )
                        }
                    )
                }
            ) {
                Surface(
                    Modifier.padding(it)
                ) {

                }
            }
        }
    }

}