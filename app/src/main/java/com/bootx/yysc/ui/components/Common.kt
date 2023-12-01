package com.bootx.yysc.ui.components

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14

@Composable
fun RightIcon(onClick:()->Unit) {
    Icon(
        modifier = Modifier.clickable {
            onClick()
        }.padding(8.dp).size(16.dp),
        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
        contentDescription = "",
    )
}

@Composable
fun LeftIcon(onClick:()->Unit) {
    Icon(
        modifier = Modifier.clickable {
            onClick()
        }.size(16.dp),
        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
        contentDescription = "",
    )
}

@Composable
fun DownloadButton(onClick:()->Unit) {
    Button(
        modifier = Modifier.padding(0.dp).height(36.dp).width(80.dp),
        onClick = onClick,
    ) {
        Text(
            text = "下载",
            fontSize = fontSize12,
        )
    }
}

@Composable
fun CardTitle(text: String) {
    Text(text = text, fontSize = fontSize14)
}

@Composable
fun TopBarTitle(text: String) {
    Text(text = text, fontSize = MaterialTheme.typography.titleMedium.fontSize)
}

@Composable
fun SoftIcon(url: String) {
    AsyncImage(
        modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon12(url: String) {
    AsyncImage(
        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(10.dp)),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon8(url: String) {
    AsyncImage(
        modifier = Modifier.size(80.dp).clip(CircleShape),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon6(url: String) {
    AsyncImage(
        modifier = Modifier.size(60.dp).clip(CircleShape),
        model = url,
        contentDescription = ""
    )
}
@Composable
fun SoftIcon4(url: String) {
    AsyncImage(
        modifier = Modifier.size(40.dp).clip(CircleShape),
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