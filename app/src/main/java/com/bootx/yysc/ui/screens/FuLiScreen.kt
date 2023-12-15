package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.FuLiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FuLiScreen(navController: NavHostController,fuLiViewModel: FuLiViewModel= viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit){
        fuLiViewModel.list(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "福利社")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn() {
                items(fuLiViewModel.list) {item->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Card(
                            elevation=  CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(40 / 17f, true),
                                contentScale = ContentScale.FillWidth,
                                model = "${item.image}",
                                contentDescription = "",
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "${item.title1}",
                                maxLines = 1,
                                color = Color(0xff0e0e0e),
                                fontSize= MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 16.dp),
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${item.title2}",
                                color = Color(0xff8b8b8b),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize= MaterialTheme.typography.titleSmall.fontSize,
                                modifier = Modifier.padding(start = 16.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}
