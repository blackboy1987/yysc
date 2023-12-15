package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape4

@Composable
fun Item3(
    item: SoftEntity,
    showDownload: Boolean = true,
    onDownload: (id: Int) -> Unit,
    onClick: (id: Int) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            onClick(item.id)
        },
        headlineContent = {
            Text(
                text = item.name,
                fontSize = MaterialTheme.typography.titleMedium.copy(fontSize = fontSize14).fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingContent = {
            SoftIcon6(url = item.logo)
        },
        trailingContent = {
            if (showDownload) {
                DownloadButton {
                    onDownload(item.id)
                }
            }
        },
        supportingContent = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF2196f3),
                    )
                    Text(
                        text = item.score,
                        fontSize = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize12).fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196f3),
                    )
                    Text(
                        text = item.versionName ?: "",
                        fontSize = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize12).fontSize,
                        color = Color(0xFF8d9195),
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(shape4))
                            .height(22.dp)
                            .background(Color(0xFF2196f3)),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(12.dp)
                                .padding(start = 4.dp),
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            tint = Color(0xFFFFFFFF),
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            modifier = Modifier.padding(end = 4.dp),
                            text = "星标",
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize12).fontSize,
                        )
                    }
                    Text(
                        text = item.memo,
                        fontSize = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize12).fontSize,
                        color = Color(0xFF8d9195),
                        modifier = Modifier.padding(start = padding8)
                    )
                }
            }
        }
    )
}
