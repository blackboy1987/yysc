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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape4

@Composable
fun ListItem3(list: List<SoftEntity>, showDownload: Boolean =true, onDownload:(id:Int)->Unit) {
    Column {
        list.forEachIndexed { _, item ->
            Row(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(padding8)
                    .clickable {

                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SubcomposeAsyncImage(
                    loading = {
                        CircularProgressIndicator() // 圆形进度条
                    },
                    model = item.logo,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.name,
                        fontSize = fontSize14,
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
                            fontSize = fontSize12,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196f3),
                        )
                        Text(
                            text = item.versionName?:"位置",
                            fontSize = fontSize12,
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
                                .width(44.dp)
                                .height(22.dp)
                                .background(Color(0xFF2196f3)),
                        ) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                imageVector = Icons.Filled.Anchor,
                                contentDescription = "",
                                tint = Color(0xFFFFFFFF),
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "星标", color = Color.White, fontSize = fontSize10)
                        }
                        Text(
                            text = item.memo,
                            fontSize = fontSize12,
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
