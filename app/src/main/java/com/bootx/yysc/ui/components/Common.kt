package com.bootx.yysc.ui.components

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.padding8

@Composable
fun RightIcon(onClick:()->Unit) {
    IconButton(
        onClick={onClick()}
    ){
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "")
    }
}

@Composable
fun LeftIcon(onClick:()->Unit) {
    IconButton(
        onClick={onClick()}
    ){
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "")
    }
}

@Composable
fun DownloadButton(title: String="下载",onClick:()->Unit) {
    Button(
        modifier = Modifier
            .padding(0.dp)
            .height(36.dp)
            .width(80.dp),
        onClick = onClick,
    ) {
        Text(
            text = title,
            fontSize = fontSize12,
        )
    }
}

@Composable
fun CardTitle(text: String,showIcon: Boolean=true,onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(padding8)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = text, fontSize = MaterialTheme.typography.bodyLarge.fontSize, fontWeight = FontWeight.Bold)
        if(showIcon){
            RightIcon {
                onClick()
            }
        }
    }
}

@Composable
fun TopBarTitle(text: String) {
    Text(text = text, fontSize = MaterialTheme.typography.titleMedium.fontSize)
}

@Composable
fun SoftIcon(url: String) {
    AsyncImage(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(8.dp)),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon12(url: String) {
    AsyncImage(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(10.dp)),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon8(url: String) {
    Log.e("SoftIcon8", "SoftIcon8: $url", )
    AsyncImage(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon6(url: String,modifier: Modifier=Modifier) {
    AsyncImage(
        modifier = Modifier
            .then(modifier)
            .size(60.dp)
            .clip(CircleShape),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon6_8(url: String) {
    AsyncImage(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp)),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon4(url: String,modifier: Modifier=Modifier) {
    AsyncImage(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape).then(modifier),
        model = url,
        contentDescription = ""
    )
}


@Composable
fun MyInput(value: String,onChange:(value: String)->Unit){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        value = value,
        onValueChange = {
            onChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
        ),
        singleLine = true,
    )
}

@Composable
fun MyPasswordInput(value: String,onChange:(value: String)->Unit){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        value = value,
        onValueChange = {
            onChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

fun toast(context: Context,message: String){
    val toast =
        Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0)
    toast.show()
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MyTabRow(tabs: List<String>,onClick: (index: Int) -> Unit){
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    SecondaryTabRow(
        divider = @Composable {

        },
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .focusRestorer()
            .padding(horizontal = 8.dp, vertical = 0.dp),
        tabs = {
            tabs.forEachIndexed { index, item ->
                Tab(selected = selectedTabIndex == index, onClick = {
                    selectedTabIndex = index
                    onClick(index)
                }) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    )
}

@Composable
fun Tag(text: String){
    Card(
        modifier = Modifier.padding(0.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
    ) {
        Text(
            modifier = Modifier.height(16.dp).padding(horizontal = 12.dp, vertical = 0.dp),
            text = text, fontSize = 10.sp, fontStyle = FontStyle.Italic,
            lineHeight = 16.sp,
        )
    }

}