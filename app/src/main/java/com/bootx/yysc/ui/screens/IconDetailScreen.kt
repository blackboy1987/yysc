package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.MyBottomSheet
import com.bootx.yysc.ui.components.RightIcon
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.TopBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconDetailScreen(navController: NavHostController) {

    val show = remember {
        mutableStateOf(false)
    }

    val title = remember {
        mutableStateOf("是否全部已读")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle(text = "硬币明细") },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
            )

        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(400) {
                    ListItem(
                        modifier = Modifier.clickable {

                        },
                        headlineContent = { Text(text = "投币给小幅") },
                        supportingContent = {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(vertical = 0.dp, horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    model = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/${it + 30}.png",
                                    contentDescription = ""
                                )
                                Text(
                                    text = "爱尚搜索",
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                            }
                        },
                        leadingContent = {
                            SoftIcon6(url = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/${it + 10}.png")
                        },
                        trailingContent = {
                            Column {
                                Text(text = "-1")
                                Text(text = "2天前")
                            }
                        }
                    )
                }
            }
        }
    }
}