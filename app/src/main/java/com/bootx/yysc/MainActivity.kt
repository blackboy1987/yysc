package com.bootx.yysc

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bootx.yysc.config.Config
import com.bootx.yysc.util.ActivityStackManager
import com.youxiao.ssp.ad.bean.NextAdInfo
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter
import com.youxiao.ssp.core.SSPSdk

class MainActivity : ComponentActivity() {


    // 广告区域容器
    private var mAdLayout: ViewGroup? = null

    // 自定义宣传内容
    private var customLayout: FrameLayout? = null

    // 展示样式
    var showStyle = 0

    // 展示样式示例：全屏、非全屏，底部自定义内容样式
    private val SHOW_STYLE = "SHOW_STYLE"

    // 非全屏
    val SHOW_STYLE_NOT_FULL = 0x11

    // 底部自定义内容
    val SHOW_STYLE_CUSTOM_SCREEN = 0x12

    // 动态权限
    private var mDynamicPermissions: List<String> = listOf()


    // 动态权限请求吗
    private var REQUEST_CODE = 0x02


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SSPSdk.attachBaseContext(base);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityStackManager.pushActivity(this@MainActivity)
        super.onCreate(savedInstanceState)

        SSPSdk.init(this@MainActivity, Config.MEDIA_ID, true);
        SSPSdk.init(this@MainActivity, Config.MEDIA_ID, null, true);

        SSPSdk.setReqPermission(true);

        // 启动页广告
        setContentView(R.layout.activity_splash)
        mAdLayout = findViewById(R.id.ad_layout)
        customLayout = findViewById(R.id.custom_layout)

        if (intent != null) {
            showStyle = intent.getIntExtra(SHOW_STYLE, 0)
        }

        requestPermission()

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        requestSplashAd()

    }

    private fun requestPermission() {
        mDynamicPermissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissions(mDynamicPermissions.toTypedArray(), REQUEST_CODE)
    }

    /**
     * 请求开屏广告
     */
    private fun requestSplashAd() {
        // 展示样式
        when (showStyle) {
            SHOW_STYLE_NOT_FULL -> customLayout!!.visibility =
                View.INVISIBLE
            SHOW_STYLE_CUSTOM_SCREEN -> customLayout!!.visibility =
                View.VISIBLE

            else -> {}
        }

        //初始化广告客户端实例
        val adClient = AdClient(this)
        adClient.requestSplashAd(mAdLayout, Config.SPLASH_AD_ID, object : AdLoadAdapter() {

            override fun onStatus(p0: Int, p1: Int, p2: Int, p3: String?) {
                super.onStatus(p0, p1, p2, p3)
                Log.e("requestSplashAd", "onStatus: $p0,$p1,$p2,$p3")
            }

            override fun onNext(p0: NextAdInfo?) {
                super.onNext(p0)
                Log.e("requestSplashAd", "onNext: $p0")
            }

            override fun onAdLoad(p0: SSPAd?) {
                super.onAdLoad(p0)
                Log.e("requestSplashAd", "onAdLoad")
            }

            override fun onAdClick(p0: SSPAd?) {
                super.onAdClick(p0)
                Log.e("requestSplashAd", "onAdClick")
            }

            override fun onStartDownload(p0: String?) {
                super.onStartDownload(p0)
                Log.e("requestSplashAd", "onStartDownload: $p0")
            }

            override fun onDownloadCompleted(p0: String?) {
                super.onDownloadCompleted(p0)
                Log.e("requestSplashAd", "onDownloadCompleted: $p0")
            }

            override fun onStartInstall(p0: String?) {
                super.onStartInstall(p0)
                Log.e("requestSplashAd", "onStartInstall: $p0")
            }

            override fun onInstallCompleted(p0: String?) {
                super.onInstallCompleted(p0)
                Log.e("requestSplashAd", "onInstallCompleted: $p0")
            }

            override fun onAdShow(sspAd: SSPAd) {
                super.onAdShow(sspAd)
                if (showStyle == SHOW_STYLE_NOT_FULL) {
                    Toast.makeText(
                        this@MainActivity,
                        "开屏广告加载成功(非全屏展示)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (showStyle == SHOW_STYLE_CUSTOM_SCREEN) {
                    Toast.makeText(
                        this@MainActivity,
                        "开屏广告加载成功(底部自定义内容样式)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this@MainActivity, "开屏广告加载成功", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onError(i: Int, s: String) {
                Log.e("requestSplashAd", "onError: $s")
                super.onError(i, s)
                //获取广告失败，跳转主页
                gotoMainActivity()
            }

            override fun onAdDismiss(ad: SSPAd) {
                super.onAdDismiss(ad)
                Log.e("requestSplashAd", "onAdDismiss: $ad")
                //广告关闭（开屏广告展示时间到或用户点击跳转），跳转主页
                gotoMainActivity()
            }
        })
    }

    /**
     * 跳转至主页
     */
    private fun gotoMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}