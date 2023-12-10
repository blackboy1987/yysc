package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.R
import com.bootx.yysc.ui.AnimatedButton
import com.bootx.yysc.ui.theme.YYSCTheme
import com.bootx.yysc.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen23(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    YYSCTheme {
        Surface(
            color = Color.White,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ){
                AsyncImage(
                    model = "https://bpic.588ku.com/back_pic/05/82/50/235c41d4847aa0b.jpg",
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Card(
                    modifier = Modifier.background(Color.White),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape =  MaterialTheme.shapes.medium
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(12.dp),
                    ) {
                        val username = remember {
                            mutableStateOf("")
                        }
                        val password = remember {
                            mutableStateOf("")
                        }

                        val color1 = Color(0xFF9ea5b8)
                        val color2 = Color(0xFFd4d6d5)
                        val colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = color1,
                            unfocusedBorderColor = color2,
                            cursorColor = color2,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = username.value,
                            colors=colors,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "",
                                    tint = Icons.Default.Email.tintColor,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(0.85f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                            ),
                            singleLine = true,
                            onValueChange = { username.value = it },
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = password.value,
                            colors=colors,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "",
                                    tint = Icons.Default.Lock.tintColor,
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = if(password.value.isNotEmpty()) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = "",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(0.85f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                            ),
                            singleLine = true,
                            onValueChange = { password.value = it },
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "忘记密码", fontSize = 12.sp, textAlign = TextAlign.Right)
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimatedButton(username.value,password.value, onLogin = {
                            coroutineScope.launch {
                                val data = loginViewModel.login(username.value,password.value)
                                Log.e("LoginScreen", "LoginScreen: ${data.toString()}", )
                            }
                        })
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "或者", fontSize = 12.sp, textAlign = TextAlign.Right)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(48.dp), onClick = {
                            navController.popBackStack()
                        }) {
                            Text(text = "注册账号")
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}
