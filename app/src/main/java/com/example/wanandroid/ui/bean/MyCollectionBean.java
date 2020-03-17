package com.example.wanandroid.ui.bean;

import java.util.List;

public class MyCollectionBean {

    /**
     * data : {"curPage":1,"datas":[{"author":"鸿洋","chapterId":249,"chapterName":"干货资源","courseId":13,"desc":"","envelopePic":"","id":119463,"link":"http://gk.link/a/103Ei","niceDate":"4小时前","origin":"","originId":10285,"publishTime":1583463179000,"title":"本站福利，极客199 礼包自取","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":502,"chapterName":"自助","courseId":13,"desc":"","envelopePic":"","id":119255,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"1天前","origin":"","originId":12180,"publishTime":1583373978000,"title":"Android 组件化开源app -开眼短视频(OpenEyes)","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":76,"chapterName":"项目架构","courseId":13,"desc":"","envelopePic":"","id":119164,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2天前","origin":"","originId":12178,"publishTime":1583303910000,"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":76,"chapterName":"项目架构","courseId":13,"desc":"","envelopePic":"","id":119163,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2天前","origin":"","originId":12177,"publishTime":1583303909000,"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","userId":47034,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":4}
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
         * datas : [{"author":"鸿洋","chapterId":249,"chapterName":"干货资源","courseId":13,"desc":"","envelopePic":"","id":119463,"link":"http://gk.link/a/103Ei","niceDate":"4小时前","origin":"","originId":10285,"publishTime":1583463179000,"title":"本站福利，极客199 礼包自取","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":502,"chapterName":"自助","courseId":13,"desc":"","envelopePic":"","id":119255,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"1天前","origin":"","originId":12180,"publishTime":1583373978000,"title":"Android 组件化开源app -开眼短视频(OpenEyes)","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":76,"chapterName":"项目架构","courseId":13,"desc":"","envelopePic":"","id":119164,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2天前","origin":"","originId":12178,"publishTime":1583303910000,"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","userId":47034,"visible":0,"zan":0},{"author":"","chapterId":76,"chapterName":"项目架构","courseId":13,"desc":"","envelopePic":"","id":119163,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2天前","origin":"","originId":12177,"publishTime":1583303909000,"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","userId":47034,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 4
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
             * author : 鸿洋
             * chapterId : 249
             * chapterName : 干货资源
             * courseId : 13
             * desc :
             * envelopePic :
             * id : 119463
             * link : http://gk.link/a/103Ei
             * niceDate : 4小时前
             * origin :
             * originId : 10285
             * publishTime : 1583463179000
             * title : 本站福利，极客199 礼包自取
             * userId : 47034
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
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

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
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

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
        }
    }
}
