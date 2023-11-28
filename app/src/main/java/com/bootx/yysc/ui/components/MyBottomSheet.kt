package com.bootx.yysc.ui.components

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyBottomSheet(open: Boolean=false,onClose:(open: Boolean)->Unit,content:@Composable ColumnScope.() -> Unit) {
    val sheetState = rememberModalBottomSheetState(if(open)ModalBottomSheetValue.Expanded else ModalBottomSheetValue.Hidden);
    LaunchedEffect(sheetState.currentValue) {
        println(sheetState.currentValue)
        onClose(sheetState.currentValue==ModalBottomSheetValue.Expanded)
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = MaterialTheme.shapes.large.copy(bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)),
        sheetContent = content,
    ){

    }
}
