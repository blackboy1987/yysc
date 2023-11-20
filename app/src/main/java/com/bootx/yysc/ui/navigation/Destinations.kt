package com.bootx.yysc.ui.navigation

sealed class Destinations(val route: String) {
    //首页
    object HomeFrame : Destinations("HomeFrame")
    //精选
    object JingXuanFrame : Destinations("JingXuanFrame")
    //关注
    object GuanZhuFrame : Destinations("GuanZhuFrame")
    //应用
    object AppFrame : Destinations("AppFrame")
    //游戏
    object GameFrame : Destinations("GameFrame")
    //广场
    object PlazaFrame : Destinations("PlazaFrame")
    //我的
    object MineFrame : Destinations("MineFrame")
    //注册
    object RegisterFrame : Destinations("RegisterFrame")
    //登录
    object LoginFrame : Destinations("LoginFrame")
    //应用榜单
    object AppRankFrame : Destinations("AppRankFrame")
    //应用详情
    object AppDetailFrame : Destinations("AppDetailFrame")
    //应用投稿列表
    object TouGaoListFrame : Destinations("TouGaoListFrame")
    //应用投稿
    object TouGaoFrame : Destinations("TouGaoFrame")
    //应用更新
    object AppUpdateFrame : Destinations("AppUpdateFrame")
    //我的收藏
    object CollectFrame : Destinations("CollectFrame")
    //签到
    object SignInFrame : Destinations("SignInFrame")
    //下载管理
    object DownloadManagerFrame : Destinations("DownloadManagerFrame")
    //支持应用
    object SupportFrame : Destinations("SupportFrame")
    //支持排行榜
    object SupportRankFrame : Destinations("SupportRankFrame")

    //历史
    object HistoryFrame : Destinations("HistoryFrame")
    //搜索
    object SearchFrame : Destinations("SearchFrame")
    //群组
    object QunZuFrame : Destinations("QunZuFrame")
    //福利
    object FuLiFrame : Destinations("FuLiFrame")
    //热门
    object HotFrame : Destinations("HotFrame")
    //粉丝
    object FanFrame : Destinations("FanFrame")
    //我的硬币
    object MyIconFrame : Destinations("MyIconFrame")



}
