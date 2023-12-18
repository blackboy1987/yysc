package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6_8
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.viewmodel.MineViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController, mineViewModel: MineViewModel = viewModel()) {
    val context = LocalContext.current
    var coroutineScope = rememberCoroutineScope();
    val lazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        mineViewModel.load(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "浏览历史") },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            mineViewModel.clearHistory(context)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription ="")
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(),
        ) {
            LazyColumn(
                state = lazyListState,
            ) {
                itemsIndexed(mineViewModel.list) { index, item ->
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Destinations.AppDetailFrame.route + "/${item.id}")
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.White
                        ),
                        headlineContent = {
                            Text(text = "${item.name}", minLines = 1, overflow = TextOverflow.Ellipsis)
                        },
                        supportingContent = {
                            Text(text = "${item.packageName}", minLines = 1, overflow = TextOverflow.Ellipsis)
                        },
                        leadingContent = {
                            SoftIcon6_8("${item.logo}")
                        },
                        trailingContent = {
                            Text(
                                text = "${CommonUtils.getDayInfo(item.updateDate)}",
                                minLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                    )
                }
            }
        }
    }
}