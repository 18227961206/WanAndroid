package com.example.wanandroid.ui.bean;

import java.util.List;

public class TopBean {

    /**
     * data : [{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":269,"chapterName":"官方发布","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12240,"link":"https://www.wanandroid.com/blog/show/2717","niceDate":"23小时前","niceShareDate":"23小时前","origin":"","prefix":"","projectLink":"","publishTime":1583640947000,"selfVisible":0,"shareDate":1583640877000,"shareUser":"","superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"服务器又要续费鸟...挂赞助两周","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>很久以前有Activity.onResume就是界面可见的说法，这种说法毫无疑问是不准确的。<\/p>\r\n<p>问题是：<\/p>\r\n<ol>\r\n<li>Activity.onCreate 和 Activity.onResume，在调用时间上有差别么？可以从Message调度去考虑。<\/li>\r\n<li>有没有一个合理的时机，让我们认为Activity 界面可见了？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":true,"id":12175,"link":"https://wanandroid.com/wenda/show/12175","niceDate":"1天前","niceShareDate":"2020-03-03 18:49","origin":"","prefix":"","projectLink":"","publishTime":1583638222000,"selfVisible":0,"shareDate":1583232546000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | 很久以前有Activity.onResume就是界面可见的说法，这种说法错了多少？","type":1,"userId":2,"visible":1,"zan":1},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>之前我们讨论过 <a href=\"https://wanandroid.com/wenda/show/8488\">View的onAttachedToWindow ,onDetachedFromWindow 调用时机<\/a> 。<\/p>\r\n<p>这个机制在RecyclerView卡片中还适用吗？<\/p>\r\n<p>例如我们在RecyclerView的Item的onBindViewHolder时，利用一个CountDownTimer去做一个倒计时显示 / 或者是有一个属性动画效果？<\/p>\r\n<ol>\r\n<li>到底在什么时候可以cancel掉这个倒计时/ 动画，而不影响功能了（滑动到用户可见范围内，倒计时/动画 运作正常）?<\/li>\r\n<li>有什么方法可以和onBindViewHolder 对应吗？就像onAttachedToWindow ,onDetachedFromWindow这样 。<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":true,"id":12148,"link":"https://wanandroid.com/wenda/show/12148","niceDate":"1天前","niceShareDate":"2020-03-01 15:14","origin":"","prefix":"","projectLink":"","publishTime":1583638219000,"selfVisible":0,"shareDate":1583046877000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 RecyclerView卡片中持有的资源，到底该什么时候释放？","type":1,"userId":2,"visible":1,"zan":13},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":true,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":10916,"link":"https://wanandroid.com/blog/show/2701","niceDate":"1天前","niceShareDate":"2019-12-17 13:15","origin":"","prefix":"","projectLink":"","publishTime":1583638203000,"selfVisible":0,"shareDate":1576559725000,"shareUser":"18818486692","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"玩 Android 交流星球 限时开放","type":1,"userId":22014,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":10285,"link":"http://gk.link/a/103Ei","niceDate":"2020-01-01 22:34","niceShareDate":"2019-11-15 01:01","origin":"","prefix":"","projectLink":"","publishTime":1577889274000,"selfVisible":0,"shareDate":1573750881000,"shareUser":"","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"本站福利，极客199 礼包自取","type":1,"userId":-1,"visible":1,"zan":0}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkLink :
         * audit : 1
         * author : 鸿洋
         * canEdit : false
         * chapterId : 269
         * chapterName : 官方发布
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : true
         * id : 12240
         * link : https://www.wanandroid.com/blog/show/2717
         * niceDate : 23小时前
         * niceShareDate : 23小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1583640947000
         * selfVisible : 0
         * shareDate : 1583640877000
         * shareUser :
         * superChapterId : 60
         * superChapterName : 开发环境
         * tags : []
         * title : 服务器又要续费鸟...挂赞助两周
         * type : 1
         * userId : -1
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
