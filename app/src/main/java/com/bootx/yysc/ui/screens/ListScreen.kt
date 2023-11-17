package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.DownloadManager
import com.bootx.yysc.ui.components.NetWorkError
import com.bootx.yysc.ui.components.ServerError
import com.bootx.yysc.ui.theme.backgroundColor
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape4
import com.bootx.yysc.util.NetWorkUtils
import com.bootx.yysc.viewmodel.SoftViewModel
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavHostController,title: String,orderBy: String,vm: SoftViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val connected = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        // 发起网络请求
        vm.fetchList(1,20,orderBy)
        connected.value = NetWorkUtils.isConnected(context)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it).fillMaxHeight(),
            color = backgroundColor,
        ) {
            if(!connected.value){
                NetWorkError()
            }else{
                if(vm.listLoadedErrorData.isNotEmpty()){
                    ServerError()
                }else{
                    LazyColumn(
                    ) {
                        items(1000){
                            Row(
                                modifier = Modifier
                                    .background(Color(0xFFFFFFFF))
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(padding8)
                                    .clickable {
                                               coroutineScope.launch {
                                                   DownloadManager.download(
                                                       "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/yysc/res/-E.png",
                                                       File(context.getExternalFilesDir("")?.absolutePath + "/zx1001.txt")
                                                   )
                                               }
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "$it", modifier = Modifier.width(40.dp), textAlign = TextAlign.Center,)
                                AsyncImage(
                                    model = "https://pp.myapp.com/ma_icon/0/icon_52575843_1694568765/256",
                                    contentDescription = "",
                                    modifier = Modifier.size(60.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = "和平精英", fontSize = fontSize14, color = Color(0xFF050505))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = "",
                                            modifier = Modifier.size(12.dp),
                                            tint = Color(0xFF2196f3),
                                        )
                                        Text(
                                            text = "9.7",
                                            fontSize = fontSize12,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2196f3),
                                        )
                                        Text(
                                            text = "1.0.1",
                                            fontSize = fontSize12,
                                            color = Color(0xFF8d9195),
                                            modifier = Modifier.padding(start = 8.dp),
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(shape4))
                                                .width(44.dp)
                                                .height(22.dp)
                                                .background(Color(0xFF2196f3)),
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(10.dp),
                                                imageVector = Icons.Filled.Anchor,
                                                contentDescription = "",
                                                tint = Color(0xFFFFFFFF),
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(text = "星标", color = Color.White, fontSize = fontSize10)
                                        }
                                        Text(
                                            text = "2013条评论",
                                            fontSize = fontSize12,
                                            color = Color(0xFF8d9195),
                                            modifier = Modifier.padding(start = padding8)
                                        )
                                    }
                                }
                                Button(
                                    colors = ButtonColors(
                                        containerColor = Color(0xFFf2f1f6),
                                        contentColor = Color(0xFF030304),
                                        disabledContainerColor = Color(0xFFf2f1f6),
                                        disabledContentColor = Color(0xFF030304),
                                    ),
                                    modifier = Modifier
                                        .height(32.dp)
                                        .align(Alignment.CenterVertically),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "下载", fontSize = fontSize12, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
