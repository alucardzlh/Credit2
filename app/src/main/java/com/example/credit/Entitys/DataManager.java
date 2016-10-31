package com.example.credit.Entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DataManager {

    public static String StringZero = "";//公司
    public static String String0 = "";//工商
    public static String String1 = "";//
    public static String String2 = "";//
    public static String String3 = "";//
    public static String String4 = "";//
    public static String String5 = "";//
    public static String String6 = "";//
    public static String String7 = "";//
    public static String String8 = "";//
    public static String String9 = "";//
    public static String String10 = "";//
    public static String String11 = "";//
    public static String String12 = "";//
    public static String String13 = "";//
    public static String String14 = "";//
    public static String String15 = "";//
    public static String String16 = "";//
    public static String String17 = "";//
    public static String String18 = "";//
    public static String String19 = "";//
    public static String String20 = "";//
    public static String String21 = "";//
    public static String String22 = "";//
    public static String String23 = "";//


    public class Root {
        public int status;
        public String message;
        public DataS data;
    }

    public class DataS {
        public Paging Paging;
        public List<search> Result;
    }

    public class Paging {
        public int PageSize;
        public int PageIndex;
        public int TotalRecords;
    }

    public static search searchList = new search();//搜索列表集合
    public static search searchListMore = new search();//搜索更多列表集合

    /**
     * 搜索实体类
     */
    public static class search {//搜索列表信息 Result
        public String message;
        public String status;
        public String qtime;
        public DataBean data;

        public static class DataBean {
            public PagingBean Paging;
            public List<ResultBean> Result;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String CurrentResult;
                public String CurrentPage;
                public String TotalResult;
                public String PageIndex;
            }

            public static class ResultBean {
                public String REGCAPCUR_CN;
                public String ESTDATE;
                public String ENTNAME_HIGHT;
                public Object UNISCID;
                public Object C_COUNTY;
                public Object C_CITY;
                public String ENTTYPE;
                public String PRIPID;//主体身份代码
                public String ENTNAME;//企业(机构)名称
                public String REGNO;//注册号
                public String REGORG_CN;//登记机关（中文名称）
                public String NAME;//法定代表人
                public String OPFROM;//经营(驻在)期限自
                public String OPTO;//经营(驻在)期限至
                public String REGSTATE_CN;//登记状态中文名称
                public String C_PROVINCE;//省代码
                public String C_STATE;//经营状态  1 存续（在营，开业，在册）  2 吊销，未注销    3吊销，已注销  4，注销  5，撤销  6，迁出   9，其他
                public String REGCAP;//注册资本(金)  万元
                public String ENTTYPE_CN;//市场主体类型
                public String DOM;//住所
                public String INDUSTRYPHY;//行业代码
                public String INDUSTRYPHY_NAME;//行业代码中文名字
                public String OPSCOPE;//经营范围

            }
        }

    }

    public class Industry {
        public String IndustryCode;
        public int SubIndustryCode;
        public String Industry;
        public String SubIndustry;
    }

    public class OriginalName {
        public String Name;
    }

    public class HitReason {
        public String Field;
        public String Value;
    }


    public static citys citysList = new citys();

    /**
     * 省份实体类
     */
    public static class citys {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            /**
             * citycode : [{"c_code":"","c_name":"全省"},{"c_area_level":"2","c_code":"3601","c_name":"南昌市"},{"c_area_level":"2","c_code":"3602","c_name":"景德镇市"},{"c_area_level":"2","c_code":"3603","c_name":"萍乡市"},{"c_area_level":"2","c_code":"3604","c_name":"九江市"},{"c_area_level":"2","c_code":"3605","c_name":"新余市"},{"c_area_level":"2","c_code":"3606","c_name":"鹰潭市"},{"c_area_level":"2","c_code":"3607","c_name":"赣州市"},{"c_area_level":"2","c_code":"3608","c_name":"吉安市"},{"c_area_level":"2","c_code":"3609","c_name":"宜春市"},{"c_area_level":"2","c_code":"3610","c_name":"抚州市"},{"c_area_level":"2","c_code":"3611","c_name":"上饶市"}]
             * c_area_level : 1
             * c_code : 36
             * c_name : 江西省
             */
            public List<CityBean> city;

            public static class CityBean {
                public String c_area_level;
                public String c_code;
                public String c_name;
                /**
                 * c_code :
                 * c_name : 全省
                 */
                public List<CitycodeBean> citycode;

                public static class CitycodeBean {
                    public String c_code;
                    public String c_name;
                }
            }
        }
    }

    public static List<String> city = new ArrayList<>();//城市数据源


    public static HYData HYDataList = new HYData();

    /**
     * 行业实体类
     */
    public static class HYData {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            /**
             * EC_NAME : 所有行业
             * EC_VALUE :
             */
            public List<IndustryBean> industry;

            public static class IndustryBean {
                public String EC_NAME;
                public String EC_VALUE;
            }
        }
    }


    public static List<News> newsList;//新闻list

    /**
     * 新闻实体类
     */
    public static class News {
        public News(String img, String title, String content, String time) {
            this.img = img;
            this.title = title;
            this.content = content;
            this.time = time;
        }

        public String img;
        public String title;
        public String content;
        public String time;

    }


    public static GSXX gsxx = new GSXX();

    /**
     * 工商信息类
     */
    public static class GSXX {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public AreaBean area;//地区
            public BaseInfoBean baseInfo;//登记信息
            public List<PartnersInfoBean> partnersInfo;//投资人信息
            public List<EmployeesInfoBean> employeesInfo;//主要人员信息
            public List<AnnualReportsInfoBean> annualReportsInfo;//分支机构信息
            public List<ChangeRecordsInfoBean> changeRecordsInfo;//变更信息


            public static class AreaBean {
                public String C_COUNTY;
                public String C_CITY;
                public String C_PROVINCE;
            }

            /**
             * 登记信息
             */
            public static class BaseInfoBean {
                public String REGCAPCUR_CN;//注册资本(金)币种（中文名称）
                public String D_ADDTIME;
                public String REGCAP;//注册资本(金)
                public String NAME;//法定代表人
                public String ESTDATE;//成立日期
                public String REGSTATE_CN;//登记状态（中文名称）
                public String UNISCID;//统一社会信用代码
                public String OPTO;//经营(驻在)期限至
                public String ENTTYPE;
                public String INDUSTRYPHY;//行业门类
                public String REGNO;//注册号
                public String DOM;//住所
                public String REGORG_CN;//登记机关（中文名称）
                public String APPRDATE;//
                public String ENTTYPE_CN;//市场主体类型（中文名称）
                public String ENTNAME;//企业(机构)名称
                public String OPSCOPE;
                public String PRIPID;
                public String C_STATE;
                public String OPFROM;//经营(驻在)期限自
                public String C_PROVINCE;
            }

            /**
             * 投资人信息
             */
            public static class PartnersInfoBean {
                public String RESPFORM_CN;//承担责任方式/责任形式（中文名称）
                public String SUBCONPROP;//认缴出资比例
                public String SUBCONFORM;
                public String SUBCONAM;//认缴出资额
                public String ACCONAM;//实缴出资额
                public String INV;//投资人/主管部门名称
                public String CERTYPE_CN;
                public String INVTYPE_CN;//投资人类型/主管部门类型（中文名称）
                public String ACCONAMUSD;
                public String INVID;
                public String CONDATE;//认缴出资时间
                public String COUNTRY_CN;
                public String DOM;
                public String SCONFORM;//认缴出资方式
                public String SUBCONAMUSD;//认缴出资额折万美元
                public String PRIPID;
            }

            /**
             * 主要人员信息
             */
            public static class EmployeesInfoBean {
                public String NAME;//姓名
                public String CERNO;//职务（中文名称）
                public String POSITION_CN;//法定代表人标志/首席代表标志/负责人标识
                public String SEX;//性别
                public String PERSONID;
            }

            /**
             * 分支机构信息
             */
            public static class AnnualReportsInfoBean {
                public String PPRIPID;
                public String REGIDATE;//登记日期
                public String REGNO;//注册号
                public String REGORG_CN;//登记机关（中文名称）
                public String BRID;
                public String UNISCID;//统一社会信用代码
                public String PRIPID;
                public String BRNAME;//分支机构名称

            }

            /**
             * 变更信息
             */
            public static class ChangeRecordsInfoBean {
                public String ALTITEM_CN;//变更事项（中文名称）
                public String ALTAF;//变更后内容
                public String ALTDATE;//变更日期
                public String ALTBE;//变更前内容
            }
        }
    }


    /**
     * 工商变更临时
     */

    public static class ChangeTime {
        public String ALTDATE;//变更日期
        public List<ChangeData> changedata;//变更信息
    }

    public static class ChangeData {//临时仓库
        public String ALTDATE;//变更日期
        public String ALTITEM_CN;//变更事项
        public String ALTBE;//变更前
        public String ALTAF;//变更后

    }


    public static Mychattel MychattelS = new Mychattel();

    /**
     * 抵押信息动产实体类mortgageMP_List
     */
    public static class Mychattel {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<MortregInfoBean> mortregInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class MortregInfoBean {
                public String REGIDATE;//登记日期
                public String REGORG_CN;//登记机关（中文名称）
                public String PRICLASECAM;//抵押数额 万元
                public String PUBLICDATE;//公示日期
                public String MORREG_ID;//抵押登记ID
                public String MORREGCNO;//登记编号
            }
        }
    }
//    public static MyrealEstate MyrealEstateS=new MyrealEstate();
//    /**
//     * 抵押信息不动产实体类mortgageRE_List   [暂无]
//     */
//    public static class MyrealEstate {
//        public DataBean data;
//        public static class DataBean {
//            public PagingBean Paging;
//            public List<realEstateBean> realEstate;
//            public static class PagingBean {
//                public int TotalPage;
//                public int ShowCount;
//                public int TotalResult;
//                public int CurrentResult;
//                public int CurrentPage;
//            }
//            public static class realEstateBean {
//                public String C_INFOID;//登记id
//                public String C_DYDJZH;//登记编号
//                public String D_DJRQ;//登记日期
//                public String D_SQRQ;//公示时间
//                public String C_DJJG;//登记机关
//                public String C_DBFW;//详情
//                // public String valuation;//抵押物估值
//            }
//        }
//    }

    public static Admin AdminList = new Admin();

    /**
     * 行政审批
     */
    public static class Admin {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<LicCertificateBean> lic_Certificate;//行政许可信息
            public List<OtPermitBean> ot_Permit;//其他信息

            public static class LicCertificateBean {
                public String PRIPID;//主体身份代码
                public String LICNAME;//许可文件名称
                public String LICNO;//许可文件编号
                public String VALFROM;//有效期自
                public String LICANTH;//许可机关
                public String VALTO;//有效期止

            }

            public static class OtPermitBean {
                public String CERFITICATION_DATE;//发证日期

                public String LICANTH;//许可机关
                public String REGNO;//注册号
                public String VALFROM;//有效期自
                public String LICNAME_CN;//许可文件名称（中文名称）
                public String LICID;//其他部门行政许可信息
                public String ENTNAME;//名称
                public String LICNO;//许可文件编号
                public String VALTO;//有效期至
                public String PRIPID;//主体身份代码
                public String TYPE;//状态(  1有效   2无效)
                public String LICITEM;//许可内容
            }
        }
    }

    public static honorMar honorMarList = new honorMar();

    /**
     * 荣誉信息实体类
     */
    public static class honorMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<HonorInfoBean> honorInfo;

            public static class HonorInfoBean {
                public String ENTNAME;
                public String ANNUAL;

                public String HONORID;//荣誉id
                public String HONORNAME;//荣誉名称
                public String HONORCONTENT;//荣誉内容（类型）
                public String ORGAN;//机关
                public String C_UNIQUE_CODE;//统一社会信用代码
            }
        }
    }


    public static supportInfo supportInfoS = new supportInfo();

    /**
     * 企业扶持信息实体类
     */

    public static class supportInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<SupportInfoBean> supportInfo;

            public static class SupportInfoBean {

                public String PRIPID;//主体身份代码
                public Double ENJSPAMOUNT;//享受扶持政策的数额
                public String ENJSPCONTENT;//享受扶持政策内容
                public String IMPSPDEPART;//享受扶持政策的的部门
                public String IMPSPDATE;//实施扶持政策日期
            }
        }
    }

    public static pledgeMar pledgeMarList = new pledgeMar();

    /**
     * 出质信息实体类
     */

    public static class pledgeMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<PledgeInfoBean> pledgeInfo;

            public static class PledgeInfoBean {
                public String REGNO;//股权所在公司注册号
                public String EQUITYNO;//股权登记编号
                public String PLEDGOR;//出质人
                public String PLEDBLICNO;//出质人证照号
                public String PLEDBLICTYPE_CN;//出质人证件类型
                public String IMPAM;//出质股权数额
                public String IMPORG;//质权人
                public String IMPORGBLICNO;//质权人证照号
                public String ENTNAME;//股权所在公司名称
                public String EQUPLEDATE;//股权出质登记日期
                public String PUBLICDATE;//公示日期
            }
        }
    }

    /**
     * 经营异常信息实体类
     */

    public static abnormalInfo abnormalInfoS = new abnormalInfo();

    public static class abnormalInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<AnomaliesInfoBean> anomaliesInfo;

            public static class AnomaliesInfoBean {
                public String BUSEXCLIST;
                public String DECORG;//列入决定机关（中文名称）
                public String REDECORG_CN;//移出决定机关（中文名称）
                public String SPECAUSE;//
                public String ABNTIME;//列入日期
                public String SPECAUSE_CN;//列入经营异常名录原因类型（中文名称）
                public String REMEXCPRES_CN;//移出经营异常名录原因（中文名称）
                public String UNISCID;
                public String REDECORG;
                public String REMDATE;//移出日期
                public String REMEXCPRES;
                public String REGNO;
                public String LEREP;
                public String CERNO;
                public String ENTNAME;
                public String ISMOVE;
                public String CERTYPE;
                public String PRIPID;
                public String DECORG_CN;
            }
        }
    }


    public static PatentInfo PatentInfoS = new PatentInfo();
    public static PatentInfo PatentInfoSMore = new PatentInfo();
    /**
     * 专利信息实体类
     */
    public static class PatentInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<PatentInfoBean> patentInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class PatentInfoBean {
                public String LAWTIME;//法律状态生效日
                public String RCODECN;//申请公布号
                public String UNISCID;//统一社会信用代码
                public String ADDTIME;//添加时间
                public String RCODECNDATE;//申请公布日期
                public String AGENT;//代理人
                public String REGNO;//企业注册号
                public String IPCNAME;//分类名称
                public String IPCCODE;//分类号
                public String UPDATETIME;//更新时间
                public String ID;//专利ID   后加
                public String PRIPID;//企业ID
                public String PATENTNAME;//专利名称
                public String RCODE;//申请号
                public String RDATE;//申请日期
                public String ACODE;//授权公布号
                public String ADATE;//授权公布日期
                public String INVENTOR;//发明人
                public String PATENTTYPE;//专利类型
                public String AGENCY;//代理机构
                public String LEGALSTATUS;//法律状态
                public String DETAILINFO;//详细信息
                public String ABSTRACTGRAPH;//图片地址
                public String ENTNAME;//公司名称 后加
            }
        }
    }

    public static punishMar punishMarList = new punishMar();

    /**
     * 处罚信息列表实体类
     */

    public static class punishMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<OtCaseInfoBean> otCaseInfo;

            public static class OtCaseInfoBean {
                public String PRIPID;
                public String PUBLICDATE;//公示日期

                public String CASEID;//行政处罚ID
                public String ILLEGACTTYPE;//违法行为类型
                public String PENCONTENT;//处罚内容
                public String PENDECNO;//处罚决定书文号
                public String JUDAUTH;//作出行政处罚决定机关名称
                public String PENDECISSDATE;//作出处罚决定书日期

            }
        }
    }
    public static punishMarDri punishMarDriList = new punishMarDri();

    /**
     * 处罚信息列表详情实体类
     */
    public static class punishMarDri {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            /**
             * DWFDDBR : 李义
             * JUDAUTH : 南昌市城市管理行政执法局西湖分局
             * PENDECCONTENT : 你单位于2014年8月14日在江西省核工宾馆因未取得建筑工程许可证擅自施工的行为 违反了《江西省建筑管理条例》第八条第一款 依据《江西省建筑管理条例》第三十七条和《行政处罚法》第23条规定 现责令你于2014年9月11日前改正上述行为，并决定对你做出初一工程合同价款贰拾万元的2%，共计肆仟元整的行政处罚。现要求：你单位于2014年9月12日前将罚款交至南昌市工商银行，地址市区任意经营网点。
             * ENTNAME : 江西省核工宾馆（江西省核工业地质局招待所）
             * PENDECNO : 洪行执西字[2014]6110034号
             * UNISCID : 91360000858384498J
             * PENDECISSDATE : 2014-08-14
             * PENCONTENT : 责令补办建筑工程许可证限期改正;罚款4000元
             * ILLEGACTTYPE : 未取得建筑工程许可证
             * Remark : A
             * PRIPID : 3600002000057947
             * PUBLICDATE : 2014-08-14
             * CASEID : 34E5250F4BB04D2C940E1EE48F4FBD2B
             */
            public List<OtCaseInfoBean> otCaseInfo;
            public static class OtCaseInfoBean {
                public String DWFDDBR;
                public String JUDAUTH;
                public String PENDECCONTENT;
                public String ENTNAME;
                public String PENDECNO;
                public String UNISCID;
                public String PENDECISSDATE;
                public String PENCONTENT;
                public String ILLEGACTTYPE;
                public String Remark;
                public String PRIPID;
                public String PUBLICDATE;
                public String CASEID;
            }
        }
    }
    public static trademarkModel trademarkModelS = new trademarkModel();
    public static trademarkModel trademarkModelSMore = new trademarkModel();
    /**
     * 商标信息实体类 +  首页商标查询实体类
     */
    public static class trademarkModel {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<BrandInfoBean> brandInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class BrandInfoBean {
                public String CLASSNAME;//商标分类号名称
                public String BRANDLIST;//商品/服务列表
                public String PROCEDURE;//商标流程
                public String ID;//商标id
                public String PRIPID;//企业id
                public String REGNO;//注册号
                public String APPLICATIONDATE;//申请日期
                public String APPLICANT;//申请人
                public String BRANDSTAUTS;//商标状态
                public String CLASSIFYID;//商标分类号
                public String BRANDIMG;//商标图标链接
                public String AGENCY;//代理机构
                public String LIFESPAN;//使用期限
                public String REGCORE;//商标注册号
                public String BRANDNAME;//商标名称
                public String ENTNAME;//企业名称
                public String UNISCID;//社会统一信用代码
            }
        }
    }

    public static ZZQModels ZZQModelsList = new ZZQModels();

    /**
     * 著作信息实体类
     */

    public static class ZZQModels {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public class DataBean {
            public List<WorkCopyrightInfoBean> workCopyrightInfo;//著作权信息对象
            public List<CopyrightInfoBean> copyrightInfo;//软件著作权信息对象

            public class WorkCopyrightInfoBean {
                public String REGISTERID;//登记号
                public String WORKCLASS;//作品类别
                public String ADDTIME;//添加时间
                public String UNISCID;//统一社会信用代码
                public String FINISHDATE;//创作完成日期
                public String FIRSTDATE;//首次发表日期
                public String WORKOWNER;//著作权人
                public String REGISTERDATA;//登记日期
                public String REGNO;//企业注册号REGNORE
                public String ENTNAME;//企业名称
                public String ID;
                public String WORKNAME;//作品名称
                public String PRIPID;//企业PRIPID
                public String UPDATETIME;//更新时间
            }

            public class CopyrightInfoBean {
                public String ID;
                public String SOFTWARENAME;//软件名称
                public String REGISTERDATA;//登记日期
                public String REGISTERID;//登记号
                public String SOFTWARESHORT;//软件简称
                public String STARTINGDATE;//首发日期
                public String CATEGORYID;//分类号
                public String OWNERNAME;//著权人
                public String UNISCID;//统一社会信用代码
                public String ADDTIME;//添加时间
                public String VERSIONNUMBER;//版本号
                public String REGNO;//企业注册号
                public String ENTNAME;//企业名称
                public String PRIPID;//企业PRIPID
                public String UPDATETIME;//更新时间

            }
        }
    }


    public static ZZGS ZZGSModels = new ZZGS();

    /**
     * 新自主公示
     */
    public static class ZZGS {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<EimcaseInfoBean> eimcaseInfo;//行政处罚信息
            public List<AnSubcapitalInfoBean> anSubcapitalInfo;//股权变更
            public List<EimpermitInfoBean> eimpermitInfo;//许可信息
            public List<ShareholdersandInvestmentBean> shareholdersandInvestment;//股东及出资信息
            public List<AnbaseInfoBean> anbaseInfo;//年报基本信息
            public List<AimippldgInfoBean> eimippldgInfo;//知识产权登记信息

            public static class EimcaseInfoBean {
                public String PENTYPE_CN;       //处罚种类（中文名称）
                public String REMARK;            //备注
                public String UNISCID;          //统一社会信用代码
                public String PENDECISSDATE;    //作出处罚决定书日期
                public String PENAM;            //罚款金额
                public String CASEID;         //行政处罚ID
                public String REGNO;           //注册号
                public String JUDAUTH;        //作出行政处罚决定机关名称
                public String ENTNAME;        //企业(机构)名称
                public String PENDECNO;         //处罚决定书文号
                public String PENTYPE;        //处罚种类
                public String FORFAM;         //没收金额
                public String ILLEGACTTYPE;    //违法行为类型
                public String PENCONTENT;       //处罚内容
                public String PRIPID;          //主体身份代码
                public String PUBLICDATE;      //公示日期
            }

            public static class EimpermitInfoBean {//企业自主公示许可信息
                public String SUGREVREASON;
                public String INVALIDDATE;
                public String CANDATE;
                public String EQUPLECANREA;
                public String REVDATE;
                public String INVALIDREA;
                public String TYPE;
                public String LICNAME_CN;//许可文件名称
                public String LICNO;//许可文件编号
                public String VALFROM;//有效期起
                public String VALTO;//有效期止
                public String LICANTH;//许可机关
                public String PUBLICDATE;//公示日期
                public String LICITEM;//许可内容
                public String invalidDate;//起止时间拼接
            }

            public static class ShareholdersandInvestmentBean {//企业自主公示股东及出资信息
                public String ACCONFORM_CN;//实缴出资方式CN
                public String ACCONDATE;//实缴出资日期
                public String ACCONFORM;//实缴出资方式
                public String CONDATE;
                public String CONFORM;//认缴出资方式
                public String SUBCONAM;//认缴额=认缴出资额
                public String ACCONAM;//实缴额=实缴出资额
                public String CONFORM_CN;//认缴出资方式CN
                public String INV;//股东 发起人名字
                public String PUBLICDATE;//认缴出资日期=公示日期
                public String ACPUBLICDATE;//实缴公示日期
            }

            public static class AnbaseInfoBean {//企业自主公示年报基本信息
                public String ANCHEDATE;//年报年份
                public String ANCHEYEAR;//年报年度
                public String ANCHEID;//年报id
            }

            public static class AnSubcapitalInfoBean {//企业自主公示股权变更
                public String INV;//股东名称
                public String TRANSAMPRBF;//转让前股权比例
                public String TRANSAMPRAF;//转让后股权比例
                public String ALTDATE;//股权变更日期
            }

            public static class AimippldgInfoBean {//知识产权登记信息
                public String TYPENAME;      //状态名称
                public String TMNAME;        //名称
                public String INVALIDDATE;     //其他无效日期
                public String PLEREGPERFROM;      //知识产权质权登记期限自
                public String EQUPLECANREA;      //注销原因
                public String CANDATE;       //注销日期
                public String UNISCID;      //统一社会信用代码
                public String KINDS;         //种类代码
                public String PLEREGPERTO;    //知识产权质权登记期限止
                public String REGNO;          //注册号
                public String TMREGNO;        //商品注册号
                public String PLEDGOR;       //知识产权出质人名称
                public String PLEID;           //出质ID
                public String ENTNAME;          //企业机构名称
                public String INVALIDREA;       //其他无效原因
                public String PRIPID;          //主体身份证代码
                public String PUBLICDATE;          //公示日期
                public String IMPORG;          //知识产权质权人名称
                public String TYPE;            //状态
            }
        }
    }


    public static AlertInfo AlertInfoS = new AlertInfo();

    /**
     * 新预警信息
     */
    public static class AlertInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<LicenseExpires> licenseExpires;//证照到期
            public List<LicenseExpiredBean> licenseExpired;//证照过期
            public List<OrderCorrection> orderCorrection;//责令改正
            public List<OweLoan> oweLoan;//欠贷信息
            public List<OweTax> oweTax;//欠税信息
            public List<OweSalary> oweSalary;//欠薪信息

            public static class LicenseExpiredBean {//证照过期
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }

            public static class OrderCorrection {//责令改正
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }

            public static class OweLoan {//欠贷信息
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }

            public static class OweSalary {//欠薪信息
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }

            public static class OweTax {//欠税信息
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }

            public static class LicenseExpires {//证照到期
                public String SUPDEPARTMENT;//上级部门编码
                public String STATE;//数据状态（0有效，1无效）
                public String BUSINESSATT;//业务属性
                public String SOURCENAME;//部门名称
                public String D_AD;
                public String WARNID;//数据id
                public String DEPID;//部门id
                public String UNISCID;//社会统一信用代码
                public String WARNCONTENT;//预警内容
                public String WARNAMOUNT;//预警金额
                public String WARNSTATUS;//预警状态
                public String REGNO;//注册号
                public String SOURCE;//数据来源（部门）
                public String DATATYPE;//数据种类
                public String ENTNAME;//企业名称
                public String ORGAN;//预警机关
                public String USERNAME;//上传用户
                public String WARNDATE;//预警日期
                public String PRIPID;//企业id
                public String UPDATETIME;//更新时间
                public String UPDEPARTMENTNAME;//上级部门名称
            }
        }
    }

    public static JudicialDocumentsMar JudicialDocumentsMarList = new JudicialDocumentsMar();

    /**
     * 司法信息实体类
     */
    public static class JudicialDocumentsMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<JudgmentInfoBean> judgmentInfo;//司法文书信息
            public List<CourtcaseInfoBean> courtcaseInfo;//失信被执行人信息
            public List<SfInfoBean> sfInfo;//股权冻结信息
            public List<SfAlterBean> sfAlter;//股权变更信息

            public static class JudgmentInfoBean {//司法文书信息
                public String CASENUM;//判决书文号
                public String SENTENCECONMENT;//判决内容
                public String SUPDEPARTMENT;//作出判决机关
                public String SENTENCEDATE;//作出判决书日期
                public String REDECORG_CN;//详情
            }

            public static class CourtcaseInfoBean {//失信被执行人信息
                public String COURT_NAME;//执行法院
                public String REG_DATE;//立案时间
                public String COURTCASEID;//法院案件表ID
                public String GIST_CID;//执行依据文号
                public String PERFORMANCE;//被执行人的履行情况
                public String DISREPUT_TYPE_NAME;//类型名称（APP增加此项）
                public String DATARESOURCE;
                public String STATE;
                public String CASE_CODE;
                public String INAME;
                public String PUBLISH_DATE;
                public String GIST_UNIT;
                public String REGNORE;
                public String AREA_NAME;
                public String BUESINESSENTITY;
                public String DELETEMARK;
                public String DUTY;//具体详情
                public String CARDTYPE;
            }
            public static class SfAlterBean {//股权变更信息
                public String ALIEN;//受让人
                public String REGNO;//注册号
                public String FROAUTH;//执行法院
                public String FROAM;//股权数额
                public String INV;//被执行人

            }
            public static class SfInfoBean {//股权冻结信息
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

                public String REGCAPCUR_CN;
                public String FROTO;
                public String EXECUTEITEM_CN;//执行事项
                public String FROZSTATE_CN;
                public String INV;

                public String FROID;//冻结ID
                public String FROFROM;//冻结期限自
                public String FROZDEADLINE;//冻结期限
                public String FROAUTH;//执行法院
                public String INVTYPE_CN;//被执行人
                public String FROAM;//股权数额


            }

        }
    }


    public static advertisementMar advertisementMarList = new advertisementMar();

    /**
     * 广告信息实体类
     */
    public static class advertisementMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<AdvertisingInfoBean> advertisingInfo;

            public static class AdvertisingInfoBean {
                public String SOURCENAME;
                public String ADVERTID;//ID
                public String C_LEVEL;// 广告资质级别
                public String CATEGORY;//类别
                public String IDENTIFYDATE;//认定时间
                public String VALFORM;//有效期自
                public String VALTO;//有效期至
                public String IDENTIFYORGANS;//认定机关
            }
        }
    }

    public static obeyedMar obeyedMarList = new obeyedMar();

    /**
     * 守合同重信用信息实体类
     */

    public static class obeyedMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<KchcInfoBean> kchcInfo;

            public static class KchcInfoBean {
                public String PRIPID;//企业id
                public String ENTNAME;//企业名称
                public String REGNO;//注册号
                public String UNISCID;//统一社会信用代码
                public String CONTENT;//内容
                public String IDENTIFYDATE;//认定日期
                public String IDENTIFYORGANS;//认定机关
                public String STATE;//状态，1表示有效，2表示无效
            }
        }
    }

    public static MyNews MyNewsS = new MyNews();//初始新闻信息
    public static MyNews MyNewsSMore = new MyNews();//更多新闻列表

    /**
     * 新闻实体类
     */
    public static class MyNews {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<NewInformationBean> newInformation;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class NewInformationBean {
                public String AUTHOR;//作者
                public String MODEL_ID;
                public String RELEASE_DATE;//发布时间
                public String DESCRIPTION;//描述
                public String ID;
                public String CONTENT_IMG;//内容图
                public String TITLE_IMG;//标题图片
                public String ORIGIN;//来源
                public String TITLE;//标题
            }
        }
    }

    public static QJiugongG QJiugongGList = new QJiugongG();

    /**
     * 九宫格数据
     */
    public static class QJiugongG {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<AllCountBean> allCount;
            public List<BaseInfoBean> baseInfo;

            public static class AllCountBean {
                public String Pripid;

                public String EnterAddtionID;//企业附加表ID
                public boolean IsFavorite;//关注信息
                public String BiddingCount;//招标总数
                public String JobCount;//企业招聘总数
                public String EntNewCount;//企业新闻总数
                public String HonorCount;//荣誉信息
                public String JudiciaryCount;//司法信息
                public String PledgeCount;//出质
                public String CopyrightCount;//著作权
                public String AnnualCount;//自主公示
                public String AdvertisementCount;//广告资质
                public String BaseInfoCount;//基本信息 工商
                public String ApprovalCount;//赞同  行政审批
                public String PunishCount;//行政处罚
                public String WarningCount;//预警信息
                public String TrademarkCount;//商标
                public String AbnormityCount;//经营异常
                public String CreditCount;//守合同重信用
                public String SupportCount;//扶持
                public String MortgagorCount;//抵押
                public String PatentCount;//专利
                public String EntShowCount;//企业展示
                public String PageView;//浏览量    +++
                public String IsClaim;//是否认领   +++
            }

            public static class BaseInfoBean {
                public String REGSTATE;//登记状态
                public String REGNO;//企业注册号
                public String NAME;//法人
                public String REGCAP;//注册资金
                public String ESTDATE;//成立日期
                public String ENTTYPE_CN;//公司类型（市场主体类型中文）
                public String ENTNAME;//公司名字
                public String REGSTATE_CN;//经营状态（中文）
                public String UNISCID;//统一社会信用代码
                public String PRIPID;//主体身份代码
                public String ENTTYPE;//市场主体类型

            }
        }
    }


    /**
     * 企业的评论列表 + 我的评价 列表
     */
    public static MyCommentlistr MyCommentlistrS = new MyCommentlistr();

    public static class MyCommentlistr {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<CommentInfoBean> commentInfo;

            public static class CommentInfoBean {
                public String commentSonInfoCount;
                public String HEADICON;
                public String ENTNAME;
                public String PRIPID;
                public List<CommentSonInfoBean> commentSonInfo;
                public String FAILEDQTY;//吐槽数量
                public String MEMBERID;//本评论用户ID
                public String CREATETIME;//评论时间
                public String COMMENTID;//评论ID
                public String CONTENT;//点评内容
                public String SUCCESSQTY;//点赞数量123123.
                public String ISSUCCESS;//是否点赞 0为否，1为是
                public String MEMBERNAME;//本评论用户名称
                public String ISFAILED;//是否吐槽 0为否，1为是


                public static class CommentSonInfoBean {
                    public String MEMBERID;//本评论用户ID
                    public String MEMBERNAME;//用户名称
                    public String CONTENT;//点评内容
                    public String CREATETIME;//回复评论时间
                    public String FAILEDQTY;//吐槽数量
                    public String HEADICON;//头像路径
                    public String ENTNAME;//企业名称
                    public String PRIPID;//企业pripid
                    public String COMMENTID;//用户ID
                    public String SUCCESSQTY;//点赞数量


//                    public String CHILDMEMBERID;//用户ID
//                    public String REPLAYCOMMENT;//回复评论内容
//                    public String CHILDMEMBERNAME;//用户名称
//                    public String REPLAYTIME;//回复评论时间
                }
            }

            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
        }
    }

    public static List<CommentSonInfoBean> commentSonInfo = new ArrayList<>();

    /**
     * 回复评论（楼中楼）
     */
    public static class CommentSonInfoBean {
        public String FAILEDQTY;//吐槽数量
        public String HEADICON;//头像路径
        public String CONTENT;//点评内容
        public String MEMBERID;//本评论用户ID
        public String ENTNAME;//企业名称
        public String CREATETIME;//回复评论时间
        public String PRIPID;//企业pripid
        public String COMMENTID;//用户ID
        public String SUCCESSQTY;//点赞数量
        public String MEMBERNAME;//用户名称
    }

    /**
     * 点赞 and 差评  and 发表 and 回复
     */
    public class Root202 {
        public String message;
        public int status;
        public Data202 data;
        public String version;
    }

    public class Data202 {
        public String result;
    }

    public static String Result = "";

    public static ToComm ToCommMar = new ToComm();

    /**
     * 评论 发表 and 回复
     */
    public static class ToComm {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String affectedRow;
        }
    }

    public static User user = new User();

    /**
     * 用户信息 + 修改个人资料  实体类
     */
    public static class User {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<MemberInfoBean> memberInfo;

            public static class MemberInfoBean {
                public String EDUCATION_NAME;//学历
                public String IP;//登陆IP地址
                public String CREATETIME;//注册时间
                public String HEADICON;//头像
                public String LAST_PASSWORD;//最后一次修改密码前密码
                public String TEL;//手机
                public String INDUSTRY_NAME;//所属行业
                public String STATUS;//注册状态（默认为0，未激活；1，已激活）
                public String INDUSTRY;//所属行业
                public String SEX;//性别（0，女；1，男）
                public String ISDELETE;//账户是否删除 默认0未删除，1已删除
                public String ID;//用户ID
                public String EDUCATION;//学历
                public String USERNAME;//用户账户
                public String EMAIL;//邮箱
                public String ALIASNAME;//用户姓名
                public String PASSWORD;//密码
            }
        }
    }

    public static MyComplaint myComplaint = new MyComplaint();

    /**
     * 我的投诉列表实体类
     */
    public static class MyComplaint {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<ComplaintInfoBean> complaintInfo;
            public PagingBean Paging;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class ComplaintInfoBean {
                public String TYPE_NAME;//投诉类型
                public String REGNORE;//注册号
                public String ENTNAME;//投诉企业名称
                public String ID;//投诉表ID
                public String REMARK;////投诉内容
                public String UNISCID;//社会统一信用代码
                public String CREATETIME;//投诉时间
                public String PRIPID;//企业pripid
                public String STATUS_NAME;//审核状态
                public String MEMBER_ID;//投诉人
                public String TITLE;//投诉标题
            }
        }
    }

    /**
     * 关注 and 取消关注
     */
    public static Favotite FavotiteS = new Favotite();

    public static class Favotite {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String affectedRow;
        }
    }

    /**
     * 我的关注列表
     */
    public static FavotiteList FavotiteListS = new FavotiteList();

    public static class FavotiteList {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<FollowInfoBean> followInfo;

            public static class FollowInfoBean {
                public String REGCAPCUR_CN;//注册资本(金)币种（中文名称）
                public String REGCAP;//注册资本(金)
                public String NAME;//法定代表人
                public String REGNORE;//注册号
                public String ENTNAME;//企业名称
                public String ID;//关注ID
                public String UNISCID;//社会统一信用代码
                public String CREATETIME;//关注时间
                public String STATUS;//状态: 0为正常，1为取消
                public String PRIPID;//企业pripid
                public String ENTTYPE;//企业类型  不为9500则为企业，9500为个体
                public String MEMBER_ID;//用户ID
            }
        }
    }


    public static ComplaintDetail complaintDetail = new ComplaintDetail();

    /**
     * 投诉详情实体类
     */
    public static class ComplaintDetail {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<ComplaintInfoBean> complaintInfo;

            public static class ComplaintInfoBean {
                public String attachmentInfoCount;
                public String TYPE_NAME;//投诉类型
                public String REGNORE;//注册号
                public String ENTNAME;//投诉企业名称
                public String ID;//投诉表ID
                public String REMARK;////投诉内容
                public String UNISCID;//社会统一信用代码
                public String CREATETIME;//投诉时间
                public String PRIPID;//企业pripid
                public String STATUS_NAME;//审核状态
                public String MEMBER_ID;//投诉人
                public String TITLE;//投诉标题
                /**
                 * ATTACHMENT_CREATEUSER : 2e6f572ee06b406f81d96449c121344e
                 * ATTACHMENT_DESCRIPTION : 242cf4eb14d3aa33920fad064a6e6fe2
                 * ATTACHMENT_NAME : 36d905e1602448089a515e1fda679b401476065804498.jpg
                 * ATTACHMENT_TYPE : 投诉类型
                 * ATTACHMENT_ID : f2b95cfd51a94fc5bd26a1f35c3487d0
                 * ATTACHMENT_CREATETIME : 2016-10-10 10:16:44
                 * ATTACHMENT_PATH:/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL
                 * RELATION_ID : 05eda095d6ab4a27be421d5a52343dc5
                 */
                public List<AttachmentInfoBean> attachmentInfo;

                public static class AttachmentInfoBean {
                    public String ATTACHMENT_CREATEUSER;
                    public String ATTACHMENT_DESCRIPTION;
                    public String ATTACHMENT_NAME;
                    public String ATTACHMENT_TYPE;
                    public String ATTACHMENT_ID;
                    public String ATTACHMENT_CREATETIME;
                    public String ATTACHMENT_PATH;
                    public String RELATION_ID;

                }
            }
        }

    }

    public static ClaimUtils ClaimUtilsModel = new ClaimUtils();

    /**
     * 提交认领实体类 + 提交认领附件实体类
     */
    public static class ClaimUtils {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String relationId;
            public String affectedRow;
        }
    }

    public static ClaimFuJianUtils ClaimFuJianUtilsList = new ClaimFuJianUtils();

    /**
     * 提交认领附件实体类
     */
    public static class ClaimFuJianUtils {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String affectedRow;
        }
    }


    public static NewClaimUtils NewClaimUtilsList = new NewClaimUtils();

    /**
     * 最新认领列表实体类 + 我的认领列表实体类
     */
    public static class NewClaimUtils {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<ClaimInfoBean> claimInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;

            }

            public static class ClaimInfoBean {
                public String REGCAP;//注册资本(金)
                public String NAME;//法定代表人
                public String ESTDATE;//成立日期
                public String REGSTATE_CN;//登记状态（中文名称）
                public String REMARK;//认领描述
                public String TEL;//联系电话
                public String ENTNAME;//企业名称
                public String MEMBERID;//认领人
                public String ENTTYPE;//市场主体类型代码
                public String USERNAME;//用户账户
                private String ALIASNAME;//认领人名字
                public String CLAIMID;//认领ID
                public String CLAIMTIME;//认领时间
                public String STATUS;//审核状态（0未审核，1已审核通过 ,  2审核不通过）
                public String STATUSNAME;//认领状态中文
                public String REGNORE;//注册号
                public String ENTTYPE_CN;//企业类型
                public String EMAIL;//邮件地址
                public String PRIPID;//认领企业PRIPID

            }
        }
    }

    public static NewClaimDils NewClaimDilsList = new NewClaimDils();

    /**
     * 认领详情
     */
    public static class NewClaimDils {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<ClaimInfoBean> claimInfo;

            public static class ClaimInfoBean {
                public String RESULTREMARK;
                public String RESULTUSER;
                public String RESULTTIME;
                public String attachmentInfoCount;
                public String REGCAP;//注册资本(金)
                public String NAME;//法定代表人
                public String ESTDATE;//成立日期
                public String REGSTATE_CN;//登记状态（中文名称）
                public String REMARK;//认领描述
                public String TEL;//联系电话
                public String ENTNAME;//企业名称
                public String MEMBERID;//认领人
                public String ENTTYPE;//市场主体类型代码
                public String USERNAME;//用户账户
                private String ALIASNAME;//认领人名字
                public String CLAIMID;//认领ID
                public String CLAIMTIME;//认领时间
                public String STATUS;//审核状态（0未审核，1已审核通过 ,  2审核不通过）
                public String STATUSNAME;//认领状态中文
                public String REGNORE;//注册号
                public String ENTTYPE_CN;//企业类型
                public String EMAIL;//邮件地址
                public String PRIPID;//认领企业PRIPID

                public List<AttachmentInfoBean> attachmentInfo;

                public static class AttachmentInfoBean {
                    public String ATTACHMENT_CREATEUSER;
                    public String ATTACHMENT_DESCRIPTION;
                    public String ATTACHMENT_NAME;
                    public String ATTACHMENT_TYPE;
                    public String ATTACHMENT_ID;
                    public String ATTACHMENT_CREATETIME;
                    public String ATTACHMENT_PATH;
                    public String RELATION_ID;

                }
            }
        }

    }

    public static ToComplain toComplain = new ToComplain();

    /**
     * 提交投诉成功后返回数据实体类
     */
    public static class ToComplain {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String relationId;
            public String affectedRow;
        }
    }

    public static TwoDim TwoDimSli = new TwoDim();

    /**
     * 二维码名片
     */
    public static class TwoDim {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String result;
        }
    }
    public static zl_search zl_searchS = new zl_search();
    public static zl_search zl_searchSMore = new zl_search();

    /**
     * 首页专利查询实体类
     */
    public static class zl_search {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<PatentInfoBean> patentInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class PatentInfoBean {
                public String LAWTIME;//法律状态生效日
                public String RCODECN;//申请公布号
                public String UNISCID;//统一社会信用代码
                public String ADDTIME;//添加时间
                public String RCODECNDATE;//申请公布日期
                public String AGENT;//代理人
                public String REGNO;//企业注册号
                public String IPCNAME;//分类名称
                public String IPCCODE;//分类号
                public String UPDATETIME;//更新时间
                public String ID;//专利ID   后加
                public String PRIPID;//企业ID
                public String PATENTNAME;//专利名称
                public String RCODE;//申请号
                public String RDATE;//申请日期
                public String ACODE;//授权公布号
                public String ADATE;//授权公布日期
                public String INVENTOR;//发明人
                public String PATENTTYPE;//专利类型
                public String AGENCY;//代理机构
                public String LEGALSTATUS;//法律状态
                public String DETAILINFO;//详细信息
                public String ABSTRACTGRAPH;//图片地址
                public String ENTNAME;//公司名称 后加
            }
        }
    }

    public static MyHot MyHotS = new MyHot();

    /**
     * 热点
     */
    public static class MyHot {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<SearchHistoryBean> searchHistory;

            public static class SearchHistoryBean {
                public String MEMBER_ID;//用户Id
                public String KEYWORDS;//关键词
                public String LOGID;//记录ID
                public String LOGTIME;//访问时间
                public String REMARK;//备注
            }
        }
    }
    public static MyDishonesty MyDishonestyS = new MyDishonesty();
    public static MyDishonesty MyDishonestySMore = new MyDishonesty();
    /**
     * 失信
     */
    public static class MyDishonesty {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            public List<CourtcaseInfoBean> courtcaseInfo;

            public static class PagingBean {
                public String TotalPage;
                public String ShowCount;
                public String TotalResult;
                public String CurrentResult;
                public String CurrentPage;
            }

            public static class CourtcaseInfoBean {
                public String CARDNUM;//身份证号码/组织机构代码
                public String COURT_NAME;//执行法院
                public String CASE_CODE;//案号
                public String DISREPUT_TYPE_NAME;//失信被执行人行为具体情形
                public String INAME;//被执行人姓名/名称
                public String COURTCASEID;//法院案件表ID
                public String PERFORMANCE;//被执行人的履行情况
                public String PUBLISH_DATE;//发布时间
                public String GIST_UNIT;//做出执行依据单位
                public String REG_DATE;//立案时间
                public String GIST_CID;//执行依据文号
                public String CASE_CONTENT;//处罚内容
                public String AREA_NAME;//省份
                public String BUESINESSENTITY;//法定代表人或者负责人姓名
                public String DUTY;//生效法律文书确定的义务
                public String CARDTYPE;//类型，1为企业，0为自然人
            }
        }
    }

    public static String ReportText = "";


    public static MyNewApp MyNewAppS = new MyNewApp();

    /**
     * 最新APP版本
     * {"message":"success","status":"1","data":{"VersionInfo":[{"STATE":"1","NAME":"android","VERSION":"1.0.1123","REMARK":"frtshfgxfg","PATH":"F:\\javaworkmars1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\zhirong.creditcode\\uploadfiles","CREATETIME":"2016年09月23日","TYPE":"1"}]},"version":"v1.0"}
     */
    public static class MyNewApp {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<VersionInfoBean> VersionInfo;

            public static class VersionInfoBean {
                public String STATE;
                public String NAME;
                public String VERSION;
                public String REMARK;
                public String PATH;
                public String CREATETIME;
                public String TYPE;
            }
        }
    }

    public static LBimg LBimgS = new LBimg();

    /**
     * APP首页轮播图
     */
    public static class LBimg {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<CarouselInfoBean> carouselInfo;

            public static class CarouselInfoBean {
                public String TOURL;
                public String NAME;
                public String ORDERBY;
                public String CREATEUSER_ID;
                public String ID;
                public String AREA_CODE;
                public String CREATEUSER_NAME;
                public String PATH;
                public String CREATETIME;
            }
        }
    }

    public static disRoomMar disRoomMarList = new disRoomMar();
    public static disRoomMar disRoomMarList1 = new disRoomMar();//备用仓库

    /**
     * 字典表实体类
     */
    public static class disRoomMar {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public List<DictionarieInfoBean> dictionarieInfo;

            /**
             * NAME : 非人道主义
             * ORDY_BY : 1
             * PNAME : 投诉管理
             * JB : 2
             * ZD_ID : 212a6765fddc4430941469e1ec8c8e6c
             * BIANMA : 001
             * PARENT_ID : c067fdaf51a141aeaa56ed26b70de863
             */
            public static class DictionarieInfoBean {
                public String NAME;//名称
                public String ORDY_BY;//排序号
                public String PNAME;//父名称
                public String JB;//级别
                public String ZD_ID;//字典表ID
                public String BIANMA;//编码
                public String PARENT_ID;//父id
            }
        }
    }

    public static delclaimR delclaimRList = new delclaimR();

    /**
     * 删除认领附件
     */
    public static class delclaimR {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public String affectedRow;
        }
    }


    public static getad getadList = new getad();

    /**
     * 获取短信验证码
     */
    public static class getad {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public String verification_code;
            public Object sub_msg;
            public String sub_code;
        }
    }

    public static getMap getMapList = new getMap();

    /**
     * 地图
     */
    public static class getMap {
        public String status;
        public String info;
        public String infocode;
        public String count;
        /**
         * formatted_address : 江西省南昌市青山湖区绿茵路|669号
         * province : 江西省
         * citycode : 0791
         * city : 南昌市
         * district : 青山湖区
         * township : []
         * neighborhood : {"name":[],"type":[]}
         * building : {"name":[],"type":[]}
         * adcode : 360111
         * street : []
         * number : []
         * location : 115.857949,28.698126
         * level : 门牌号
         */
        public List<GeocodesBean> geocodes;
        public static class GeocodesBean {
            public String formatted_address;
            public String province;
            public String citycode;
            public String city;
            public String district;
            public NeighborhoodBean neighborhood;
            public BuildingBean building;
            public String adcode;
            public String location;
            public String level;
            public List<?> township;
            public List<?> street;
            public List<?> number;
            public static class NeighborhoodBean {
                public List<?> name;
                public List<?> type;
            }
            public static class BuildingBean {
                public List<?> name;
                public List<?> type;
            }
        }
    }
    public static getwalking getwalkingList = new getwalking();
    /**
     * 步行
     */
    public static class getwalking {
        public String status;
        public String info;
        public String infocode;
        public String count;
        public RouteBean route;
        public static class RouteBean {
            public String origin;//起点坐标  "115.858315,28.690403",
            public String destination;//终点坐标    "115.857949,28.698126",
            public List<PathsBean> paths;
            public static class PathsBean {
                public String distance;//起点和终点的步行距离 单位：米  "1199",
                public String duration;//步行时间预计 单位：秒   "856",
                public List<StepsBean> steps;
                public static class StepsBean {
                    public String instruction;//路段步行指示  "" "沿江报路向东北步行362米左转",
                    //                    public String orientation;//方向    "东北",
                    public String road;//街道   "江报路",
                    public String distance;//此路段距离 单位：米   "362",
                    public String duration;//此路段预计步行时间   "259",
                    public String polyline;//坐标集合字符串  : "115.858284,28.690456;115.858543,28.690565;115.860207,28.691219;115.860779,28.691423;115.861328,28.69162;115.86158,28.69171;115.861717,28.69171",
//                    public String action;//动作   "左转",
//                    public String assistant_action;//最后一步才有数据    "到达目的地"
                }
            }
        }
    }

    public static getDriving getDrivingList = new getDriving();
    /**
     * 驾车
     */
    public static class getDriving {
        public String status;
        public String info;
        public String infocode;
        public String count;
        public RouteBean route;
        public static class RouteBean {
            public String origin;
            public String destination;
            public List<PathsBean> paths;
            public static class PathsBean {
                public String distance;
                public String duration;
                public List<StepsBean> steps;
                public static class StepsBean {
                    public String instruction;
                    //                    public String orientation;
                    public String road;
                    public String distance;
                    public String duration;
                    public String polyline;
//                public String action;
//                public List<?> assistant_action;
                }
            }
        }
    }

    public static getBus getBusList = new getBus();
    /**
     * 公交
     */
    public static class getBus {
        public String status;
        public String info;
        public String infocode;
        public String count;//公交换乘方案数目
        public RouteBean route;
        public static class RouteBean {//公交换乘信息列表
            public String origin;//起点坐标
            public String destination;//终点坐标
            public String distance;//起点和终点的步行 距离：米
            public String taxi_cost;//出租车费用 单位：元
            public List<TransitsBean> transits;
            public static class TransitsBean {//公交换乘方案列表
                public String cost;//此换乘方案价格 单位：元
                public String duration;//此换乘方案预期时间  单位：秒
                public String nightflag;//是否是夜班车 0：非夜班车；1：夜班车
                public String walking_distance;//此方案总步行距离 单位：米
                public String distance;//起点和终点的步行 距离：米
                public String missed;
                public List<SegmentsBean> segments;
                public static class SegmentsBean {
                    public WalkingBean walking;//此路段步行导航信息
                    public BusBean bus;//此路段公交导航信息
//                    public List<EntranceBean> entrance;//地铁入口 只在地铁路段有值
//                    public List<ExitBean> exit;//地铁出口 只在地铁路段有值
//                    public RailwayBean railway;//乘坐火车的信息 详情见只在地铁路段有值
//                    public List<?> taxi;

                    public static class WalkingBean {
                        public String origin;//起点坐标
                        public String destination;//终点坐标
                        public String distance;//每段规划线路的步行距离 单位：米
                        public String duration;//步行时间预计 单位：秒
                        public List<StepsBean> steps;//步行路段列表

                        public static class StepsBean {
                            public String instruction;//终点坐标
                            //                      public String road;//道路名称
                            public String distance;//起点和终点的步行距离 单位：米
                            public String polyline;//此路段坐标点 格式为坐标串，如：116.481247,39.990704;116.481270,39.990726
//                      public String action;
//                      public List<?> duration;
//                      public List<?> assistant_action;
                        }
                    }
                    public static class BusBean {
                        public List<BuslinesBean> buslines;//步行路段列表
                        public static class BuslinesBean {
                            public DepartureStopBean departure_stop;//此段起乘站  格式如：中关村
                            public ArrivalStopBean arrival_stop;//此段下车站  格式如：中关村
                            public String name;//公交路线名称  格式如：445路(南十里居--地铁望京西站)
                            public String id;
                            public String type;//公交类型  格式如：地铁线路
                            public String distance;//公交行驶距离  单位：米
                            public String duration;//公交预计行驶时间 单位：秒
                            public String polyline;//此路段坐标集 格式为坐标串，如：116.481247,39.990704;116.481270,39.990726
                            public String via_num;//此段途经公交站数
 //                         public List<?> start_time;//首班车时间 格式如：0600，代表06：00
//                          public List<?> end_time;
                            public List<ViaStopsBean> via_stops;

                            public static class DepartureStopBean {
                                public String name;
                                public String id;
                                public String location;

                            }
                            public static class ArrivalStopBean {
                                public String name;
                                public String id;
                                public String location;

                            }
                            public static class ViaStopsBean {
                                public String name;
                                public String id;
                                public String location;
                            }
                        }
                    }
                    public static class EntranceBean {
                        public String name;
                        public String location;
                    }
                    public static class ExitBean {
                        public String name;
                        public String location;
                    }
                    public static class RailwayBean {
//                        public List<?> spaces;
                    }
                }
            }
        }
    }
}

