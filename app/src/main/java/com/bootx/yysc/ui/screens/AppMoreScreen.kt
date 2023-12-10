package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.SoftViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMoreScreen(
    navController: NavHostController,
    id: String,
    softViewModel: SoftViewModel = viewModel(),
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        softViewModel.more(SharedPreferencesUtils(context).get("token"), id)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { TopBarTitle(text = softViewModel.softDetail.name) },
            navigationIcon = {
                LeftIcon {
                    navController.popBackStack()
                }
            },
        )
    }) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "Package Name")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.packageName)
                        }
                    )
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "Version")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.versionName)
                        }
                    )
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "Version Code")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.versionCode)
                        }
                    )
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "Target SKD")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.targetSdkVersion)
                        }
                    )
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "Min SDK")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.minSdkVersion)
                        }
                    )
                }
                item{
                    ListItem(
                        headlineContent = {
                            Text(text = "App Size")
                        },
                        trailingContent = {
                            Text(text = softViewModel.softDetail.size)
                        }
                    )
                }
            }
        }
    }
}