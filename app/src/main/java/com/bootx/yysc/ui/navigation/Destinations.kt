package com.bootx.yysc.ui.navigation

sealed class Destinations(val route: String) {
    //首页
    data object MainFrame : Destinations("MainFrame")
    //登录
    data object LoginFrame : Destinations("LoginFrame")
    //应用详情
    data object AppDetailFrame : Destinations("AppDetailFrame")
    //应用更多信息
    data object AppMoreFrame : Destinations("AppMoreFrame")
    //应用投稿列表
    data object TouGaoListFrame : Destinations("TouGaoListFrame")
    data object TouGaoAppInfoListFrame : Destinations("TouGaoAppInfoListFrame")
    //应用投稿
    data object TouGaoFrame : Destinations("TouGaoFrame")
    //签到
    data object SignInFrame : Destinations("SignInFrame")
    //下载管理
    data object DownloadManagerFrame : Destinations("DownloadManagerFrame")
    //支持应用
    data object SupportFrame : Destinations("SupportFrame")
    //支持排行榜
    data object SupportRankFrame : Destinations("SupportRankFrame")

    //历史
    data object HistoryFrame : Destinations("HistoryFrame")
    //搜索
    data object SearchFrame : Destinations("SearchFrame")
    //群组
    data object QunZuFrame : Destinations("QunZuFrame")
    //福利
    data object FuLiFrame : Destinations("FuLiFrame")
    //热门
    data object HotFrame : Destinations("HotFrame")
    //粉丝
    data object FanFrame : Destinations("FanFrame")
    //我的硬币
    data object MyIconFrame : Destinations("MyIconFrame")
    //我的硬币明细
    data object MyIconListFrame : Destinations("MyIconListFrame")
    //设置
    data object SettingFrame : Destinations("SettingFrame")
    //通知信息
    data object NotifyFrame : Destinations("NotifyFrame")
    //信息列表
    data object NotifyListFrame : Destinations("NotifyListFrame")
    //系统消息
    data object SysMsgFrame : Destinations("SysMsgFrame")
    //硬币明细
    data object IconDetailFrame : Destinations("IconDetailFrame")
    //关于
    data object AboutFrame : Destinations("AboutFrame")
    //应用列表
    data object ListFrame : Destinations("ListFrame")
    //投诉
    data object ComplaintsFrame : Destinations("ComplaintsFrame")
    //webView
    data object OtherFrame : Destinations("OtherFrame")
    //会员信息
    data object MemberFrame : Destinations("MemberFrame")
    //排行榜
    data object AppRankFrame : Destinations("AppRankFrame")











    //测试
    object TestFrame : Destinations("TestFrame")

}
