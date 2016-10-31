package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam111 {


    /**
     * message : success
     * status : 1
     * data : {"judgmentInfo":[],"courtcaseInfo":[],"sfInfo":[{"REGCAPCUR_CN":"人民币","FROTO":"2018-10-17","EXECUTEITEM_CN":"冻结","FROAUTH":"江西省南昌市中级人民法院","FROZSTATE_CN":"已冻结","FROAM":"647.500000","INV":"万斌","FROID":"20161018151529428719","FROFROM":"2016-10-18","FROZDEADLINE":"0","INVTYPE_CN":"中华人民共和国居民身份证"},{"REGCAPCUR_CN":"人民币","FROTO":"2016-10-23","EXECUTEITEM_CN":"","FROAUTH":"南昌市中级人民法院","FROZSTATE_CN":"已冻结","FROAM":"647.500000","INV":"万斌","FROID":"3609832014102400001992","FROFROM":"2014-10-24","FROZDEADLINE":"0","INVTYPE_CN":"自然人股东"},{"REGCAPCUR_CN":"人民币","FROTO":"2017-09-20","EXECUTEITEM_CN":"","FROAUTH":"南昌市东湖区人民法院","FROZSTATE_CN":"已冻结","FROAM":"647.500000","INV":"万斌","FROID":"3609832015092100002734","FROFROM":"2015-09-21","FROZDEADLINE":"0","INVTYPE_CN":"自然人股东"},{"REGCAPCUR_CN":"人民币","FROTO":"2018-10-29","EXECUTEITEM_CN":"","FROAUTH":"江西省南昌市西湖区人民法院","FROZSTATE_CN":"已冻结","FROAM":"647.500000","INV":"万斌","FROID":"3609832015103000003146","FROFROM":"2015-10-30","FROZDEADLINE":"0","INVTYPE_CN":"自然人股东"}],"sfAlter":[]}
     * version : v1.0
     */

    public String message;
    public String status;
    public DataBean data;
    public String version;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        public List<?> judgmentInfo;
        public List<?> courtcaseInfo;
        /**
         * REGCAPCUR_CN : 人民币
         * FROTO : 2018-10-17
         * EXECUTEITEM_CN : 冻结
         * FROAUTH : 江西省南昌市中级人民法院
         * FROZSTATE_CN : 已冻结
         * FROAM : 647.500000
         * INV : 万斌
         * FROID : 20161018151529428719
         * FROFROM : 2016-10-18
         * FROZDEADLINE : 0
         * INVTYPE_CN : 中华人民共和国居民身份证
         */
        public List<SfInfoBean> sfInfo;
        public List<?> sfAlter;
        public static class SfInfoBean {
            public String REGCAPCUR_CN;
            public String FROTO;
            public String EXECUTEITEM_CN;
            public String FROAUTH;
            public String FROZSTATE_CN;
            public String FROAM;
            public String INV;
            public String FROID;
            public String FROFROM;
            public String FROZDEADLINE;
            public String INVTYPE_CN;
        }
    }
}


