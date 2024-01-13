package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "爱尚应用",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                    Text(
                        text = "欢迎回来，共聚此刻",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )
                    MyInput(value = username, leadingIcon = Icons.Default.Email, onChange = {
                        username = it
                    }, placeholder = { Text(text = "邮箱或用户名") })
                    Spacer(modifier = Modifier.height(8.dp))
                    MyPasswordInput(value = password, onChange = {
                        password = it
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "忘记密码",
                        textAlign = TextAlign.End,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        enabled = username.isNotEmpty() && password.isNotEmpty() && !loginViewModel.loading,
                        onClick = {
                            coroutineScope.launch {
                                loginViewModel.login(context, username, password)
                                if (loginViewModel.data.token.isBlank()) {
                                    toast(context, "用户不存在！")
                                } else {
                                    val sharedPreferencesUtils = SharedPreferencesUtils(context)
                                    sharedPreferencesUtils.set("token", loginViewModel.data.token)
                                    navController.navigate(Destinations.MainFrame.route + "/0")
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "登录")
                    }
                    Text(text = "或者", modifier = Modifier.padding(vertical = 8.dp))
                    OutlinedButton(onClick = {
                        navController.navigate(Destinations.RegisterFrame.route)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "账号注册")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
    if (loginViewModel.loading) {
        Loading("登录中...")
    }
}

