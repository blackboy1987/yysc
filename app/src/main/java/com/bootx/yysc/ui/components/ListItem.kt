package com.bootx.yysc.ui.components

import androidx.compose.runtime.Composable
import com.bootx.yysc.model.entity.SoftEntity

@Composable
fun ListItem(type: Int,list: List<SoftEntity>,onDownload:(id: Int)->Unit) {
    when(type){
        1-> ListItem1(list,onDownload)
        2-> ListItem2(list,onDownload)
        3-> ListItem3(list, true,onDownload)
        4-> ListItem4(1)
    }
}
