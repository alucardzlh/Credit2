package com.example.credit.Utils;

import com.example.credit.Activitys.CommentListActivity;
import com.example.credit.Activitys.CommentListDetailsActivity;
import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.LoginActivity;
import com.example.credit.Activitys.MainActivity;
import com.example.credit.Activitys.Main_NewCliam_MoreListActivity;
import com.example.credit.Activitys.Main_SearchActivity;
import com.example.credit.Activitys.Main_Search_ListActivity;
import com.example.credit.Activitys.MyClaimActivity;
import com.example.credit.Activitys.MycomplaintsListActivity;
import com.example.credit.Activitys.MyconcernActivity;
import com.example.credit.Activitys.PassWordActivity;
import com.example.credit.Activitys.PatentActivity;
import com.example.credit.Activitys.PunishActivity;
import com.example.credit.Activitys.RegisterActivity;
import com.example.credit.Activitys.ReportActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Activitys.ToClaimActivity;
import com.example.credit.Activitys.ToCommentActivity;
import com.example.credit.Activitys.ToComplaintActivity;
import com.example.credit.Activitys.TrademarkActivity;
import com.example.credit.Activitys.UserSetActivity;
import com.example.credit.Activitys.WelcomeActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Map.StartMapActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alucard on 2016-05-18.
 */
public class MyhttpCallBack implements HttpCallBack {
    Gson gson;
    static Map<String, Object> map;
    String jsonString;

    private static MyhttpCallBack instance;
    CreditSharePreferences csp = CreditSharePreferences.getLifeSharedPreferences();

    public static MyhttpCallBack getInstance() {
        if (instance == null) {
            instance = new MyhttpCallBack();
        }
        return instance;
    }


    @Override
    public void onSucceed(int what, Response response) {
        gson = new Gson();
        try {
            jsonString= (String) response.get();
            map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
            }.getType());
            if (map.get("status").equals("1")) {//请求正常
                switch (what) {
                    case 0x021://获取城市
                        jsonString= (String) response.get();
                        DataManager.citysList = gson.fromJson(jsonString, DataManager.citys.class);
                        break;
                    case 0x110://获取APP最新版本
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")) {
                            DataManager.MyNewAppS = gson.fromJson(jsonString, DataManager.MyNewApp.class);
                        }
                        WelcomeActivity.handler.sendEmptyMessage(1);
                        break;
                    case 0x111://获取新闻
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")) {
                            DataManager.MyNewsS = gson.fromJson(jsonString, DataManager.MyNews.class);
                            if (DataManager.MyNewsS.data.newInformation != null && DataManager.MyNewsS.data.newInformation.size() > 0) {
                                MainActivity.MyNewsList = DataManager.MyNewsS.data.newInformation;
                            }
                        }
                        WelcomeActivity.handler.sendEmptyMessage(10);
                        break;
                    case 0x1111://获取更多新闻
                        jsonString = (String) response.get();
                        DataManager.MyNewsSMore = gson.fromJson(jsonString, DataManager.MyNews.class);
                        if (DataManager.MyNewsSMore.data.newInformation != null && DataManager.MyNewsSMore.data.newInformation.size() > 0) {
                            for (int i = 0; i < DataManager.MyNewsSMore.data.newInformation.size(); i++) {
                                MainActivity.MyNewsList.add(DataManager.MyNewsSMore.data.newInformation.get(i));
                            }
                            MainActivity.handler.sendEmptyMessage(0);
                        } else {
                            MainActivity.handler.sendEmptyMessage(101);
                        }
                        break;
                    case 0x112://获取APP首页轮播图
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")) {
                            DataManager.LBimgS = gson.fromJson(jsonString, DataManager.LBimg.class);
                        }
                        WelcomeActivity.handler.sendEmptyMessage(0);
                        break;
                    case 0x113://获取最新认领
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")){
                            DataManager.NewClaimUtilsList = gson.fromJson(jsonString, DataManager.NewClaimUtils.class);
                            if (DataManager.NewClaimUtilsList.data.claimInfo != null && DataManager.NewClaimUtilsList.data.claimInfo.size() > 0) {
                                MainActivity.MyCliamList = DataManager.NewClaimUtilsList.data.claimInfo;
                                if (MainActivity.handler != null) {
                                    MainActivity.handler.sendEmptyMessage(7);
                                }
                            }
                        }
                        WelcomeActivity.handler.sendEmptyMessage(2);
                        break;
                    case 0x1133://获取最新认领1
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")) {
                            DataManager.NewClaimUtilsList = gson.fromJson(jsonString, DataManager.NewClaimUtils.class);
                            if (DataManager.NewClaimUtilsList.data.claimInfo != null && DataManager.NewClaimUtilsList.data.claimInfo.size() > 0) {
                                MainActivity.MyCliamList = DataManager.NewClaimUtilsList.data.claimInfo;
                            }
                        }
                        MainActivity.handler.sendEmptyMessage(7);
                        break;
                    case 0x1131://获取最新认领(more)
                        jsonString = (String) response.get();
                        DataManager.NewClaimUtilsList = gson.fromJson(jsonString, DataManager.NewClaimUtils.class);
                        if (DataManager.NewClaimUtilsList.data.claimInfo != null && DataManager.NewClaimUtilsList.data.claimInfo.size() > 0) {
                            Main_NewCliam_MoreListActivity.MyCliamListMore = DataManager.NewClaimUtilsList.data.claimInfo;
                            MainActivity.handler.sendEmptyMessage(11);
                        }
                        break;
                    case 0x114://获取热点
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")) {
                            DataManager.MyHotS = gson.fromJson(jsonString, DataManager.MyHot.class);
                            if (DataManager.MyHotS.data != null && DataManager.MyHotS.data.searchHistory != null && DataManager.MyHotS.data.searchHistory.size() > 0) {
                                MainActivity.MyHotsList = DataManager.MyHotS.data.searchHistory;
                            }
                        }
                        WelcomeActivity.handler.sendEmptyMessage(3);
                        break;
                    case 0x022://搜索结果
                        jsonString = (String) response.get();
                        if(!jsonString.equals("")){
                            DataManager.searchList = gson.fromJson(jsonString, DataManager.search.class);
                            if(DataManager.searchList.status.equals("1")){
                                if (DataManager.searchList.data.Result != null && DataManager.searchList.data.Result.size() > 0) {
                                    try {
                                        if(DataManager.searchListMore.data.Result !=null && DataManager.searchListMore.data.Result.size()>0){
                                            DataManager.searchListMore.data.Result.clear();
                                        }
                                    }catch (NullPointerException e){

                                    }
                                    SearchFirmActivty.handler.sendEmptyMessage(0);
                                } else {
                                    SearchFirmActivty.handler.sendEmptyMessage(500);
                                }
                            }else{
                                SearchFirmActivty.pd.dismiss();
                                Toast.show(DataManager.searchList.message+"");
                            }
                        }else{
                            Toast.show("后台程序有误,数据返回为空!!!");
                            SearchFirmActivty.pd.dismiss();
                        }

                        break;
                    case 0x0221://搜索加载更多
                        jsonString = (String) response.get();
                        DataManager.searchListMore = gson.fromJson(jsonString, DataManager.search.class);
                        if (DataManager.searchListMore.data.Result != null && DataManager.searchListMore.data.Result.size() > 0) {
                            SearchFirmActivty.handler.sendEmptyMessage(0);
                        } else {
                            SearchFirmActivty.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x023://预留获取行业
                        jsonString = (String) response.get();
                        DataManager.HYDataList = gson.fromJson(jsonString, DataManager.HYData.class);
                        break;
                    case 0x024://获取企业详情24宫格等
                        jsonString = (String) response.get();
                        DataManager.QJiugongGList = gson.fromJson(jsonString, DataManager.QJiugongG.class);
                        if (DataManager.QJiugongGList.data.baseInfo != null && DataManager.QJiugongGList.data.baseInfo.size() > 0) {
                            SearchFirmActivty.handler.sendEmptyMessage(5);
                        } else {
                            SearchFirmActivty.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x025://我的关注跳公司详情界面的请求
                        jsonString = (String) response.get();
                        DataManager.QJiugongGList = gson.fromJson(jsonString, DataManager.QJiugongG.class);
                        if (DataManager.QJiugongGList.data.baseInfo != null && DataManager.QJiugongGList.data.baseInfo.size() > 0) {
                            MyconcernActivity.handler.sendEmptyMessage(1);
                        } else {
                            SearchFirmActivty.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x026://主界面 跳公司详情
                        jsonString = (String) response.get();
                        DataManager.QJiugongGList = gson.fromJson(jsonString, DataManager.QJiugongG.class);
                        if (DataManager.QJiugongGList.data.baseInfo != null && DataManager.QJiugongGList.data.baseInfo.size() > 0) {
                            MainActivity.handler.sendEmptyMessage(8);
                        }
                        break;
                    case 0x027://扫二维码 跳公司详情
                        jsonString = (String) response.get();
                        DataManager.QJiugongGList = gson.fromJson(jsonString, DataManager.QJiugongG.class);
                        if (DataManager.QJiugongGList.data.baseInfo != null && DataManager.QJiugongGList.data.baseInfo.size() > 0) {
                            MainActivity.handler.sendEmptyMessage(12);
                        }
                        break;
                    case 0x000://工商信息
                        jsonString = (String) response.get();
                        DataManager.gsxx = gson.fromJson(jsonString, DataManager.GSXX.class);
                        if(DataManager.gsxx.status.equals("1")){
                            if (DataManager.gsxx.data.baseInfo != null) {
                                CompanyDetailsActivity.handler.sendEmptyMessage(0);
                            } else {
                                CompanyDetailsActivity.handler.sendEmptyMessage(500);
                            }
                        }else{
                            CompanyDetailsActivity.waitDialog.dismiss();
                            Toast.show(DataManager.gsxx.message+"");
                        }

                        break;
                    case 0x001://行政信息
                        jsonString = (String) response.get();
                        DataManager.AdminList = gson.fromJson(jsonString, DataManager.Admin.class);
                        if (DataManager.AdminList.data.lic_Certificate != null || DataManager.AdminList.data.ot_Permit != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(1);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x002://荣誉信息
                        jsonString = (String) response.get();
                        DataManager.honorMarList = gson.fromJson(jsonString, DataManager.honorMar.class);

                        if (DataManager.honorMarList.data.honorInfo != null && DataManager.honorMarList.data.honorInfo.size() > 0) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(2);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x003://企业扶持信息
                        jsonString = (String) response.get();
                        DataManager.supportInfoS = gson.fromJson(jsonString, DataManager.supportInfo.class);
                        if (DataManager.supportInfoS.data.supportInfo != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(3);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x004://抵押信息/动产
                        jsonString = (String) response.get();
                        DataManager.MychattelS = gson.fromJson(jsonString, DataManager.Mychattel.class);
                        if (DataManager.MychattelS.data.mortregInfo != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(4);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
//                case 0x0041://抵押信息/不动产
//                    jsonString = (String) response.get();
//                    DataManager.MyrealEstateS = gson.fromJson(jsonString, DataManager.MyrealEstate.class);
////                    CompanyDetailsActivity.handler.sendEmptyMessage(4);
//                    break;
                    case 0x005://出质信息
                        String jstring5 = (String) response.get();
                        DataManager.pledgeMarList = gson.fromJson(jsonString, DataManager.pledgeMar.class);

                        if (DataManager.pledgeMarList.data.pledgeInfo != null && DataManager.pledgeMarList.data.pledgeInfo.size() > 0) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(5);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x006://司法信息
                        jsonString = (String) response.get();
                        DataManager.JudicialDocumentsMarList = gson.fromJson(jsonString, DataManager.JudicialDocumentsMar.class);
                        if( !DataManager.JudicialDocumentsMarList.data.equals("")){
                            CompanyDetailsActivity.handler.sendEmptyMessage(6);
                        }else{
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x007://预警信息zlh
                        jsonString = (String) response.get();
                        DataManager.AlertInfoS = gson.fromJson(jsonString, DataManager.AlertInfo.class);
                        if (!DataManager.AlertInfoS.data.equals("")) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(7);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x008://行政处罚
                        jsonString = (String) response.get();
                        DataManager.punishMarList = gson.fromJson(jsonString, DataManager.punishMar.class);
                        if (DataManager.punishMarList.data.otCaseInfo.size() > 0 && DataManager.punishMarList.data.otCaseInfo != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(8);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x0081://行政处罚详情
                        jsonString = (String) response.get();
                        DataManager.punishMarDriList = gson.fromJson(jsonString, DataManager.punishMarDri.class);
                        if (DataManager.punishMarDriList.data.otCaseInfo.size() > 0 && DataManager.punishMarDriList.data.otCaseInfo != null) {
                            PunishActivity.handler.sendEmptyMessage(0);
                        }
                        break;
                    case 0x009://经营异常信息
                        jsonString = (String) response.get();
                        DataManager.abnormalInfoS = gson.fromJson(jsonString, DataManager.abnormalInfo.class);
                        CompanyDetailsActivity.handler.sendEmptyMessage(9);

                        break;
                    case 0x010://专利信息
                        jsonString = (String) response.get();
                        DataManager.PatentInfoS = gson.fromJson(jsonString, DataManager.PatentInfo.class);
                        if (DataManager.PatentInfoS.data.patentInfo != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(10);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x01012://专利信息(加载更多)
                        jsonString = (String) response.get();
                        DataManager.PatentInfoSMore = gson.fromJson(jsonString, DataManager.PatentInfo.class);
                        if (DataManager.PatentInfoSMore.data.patentInfo != null) {
                            PatentActivity.handler.sendEmptyMessage(0);
                        }

                        break;
                    case 0x011://商标信息
                        jsonString = (String) response.get();
                        DataManager.trademarkModelS = gson.fromJson(jsonString, DataManager.trademarkModel.class);
                        if (DataManager.trademarkModelS.data.brandInfo.size() > 0 && DataManager.trademarkModelS.data.brandInfo != null) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(11);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x01112://商标信息（加载更多）
                        jsonString = (String) response.get();
                        DataManager.trademarkModelSMore = gson.fromJson(jsonString, DataManager.trademarkModel.class);
                        if (DataManager.trademarkModelSMore.data.brandInfo.size() > 0 && DataManager.trademarkModelSMore.data.brandInfo != null) {
                            TrademarkActivity.handler.sendEmptyMessage(0);
                        }
                        break;
                    case 0x012://著作信息
                        jsonString = (String) response.get();
                        DataManager.ZZQModelsList = gson.fromJson(jsonString, DataManager.ZZQModels.class);
                        if (!DataManager.ZZQModelsList.data.equals("")) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(12);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x013://广告资质
                        jsonString = (String) response.get();
                        DataManager.advertisementMarList = gson.fromJson(jsonString, DataManager.advertisementMar.class);
                        if (DataManager.advertisementMarList.data.advertisingInfo != null && DataManager.advertisementMarList.data.advertisingInfo.size() > 0) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(13);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x014://守合同重信用信息
                        jsonString = (String) response.get();
                        DataManager.obeyedMarList = gson.fromJson(jsonString, DataManager.obeyedMar.class);

                        if (DataManager.obeyedMarList.data.kchcInfo != null && DataManager.obeyedMarList.data.kchcInfo.size() > 0) {
                            CompanyDetailsActivity.handler.sendEmptyMessage(14);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }

                        break;
                    case 0x015://自主公示zlh
                        jsonString = (String) response.get();
                        DataManager.ZZGSModels = gson.fromJson(jsonString, DataManager.ZZGS.class);
                        if( !DataManager.ZZGSModels.data.equals("")){
                            CompanyDetailsActivity.handler.sendEmptyMessage(15);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x101://关注企业
                        jsonString = (String) response.get();
                        DataManager.FavotiteS = gson.fromJson(jsonString, DataManager.Favotite.class);
                        CompanyDetailsActivity.handler.sendEmptyMessage(22);
                        break;
                    case 0x102://取消关注企业
                        jsonString = (String) response.get();
                        DataManager.FavotiteS = gson.fromJson(jsonString, DataManager.Favotite.class);
                        CompanyDetailsActivity.handler.sendEmptyMessage(23);
                        break;
                    case 0x103://我的关注列表
                        jsonString = (String) response.get();
                        DataManager.FavotiteListS = gson.fromJson(jsonString, DataManager.FavotiteList.class);
                        MainActivity.handler.sendEmptyMessage(5);
                        break;
                    case 0x201://评论
                        jsonString = (String) response.get();
                        DataManager.MyCommentlistrS = gson.fromJson(jsonString, DataManager.MyCommentlistr.class);
                        if (DataManager.MyCommentlistrS.data.commentInfo != null && DataManager.MyCommentlistrS.data.commentInfo.size() > 0) {
                            CommentListActivity.handler.sendEmptyMessage(0);
                        } else {
                            CommentListActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x202://点赞
                        String jstring202 = (String) response.get();
                        DataManager.Root202 jsonRoot202 = gson.fromJson(jstring202, new TypeToken<DataManager.Root202>() {
                        }.getType());
                        DataManager.Data202 d202 = jsonRoot202.data;
                        DataManager.Result = d202.result;
//                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
//                }
                        break;
                    case 0x203://差评
                        String jstring203 = (String) response.get();
                        DataManager.Root202 jsonRoot203 = gson.fromJson(jstring203, new TypeToken<DataManager.Root202>() {
                        }.getType());
                        DataManager.Data202 d203 = jsonRoot203.data;
                        DataManager.Result = d203.result;
//                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
//                }
                        break;
                    case 0x204://发表评论
                        jsonString = (String) response.get();
                        DataManager.ToCommMar = gson.fromJson(jsonString, DataManager.ToComm.class);
                        if (DataManager.ToCommMar.data.affectedRow.equals("1")) {
                            ToCommentActivity.handler.sendEmptyMessage(1);
                        } else {
                            ToCommentActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 0x205://回复评论
                        jsonString = (String) response.get();
                        DataManager.ToCommMar = gson.fromJson(jsonString, DataManager.ToComm.class);
                        if (DataManager.ToCommMar.data.affectedRow.equals("1")) {
                            CommentListDetailsActivity.handler.sendEmptyMessage(1);
                        } else {
                            CommentListDetailsActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 0x206://我的评价
                        jsonString = (String) response.get();
                        DataManager.MyCommentlistrS = gson.fromJson(jsonString, DataManager.MyCommentlistr.class);
                        MainActivity.handler.sendEmptyMessage(1);
                        break;
                    case 0x301://提交认领s
                        jsonString = (String) response.get();
                        DataManager.ClaimUtilsModel = gson.fromJson(jsonString, DataManager.ClaimUtils.class);
                        if (DataManager.ClaimUtilsModel.message.equals("success")) {
                            ToClaimActivity.handler.sendEmptyMessage(0);
                        } else {
                            ToClaimActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x302://提交认领附件
                        jsonString = (String) response.get();
                        DataManager.ClaimFuJianUtilsList = gson.fromJson(jsonString, DataManager.ClaimFuJianUtils.class);
                        if (DataManager.ClaimFuJianUtilsList.message.equals("success")) {
                            ToClaimActivity.handler.sendEmptyMessage(2);
                        } else {
                            ToClaimActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x3021://删除认领附件
                        jsonString = (String) response.get();
                        DataManager.delclaimRList = gson.fromJson(jsonString, DataManager.delclaimR.class);
                        if (DataManager.delclaimRList.message.equals("success")) {
                            ToClaimActivity.handler.sendEmptyMessage(1);
                        } else {
                            ToClaimActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x303://我的认领列表
                        jsonString = (String) response.get();
                        DataManager.NewClaimUtilsList = gson.fromJson(jsonString, DataManager.NewClaimUtils.class);
                        MainActivity.handler.sendEmptyMessage(6);
                        break;
                    case 0x3031://我的认领列表{副}
                        jsonString = (String) response.get();
                        DataManager.NewClaimUtilsList = gson.fromJson(jsonString, DataManager.NewClaimUtils.class);
                        MyClaimActivity.handler.sendEmptyMessage(2);
                        break;
                    case 0x304://我的认领详情
                        jsonString = (String) response.get();
                        DataManager.NewClaimDilsList = gson.fromJson(jsonString, DataManager.NewClaimDils.class);
                        MyClaimActivity.handler.sendEmptyMessage(6);
                        break;
                    case 0x3041://修改我的认领跳转查询
                        jsonString = (String) response.get();
                        DataManager.NewClaimDilsList = gson.fromJson(jsonString, DataManager.NewClaimDils.class);
                        MyClaimActivity.handler.sendEmptyMessage(7);
                        break;
                    case 0x305://取消认领
                        jsonString = (String) response.get();
                        DataManager.ClaimUtilsModel = gson.fromJson(jsonString, DataManager.ClaimUtils.class);
                        if (DataManager.ClaimUtilsModel.message.equals("success")) {
                            MyClaimActivity.handler.sendEmptyMessage(1);
                        } else {
                            MyClaimActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x401://修改个人资料{"message":"Success","status":"1","version":"v1.0"}
                        jsonString = (String) response.get();
                        DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                        if (DataManager.user.message.equals("success")) {
                            UserSetActivity.handler.sendEmptyMessage(1);
                        } else {
                            UserSetActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 0x501://修改密码
                        jsonString = (String) response.get();
                        DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                        if (DataManager.user.message.equals("success")) {
                            PassWordActivity.handler.sendEmptyMessage(1);
                        } else if (DataManager.user.message.equals("原始密码错误")) {
                            PassWordActivity.handler.sendEmptyMessage(3);
                        } else {
                            PassWordActivity.handler.sendEmptyMessage(2);
                        }

                        break;
                    case 0x601://二维码名片
                        jsonString = (String) response.get();
                        DataManager.TwoDimSli = gson.fromJson(jsonString, DataManager.TwoDim.class);
                        if (DataManager.TwoDimSli.message.equals("success")) {
                            CompanyDetailsActivity.waitDialog.dismiss();
                            CompanyDetailsActivity.handler.sendEmptyMessage(25);
                        } else {
                            CompanyDetailsActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x701://信用报告1
                        jsonString = (String) response.get();
                        if (jsonString.equals(DataManager.QJiugongGList.data.baseInfo.get(0).ENTNAME + ".pdf")) {
                            DataManager.ReportText = jsonString;
                            ReportActivity.handler.sendEmptyMessage(0);
                        } else {
                            ReportActivity.handler.sendEmptyMessage(1);
                        }
                        break;
                    case 0x702://信用报告2
                        jsonString = (String) response.get();
                        if (jsonString.equals("success")) {
                            ReportActivity.handler.sendEmptyMessage(3);
                        } else {
                            ReportActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 0x999://登入
                        jsonString = (String) response.get();
                        DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                        if (! DataManager.user.status.equals("1")) {//登入失败
                            Toast.show(DataManager.user.message+"");
                            LoginActivity.wd.dismiss();
                        } else {//登入成功
                            csp.putUser(DataManager.user);
                            csp.putLoginStatus(true);
                            Toast.show("登录成功");
                            if (csp.getALIASNAME().equals("")) {
                                csp.putALIASNAME("用户12138");
                            }
                            if (!csp.getALIASNAME().equals("")) {
                                MainActivity.UserSz.setText(csp.getALIASNAME());
                            } else {
                                MainActivity.UserSz.setText(csp.getUSERNAME());
                            }
                            if (!csp.getICONSTEAM().equals("")) {
                                MainActivity.loginImg(csp.getICONSTEAM());
                            }
                            LoginActivity.handler.sendEmptyMessage(0);
                            LoginActivity.wd.dismiss();
                        }
                        break;
                    case 0x998://注册
                        jsonString = (String) response.get();
                        map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if (map.get("message").equals("success")) {//注册成功{"message":"success","status":"1","data":{"affectedRow":"1"},"version":"v1.0"}
                            RegisterActivity.handler.sendEmptyMessage(0);
                        } else {//注册失败
                            Toast.show("注册失败 , " + map.get("message").toString()+" !!!");
                        }
                        break;
                    case 0x9981://注册时 获取短信
                        jsonString = (String) response.get();
                        DataManager.getadList = gson.fromJson(jsonString, DataManager.getad.class);
                        RegisterActivity.handler.sendEmptyMessage(1);
                        break;
                    case 0x997://个人中心获取投诉列表
                        jsonString = (String) response.get();
                        DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                        MainActivity.handler.sendEmptyMessage(2);

                        break;
                    case 0x9971://个人中心获取投诉列表
                        jsonString = (String) response.get();
                        DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                        MycomplaintsListActivity.handler.sendEmptyMessage(21);

                        break;

                    case 0x996://个人中心取消投诉请求
                        jsonString = (String) response.get();
                        map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if (!map.get("status").equals("1")) {//取消失败
                            MycomplaintsListActivity.pd.dismiss();
                            Toast.show("取消失败" + map.get("message").toString());
                        } else {//取消成功
                            MycomplaintsListActivity.handler.sendEmptyMessage(1);
                        }
                        break;
                    case 0x995://获取投诉详情
                        jsonString = (String) response.get();
                        DataManager.complaintDetail = gson.fromJson(jsonString, DataManager.ComplaintDetail.class);
                        MycomplaintsListActivity.handler.sendEmptyMessage(3);
                        break;
                    case 0x994://获取企业投诉列表
                        jsonString = (String) response.get();
                        DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                        if (CompanyDetailsActivity.waitDialog != null) {
                            CompanyDetailsActivity.waitDialog.dismiss();
                        }
                        CompanyDetailsActivity.handler.sendEmptyMessage(24);
                        break;
                    case 0x9941://获取企业投诉列表更多
                        jsonString = (String) response.get();
                        DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                        if (CompanyDetailsActivity.waitDialog != null) {
                            CompanyDetailsActivity.waitDialog.dismiss();
                        }
                        MycomplaintsListActivity.handler.sendEmptyMessage(6);
                        break;

                    case 0x993://提交企业投诉
                        jsonString = (String) response.get();
                        DataManager.toComplain = gson.fromJson(jsonString, DataManager.ToComplain.class);
                        if (!DataManager.toComplain.status.equals("1")) {//返回提交投诉失败
                            Toast.show("提交投诉失败" + map.get("message"));
                        } else {//成功
                            ToComplaintActivity.handler.sendEmptyMessage(1);

                        }
                        break;
                    case 0x992://提交投诉附件
                        jsonString = (String) response.get();
                        map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if (!map.get("status").equals("1")) {//返回提交投诉失败
                            Toast.show("提交投诉图片失败" + map.get("message"));
                        } else {//成功
                            ToComplaintActivity.handler.sendEmptyMessage(0);

                        }
                        break;
                    case 0x991://提交投诉后刷新企业投诉
                        jsonString = (String) response.get();
                        DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                        MycomplaintsListActivity.handler.sendEmptyMessage(5);
                        break;
                    case 0x12138://记录24宫格
                        jsonString = (String) response.get();
                        break;
                    case 0x1001://商标查询
                        jsonString = (String) response.get();
                        DataManager.trademarkModelS = gson.fromJson(jsonString, DataManager.trademarkModel.class);
                        if (DataManager.trademarkModelS.data.brandInfo.size() > 0) {
                            Main_SearchActivity.handler.sendEmptyMessage(0);
                        } else {
                            Main_SearchActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x1002://首页专利查询
                        jsonString = (String) response.get();
                        DataManager.zl_searchS = gson.fromJson(jsonString, DataManager.zl_search.class);
                        if (DataManager.zl_searchS.data.patentInfo.size() > 0 && DataManager.zl_searchS.data.patentInfo != null) {
                            Main_SearchActivity.handler.sendEmptyMessage(1);
                        } else {
                            Main_SearchActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x1003://首页商标查询(上下拉事件)more
                        jsonString = (String) response.get();
                        DataManager.trademarkModelSMore = gson.fromJson(jsonString, DataManager.trademarkModel.class);
                        if (DataManager.trademarkModelSMore.data.brandInfo.size() > 0) {
                            Main_Search_ListActivity.handler.sendEmptyMessage(0);
                        }
                        break;
                    case 0x1004://专利查询(上下拉事件)more
                        jsonString = (String) response.get();
                        DataManager.zl_searchSMore = gson.fromJson(jsonString, DataManager.zl_search.class);
                        if (DataManager.zl_searchSMore.data.patentInfo.size() > 0 && DataManager.zl_searchSMore.data.patentInfo != null) {
                            Main_Search_ListActivity.handler.sendEmptyMessage(1);
                        }
                        break;
                    case 0x1005://失信人查询
                        jsonString = (String) response.get();
                        DataManager.MyDishonestyS = gson.fromJson(jsonString, DataManager.MyDishonesty.class);
                        if (DataManager.MyDishonestyS.data.courtcaseInfo.size() > 0 && DataManager.MyDishonestyS.data.courtcaseInfo != null) {
                            Main_SearchActivity.handler.sendEmptyMessage(2);
                        } else {
                            Main_SearchActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x1006://失信人查询(上下拉事件)
                        jsonString = (String) response.get();
                        DataManager.MyDishonestySMore = gson.fromJson(jsonString, DataManager.MyDishonesty.class);
                        if (DataManager.MyDishonestySMore.data.courtcaseInfo.size() > 0 && DataManager.MyDishonestySMore.data.courtcaseInfo != null) {
                            Main_Search_ListActivity.handler.sendEmptyMessage(2);
                        }
                        break;

                    case 0x1000://投诉类型字典
                        jsonString = (String) response.get();
                        DataManager.disRoomMarList = gson.fromJson(jsonString, DataManager.disRoomMar.class);
                        if (DataManager.disRoomMarList.data.dictionarieInfo.size() > 0) {
                            MycomplaintsListActivity.handler.sendEmptyMessage(7);
                        } else {
                            MycomplaintsListActivity.handler.sendEmptyMessage(500);
                        }
                        break;
                    case 0x100001://行业 字典
                        jsonString = (String) response.get();
                        DataManager.disRoomMarList = gson.fromJson(jsonString, DataManager.disRoomMar.class);
                        break;
                    case 0x100002://学历 字典
                        jsonString = (String) response.get();
                        DataManager.disRoomMarList1 = gson.fromJson(jsonString, DataManager.disRoomMar.class);
                        break;

                    case 0x1132://地图showLocation&&showLocation({"status":0,"result":{"location":{"lng":115.86455138111218,"lat":28.703788358019084},"precise":1,"confidence":80,"level":"道路"}})
                        jsonString = (String) response.get();
                        DataManager.getMapList = gson.fromJson(jsonString, DataManager.getMap.class);
                        if(DataManager.getMapList.status.equals("1")){
                            try{
                                if(DataManager.getMapList.geocodes!=null &&   DataManager.getMapList.geocodes.size()>0 ){
                                    CompanyDetailsActivity.handler.sendEmptyMessage(501);
                                }else{
                                    Toast.show("公司位置异常，无法定位!");
                                }
                            }catch (Exception e){
                                Toast.show("公司位置异常，无法定位!");
                            }
                        }else{
                            Toast.show("公司位置异常，无法定位!");
                        }
                        break;
                    case 0x1141://步行{"status":"0","info":"OVER_DIRECTION_RANGE","infocode":"20803"}
                        jsonString = (String) response.get();
                        map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if (map.get("infocode").equals("10000")) {//请求正常
                            DataManager.getwalkingList = gson.fromJson(jsonString, DataManager.getwalking.class);
                            StartMapActivity.handler.sendEmptyMessage(0);
                        } else if (map.get("infocode").equals("10001")) {
                            Toast.show("key不正确或过期!");
                        } else if (map.get("infocode").equals("10002")) {
                            Toast.show("没有权限使用相应的服务或者请求接口的路径拼写错误!");
                        } else if (map.get("infocode").equals("10003")) {
                            Toast.show("访问已超出日访问量!");
                        } else if (map.get("infocode").equals("10004")) {
                            Toast.show("单位时间内访问过于频繁!");
                        } else if (map.get("infocode").equals("10013")) {
                            Toast.show("Key被删除!");
                        } else if (map.get("infocode").equals("20800")) {
                            Toast.show("规划点（包括起点、终点、途经点）不在中国陆地范围内!");
                        } else if (map.get("infocode").equals("20801")) {
                            Toast.show("划点（起点、终点、途经点）附近搜不到路!");
                        } else if (map.get("infocode").equals("20802")) {
                            Toast.show("路线计算失败，通常是由于道路连通关系导致!");
                        } else if (map.get("infocode").equals("20803")) {
                            Toast.show("起点终点距离过长!");
                        }

                        break;
                    case 0x1142://驾车
                        jsonString = (String) response.get();
                        map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        if (map.get("infocode").equals("10000")) {//请求正常
                            DataManager.getDrivingList = gson.fromJson(jsonString, DataManager.getDriving.class);
                            StartMapActivity.handler.sendEmptyMessage(1);
                        } else if (map.get("infocode").equals("10001")) {
                            Toast.show("key不正确或过期!");
                        } else if (map.get("infocode").equals("10002")) {
                            Toast.show("没有权限使用相应的服务或者请求接口的路径拼写错误!");
                        } else if (map.get("infocode").equals("10003")) {
                            Toast.show("访问已超出日访问量!");
                        } else if (map.get("infocode").equals("10004")) {
                            Toast.show("单位时间内访问过于频繁!");
                        } else if (map.get("infocode").equals("10013")) {
                            Toast.show("Key被删除!");
                        } else if (map.get("infocode").equals("20800")) {
                            Toast.show("规划点（包括起点、终点、途经点）不在中国陆地范围内!");
                        } else if (map.get("infocode").equals("20801")) {
                            Toast.show("划点（起点、终点、途经点）附近搜不到路!");
                        } else if (map.get("infocode").equals("20802")) {
                            Toast.show("路线计算失败，通常是由于道路连通关系导致!");
                        } else if (map.get("infocode").equals("20803")) {
                            Toast.show("起点终点距离过长!");
                        }
                        break;
                    case 0x1143://公交
                        jsonString = (String) response.get();
                        DataManager.getBusList = gson.fromJson(jsonString, DataManager.getBus.class);
                        StartMapActivity.handler.sendEmptyMessage(2);
                        break;

                    default:
                        break;
                }
            }else{
                Toast.show(map.get("message")+"");
                switch (what) {
                    case 0x022://搜索接口
                        SearchFirmActivty.pd.dismiss();
                        break;
                    case 0x024://获取企业详情16宫格
                        SearchFirmActivty.pd.dismiss();
                        break;
                    case 0x000://工商信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x001://行政审批
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x002://荣誉信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x003://扶持信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x004://抵押信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x005://出质信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x006://司法信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x007://预警信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x008://行政处罚
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x009://经营异常
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x010://专利信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x011://商标信息
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x012://著作权
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x013://广告资质
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x014://守合同重信用
                        CompanyDetailsActivity.waitDialog.dismiss();
                    case 0x015://企业自主公示
                        CompanyDetailsActivity.waitDialog.dismiss();
                        break;
                    case 0x701://信用报告1
                        ReportActivity.wd.dismiss();
                        break;
                    case 0x702://信用报告2
                        Toast.show("邮件正在发送中...");
                        break;
                    case 0x996://个人中心取消投诉请求
                        MycomplaintsListActivity.pd.dismiss();
                        break;
                    case 0x997://个人中心获取投诉列表
                        MainActivity.pd.dismiss();
                        break;
                    case 0x204://发表评论
                    case 0x205://回复评论
                    case 0x201://公司我的评价链表

                        if (CommentListActivity.wd != null) {
                            CommentListActivity.wd.dismiss();
                        }
                        if (CompanyDetailsActivity.pd != null) {
                            CompanyDetailsActivity.pd.dismiss();
                        }
                        if (ToCommentActivity.wd != null) {
                            ToCommentActivity.wd.dismiss();
                        }
                        if (CommentListDetailsActivity.wd != null) {
                            CommentListDetailsActivity.wd.dismiss();
                        }

                        break;
                    case 0x206://我的评价
                        MainActivity.ad.dismiss();
                        MainActivity.pd.dismiss();
                        break;
                    case 0x995:
                    case 0x994://获取企业投诉列表
                    case 0x993://提交企业投诉
                    case 0x992://提交投诉附件
                    case 0x991://提交投诉后刷新企业投诉
                        if (ToComplaintActivity.pd != null) {
                            ToComplaintActivity.pd.dismiss();
                        }
                        if (MycomplaintsListActivity.pd != null) {
                            MycomplaintsListActivity.pd.dismiss();
                        }
                        break;
                    case 0x3031://我的认领列表{副}
                    case 0x305://取消认领
                    case 0x303://我的认领列表
                    case 0x301://提交认领
                    case 0x302://提交认领附件
                        MainActivity.ad.dismiss();
                        MainActivity.pd.dismiss();
                        ToClaimActivity.wd.dismiss();
                        break;
                    case 0x101://关注企业
                    case 0x102://取消关注企业
                    case 0x103://我的关注列表
                        CompanyDetailsActivity.waitDialog.dismiss();
                        CompanyDetailsActivity.pd.dismiss();
                        MainActivity.ad.dismiss();
                        MainActivity.pd.dismiss();
                        break;
                    case 0x20111://查询评论列表
                        CommentListDetailsActivity.wd.dismiss();
                        break;
                    case 0x999://查询评论列表
                        LoginActivity.wd.dismiss();
                        break;
                    case 0x1001://商标
                        Main_SearchActivity.wd.dismiss();
                        break;
                    case 0x1002://专利
                        Main_SearchActivity.wd.dismiss();
                        break;
                    case 0x1005://失信人
                        Main_SearchActivity.wd.dismiss();
                        break;
                    case 0x111://获取新闻
                        WelcomeActivity.handler.sendEmptyMessage(10);

                        break;
                    case 0x110://获取APP最新版本
                        WelcomeActivity.handler.sendEmptyMessage(10);
                        Toast.show("连接服务器失败");
                        break;
                    default:
                        break;
                }

            }
        }catch (NullPointerException e) {
            showdisplay(what);
            Toast.show("后台数据空返回!");
        }
        catch (IndexOutOfBoundsException e) {
            showdisplay(what);
            Toast.show("后台数据结构变更下标越界!");
        } catch (ClassCastException e) {
            showdisplay(what);
            Toast.show("后台数据变更类型转换出错!");
        }catch (NumberFormatException e) {
            showdisplay(what);
            Toast.show("字符串转换为数字异常!");
        }
        catch (JsonSyntaxException e) {
            showdisplay(what);
            Toast.show("后台数据变更json解析出错!");
        }
    }

    @Override
    public void onFailed(int what, Response response) {
        showdisplay(what);
    }

    public void showdisplay(int what) {
        switch (what) {
            case 0x022://搜索接口
                SearchFirmActivty.pd.dismiss();
                break;
            case 0x024://获取企业详情16宫格
                SearchFirmActivty.pd.dismiss();
                break;
            case 0x000://工商信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x001://行政审批
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x002://荣誉信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x003://扶持信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x004://抵押信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x005://出质信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x006://司法信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x007://预警信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x008://行政处罚
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x009://经营异常
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x010://专利信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x011://商标信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x012://著作权
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x013://广告资质
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x014://守合同重信用
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x015://企业自主公示
                CompanyDetailsActivity.waitDialog.dismiss();
                break;
            case 0x701://信用报告1
                ReportActivity.wd.dismiss();
                break;
            case 0x702://信用报告2
                Toast.show("邮件正在发送中...");
                break;
            case 0x996://个人中心取消投诉请求
                MycomplaintsListActivity.pd.dismiss();
                break;
            case 0x997://个人中心获取投诉列表
                MainActivity.pd.dismiss();
                break;
            case 0x204://发表评论
            case 0x205://回复评论
            case 0x201://公司我的评价链表

                if (CommentListActivity.wd != null) {
                    CommentListActivity.wd.dismiss();
                }
                if (CompanyDetailsActivity.pd != null) {
                    CompanyDetailsActivity.pd.dismiss();
                }
                if (ToCommentActivity.wd != null) {
                    ToCommentActivity.wd.dismiss();
                }
                if (CommentListDetailsActivity.wd != null) {
                    CommentListDetailsActivity.wd.dismiss();
                }

                break;
            case 0x206://我的评价
                MainActivity.ad.dismiss();
                MainActivity.pd.dismiss();
                break;
            case 0x995:
            case 0x994://获取企业投诉列表
            case 0x993://提交企业投诉
            case 0x992://提交投诉附件
            case 0x991://提交投诉后刷新企业投诉
                if (ToComplaintActivity.pd != null) {
                    ToComplaintActivity.pd.dismiss();
                }
                if (MycomplaintsListActivity.pd != null) {
                    MycomplaintsListActivity.pd.dismiss();
                }
                break;
            case 0x3031://我的认领列表{副}
            case 0x305://取消认领
            case 0x303://我的认领列表
            case 0x301://提交认领
            case 0x302://提交认领附件
                MainActivity.ad.dismiss();
                MainActivity.pd.dismiss();
                ToClaimActivity.wd.dismiss();
                break;
            case 0x101://关注企业
            case 0x102://取消关注企业
            case 0x103://我的关注列表
                CompanyDetailsActivity.waitDialog.dismiss();
                CompanyDetailsActivity.pd.dismiss();
                MainActivity.ad.dismiss();
                MainActivity.pd.dismiss();
                break;
            case 0x20111://查询评论列表
                CommentListDetailsActivity.wd.dismiss();
                break;
            case 0x999://查询评论列表
                LoginActivity.wd.dismiss();
                break;
            case 0x1001://商标
                Main_SearchActivity.wd.dismiss();
                break;
            case 0x1002://专利
                Main_SearchActivity.wd.dismiss();
                break;
            case 0x1005://失信人
                Main_SearchActivity.wd.dismiss();
                break;
            case 0x111://获取新闻
                WelcomeActivity.handler.sendEmptyMessage(10);

                break;
            case 0x110://获取APP最新版本
                WelcomeActivity.handler.sendEmptyMessage(10);
                Toast.show("连接服务器失败");
                break;
            default:
                break;
        }

    }
}
