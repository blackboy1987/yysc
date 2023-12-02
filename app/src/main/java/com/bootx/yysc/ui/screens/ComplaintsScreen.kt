package com.bootx.yysc.ui.screens

import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon

/**
 * 投诉
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ComplaintsScreen(navController: NavHostController, id: String) {


    val heightPixels = DisplayMetrics().heightPixels
    val widthPixels = DisplayMetrics().widthPixels

    var reason by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "投诉") },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },
                actions = {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "提交")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                ComplaintsList()
                Text(text = "违规描述:${widthPixels}: $heightPixels")
                OutlinedTextField(
                    minLines=7,
                    value = reason,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        reason = it
                })

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                ) {
                    repeat(50){
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                }
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xfff4f4f4))
                                .width(120.dp)
                                .height(120.dp)
                                .padding(4.dp),
                            contentAlignment = Alignment.Center,
                        ){
                            Icon(modifier = Modifier.size(40.dp), imageVector = Icons.Filled.Add, contentDescription = "")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComplaintsList(){
    var index by remember {
        mutableStateOf(1)
    }
    data class Item(
        val key: Int,
        val title: String,
    )

    val list = listOf<Item>(
        Item(
            key=1,
            title="资源违规"
        ),
        Item(
            key=2,
            title="分享过期"
        ),
        Item(
            key=3,
            title="应用闪退"
        ),
        Item(
            key=4,
            title="明显引流"
        ),
        Item(
            key=5,
            title="色情低俗"
        ),
        Item(
            key=6,
            title="信息不全"
        ),
        Item(
            key=7,
            title="应用侵权"
        ),
        Item(
            key=8,
            title="恶意应用"
        ),
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list){item->
            TextButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
                colors = if(index==item.key)ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) else ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
                onClick = { index = item.key }) {
                if(index==item.key){
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp)
                    )
                }
                Text(text = item.title,fontSize=12.sp)
            }
        }
    }
}