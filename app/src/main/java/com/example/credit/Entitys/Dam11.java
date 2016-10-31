package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam11 {

    private String message;
    private String status;
    private DataBean data;
    private String version;

    public static class DataBean {
        private BaseInfoBean baseInfo;
        private List<?> liqMbrInfo;
        private List<?> partnersInfo;
        private List<?> employeesInfo;
        private List<?> annualReportsInfo;
        private List<?> changeRecordsInfo;

        public static class BaseInfoBean {
            private String REGCAPCUR_CN;
            private String D_ADDTIME;
            private String REGCAP;
            private String NAME;
            private String ESTDATE;
            private String REGSTATE_CN;
            private String UNISCID;
            private String COMPFORM_CN;
            private String OPTO;
            private String ENTTYPE;
            private String INDUSTRYPHY;
            private String REGNO;
            private String DOM;
            private String REGORG_CN;
            private String APPRDATE;
            private String ENTNAME;
            private String OPSCOPE;
            private String PRIPID;
            private String C_STATE;
            private String OPFROM;
            private String C_PROVINCE;

        }
    }
}

