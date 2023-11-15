package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ListData2() {

    val colors = listOf(
        Color(0xFF6F4849),
        Color(0xFFD67940),
        Color(0xFF285185),
        Color(0xFF262220),
        Color(0xFFA15C38),
        Color(0xFFC3A6A0),
        Color(0xFFF7F1F0),
        Color(0xFFF76566),
        Color(0xFF18ACBA),
        Color(0xFFFFA998),
        Color(0xFFFFC397),
        Color(0xFFF7F3F5),
        Color(0xFFE2C2B3),
        Color(0xFF687477),
        Color(0xFF403234),
        Color(0xFF6184C6),
        Color(0xFFFCD752),
        Color(0xFFFCA3B9),
        Color(0xFF41403C),
        Color(0xFFAA210f),
    )



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "新鲜出炉", fontSize = 14.sp, color = Color(0xFF050505))
            Icon(imageVector = Icons.Default.Autorenew, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(100) {
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .width(160.dp)
                        .height(280.dp),
                    colors = CardColors(
                        containerColor = colors[it % colors.size],
                        contentColor = Color.Transparent,
                        disabledContainerColor = colors[it % colors.size],
                        disabledContentColor = Color.Transparent,
                    ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(50.dp)),
                            model = "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA15mchH.img",
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "水印相机打卡机器",
                            color = Color.White,
                            maxLines = 1,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(0.8f),
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "1237人下载",
                            color = Color.White,
                            maxLines = 1,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(0.8f),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewListData2() {
    ListData2()
}