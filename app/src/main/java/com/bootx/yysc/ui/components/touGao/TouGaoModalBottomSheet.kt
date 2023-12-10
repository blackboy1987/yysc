package com.bootx.yysc.ui.components.touGao


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouGaoModalBottomSheet(onClose: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    scope.launch {
        sheetState.partialExpand()
    }
    ModalBottomSheet(
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp,
        ),
        onDismissRequest = { onClose() },
        dragHandle = {},
        sheetState = sheetState,
        properties = ModalBottomSheetDefaults.properties(
            shouldDismissOnBackPress = true,
        ),
        content = content,
    )
}