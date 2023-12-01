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
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.bootx.yysc.model.entity.DownloadManager
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape4
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun SoftItemRank(soft: SoftEntity, index: Int,onClick:()->Unit,onDownload:()->Unit,showRank: Boolean=true) {
    Row(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .height(100.dp)
            .fillMaxWidth()
            .padding(padding8)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(showRank){
            RankIndex(index)
        }
        AsyncImage(
            model = soft.logo,
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = soft.name, fontSize = fontSize14, color = Color(0xFF050505))
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
                    text = soft.score,
                    fontSize = fontSize12,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196f3),
                )
                Text(
                    text = "1.0.1",
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
                    text = soft.memo,
                    fontSize = fontSize12,
                    color = Color(0xFF8d9195),
                    modifier = Modifier.padding(start = padding8)
                )
            }
        }
        Button(
            colors = ButtonColors(
                containerColor = Color(0xFFf2f1f6),
                contentColor = Color(0xFF030304),
                disabledContainerColor = Color(0xFFf2f1f6),
                disabledContentColor = Color(0xFF030304),
            ),
            modifier = Modifier
                .height(32.dp)
                .align(Alignment.CenterVertically),
            onClick = {
                onDownload()
            }) {
            Text(text = "下载", fontSize = fontSize12, textAlign = TextAlign.Center)
        }
    }
}