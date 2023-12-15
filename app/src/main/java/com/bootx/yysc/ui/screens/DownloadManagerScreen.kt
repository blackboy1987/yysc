package com.bootx.yysc.ui.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.DownloadButton
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.viewmodel.DownloadManagerViewModel
import com.bootx.yysc.viewmodel.DownloadViewModel
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun DownloadManagerScreen(
    navController: NavHostController,
    downloadManagerViewModel: DownloadManagerViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var alertDialogShow by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        downloadManagerViewModel.list(context)
    }

    Scaffold(topBar = {
        TopAppBar(title = { TopBarTitle(text = "下载管理") }, navigationIcon = {
            LeftIcon {
                navController.popBackStack()
            }
        }, actions = {
            IconButton(onClick = {
                alertDialogShow = true
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        })
    }) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                items(downloadManagerViewModel.list) { item ->
                    ListItem(modifier = Modifier.clickable {
                        navController.navigate(Destinations.AppDetailFrame.route + "/${item.id}")
                    }, headlineContent = {
                        Text(text = "${item.name ?: ""}")
                    }, leadingContent = {
                        SoftIcon6(url = "${item.logo ?: ""}")
                    }, supportingContent = {
                        Text(text = "${item.packageName ?: ""}")
                    }, trailingContent = {
                        DownloadButton(title = "安装", onClick = {
                            coroutineScope.launch {
                                downloadViewModel.install(context, item.path)
                            }
                        })
                    })
                }
            }
        }
        if (alertDialogShow) {
            AlertDialog(
                icon = {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Example Icon")
                },
                title = {
                    Text(text = "提示")
                },
                text = {
                    Text(
                        text = "是否清空下载列表",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                onDismissRequest = {
                    alertDialogShow = false
                },
                confirmButton = {
                    Button(
                        onClick = {
                            alertDialogShow = false
                            coroutineScope.launch {
                                downloadManagerViewModel.removeAll(context)
                            }
                        }
                    ) {
                        Text("清空")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = {
                            alertDialogShow = false
                        }
                    ) {
                        Text("取消")
                    }
                }
            )
        }
    }
}