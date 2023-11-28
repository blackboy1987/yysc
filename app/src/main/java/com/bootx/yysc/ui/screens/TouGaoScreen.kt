package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.AppInfoUtils
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
    touGaoViewModel: TouGaoViewModel = viewModel()
) {
    val context = LocalContext.current
    val appInfo by remember {
        mutableStateOf<AppInfo>(
            AppInfoUtils.getAppInfo(
                context,
                packageName
            )
        )
    }
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("基本信息", "详细信息", "应用基因")
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    var categoryId0 by remember {
        mutableStateOf(0)
    }
    var category1 by remember {
        mutableStateOf(listOf<CategoryEntity>())
    }
    var categoryId1 by remember {
        mutableStateOf(0)
    }
    var images by remember {
        mutableStateOf(listOf<Uri?>())
    }

    LaunchedEffect(Unit) {
        touGaoViewModel.categoryList()
        categoryId0 = touGaoViewModel.categories[0].id
        category1 = touGaoViewModel.categories[0].children
        categoryId1 = category1[0].id
    }

    var imageType by remember { mutableIntStateOf(0) }

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
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    item{
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
                    }
                    item{
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
                    }
                    if(selectedTabIndex==0){
                        item {
                            Text(text = "类别")
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                                    .background(Color(0xFFf4f4f4))
                                    .padding(8.dp)
                            ) {
                                touGaoViewModel.categories.forEach { item ->
                                    CategoryItem(text = item.name, selected = categoryId0 == item.id, onClick = {
                                        coroutineScope.launch {
                                            categoryId0 = item.id
                                            category1 = item.children
                                            categoryId1 = category1[0].id
                                        }
                                    })
                                }
                            }
                        }
                        item{
                            Text(text = "渠道")
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                                    .background(Color(0xFFf4f4f4))
                                    .padding(8.dp)
                            ) {
                                touGaoViewModel.categories.forEach { item ->
                                    CategoryItem(text = item.name, selected = categoryId0 == item.id, onClick = {
                                        coroutineScope.launch {
                                            categoryId0 = item.id
                                            category1 = item.children
                                        }
                                    })
                                }
                            }
                        }
                        item {
                            Text(text = "分区")
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                                    .background(Color(0xFFf4f4f4))
                                    .padding(8.dp)
                            ) {
                                category1.forEach { item ->
                                    CategoryItem(text = item.name, selected = categoryId1 == item.id, onClick = {
                                        coroutineScope.launch {
                                            categoryId1 = item.id
                                        }
                                    })
                                }
                            }
                        }
                    }else if(selectedTabIndex==1){
                        item{
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(8.dp)
                                    )
                                    .background(Color(0xFFf4f4f4))
                                    .padding(8.dp)
                            ) {
                                CategoryItem("竖屏",imageType == 0, onClick = {
                                    coroutineScope.launch {
                                        imageType = 0
                                    }
                                })
                                CategoryItem("横屏",imageType == 1, onClick = {
                                    coroutineScope.launch {
                                        imageType = 1
                                    }
                                })
                            }
                        }
                        item{
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        item{
                            LazyRow(){
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                item{
                                    ImageView1(context)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }
                        item{
                            Text(text = "应用标题")
                            OutlinedTextField(value = "abc", onValueChange = {})
                        }
                        item{
                            Text(text = "投稿说明")
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "abc",
                                onValueChange = {},
                                maxLines = 8,
                                minLines = 8,

                            )
                        }
                        item{
                            Text(text = "应用介绍")
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "abc",
                                onValueChange = {},
                                maxLines = 8,
                                minLines = 8,

                                )
                        }
                        item{
                            Text(text = "投稿说明")
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "abc",
                                onValueChange = {},
                                maxLines = 8,
                                minLines = 8,

                                )
                        }
                    }else if(selectedTabIndex==2){
                        item{
                            Text(text = "该应用是否包含广告")
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "无广告")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "少量广告")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "超过广告")
                            }

                        }
                        item{
                            Text(text = "该应用是否有付费内容")
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "完全免费")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "会员制")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "没钱不给用")
                            }
                        }
                        item{
                            Text(text = "该应用的运营方式")
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "企业开发")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "独立开发")
                            }
                        }
                        item{
                            Text(text = "该应用有什么闪光点")
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "白嫖")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "Material Design")
                                RadioButton(selected = true, onClick = { /*TODO*/ })
                                Text(text = "神作")
                            }
                        }
                    }

                    item{
                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(text: String,selected: Boolean,onClick:()->Unit){
    Card(
        modifier = Modifier
            .padding(
                start = 0.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
            .clickable {
                onClick()
            },
        colors = if (selected) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ) else CardDefaults.cardColors()
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 2.dp),
            text = text,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ImageView1(context: Context){
    var image by remember {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            image = uri
            Log.e("ImageView1", "ImageView1: ${uri.toString()}", )
        }
    )

    /*if (imageUrlList.value.isNotEmpty()) {
        coroutineScope.launch {
            val file = UploadUtils.uri2File(imageUrlList.value[0], context)
            if (file != null) {
                val url = UploadUtils.uploadImage(file)
                Log.e("TouGaoScreen", "TouGaoScreen: $url")
            } else {
                Log.e("TouGaoScreen", "TouGaoScreen: file is null")
            }
        }
    }*/
    Box(
        modifier = Modifier
            .clickable {
                galleryLauncher.launch("image/*")
            }
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xfff4f4f4))
            .width(162.dp)
            .height(288.dp)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ){
        Icon(modifier = Modifier.size(40.dp), imageVector = Icons.Filled.Add, contentDescription = "")
        AsyncImage(contentScale= ContentScale.FillBounds, model = image, contentDescription = "")
    }
}