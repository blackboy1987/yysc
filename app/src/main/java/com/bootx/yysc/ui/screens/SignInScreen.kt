package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current;
    LaunchedEffect(Unit) {
        userViewModel.signIn(SharedPreferencesUtils(context).get("token"))
        userViewModel.loadUserInfo(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "签到")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 16.dp, bottom = 16.dp),
                ) {
                    Spacer(modifier = Modifier.width(40.dp))
                    SoftIcon6(url = "${userViewModel.userInfo.avatar}")
                    Column(
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "${userViewModel.userInfo.username}",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.primary
                        )
                        var text = "连续签到${userViewModel.signInInfo.days}天";
                        if(!userViewModel.signInInfo.isSign){
                            text += "，今日未签到"
                        }else{
                            text += "，排名：${userViewModel.signInInfo.rank}"
                        }
                        Text(
                            text = text,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    if (!userViewModel.signInInfo.isSign) {
                        Button(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = {
                                coroutineScope.launch {
                                    userViewModel.signIn(SharedPreferencesUtils(context).get("token"))
                                }
                            }) {
                            Text(text = "签到")
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(
                        text = "排行榜",
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                LazyColumn() {
                    itemsIndexed(userViewModel.signInInfo.list) {index,item->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "${index + 1}",
                                modifier = Modifier.width(40.dp),
                                textAlign = TextAlign.Center
                            )
                            ListItem(
                                leadingContent = {
                                    SoftIcon6(url = "${item.avatar?:""}")
                                },
                                headlineContent = {
                                    Text(
                                        text = "${item.username ?:""}",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                },
                                supportingContent = { Text(text = "凌晨 00:00") },
                                trailingContent = {
                                    Text(
                                        buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    color = MaterialTheme.colorScheme.primary,
                                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                                                )
                                            ) {
                                                append("${item.continuousSignInDays?:0}天\n")
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                                    color = MaterialTheme.colorScheme.secondary
                                                )
                                            ) {
                                                append("连续")
                                            }
                                        }
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
