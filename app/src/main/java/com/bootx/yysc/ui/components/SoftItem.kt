package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.SoftEntity

@Composable
fun SoftItem(soft: SoftEntity){
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = soft.logo,
            contentDescription = ""
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = soft.name, color = Color(0xFF050505))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "",
                    tint = Color(0xFF2196f3),
                )
                Text(
                    text = "9.7",
                    fontWeight= FontWeight.Bold,
                    color = Color(0xFF2196f3),
                )
                Text(
                    text = "1.0.1",
                    color= Color(0xFF8d9195),
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF2196f3))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ){
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Filled.Anchor,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF),
                    )
                    Text(text = "星标",color= Color.White, fontSize = 12.sp)
                }
                Text(text = "2013条评论",color= Color(0xFF8d9195), modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}