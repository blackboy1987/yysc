package com.bootx.yysc.model.entity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bootx.yysc.config.Config
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.MyBottomSheet
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.TopBarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SysMsgScreen(navController: NavHostController) {

    val show = remember {
        mutableStateOf(false)
    }

    val title = remember {
        mutableStateOf("是否全部已读")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle(text = "系统消息") },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    Icon(modifier = Modifier
                        .clickable {
                            show.value = true
                            title.value = "是否全部已读"
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp),
                        imageVector = Icons.Filled.MarkEmailRead,
                        contentDescription = "")
                    Icon(modifier = Modifier
                        .clickable {
                            show.value = true
                            title.value = "是否删除全部消息"
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp),
                        imageVector = Icons.Filled.ClearAll,
                        contentDescription = "")
                }
            )

        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(4) {
                    ListItem(
                        modifier = Modifier.clickable {

                        },
                        headlineContent = { Text(text = "签到奖励") },
                        supportingContent = { Text(text = "签到奖励硬币5枚") },
                        leadingContent = {
                            SoftIcon4(url = "${Config.imageUrl}avatar/${it + 10}.png")
                        },
                        trailingContent = {
                            Text(text = "8天前")
                        }
                    )
                }
            }
        }
    }
    MyBottomSheet(open = show.value, onClose = {
        show.value = it
    }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "提示",
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = title.value,
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    OutlinedButton(
                        contentPadding = PaddingValues(horizontal = 32.dp),
                        onClick = { show.value = false }) {
                        Text(text = "取消")
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Button(
                        contentPadding = PaddingValues(horizontal = 32.dp),
                        onClick = { show.value = false }) {
                        Text(text = "已读")
                    }
                }
            }
        }
    }
}