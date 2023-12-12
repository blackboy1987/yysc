package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.Loading
import com.bootx.yysc.ui.components.MyInput
import com.bootx.yysc.ui.components.MyPasswordInput
import com.bootx.yysc.ui.components.toast
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen1() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "爱尚应用",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 64.dp)
                )
                Text(
                    text = "欢迎回来，共聚此刻",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
                )
                MyInput(value = username, onChange = {
                    username = it
                })
                Spacer(modifier = Modifier.height(8.dp))
                MyPasswordInput(value = password, onChange = {
                    password = it
                })
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "忘记密码",
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    enabled = username.isNotEmpty() && password.isNotEmpty(),
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "登录")
                }
                Text(text = "或者", modifier = Modifier.padding(vertical = 16.dp))
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "账号注册")
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewLogin() {
    LoginScreen1()
}

