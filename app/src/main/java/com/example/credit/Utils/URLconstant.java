package com.example.credit.Utils;

/**
 * Created by alucard on 2016-05-13.
 */
public class URLconstant {

    public static final String URLINSER = "http://101.201.211.27:8282/zhirong.creditcode/Interface/";//所有接口前缀

    /**http://192.168.10.44:8080/zhirong.creditcode/Interface/
     *     ==========================================   新地址入口  =====================================================
     */
    /**
     * 九宫格
     */
    public static final String GETITEMNUM="baseinfoInterface/queryAllCountForEnterprise";//获取九宫格数据

    public static final String SAVESUM="baseinfoInterface/saveOperationLog.do";//记录用户进入九宫格

    public static final String DETAILSCINFOURL = "infomessageInterface/queryinfomessage";//获取工商信息数据

    public static final String ADMINURL = "certificateInterface/queryCertificateInfo";//获取行政审批数据

    public static final String SUPPORTURL = "supportInterface/querySupportInfo";//获取扶持数据

    public static final String TRADEMARKURL = "brandInterface/queryBrandInfo";//商标数据（包括首页查询）

    public static final String PATENTURL = "patentInterface/queryPatentinfo";//获取专利数据

    public static final String ABNORMALURL = "anomaliesInterface/queryAnomaliesInfo.do";//获取经营异常数据

    public static final String GETAUTO = "myselferInterface/queryMyselferInfo.do";//自主公示接口数据

    public static final String HONORURL = "honorInterface/queryHonorInfo";//获取荣誉数据

    public static final String PLEDGEURL = "pledgeInterface/queryPledgeInfo";//获取出质Pledge

    public static final String PUNISHURL = "otCaseInfoInterface/queryOtCaseInfo";//获取行政处罚Punish

    public static final String COPYRIGHTURL = "copyrightInterface/queryCopyright";//著作数据

    public static final String OBEYEDURL = "kchcInfoInterface/queryKchcInfo";//守合同重信用信息URLObeyed

    public static final String ADVERTISEMENTURL = "advertisingInterface/queryAdvertisingInfo.do";//广告信息URLAdvertisement

    public static final String JUDICIALURL = "courtcaseInterface/queryJusticeInfo.do";//司法信息URL

    public static final String INVESTMENTURL = "http://101.201.211.27:8282/zhirong.credith5/pages/baseinfo/investallview.jsp?";//New模块 投资全景Investment

    public static final String REPORTURL1 ="MakePdfInterface/makePdf.do";//信用报告Report1

    public static final String REPORTURL2 ="MakePdfInterface/sendPdfByEmail.do";//信用报告Report2

    public static final String MORTINFO = "mortregInterface/queryMortregInfo";//抵押信息  动产抵押数据
    //- - - -- ----------------------- - - - -


    /**
     * 首页
     */

    public static final String SXDETAILS="courtcaseInterface/queryCourtcaseInfo.do";//失信人查询

    public static final String NEWSURL ="newsInterface/queryNewInformation";//获取首页新闻数据

    public static final String LUNBOIMH ="carouselInterface/queryCarouselInfo";//APP首页轮播图

    public static final String HOTSPOT ="businessInterface/querySearchHistoryInfo";//热搜数据

    public static final String SEARCHURL ="baseinfoInterface/queryEnterpriseInfo";//搜索接口

    public static final String NEWAPP ="VersionInterface/queryVersionInfo";//APP最新版本

    public static final String GETCITY = "baseinfoInterface/queryArea.do";//获取城市接口数据

    public static final String GETINDUSTRY = "baseinfoInterface/queryindustry.do";//获取行业门类      没参    返回  data    industry：  EC_VALUE：行业门类代码   EC_NAME：行业门类名字


    /**
     * 自主业务
     */

    public static final String USERLOGIN = "/memberInterface/userLogin";//登录接口

    public static final  String COMM="activeInterface/queryCommentInfo";//评论

    public static final  String HHOMM="activeInterface/createCommentInfo";//评论发表+回复

    public static final String ZZOMM="activeInterface/createPraiseSuccessqty";//点赞接口

    public static final String NNOMM="activeInterface/createPraiseFailedqty.do";//差评接口

    public static final  String MMOMM="activeInterface/queryCommentInfo";//我的评价


    public static final  String YESFAVORITE="activeInterface/createFollowInfo";//关注接口

    public static final  String NOFAVORITE="activeInterface/cancelFollowInfo";//取消关注接口



    public static final String GETCOMPLAIN = "activeInterface/queryComplaintInfo";//获取投诉列表接口 +投诉详情接口

    public static final String CANCELCOM = "activeInterface/cancelComplainInfo";//取消投诉接口

    public static final String SEVECOM="activeInterface/createComplainInfo";//投诉企业接口

    public static final  String ENCLOSUREURL="activeInterface/createAttachment";//附件接口enclosure;

    public static final  String DISROOM="dictionarieInterface/queryDictionarieInfo";//字典表接口

    public static final String NEWCLAIM="activeInterface/queryClaimInfo";//最新认领列表接口revise +我的认领列表接口 + 详情

    public static final  String CLAIMURL="activeInterface/createClaimInfo";//提交认领 and 修改认领 接口

    public static final  String DISSCLAIMURL="activeInterface/cancelClaimInfo";//取消认领接口

    public static final  String DELCLAIMR="activeInterface/deleteAttachment";//删除认领附件接口

    public static final  String MYFAVORITE="activeInterface/queryFollowInfo";//我的关注列表接口

    public static final String RPASSWORD="memberInterface/passwordModify";//修改密码接口revise

    public static final String TWODIM="qrCodeInterface/createTwoDimensionCode";//二维码接口revise 带圈的
//    public static final String TWODIM="qrCodeInterface/creditHomePage";//二维码接口revise 不带圈的

    public static final String REVISEUSER="memberInterface/userRegister.do";//用户注册或修改资料接口  openType=0注册  openType=1 修改

    public static final String GETALERT = "warningInterface/queryWarninginfo.do";//获取预警信息URL

    public static final String GETAD = "smsInterface/sendSmsVerificationCode.do";//获取短信验证码


    /**http://101.201.211.27:8282/zhirong.creditcard/Interface/
     *     ==========================================   旧地址入口  =====================================================
     */

//    public static final String GETCITY = "baseinfo/queryArea.do";//获取城市接口

//    public static final String GETINDUSTRY = "baseinfo/queryindustry.do";//获取行业门类      没参    返回  data    industry：  EC_VALUE：行业门类代码   EC_NAME：行业门类名字

//    public static final String DETAILSCINFOURL = "info/queryinfomessage.do";//获取工商DetailsContent


//    public static final String ADMINURL = "eilcCertificate/getEilcCertificate.do";//获取行政Admin

//    public static final String HONORURL = "honorifo/getqueryhonorinfo.do";//获取荣誉

//    public static final String SUPPORTURL = "esmsupport/getqueryesmsupport.do";//获取扶持

//    public static final String PLEDGEURL = "espPledge/getespPledge.do";//获取出质Pledge

//    public static final String JUDICIALURL = "forensic/getForensic.do";//司法信息URL

//    public static final String GETALERT = "LcWarninginfo/getLcWarninginfo.do";//获取预警信息URL

//    public static final String MORTINFO = "mortreginfo/getquerymortinfo.do";//抵押信息  动产抵押
//
//    public static final String MORTINFOBDC = "mortreginfo/getquerymortinfobdc.do";//抵押信息   不动产抵押


//    public static final String PUNISHURL = "zrvcasepubbaseinfo/getqueryzrvcasepubbaseinfo.do";//获取行政处罚Punish

//    public static final String ABNORMALURL = "zrvaoopanomaly/getqueryzrvaoopanomaly.do";//获取经营异常

//    public static final String PATENTURL = "tblPatent/getTblPatent.do";//获取专利

//    public static final String TRADEMARKURL = "trademark/getquerytrademark.do";//商标URLtrademark
//
//    public static final String COPYRIGHTURL = "patentInfo/getquerypatentInfo.do";//著作URLcopyright

//    public static final String SBSREACH="trademark/getquerytrademark.do";//商标查询

//    public static final String GETAUTO = "myselferinfo/getquerymyselferinfo.do";//自主公示接口

//    public static final String ADVERTISEMENTURL = "advertising/getqueryadvertising.do";//广告信息URLAdvertisement

//    public static final String OBEYEDURL = "lcPatent/getLcPatent.do";//守合同重信用信息URLObeyed

//    public static final String GETITEMNUM="baseinfo/queryAllCountForEnterprise.do";//获取16个item右上角条数

//    public static final String USERLOGIN = "userMemberController/userLogin.do";//登录接口

//    public static final String NNOMM="businessController/savePraiseFailedqty.do";//差评接口
//
//    public static final String ZZOMM="businessController/savePraiseSuccessqty.do";//点赞接口

//    public static final  String COMM="businessController/getCommentlistPage.do";//评论

//    public static final  String HHOMM="businessController/SaveComment.do";//评论发表+回复

//    public static final  String YESFAVORITE="businessController/saveAttentionCompany.do";//关注接口
//
//    public static final  String NOFAVORITE="businessController/deleteAttentionCompany.do";//取消关注接口
//
//    public static final  String MYFAVORITE="businessController/getMyAttentionlistPage.do";//我的关注列表接口

//    public static final  String CLAIMURL="businessController/saveClaimCompany.do";//提交认领 and 修改认领 接口

//    public static final  String MYCLAIMURL="businessController/getMyClaimlistPage.do";//我的认领列表接口

//    public static final  String DISSCLAIMURL="businessController/cancelClaimCompany.do";//取消认领接口


//    public static final String REVISEUSER="userMemberController/userModify.do";//用户注册或修改资料接口  openType=0注册  openType=1 修改

//    public static final String RPASSWORD="userMemberController/passwordModify.do";//修改密码接口revise

//    public static final String TWODIM="businessController/createTwoDimensionCode.do";//二维码接口revise

//    public static final String NEWCLAIM="businessController/getClaim.do";//最新认领接口revise

//    public static final String DICTIONARIE="dictionarieController/getDictionarie.do";//字典表接口revise


//    public static final String SAVESUM="baseinfo/saveOperationLog.do";//记录


//    public static final String ZLSREACH="tblPatent/getTblPatent";//专利查询

//    public static final String SXDETAILS="forensic/getCourtcaseinfolistPage";//失信查询URl

//    public static final String NEWSURL ="newsController/getNewslistPage";//新闻URLObeyed

//    public static final String HOTSPOT ="businessController/getHotspotAnalysis";//热搜Hotspot

//    public static final String REPORTURL1 ="baseinfo/makePdf.do";//信用报告Report1
//
//    public static final String REPORTURL2 ="baseinfo/sendPdfByEmail.do";//信用报告Report2

//    public static final String NEWAPP ="appPathController/getAppPathByState";//APP最新版本

//    public static final String LUNBOIMH ="photoSortController/getPhotolistPage";//APP首页轮播图

    public static final String SBDETAILS="http://101.201.211.27:8282/zhirong.credith5/trademarkInfoController/gettrademarkDetails.do";//商标详情页URL

    public static final String ZLDETAILS="http://101.201.211.27:8282/zhirong.credith5/patentController/getPatentinfoDetails.do";//专利详情页URl

    public static final String SXDETAILSDS="http://101.201.211.27:8282/zhirong.credith5/forensicInfoController/getforensicInfoDetails.do";//失信详情页URl

    public static final String TBSREACH="http://101.201.211.27:8282/zhirong.credith5/entShowController/entShow.do";//招投标 废弃

    public static final String QUNASHITUURL = "http://101.201.211.27:8282/zhirong.credith5//pages/baseinfo/allview.jsp?";//全景视图

    public static final String JYSREACH="http://101.201.211.27:8282/zhirong.credith5/entShowController/entShow.do";//经营异常 废弃

    public static final String COMPANYNEWS="http://101.201.211.27:8282/zhirong.credith5/enterinfo/entnew.do";//企业新闻接口

    public static final String RECRUIT="http://101.201.211.27:8282/zhirong.credith5/enterinfo/entrecruit.do";//招聘接口recruit

    public static final String TENDER="http://101.201.211.27:8282/zhirong.credith5/biddingInfoController/getBiddingInfolist.do";//招标接口Tender 59

    public static final String TOUZILIAN="http://101.201.211.27:8282/zhirong.credith5/pages/baseinfo/investview.jsp?";//投资链图

    public static final String FAZHAN="http://101.201.211.27:8282/zhirong.credith5/entCourseController/entCourse.do";//发展历程

    public static final String SHOW="http://101.201.211.27:8282/zhirong.credith5/entShowController/entShow.do";//企业展示

    public static final String MAPSHOW="http://restapi.amap.com/v3/geocode/geo?output=JSON&key=97d1040d0aafc15e0272d237fbba1ef4&address=";//逆地理

    public static final String MAPWALKING="http://restapi.amap.com/v3/direction/walking?key=97d1040d0aafc15e0272d237fbba1ef4";//步行walking

    public static final String MAPBUS="http://restapi.amap.com/v3/direction/transit/integrated?output=JSON&key=97d1040d0aafc15e0272d237fbba1ef4";//公交transit

    public static final String MAPDRIVING="http://restapi.amap.com/v3/direction/driving?extensions=all&output=JSON&key=97d1040d0aafc15e0272d237fbba1ef4";//驾车driving



}
