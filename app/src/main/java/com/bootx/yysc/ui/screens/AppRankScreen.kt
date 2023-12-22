package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TabRowList
import com.bootx.yysc.viewmodel.AppRankViewModel
import com.bootx.yysc.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRankScreen(
    navController: NavHostController,
    appRankViewModel: AppRankViewModel = viewModel(),
) {
    val context = LocalContext.current
    var selectIndex by remember {
        mutableStateOf(0)
    }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        appRankViewModel.appRank(context)
        Log.e("appRankViewModel", "AppRankScreen: ${appRankViewModel.list.toString()}", )
    }
    if(appRankViewModel.list.isNotEmpty()){
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                expanded = !expanded
                            }
                        ) {
                            Text(
                                text = "${appRankViewModel.list[selectIndex].title}",
                                fontSize = MaterialTheme.typography.titleMedium.fontSize
                            )
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            offset = DpOffset(0.dp, (-56).dp)
                        ) {
                            appRankViewModel.list.mapIndexed { index,item->
                                DropdownMenuItem(text = { Text(text = "${item.title}") }, onClick = {
                                    selectIndex = index
                                    selectedTabIndex = 0
                                    expanded = false
                                })
                            }
                        }
                    },
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
                if(appRankViewModel.list.isNotEmpty() && appRankViewModel.list[selectIndex].children.isNotEmpty()){
                    TabRowList(tabs = appRankViewModel.list[selectIndex].children.map { item->item.title }, selectedTabIndex = selectedTabIndex, onClick = {index->
                        selectedTabIndex = index
                    })
                }
            }
        }
    }
}