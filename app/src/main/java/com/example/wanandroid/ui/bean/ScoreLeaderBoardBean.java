package com.example.wanandroid.ui.bean;

import java.util.List;

/**
 * @Copyright (C), @2020 小天狼星
 * @ClassName: ScoreLeaderBoardBean
 * @Author: 小天狼星
 * @Date: 2020/3/19 10:55
 * @Description: 积分排行榜数据
 * @version: 1.1.5
 */

public class ScoreLeaderBoardBean {

    /**
     * data : {"curPage":1,"datas":[{"coinCount":9600,"level":96,"rank":1,"userId":20382,"username":"g**eii"},{"coinCount":8669,"level":87,"rank":2,"userId":3559,"username":"A**ilEyon"},{"coinCount":7308,"level":74,"rank":3,"userId":29303,"username":"深**士"},{"coinCount":6873,"level":69,"rank":4,"userId":27535,"username":"1**08491840"},{"coinCount":6735,"level":68,"rank":5,"userId":2,"username":"x**oyang"},{"coinCount":5729,"level":58,"rank":6,"userId":28694,"username":"c**ng0218"},{"coinCount":5367,"level":54,"rank":7,"userId":9621,"username":"S**24n"},{"coinCount":5345,"level":54,"rank":8,"userId":3753,"username":"S**phenCurry"},{"coinCount":5253,"level":53,"rank":9,"userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":5167,"level":52,"rank":10,"userId":1534,"username":"j**gbin"},{"coinCount":5136,"level":52,"rank":11,"userId":863,"username":"m**qitian"},{"coinCount":5118,"level":52,"rank":12,"userId":28607,"username":"S**Brother"},{"coinCount":5084,"level":51,"rank":13,"userId":7809,"username":"1**23822235"},{"coinCount":5070,"level":51,"rank":14,"userId":12467,"username":"c**yie"},{"coinCount":5068,"level":51,"rank":15,"userId":7710,"username":"i**Cola7"},{"coinCount":5065,"level":51,"rank":16,"userId":25793,"username":"F**_2014"},{"coinCount":5065,"level":51,"rank":17,"userId":7891,"username":"h**zkp"},{"coinCount":5061,"level":51,"rank":18,"userId":14829,"username":"l**changwen"},{"coinCount":5044,"level":51,"rank":19,"userId":833,"username":"w**lwaywang6"},{"coinCount":5040,"level":51,"rank":20,"userId":27,"username":"y**ochoo"},{"coinCount":5016,"level":51,"rank":21,"userId":12351,"username":"w**igeny"},{"coinCount":4996,"level":50,"rank":22,"userId":2068,"username":"i**Cola"},{"coinCount":4930,"level":50,"rank":23,"userId":12331,"username":"R**kieJay"},{"coinCount":4930,"level":50,"rank":24,"userId":25419,"username":"蔡**打篮球"},{"coinCount":4930,"level":50,"rank":25,"userId":26707,"username":"p**xc.com"},{"coinCount":4903,"level":50,"rank":26,"userId":4662,"username":"1**71599512"},{"coinCount":4893,"level":49,"rank":27,"userId":6095,"username":"W**derfulMtf"},{"coinCount":4893,"level":49,"rank":28,"userId":29185,"username":"轻**宇"},{"coinCount":4876,"level":49,"rank":29,"userId":2160,"username":"R**iner"},{"coinCount":4876,"level":49,"rank":30,"userId":29076,"username":"f**ham"}],"offset":0,"over":false,"pageCount":714,"size":30,"total":21414}
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
         * datas : [{"coinCount":9600,"level":96,"rank":1,"userId":20382,"username":"g**eii"},{"coinCount":8669,"level":87,"rank":2,"userId":3559,"username":"A**ilEyon"},{"coinCount":7308,"level":74,"rank":3,"userId":29303,"username":"深**士"},{"coinCount":6873,"level":69,"rank":4,"userId":27535,"username":"1**08491840"},{"coinCount":6735,"level":68,"rank":5,"userId":2,"username":"x**oyang"},{"coinCount":5729,"level":58,"rank":6,"userId":28694,"username":"c**ng0218"},{"coinCount":5367,"level":54,"rank":7,"userId":9621,"username":"S**24n"},{"coinCount":5345,"level":54,"rank":8,"userId":3753,"username":"S**phenCurry"},{"coinCount":5253,"level":53,"rank":9,"userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":5167,"level":52,"rank":10,"userId":1534,"username":"j**gbin"},{"coinCount":5136,"level":52,"rank":11,"userId":863,"username":"m**qitian"},{"coinCount":5118,"level":52,"rank":12,"userId":28607,"username":"S**Brother"},{"coinCount":5084,"level":51,"rank":13,"userId":7809,"username":"1**23822235"},{"coinCount":5070,"level":51,"rank":14,"userId":12467,"username":"c**yie"},{"coinCount":5068,"level":51,"rank":15,"userId":7710,"username":"i**Cola7"},{"coinCount":5065,"level":51,"rank":16,"userId":25793,"username":"F**_2014"},{"coinCount":5065,"level":51,"rank":17,"userId":7891,"username":"h**zkp"},{"coinCount":5061,"level":51,"rank":18,"userId":14829,"username":"l**changwen"},{"coinCount":5044,"level":51,"rank":19,"userId":833,"username":"w**lwaywang6"},{"coinCount":5040,"level":51,"rank":20,"userId":27,"username":"y**ochoo"},{"coinCount":5016,"level":51,"rank":21,"userId":12351,"username":"w**igeny"},{"coinCount":4996,"level":50,"rank":22,"userId":2068,"username":"i**Cola"},{"coinCount":4930,"level":50,"rank":23,"userId":12331,"username":"R**kieJay"},{"coinCount":4930,"level":50,"rank":24,"userId":25419,"username":"蔡**打篮球"},{"coinCount":4930,"level":50,"rank":25,"userId":26707,"username":"p**xc.com"},{"coinCount":4903,"level":50,"rank":26,"userId":4662,"username":"1**71599512"},{"coinCount":4893,"level":49,"rank":27,"userId":6095,"username":"W**derfulMtf"},{"coinCount":4893,"level":49,"rank":28,"userId":29185,"username":"轻**宇"},{"coinCount":4876,"level":49,"rank":29,"userId":2160,"username":"R**iner"},{"coinCount":4876,"level":49,"rank":30,"userId":29076,"username":"f**ham"}]
         * offset : 0
         * over : false
         * pageCount : 714
         * size : 30
         * total : 21414
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
             * coinCount : 9600
             * level : 96
             * rank : 1
             * userId : 20382
             * username : g**eii
             */

            private int coinCount;
            private int level;
            private int rank;
            private int userId;
            private String username;

            public int getCoinCount() {
                return coinCount;
            }

            public void setCoinCount(int coinCount) {
                this.coinCount = coinCount;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}