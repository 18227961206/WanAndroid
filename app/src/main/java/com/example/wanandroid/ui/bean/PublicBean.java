package com.example.wanandroid.ui.bean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: PublicBean
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:53
 * @Description: 全局公共文章列表数据
 * @version: 1.1.5
 */

public class PublicBean {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12221,"link":"https://mp.weixin.qq.com/s/xQ5I0omWC8-6W4RAYl0hkA","niceDate":"2小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1583640584000,"selfVisible":0,"shareDate":1583567191000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"一种简单优雅的TextView行间距适配方案","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12236,"link":"https://www.jianshu.com/p/e5e2c5b9797d","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"","publishTime":1583599646000,"selfVisible":0,"shareDate":1583599646000,"shareUser":"逐梦少年","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"分布式协调工具之zookeeper使用篇-Zookeeper系统核心模型","type":0,"userId":29062,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12233,"link":"https://www.jianshu.com/p/411c86eae733","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"","publishTime":1583598783000,"selfVisible":0,"shareDate":1583598783000,"shareUser":"逐梦少年","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"分布式协调工具之zookeeper协议篇-详解Paxos算法与ZAB协议","type":0,"userId":29062,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12214,"link":"https://juejin.im/post/5e6064d8e51d4526f829bc4f","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1583481940000,"selfVisible":0,"shareDate":1583481940000,"shareUser":"cscxzxzc","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android打包、签名与混淆","type":0,"userId":28454,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12213,"link":"https://juejin.im/post/5e61bf2de51d4526ea7f00bd","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583476279000,"selfVisible":0,"shareDate":1583476279000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Handler之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12210,"link":"https://juejin.im/post/5e61b253e51d45270e212eb4","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583461501000,"selfVisible":0,"shareDate":1583461501000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（九、深入理解EventBus源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12203,"link":"https://juejin.im/post/5e5e1145f265da5741120b5a","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425798000,"selfVisible":0,"shareDate":1583425041000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"UI系列一Android多子view嵌套通用解决方案","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":126,"chapterName":"绘图相关","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12204,"link":"https://www.jianshu.com/p/af99ef0618d5?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425792000,"selfVisible":0,"shareDate":1583425421000,"shareUser":"鸿洋","superChapterId":99,"superChapterName":"自定义控件","tags":[],"title":"【译】一文带你了解Android中23个关于Canvas绘制的方法","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12206,"link":"https://www.jianshu.com/p/f25c6f8b1594","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425776000,"selfVisible":0,"shareDate":1583425693000,"shareUser":"鸿洋","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"程序员常用工具总结","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12205,"link":"https://juejin.im/post/5e5f4a5be51d4526cb162775","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425768000,"selfVisible":0,"shareDate":1583425588000,"shareUser":"鸿洋","superChapterId":95,"superChapterName":"多媒体技术","tags":[],"title":"Android仿京东天猫列表页播视频看这一篇就足够了","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12202,"link":"https://juejin.im/post/5e60ecd4e51d4526ed66bdcc","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583411223000,"selfVisible":0,"shareDate":1583411144000,"shareUser":"yangchong211","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"LiveData详细分析","type":0,"userId":697,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12201,"link":"https://blog.csdn.net/m0_37700275/article/details/103703780","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583406031000,"selfVisible":0,"shareDate":1583400591000,"shareUser":"yangchong211","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"android富文本开源库分享","type":0,"userId":697,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12187,"link":"https://juejin.im/post/5e5f090de51d4526e4190980","niceDate":"2020-03-04 11:10","niceShareDate":"2020-03-04 11:10","origin":"","prefix":"","projectLink":"","publishTime":1583291437000,"selfVisible":0,"shareDate":1583291437000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之绘制优化","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":487,"chapterName":"ViewModel","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12167,"link":"https://juejin.im/post/5e5bd0d2e51d4526e807f55f","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 08:44","origin":"","prefix":"","projectLink":"","publishTime":1583249268000,"selfVisible":0,"shareDate":1583196259000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"ViewModel 这些知识点你都知道吗？","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12181,"link":"https://juejin.im/post/5e5e3cb7f265da571563183e","niceDate":"2020-03-03 23:25","niceShareDate":"2020-03-03 21:40","origin":"","prefix":"","projectLink":"","publishTime":1583249154000,"selfVisible":0,"shareDate":1583242837000,"shareUser":"darryrzhong","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"Android 内存优化篇 - 使用profile 和 MAT 工具进行内存泄漏检测","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>早上看到事件分发的一个讨论：<\/p>\r\n<p><img src=\"https://wanandroid.com/blogimgs/9bfb6411-6262-4d59-884f-3e868e5493cd.jpg\" alt><\/p>\r\n<p>那么事件到底是先到DecorView还是先到Window(Activity，Dialog)的。<\/p>\r\n<ol>\r\n<li>touch相关事件在DecorView，PhoneWindow，Activity/Dialog之间传递的顺序是什么样子的？<\/li>\r\n<li>为什么要按照1这么设计？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":12119,"link":"https://wanandroid.com/wenda/show/12119","niceDate":"2020-03-03 23:25","niceShareDate":"2020-02-27 19:07","origin":"","prefix":"","projectLink":"","publishTime":1583249132000,"selfVisible":0,"shareDate":1582801649000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | 事件到底是先到DecorView还是先到Window的？","type":0,"userId":2,"visible":1,"zan":15},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12180,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"2020-03-03 21:38","niceShareDate":"2020-03-03 21:38","origin":"","prefix":"","projectLink":"","publishTime":1583242729000,"selfVisible":0,"shareDate":1583242729000,"shareUser":"darryrzhong","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 组件化开源app -开眼短视频(OpenEyes)","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":90,"chapterName":"数据库","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12165,"link":"https://juejin.im/post/5e5bdce6e51d452705318c59","niceDate":"2020-03-03 00:17","niceShareDate":"2020-03-03 00:16","origin":"","prefix":"","projectLink":"","publishTime":1583165838000,"selfVisible":0,"shareDate":1583165776000,"shareUser":"鸿洋","superChapterId":90,"superChapterName":"数据存储","tags":[],"title":"[译] 如何用 Room 处理一对一，一对多，多对多关系？","type":0,"userId":2,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":403,"size":20,"total":8057}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12221,"link":"https://mp.weixin.qq.com/s/xQ5I0omWC8-6W4RAYl0hkA","niceDate":"2小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1583640584000,"selfVisible":0,"shareDate":1583567191000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"一种简单优雅的TextView行间距适配方案","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12236,"link":"https://www.jianshu.com/p/e5e2c5b9797d","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"","publishTime":1583599646000,"selfVisible":0,"shareDate":1583599646000,"shareUser":"逐梦少年","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"分布式协调工具之zookeeper使用篇-Zookeeper系统核心模型","type":0,"userId":29062,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12233,"link":"https://www.jianshu.com/p/411c86eae733","niceDate":"14小时前","niceShareDate":"14小时前","origin":"","prefix":"","projectLink":"","publishTime":1583598783000,"selfVisible":0,"shareDate":1583598783000,"shareUser":"逐梦少年","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"分布式协调工具之zookeeper协议篇-详解Paxos算法与ZAB协议","type":0,"userId":29062,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12214,"link":"https://juejin.im/post/5e6064d8e51d4526f829bc4f","niceDate":"1天前","niceShareDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1583481940000,"selfVisible":0,"shareDate":1583481940000,"shareUser":"cscxzxzc","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android打包、签名与混淆","type":0,"userId":28454,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12213,"link":"https://juejin.im/post/5e61bf2de51d4526ea7f00bd","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583476279000,"selfVisible":0,"shareDate":1583476279000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Handler之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12210,"link":"https://juejin.im/post/5e61b253e51d45270e212eb4","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583461501000,"selfVisible":0,"shareDate":1583461501000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（九、深入理解EventBus源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12203,"link":"https://juejin.im/post/5e5e1145f265da5741120b5a","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425798000,"selfVisible":0,"shareDate":1583425041000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"UI系列一Android多子view嵌套通用解决方案","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":126,"chapterName":"绘图相关","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12204,"link":"https://www.jianshu.com/p/af99ef0618d5?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425792000,"selfVisible":0,"shareDate":1583425421000,"shareUser":"鸿洋","superChapterId":99,"superChapterName":"自定义控件","tags":[],"title":"【译】一文带你了解Android中23个关于Canvas绘制的方法","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12206,"link":"https://www.jianshu.com/p/f25c6f8b1594","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425776000,"selfVisible":0,"shareDate":1583425693000,"shareUser":"鸿洋","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"程序员常用工具总结","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":97,"chapterName":"音视频","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12205,"link":"https://juejin.im/post/5e5f4a5be51d4526cb162775","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583425768000,"selfVisible":0,"shareDate":1583425588000,"shareUser":"鸿洋","superChapterId":95,"superChapterName":"多媒体技术","tags":[],"title":"Android仿京东天猫列表页播视频看这一篇就足够了","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12202,"link":"https://juejin.im/post/5e60ecd4e51d4526ed66bdcc","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583411223000,"selfVisible":0,"shareDate":1583411144000,"shareUser":"yangchong211","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"LiveData详细分析","type":0,"userId":697,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12201,"link":"https://blog.csdn.net/m0_37700275/article/details/103703780","niceDate":"2天前","niceShareDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1583406031000,"selfVisible":0,"shareDate":1583400591000,"shareUser":"yangchong211","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"android富文本开源库分享","type":0,"userId":697,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12187,"link":"https://juejin.im/post/5e5f090de51d4526e4190980","niceDate":"2020-03-04 11:10","niceShareDate":"2020-03-04 11:10","origin":"","prefix":"","projectLink":"","publishTime":1583291437000,"selfVisible":0,"shareDate":1583291437000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之绘制优化","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":487,"chapterName":"ViewModel","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12167,"link":"https://juejin.im/post/5e5bd0d2e51d4526e807f55f","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 08:44","origin":"","prefix":"","projectLink":"","publishTime":1583249268000,"selfVisible":0,"shareDate":1583196259000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"ViewModel 这些知识点你都知道吗？","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12181,"link":"https://juejin.im/post/5e5e3cb7f265da571563183e","niceDate":"2020-03-03 23:25","niceShareDate":"2020-03-03 21:40","origin":"","prefix":"","projectLink":"","publishTime":1583249154000,"selfVisible":0,"shareDate":1583242837000,"shareUser":"darryrzhong","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"Android 内存优化篇 - 使用profile 和 MAT 工具进行内存泄漏检测","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>早上看到事件分发的一个讨论：<\/p>\r\n<p><img src=\"https://wanandroid.com/blogimgs/9bfb6411-6262-4d59-884f-3e868e5493cd.jpg\" alt><\/p>\r\n<p>那么事件到底是先到DecorView还是先到Window(Activity，Dialog)的。<\/p>\r\n<ol>\r\n<li>touch相关事件在DecorView，PhoneWindow，Activity/Dialog之间传递的顺序是什么样子的？<\/li>\r\n<li>为什么要按照1这么设计？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":12119,"link":"https://wanandroid.com/wenda/show/12119","niceDate":"2020-03-03 23:25","niceShareDate":"2020-02-27 19:07","origin":"","prefix":"","projectLink":"","publishTime":1583249132000,"selfVisible":0,"shareDate":1582801649000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | 事件到底是先到DecorView还是先到Window的？","type":0,"userId":2,"visible":1,"zan":15},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12180,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"2020-03-03 21:38","niceShareDate":"2020-03-03 21:38","origin":"","prefix":"","projectLink":"","publishTime":1583242729000,"selfVisible":0,"shareDate":1583242729000,"shareUser":"darryrzhong","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 组件化开源app -开眼短视频(OpenEyes)","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":90,"chapterName":"数据库","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12165,"link":"https://juejin.im/post/5e5bdce6e51d452705318c59","niceDate":"2020-03-03 00:17","niceShareDate":"2020-03-03 00:16","origin":"","prefix":"","projectLink":"","publishTime":1583165838000,"selfVisible":0,"shareDate":1583165776000,"shareUser":"鸿洋","superChapterId":90,"superChapterName":"数据存储","tags":[],"title":"[译] 如何用 Room 处理一对一，一对多，多对多关系？","type":0,"userId":2,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 403
         * size : 20
         * total : 8057
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 510
             * chapterName : 大厂分享
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : true
             * id : 12221
             * link : https://mp.weixin.qq.com/s/xQ5I0omWC8-6W4RAYl0hkA
             * niceDate : 2小时前
             * niceShareDate : 23小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1583640584000
             * selfVisible : 0
             * shareDate : 1583567191000
             * shareUser : 鸿洋
             * superChapterId : 510
             * superChapterName : 大厂对外分享
             * tags : []
             * title : 一种简单优雅的TextView行间距适配方案
             * type : 0
             * userId : 2
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
