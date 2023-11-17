package com.bootx.yysc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bootx.yysc.ui.theme.fontSize12


data class Item0Model(
    val title: String,
    val icon: ImageVector,
)

var item0List = listOf<Item0Model>(
    Item0Model("应用投稿", Icons.Filled.Email),
    Item0Model("应用更新", Icons.Filled.Email),
    Item0Model("我的收藏", Icons.Filled.Email),
    Item0Model("签到", Icons.Filled.Email),
    Item0Model("下载管理", Icons.Filled.Email),
    Item0Model("支持一下", Icons.Filled.Email),
    Item0Model("排行榜", Icons.Filled.Email),
    Item0Model("邀请福利", Icons.Filled.Email),
    Item0Model("我的讨论", Icons.Filled.Email),
    Item0Model("我的帖子", Icons.Filled.Email),
    Item0Model("我的关注", Icons.Filled.Email),
    Item0Model("我的评分", Icons.Filled.Email),
    Item0Model("我的称号", Icons.Filled.Email),
    Item0Model("精选风格", Icons.Filled.Email),
    Item0Model("主题风格", Icons.Filled.Email),
    Item0Model("交流群组", Icons.Filled.Email),
)

@Composable
fun Item0(item: Item0Model) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(4.dp),
    ) {
        AsyncImage(
            modifier = Modifier.size(32.dp),
            model = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/yysc/res/jp.png",
            contentDescription = ""
        )
        Text(text = item.title, fontSize = fontSize12)
    }
}