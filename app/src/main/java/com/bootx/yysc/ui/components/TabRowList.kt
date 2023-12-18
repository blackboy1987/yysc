package com.bootx.yysc.ui.components;

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.unit.dp
import com.bootx.yysc.ui.theme.fontSize14

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TabRowList(tabs: List<String>,selectedTabIndex: Int,onClick:(selectTabIndex: Int)->Unit) {
    SecondaryTabRow(selectedTabIndex = selectedTabIndex,
        divider = @Composable {

        },
        modifier = Modifier.focusRestorer(),
        tabs = {
            tabs.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                    selected = selectedTabIndex == index,
                    onClick = {
                        onClick(index)
                    }) {
                    Text(
                        text = item,
                        fontSize = fontSize14,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    )
}
