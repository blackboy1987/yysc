package com.bootx.yysc.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bootx.yysc.extension.onBottomReached
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScrollList(
    list: List<Any>,
    reload: () -> Unit,
    loadMore: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        Log.e("refresh", "refresh: ", )
        refreshing = true
        reload()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            loadMore()
        }
    }
    Box(
        modifier = Modifier
            .pullRefresh(state)
    ) {
        if(refreshing){
            CircularProgressIndicator()
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
            ){
                items(list){

                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
        }
    }
}