package com.example.wanandroid.ui.bean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: MyIntegralBean
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:52
 * @Description: 我的积分数据
 * @version: 1.1.5
 */

public class MyIntegralBean {

    /**
     * data : {"curPage":1,"datas":[{"coinCount":11,"date":1583116941000,"desc":"2020-03-02 10:42:21 签到 , 积分：10 + 1","id":153067,"reason":"签到","type":1,"userId":47034,"userName":"MICHAELS"},{"coinCount":10,"date":1583055832000,"desc":"2020-03-01 17:43:52 签到 , 积分：10 + 0","id":152312,"reason":"签到","type":1,"userId":47034,"userName":"MICHAELS"}],"offset":0,"over":true,"pageCount":1,"size":20,"total":2}
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
         * datas : [{"coinCount":11,"date":1583116941000,"desc":"2020-03-02 10:42:21 签到 , 积分：10 + 1","id":153067,"reason":"签到","type":1,"userId":47034,"userName":"MICHAELS"},{"coinCount":10,"date":1583055832000,"desc":"2020-03-01 17:43:52 签到 , 积分：10 + 0","id":152312,"reason":"签到","type":1,"userId":47034,"userName":"MICHAELS"}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 2
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
             * coinCount : 11
             * date : 1583116941000
             * desc : 2020-03-02 10:42:21 签到 , 积分：10 + 1
             * id : 153067
             * reason : 签到
             * type : 1
             * userId : 47034
             * userName : MICHAELS
             */

            private int coinCount;
            private long date;
            private String desc;
            private int id;
            private String reason;
            private int type;
            private int userId;
            private String userName;

            public int getCoinCount() {
                return coinCount;
            }

            public void setCoinCount(int coinCount) {
                this.coinCount = coinCount;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
