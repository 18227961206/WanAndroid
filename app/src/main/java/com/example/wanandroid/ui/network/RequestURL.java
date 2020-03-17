package com.example.wanandroid.ui.network;

public class RequestURL {

    /**
     * 首页Banner图    GET
     *
     * @return
     */
    public static String homePageBanner() {
        return "https://www.wanandroid.com/banner/json";
    }

    /**
     * 首页置顶文章    GET
     *
     * @return
     */
    public static String topInfo() {
        return "https://www.wanandroid.com/article/top/json";
    }

    /**
     * 首页文章    GET
     *
     * @return
     */
    public static String homePage(int page) {
        return "https://www.wanandroid.com/article/list/" + page + "/json";
    }

    /**
     * 广场文章    GET
     *
     * @return
     */
    public static String square(int page) {
        return "https://wanandroid.com/user_article/list/" + page + "/json";
    }

    /**
     * 公众号    GET
     *
     * @return
     */
    public static String officialAccounts() {
        return "https://wanandroid.com/wxarticle/chapters/json";
    }

    /**
     * 公众号个人历史数据    GET
     *
     * @return
     */
    public static String officialAccountsInfo(int id, int page) {
        return "https://wanandroid.com/wxarticle/list/" + id + "/" + page + "/json";
    }

    /**
     * 公众号中搜索历史文章   GET
     *
     * @return
     */
    public static String officialAccountsSearchInfo(int id, int page, String keyword) {
        return "https://wanandroid.com/wxarticle/list/" + id + "/" + page + "/json?k=" + keyword;
    }

    /**
     * 技术体系     体系    GET
     *
     * @return
     */
    public static String systemOnePage() {
        return "https://www.wanandroid.com/tree/json";
    }

    /**
     * 技术体系     体系详情    GET
     *
     * @return
     */
    public static String systemOneInfoPage(int id, int page) {
        return "https://www.wanandroid.com/article/list/" + page + "/json?cid=" + id;
    }

    /**
     * 技术体系     导航    GET
     *
     * @return
     */
    public static String systemTwoPage() {
        return "https://www.wanandroid.com/navi/json";
    }

    /**
     * 项目分类    GET
     *
     * @return
     */
    public static String projectPage() {
        return "https://www.wanandroid.com/project/tree/json";
    }

    /**
     * 项目分类详情    GET
     *
     * @return
     */
    public static String projectInfoPage(int page, int id) {
        return "https://www.wanandroid.com/article/list/" + page + "/json?cid=" + id;
    }

    /**
     * 搜索    POST
     *
     * @return
     */
    public static String search(int page, String keyword) {
        return "https://www.wanandroid.com/article/query/" + page + "/json?k=" + keyword;
    }

    /**
     * 搜索热词    GET
     *
     * @return
     */
    public static String searchForWords() {
        return "https://www.wanandroid.com//hotkey/json";
    }

    /**
     * 积分排行榜    GET
     *
     * @return
     */
    public static String scoreLeaderBoard(int page) {
        return "https://www.wanandroid.com/coin/rank/" + page + "/json";
    }

    /**
     * 登录    POST
     * 以下接口需要登录才能访问
     *
     * @return
     */
    public static String login(String username, String password) {
        return "https://www.wanandroid.com/user/login?username=" + username + "&password=" + password;
    }

    /**
     * 注册    POST
     *
     * @return
     */
    public static String registered(String username, String password, String repassword) {
        return "https://www.wanandroid.com/user/register?username=" + username + "&password=" + password + "&repassword=" + repassword;
    }

    /**
     * 退出    GET
     *
     * @return
     */
    public static String exit() {
        return "https://www.wanandroid.com/user/logout/json";
    }

    /**
     * 个人积分    GET
     *
     * @return
     */
    public static String userInfo() {
        return "https://www.wanandroid.com/lg/coin/userinfo/json";
    }

    /**
     * 个人积分列表    GET
     *
     * @return
     */
    public static String integralList(int page) {
        return "https://www.wanandroid.com//lg/coin/list/" + page + "/json ";
    }

    /**
     * 收藏文章列表    GET
     *
     * @return
     */
    public static String listOfFavoriteArticles(int page) {
        return "https://www.wanandroid.com/lg/collect/list/" + page + "/json";
    }

    /**
     * 收藏站内文章    POST
     *
     * @return
     */
    public static String collectionStationArticles(int id) {
        return "https://www.wanandroid.com/lg/collect/" + id + "/json";
    }

    /**
     * 收藏站外文章    POST
     *
     * @return
     */
    public static String collectionStationArticlesOutside(String title, String author, String link) {
        return "https://www.wanandroid.com/lg/collect/add/json?title=" + title + "&author=" + author + "&link=" + link;
    }

    /**
     * 取消收藏   POST
     * 文章列表
     *
     * @return
     */
    public static String cancelTheCollection(int id) {
        return "https://www.wanandroid.com/lg/uncollect_originId/" + id + "/json";
    }

    /**
     * 取消收藏   POST
     * 我的收藏页面
     *
     * @return
     */
    public static String myCancelTheCollection(int id, int originId) {
        return "https://www.wanandroid.com/lg/uncollect/" + id + "/json?originId=" + originId;
    }

    /**
     * 收藏网站列表   GET
     *
     * @return
     */
    public static String listOfFavoriteWebsites() {
        return "https://www.wanandroid.com/lg/collect/usertools/json";
    }

    /**
     * 收藏网址   POST
     *
     * @return
     */
    public static String collectionSite(String name, String link) {
        return "https://www.wanandroid.com/lg/collect/addtool/json?name=" + name + "&link=" + link;
    }

    /**
     * 编辑收藏网站   POST
     *
     * @return
     */
    public static String editFavoriteWebsite(int id, String name, String link) {
        return "https://www.wanandroid.com/lg/collect/updatetool/json?id=" + id + "&name=" + name + "&link=" + link;
    }

    /**
     * 删除收藏网站   POST
     *
     * @return
     */
    public static String deleteFavoriteWebsites(int id) {
        return "https://www.wanandroid.com/lg/collect/deletetool/json?id=" + id;
    }

    /**
     * 自己的分享的文章列表   GET
     *
     * @return
     */
    public static String myShareList(int page) {
        return "https://wanandroid.com/user/lg/private_articles/" + page + "/json";
    }

    /**
     * 添加分享文章   POST
     *
     * @return
     */
    public static String myShareAdd(String title, String link) {
        return "https://www.wanandroid.com/lg/user_article/add/json?title=" + title + "&link=" + link;
    }

    /**
     * 删除分享文章   POST
     *
     * @return
     */
    public static String myShareDelete(int id) {
        return "https://wanandroid.com/lg/user_article/delete/" + id + "/json";
    }

    /**
     * 问答   GET
     *
     * @return
     */
    public static String questionAndAnswer(int page) {
        return "https://wanandroid.com/wenda/list/" + page + "/json ";
    }
}
