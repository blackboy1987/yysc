package com.bootx.yysc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bootx.yysc.ui.components.NavHostApp
import com.bootx.yysc.ui.theme.YYSCTheme
import com.bootx.yysc.util.ActivityStackManager
import com.youxiao.ssp.core.SSPSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityStackManager.pushActivity(this@MainActivity)
        super.onCreate(savedInstanceState)
        SSPSdk.attachBaseContext(this@MainActivity)
        SSPSdk.init(this@MainActivity, "881", true);
        SSPSdk.init(this@MainActivity, "881", null, true)

        setContent {
            YYSCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostApp()
                }
            }
        }
    }
}