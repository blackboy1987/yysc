package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.config.Config
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavHostController,userViewModel: UserViewModel= viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    var signInInfo by remember {
        mutableStateOf(SignInEntity())
    }
    var isSign by remember {
        mutableStateOf(false)
    }
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    val token = storeManager.getToken().collectAsState(initial = Config.initToken).value
    LaunchedEffect(Unit){
        isSign = userViewModel.isSign(token,)
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
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        model = "https://www.iprompt.art/images/1.jpg",
                        contentDescription = "",
                    )
                    Column(
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "blackboy",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "已连续签到${signInInfo.days}天",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    if(!isSign){
                        Button(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = {
                                coroutineScope.launch {
                                    signInInfo = userViewModel.signIn(token,)
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
                    items(100) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "${it+1}",
                                modifier = Modifier.width(40.dp),
                                textAlign = TextAlign.Center
                            )
                            ListItem(
                                leadingContent = {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(CircleShape),
                                        model = "https://www.iprompt.art/images/1.jpg",
                                        contentDescription = "",
                                    )
                                },
                                headlineContent = {
                                    Text(
                                        text = "哪篇荒芜的城中村",
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
                                                append("1天\n")
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
