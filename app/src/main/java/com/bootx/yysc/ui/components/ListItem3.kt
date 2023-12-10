package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
fun ListItem3(list: List<SoftEntity>, showDownload: Boolean = true, onDownload: (id: Int) -> Unit,onClick:(id: Int)->Unit) {
    Column {
        list.forEachIndexed { _, item ->
            Row(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(padding8)
                    .clickable {
                        onClick(item.id)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SoftIcon6_8(url = item.logo)
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.name,
                        fontSize = MaterialTheme.typography.titleMedium.copy(fontSize = fontSize14).fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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
                if (showDownload) {
                    Spacer(modifier = Modifier.width(8.dp))
                    DownloadButton {
                        onDownload(item.id)
                    }
                }

            }
        }
    }

}
