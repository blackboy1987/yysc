package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.viewmodel.QunZuViewModel

// QunZu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QunZuScreen(navController: NavHostController, qunZuViewModel: QunZuViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        qunZuViewModel.list(context)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "交流群组")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn() {
                items(qunZuViewModel.list) { item ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ListItem(
                            leadingContent = {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape),
                                    model = "${item.image}",
                                    contentDescription = "",
                                )
                            },
                            headlineContent = {
                                Text(
                                    text = "${item.title1}",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = "${item.title2}",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}
