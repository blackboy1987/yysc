package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.config.Config
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.NetWorkError
import com.bootx.yysc.ui.components.ServerError
import com.bootx.yysc.ui.components.SoftItemRank
import com.bootx.yysc.util.NetWorkUtils
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.DownloadViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListScreen(navController: NavHostController,title: String,orderBy: String,softViewModel: SoftViewModel = viewModel(),downloadViewModel:DownloadViewModel= viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val connected = remember {
        mutableStateOf(true)
    }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    val token = storeManager.getToken().collectAsState(initial = Config.initToken).value
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            refreshing = true
            softViewModel.loadMore(token,orderBy)
            refreshing = false
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        // 发起网络请求
        softViewModel.orderBy(context,1,20,orderBy)
        connected.value = NetWorkUtils.isConnected(context)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(),
        ) {
            if(!connected.value){
                NetWorkError()
            }else{
                if(softViewModel.listLoadedErrorData.isNotEmpty()){
                    ServerError()
                }else{
                    Box(){
                        LazyColumn(
                            state = lazyListState,
                        ) {
                            itemsIndexed(softViewModel.softList){ index, soft ->
                                SoftItemRank(soft = soft, index = index+1,onClick={
                                    Log.e("onClick", "ListScreen: ", )
                                }, onDownload = {
                                    coroutineScope.launch {
                                        downloadViewModel.download(context, soft.id)
                                    }
                                },showRank=(orderBy!="2"))
                            }
                        }
                        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                    }
                }
            }
        }
    }
}
