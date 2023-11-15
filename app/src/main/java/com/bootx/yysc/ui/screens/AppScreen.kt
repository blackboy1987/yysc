package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.viewmodel.AppViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Composable
fun AppScreen(navController: NavHostController,vm: AppViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        //获取分类列表
        Log.e("LaunchedEffect2",vm.listLoaded.toString())
        vm.fetchList(1,100)
        Log.e("LaunchedEffect2",vm.listLoaded.toString())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("应用类别") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Destinations.SearchFrame.route)
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Row {
                LazyColumn(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(top = 16.dp)
                ) {
                    if (vm.listLoaded){
                        vm.categories.forEachIndexed { index, category -> item {
                            CategoryItem(category,category.id==vm.currentIndex){
                                currentIndex->coroutineScope.launch {
                                vm.updateCurrentIndex(currentIndex)
                            }
                            }
                        } }
                    }

                }
                LazyColumn(
                    modifier = Modifier
                        .weight(1F)
                        .padding(top = 16.dp)
                ) {

                    if (vm.softListLoaded){
                        vm.softList.forEachIndexed { index, soft -> item {
                            SoftItem(soft)
                        } }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category:CategoryEntity,selected: Boolean,click:(id: Int)->Unit){
    TextButton(
        modifier = Modifier.width(100.dp),
        onClick = {
            click(category.id)
        },
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 18.dp,
            bottomEnd = 18.dp,
            bottomStart = 0.dp,
        ),
        colors = ButtonDefaults.textButtonColors(
            disabledContainerColor = Color(0xFFe7f9fb),
            disabledContentColor = Color(0xFF14c5cd),
            containerColor = Color.White,
            contentColor = Color(0xFF6c6c6c),
        ),
        enabled = !selected,
    ) {
        Text(
            text = category.name,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SoftItem(soft: SoftEntity){
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = soft.logo,
            contentDescription = ""
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = soft.name, color = Color(0xFF050505))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(0xFF2196f3),
                )
                Text(
                    text = "9.7",
                    fontWeight=FontWeight.Bold,
                    color = Color(0xFF2196f3),
                )
                Text(
                    text = "1.0.1",
                    color=Color(0xFF8d9195),
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF2196f3))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ){
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Filled.Anchor,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF),
                    )
                    Text(text = "星标",color=Color.White, fontSize = 12.sp)
                }
                Text(text = "2013条评论",color=Color(0xFF8d9195), modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}