package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.AppInfoUtils
import com.bootx.yysc.util.UploadUtils
import com.bootx.yysc.viewmodel.TouGaoViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class
)
@Composable
fun TouGaoScreen(
    navController: NavHostController,
    packageName: String,
    touGaoViewModel: TouGaoViewModel= viewModel()
) {
    val context = LocalContext.current
    var appInfo by remember {
        mutableStateOf<AppInfo>(
            AppInfoUtils.getAppInfo(
                context,
                packageName
            )
        )
    }
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("基本信息", "详细信息", "应用基因")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var categoryId0 by remember {
        mutableStateOf(0)
    }

    val imageUrlList = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uriList ->
            imageUrlList.value = uriList
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = {
                    TopBarTitle(text = "为止")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "添加网盘地址")
                    }
                }
            )
        },
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
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item {
                        Text(text = "列表")
                        FlowRow() {
                            repeat(2) { count ->
                                Card(
                                    modifier = Modifier
                                        .padding(
                                            start = 0.dp,
                                            top = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp
                                        )
                                        .clickable {
                                            coroutineScope.launch {
                                                categoryId0 = count
                                            }
                                        },
                                    colors = if(categoryId0==count) CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ) else CardDefaults.cardColors()
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        text = "应用软件:$count",
                                    )
                                }
                            }
                        }
                    }
                }
                Surface(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Button(onClick = {
                        galleryLauncher.launch("image/*")
                        if (imageUrlList.value.isNotEmpty()) {
                            coroutineScope.launch {
                                val file = UploadUtils.uri2File(imageUrlList.value[0], context)
                                if (file != null) {
                                    val url = UploadUtils.uploadImage(file)
                                    Log.e("TouGaoScreen", "TouGaoScreen: $url")
                                } else {
                                    Log.e("TouGaoScreen", "TouGaoScreen: file is null")
                                }
                            }
                        }
                    }) {
                        Text(text = "类别")
                    }
                }
            }
        }
    }
}
