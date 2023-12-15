package com.bootx.yysc.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bootx.yysc.model.service.ActivityData
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.height8
import com.bootx.yysc.ui.theme.shape8

@Composable
fun AdData(list:List<ActivityData>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(list) {item->
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .alpha(0.7f)
                        .clip(RoundedCornerShape(shape8)),
                )
                Text(
                    text = item.title,
                    fontSize = fontSize12,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .padding(vertical = 16.dp),
                )
            }
            Spacer(modifier = Modifier.width(height8))
        }
    }
}