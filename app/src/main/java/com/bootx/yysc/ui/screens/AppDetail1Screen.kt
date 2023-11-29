package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.config.Config
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.SoftViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetail1Screen(
    navController: NavHostController,
    id: String,
    softViewModel: SoftViewModel = viewModel()
) {
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    val token = storeManager.getToken().collectAsState(initial = Config.initToken).value
    val context = LocalContext.current
    val showTitle = remember {
        mutableStateOf(false)
    }

    val softDetail = remember {
        mutableStateOf<SoftDetailEntity>(SoftDetailEntity())
    }

    LaunchedEffect(Unit) {
        softDetail.value = softViewModel.detail(token,id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { if (showTitle.value) Text(text = softDetail.value.name) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }, imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = ""
                    )
                },
            )
        },
    ) {

        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Package Name")
                        },
                        trailingContent = {
                            Text(text = "com.magicalstory.search")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Package Name")
                        },
                        trailingContent = {
                            Text(text = "com.magicalstory.search")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Version")
                        },
                        trailingContent = {
                            Text(text = "1.4.2")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Version Code")
                        },
                        trailingContent = {
                            Text(text = "52")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Target SDK")
                        },
                        trailingContent = {
                            Text(text = "33")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "Min SDK")
                        },
                        trailingContent = {
                            Text(text = "32")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "App FrameWork")
                        },
                        trailingContent = {
                            Text(text = "64Bit,32Bit")
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = {
                            Text(text = "App Size")
                        },
                        trailingContent = {
                            Text(text = "7.79MB")
                        }
                    )
                }
            }
        }
    }

}