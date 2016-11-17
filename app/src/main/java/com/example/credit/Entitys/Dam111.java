package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class Dam111 {


    public static class DataBean {
        /**
         * REGCAPCUR_CN : 人民币
         * D_ADDTIME : 
         * REGCAP : 1180
         * NAME : 万民志
         * ESTDATE : 1998-02-17
         * REGSTATE_CN : 吊销
         * REVOKEORCANCELTIME : 2004-08-16 00:00:00
         * UNISCID : 
         * OPTO : 
         * ENTTYPE : 1100
         * INDUSTRYPHY : 制造业
         * REGNO : 3602001001337
         * DOM : 洪源镇206国道西侧平阳山段
         * REGORG_CN : 景德镇市市场监督管理局
         * APPRDATE : 2004-08-16
         * ENTTYPE_CN : 有限责任公司
         * ENTNAME : 江西吉尔福实业有限公司
         * OPSCOPE : 制造、销售环保降解包装容器,塑料包装机械;加工、销售环保纸浆模塑餐具,PP合成纸.
         * PRIPID : RlUvWnNkR0JPRjA9SzYycEJ0czUxWnJWLy85TEl5eHJYZWdFVm1rUlhNWU4_
         * REVOKEORCANCEL : 企业年度检验办法第三条第一款第十八项第十九目
         * C_STATE : 2
         * OPFROM : 1998-02-17
         * C_PROVINCE : 36
         */

        public BaseInfoBean baseInfo;
        public List<?> liqMbrInfo;
        /**
         * RESPFORM_CN : 
         * SUBCONPROP : 33.9
         * SUBCONFORM : 其他
         * SUBCONAM : 400
         * ACCONAM : 400
         * INV : 景德镇市湘赣农药有限公司
         * CERTYPE_CN : 
         * INVTYPE_CN : 企业法人
         * ACCONAMUSD : 
         * INVID : 3602001568
         * CONDATE : 1998-02-17 00:00:00
         * COUNTRY_CN : 
         * DOM : 景德镇市
         * CERNO : 
         * STOCKDATE : 
         * SCONFORM : 
         * SUBCONAMUSD : 
         * PRIPID : RlUvWnNkR0JPRjA9SzYycEJ0czUxWnJWLy85TEl5eHJYZWdFVm1rUlhNWU4_
         */

        public List<PartnersInfoBean> partnersInfo;
        public List<EmployeesInfoBean> employeesInfo;


        public static class BaseInfoBean {
            public String REGCAPCUR_CN;
            public String D_ADDTIME;
            public String REGCAP;
            public String NAME;
            public String ESTDATE;
            public String REGSTATE_CN;
            public String REVOKEORCANCELTIME;
            public String UNISCID;
            public String OPTO;
            public String ENTTYPE;
            public String INDUSTRYPHY;
            public String REGNO;
            public String DOM;
            public String REGORG_CN;
            public String APPRDATE;
            public String ENTTYPE_CN;
            public String ENTNAME;
            public String OPSCOPE;
            public String PRIPID;
            public String REVOKEORCANCEL;
            public String C_STATE;
            public String OPFROM;
            public String C_PROVINCE;

        }

        public static class PartnersInfoBean {
            public String RESPFORM_CN;
            public String SUBCONPROP;
            public String SUBCONFORM;
            public String SUBCONAM;
            public String ACCONAM;
            public String INV;
            public String CERTYPE_CN;
            public String INVTYPE_CN;
            public String ACCONAMUSD;
            public String INVID;
            public String CONDATE;
            public String COUNTRY_CN;
            public String DOM;
            public String CERNO;
            public String STOCKDATE;
            public String SCONFORM;
            public String SUBCONAMUSD;
            public String PRIPID;

        }

        public static class EmployeesInfoBean {
            public String NAME;
            public String CERNO;
            public String POSITION_CN;
            public String SEX;
            public String PERSONID;

        }
    }
}


