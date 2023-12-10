package com.bootx.yysc.ui.screens

import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.Loading
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.CoilImageEngine
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.util.SystemInfoUtils
import com.bootx.yysc.viewmodel.ComplaintsViewModel
import github.leavesczy.matisse.DefaultMediaFilter
import github.leavesczy.matisse.Matisse
import github.leavesczy.matisse.MatisseContract
import github.leavesczy.matisse.MediaResource
import github.leavesczy.matisse.MimeType
import github.leavesczy.matisse.NothingCaptureStrategy
import kotlinx.coroutines.launch

/**
 * 投诉
 */
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ComplaintsScreen(navController: NavHostController, id: String, complaintsViewModel: ComplaintsViewModel = viewModel()) {
    var context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var loading by remember {
        mutableStateOf(false)
    }
    var imageCount = 50
    var list by remember {
        mutableStateOf(listOf<MediaResource>())
    }
    var isCommit by remember {
        mutableStateOf(false)
    }
    var reason by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf(1)
    }
    val mediaPickerLauncher =
        rememberLauncherForActivityResult(contract = MatisseContract()) { images: List<MediaResource>? ->
            if (!images.isNullOrEmpty()) {
                val mediaResource = images[0]
                val uri = mediaResource.uri
                val path = mediaResource.path
                val name = mediaResource.name
                val mimeType = mediaResource.mimeType
                list = images
                isCommit = reason.isNotBlank() && type >= 1 && list.isNotEmpty()
            } else {
                list = listOf()
                isCommit = false
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle(text = "投诉") },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    Button(onClick = {
                        coroutineScope.launch {
                            loading = true
                            complaintsViewModel.save(context,SharedPreferencesUtils(context).get("token"),list,type,reason,id)
                            loading = false
                            navController.popBackStack()
                        }
                    }, enabled = isCommit) {
                        Text(text = "提交")
                    }
                }
            )
        }
    ) { it ->
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                item {
                    ComplaintsList(selectedKey = type, onClick = {key->
                        type=key
                        isCommit = reason.isNotBlank() && type >= 1 && list.isNotEmpty()
                    })
                }
                item {
                    Text(text = "违规描述:")
                    Box(
                        modifier = Modifier.clickable {
                            Log.e("ComplaintsScreen", "ComplaintsScreen: click", )
                        }
                    ){
                        OutlinedTextField(
                            minLines = 7,
                            value = reason,
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { value ->
                                reason = value
                                isCommit = value.isNotBlank() && type >= 1 && list.isNotEmpty()
                            },
                            keyboardOptions= KeyboardOptions.Default,
                            keyboardActions= KeyboardActions(
                                onDone= {
                                    Log.e("keyboardActions", "keyboardActions: onDone", )
                                },
                                onGo= {
                                    Log.e("keyboardActions", "keyboardActions: onGo", )
                                },
                                onNext= {
                                    Log.e("keyboardActions", "keyboardActions: onNext", )
                                },
                                onPrevious= {
                                    Log.e("keyboardActions", "keyboardActions: onPrevious", )
                                },
                                onSearch= {
                                    Log.e("keyboardActions", "keyboardActions: onSearch", )
                                },
                                onSend= {
                                    Log.e("keyboardActions", "keyboardActions: onSend", )
                                },
                            ),
                        )
                    }
                }
                item {
                    var imageWidth = (SystemInfoUtils.px2dp((SystemInfoUtils.getScreenWidth(context)-80) / 3,context))
                    FlowRow(
                        maxItemsInEachRow=3,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    ) {
                        list.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xfff4f4f4))
                                    .width((imageWidth - 8).dp)
                                    .height((imageWidth - 8).dp)
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                AsyncImage(
                                    model = item.uri,
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds,
                                )
                            }
                        }
                        if (list.size < imageCount) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable {
                                        val matisse = Matisse(
                                            maxSelectable = imageCount,
                                            mediaFilter = DefaultMediaFilter(
                                                supportedMimeTypes = MimeType.ofImage(),
                                                selectedResourceUri = list
                                                    .map { item -> item.uri }
                                                    .toSet(),
                                            ),
                                            imageEngine = CoilImageEngine(),
                                            captureStrategy = NothingCaptureStrategy
                                        )
                                        mediaPickerLauncher.launch(matisse)
                                    }
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xfff4f4f4))
                                    .width((imageWidth - 8).dp)
                                    .height((imageWidth - 8).dp)
                                    .padding(4.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if(loading){
        Loading("提交中")
    }
}

@Composable
fun ComplaintsList(selectedKey: Int,onClick:(key: Int)->Unit) {
    var index by remember {
        mutableStateOf(selectedKey)
    }

    data class Item(
        val key: Int,
        val title: String,
    )

    val list = listOf<Item>(
        Item(
            key = 1,
            title = "资源违规"
        ),
        Item(
            key = 2,
            title = "分享过期"
        ),
        Item(
            key = 3,
            title = "应用闪退"
        ),
        Item(
            key = 4,
            title = "明显引流"
        ),
        Item(
            key = 5,
            title = "色情低俗"
        ),
        Item(
            key = 6,
            title = "信息不全"
        ),
        Item(
            key = 7,
            title = "应用侵权"
        ),
        Item(
            key = 8,
            title = "恶意应用"
        ),
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list) { item ->
            TextButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
                colors = if (index == item.key) ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) else ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = {
                    index = item.key
                    onClick(item.key)
                }) {
                if (index == item.key) {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp)
                    )
                }
                Text(text = item.title, fontSize = 12.sp)
            }
        }
    }
}