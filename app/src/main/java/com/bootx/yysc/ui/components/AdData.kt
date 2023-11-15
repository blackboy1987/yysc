package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AdData() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "最新活动", fontSize = 14.sp, color = Color(0xFF050505))
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(100) {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp),
                ) {
                    AsyncImage(
                        model = "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA15mchH.img",
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .alpha(0.7f)
                            .clip(RoundedCornerShape(16.dp)),
                    )
                    Text(
                        text = "官方交流群",
                        fontSize=16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .padding(vertical = 32.dp),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewAdData() {
    AdData()
}