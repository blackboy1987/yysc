package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.MyInput
import com.bootx.yysc.ui.components.MyPasswordInput
import com.bootx.yysc.ui.components.toast
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ShowToast")
@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()

    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "爱尚应用",
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 64.dp)
            )
            Text(
                text = "欢迎回来，共聚此刻",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            MyInput(value = username, onChange = {
                username = it
            })
            Spacer(modifier = Modifier.height(8.dp))
            MyPasswordInput(value = password, onChange = {
                password = it
            })
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "忘记密码", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
            Button(
                enabled = username.isNotEmpty() && password.isNotEmpty() && !loginViewModel.loading,
                onClick = {
                    coroutineScope.launch {
                        loginViewModel.login(username, password)
                        if (loginViewModel.data.token.isBlank()) {
                            toast(context, "用户不存在！")
                        }else{
                            val sharedPreferencesUtils = SharedPreferencesUtils(context)
                            sharedPreferencesUtils.set("token",loginViewModel.data.token)
                            navController.navigate(Destinations.HomeFrame.route)
                        }
                    }
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

