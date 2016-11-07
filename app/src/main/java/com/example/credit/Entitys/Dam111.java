package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam111 {

    public String message;
    public String status;
    public DataBean data;
    public String version;
    public static class DataBean {
        /**
         * LOGTYPE : 22
         * KEYWORDS : aaaaaaaaaa
         * LOGID : b982f20dd85643d0b815e6097d25a055
         * LOGTIME : 2016-11-03 15:15:17
         * REMARK : null
         * MEMBER_ID : null
         */
        public List<SearchHistoryBean> searchHistory;
        public static class SearchHistoryBean {
            public String LOGTYPE;
            public String KEYWORDS;
            public String LOGID;
            public String LOGTIME;
            public Object REMARK;
            public Object MEMBER_ID;
        }
    }
}


