package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ListItem
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.AppInfoUtils
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TouGaoScreen(
    navController: NavHostController,
    packageName: String,
) {
    val current = LocalContext.current
    var appInfo by remember {
        mutableStateOf<AppInfo>(
            AppInfoUtils.getAppInfo(
                current,
                packageName
            )
        )
    }
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("基本信息", "详细信息", "应用基因")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = {

                },
                navigationIcon = {
                    Icon(modifier = Modifier.clickable {
                        navController.popBackStack()
                    }, imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                },
                actions = {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "添加网盘地址")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                ListItem(
                    headlineContent = {
                        Text(
                            text = appInfo.appName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    supportingContent = {
                        Text(
                            text = appInfo.packageName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    overlineContent = {
                        Text(
                            text = "${appInfo.appBytes}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingContent = {
                        AsyncImage(
                            modifier = Modifier.size(80.dp),
                            model = appInfo.appIcon,
                            contentDescription = ""
                        )
                    }
                )

                SecondaryTabRow(
                    divider = @Composable {

                    },
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .focusRestorer()
                        .padding(8.dp),
                    tabs = {
                        tabs.forEachIndexed { index, item ->
                            Tab(selected = selectedTabIndex == index, onClick = {
                                selectedTabIndex = index
                                coroutineScope.launch {
                                    // lazyListState.animateScrollToItem(1)
                                }
                            }) {
                                Text(
                                    text = item,
                                    fontSize = fontSize14,
                                    modifier = Modifier.padding(4.dp),
                                )
                            }
                        }
                    }
                )
                Box(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "${appInfo.appName}:$selectedTabIndex")
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetElevation = 0.dp,
        sheetState = sheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "应用投稿",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "选择安装包位置",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "手机存储",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
            }
            TextButton(onClick = {
                navController.navigate(Destinations.TouGaoAppInfoListFrame.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "已安装应用",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "取消", modifier = Modifier.fillMaxWidth())
            }
        },
        modifier = Modifier.fillMaxWidth(),
        sheetShape = RoundedCornerShape(4.dp),
        scrimColor = ModalBottomSheetDefaults.scrimColor
    ) {

    }
}
