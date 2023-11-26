package com.bootx.yysc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14

@Composable
fun ListItem2(list: List<SoftEntity>, onDownload: (id: Int) -> Unit, onClick: (id: Int) -> Unit) {
    val colors = listOf(
        Color(0xFF6F4849),
        Color(0xFFD67940),
        Color(0xFF285185),
        Color(0xFF262220),
        Color(0xFFA15C38),
        Color(0xFFC3A6A0),
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
            .padding(8.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { index, item ->
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .width(120.dp)
                        .height(200.dp)
                        .clickable {
                            onClick(item.id)
                        },
                    colors = CardColors(
                        containerColor = colors[index % colors.size],
                        contentColor = Color.Transparent,
                        disabledContainerColor = colors[index % colors.size],
                        disabledContentColor = Color.Transparent,
                    ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(50.dp)),
                            model = item.logo,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.name,
                            color = Color.White,
                            maxLines = 1,
                            fontSize = fontSize14,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(0.8f),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.memo,
                            color = Color.White,
                            maxLines = 1,
                            fontSize = fontSize12,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(0.8f),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

}
