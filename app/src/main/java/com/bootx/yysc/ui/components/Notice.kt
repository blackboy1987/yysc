package com.bootx.yysc.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Notice(title: String) {
   ListItem(
       headlineContent = { Text(text = title) },
       leadingContent = {
           Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
       },
       trailingContent = {
           RightIcon {

           }
       }
   )
}