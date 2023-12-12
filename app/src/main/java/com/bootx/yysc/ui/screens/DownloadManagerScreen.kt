package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.viewmodel.DownloadManagerViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class
)
@Composable
fun DownloadManagerScreen(
    navController: NavHostController,
    downloadManagerViewModel: DownloadManagerViewModel= viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        downloadManagerViewModel.list(context)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { TopBarTitle(text = "下载管理") },
            navigationIcon = {
                LeftIcon {
                    navController.popBackStack()
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }
            }
        )
    }) {

        Box(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "${downloadManagerViewModel.list.size}")
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                items(downloadManagerViewModel.list){item->
                    Text(text = item.name)
                }
            }
        }
    }
}