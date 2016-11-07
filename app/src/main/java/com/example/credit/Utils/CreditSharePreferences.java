package com.example.credit.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.credit.Entitys.DataManager;

public class CreditSharePreferences {
    private static final String SPNAME = "credit";

    private CreditSharePreferences() {
    }//私有构造方法

    private static CreditSharePreferences esp;
    public static SharedPreferences sp;

    //初始化本地xml文件
    public static void init(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPNAME, ctx.MODE_PRIVATE);
        }
    }

    //单例模式
    public static CreditSharePreferences getLifeSharedPreferences() {
        if (esp == null) {
            esp = new CreditSharePreferences();
        }
        return esp;
    }

    /**
     * //保存历史记录
     * @param listory
     */

    public void putHistory(String listory) {
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putString("listory", listory);
            editor.commit();}
    }

    public String getHistory() {
        if(sp!=null) {
            return sp.getString("listory", null);
        }else{
            return "";
        }
    }

    //保存历史记录(临时)
    public void putmainHistory(String mainlistory) {
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putString("mainlistory", mainlistory);
            editor.commit();}
    }

    public String getmainHistory() {
        if(sp!=null) {
            return sp.getString("mainlistory", null);
        }else{
            return "";
        }
    }
    /**
     * 保存是否登录状态
     * @param b
     */
    public void putLoginStatus(Boolean b){
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putBoolean("LoginStatus", b);
            editor.commit();}
    }
    public Boolean getLoginStatus(){
        if(sp!=null) {
            return sp.getBoolean("LoginStatus", false);
        }
        return false;
    }

    /**
     * 保存用户信息
     * @param user 用户实体类
     */
    public void putUser(DataManager.User user) {
        if(sp!=null){
            if(user==null){
                Editor editor =sp.edit();
                editor.clear();
                editor.commit();
                return;
            }
            Editor editor = sp.edit();

            /** 用户ID*/
            if(user.data.memberInfo.get(0).ID ==null){
                editor.putString("ID", "");
            }else{
                editor.putString("ID", user.data.memberInfo.get(0).ID);
            }

            /** 用户账号*/
            if(user.data.memberInfo.get(0).USERNAME ==null){
                editor.putString("USERNAME", "");
            }else{
                editor.putString("USERNAME", user.data.memberInfo.get(0).USERNAME);
            }

            /** 用户昵称*/
            if(user.data.memberInfo.get(0).ALIASNAME ==null){
                editor.putString("ALIASNAME", "");
            }else{
                editor.putString("ALIASNAME", user.data.memberInfo.get(0).ALIASNAME);
            }

            /** 密码*/
            if(user.data.memberInfo.get(0).PASSWORD ==null){
                editor.putString("PASSWORD", "");
            }else{
                editor.putString("PASSWORD", user.data.memberInfo.get(0).PASSWORD);
            }

            /** 头像*/
            if(user.data.memberInfo.get(0).HEADICON ==null){
                editor.putString("ICONSTEAM", "");
            }else{
                editor.putString("ICONSTEAM", user.data.memberInfo.get(0).HEADICON);
            }

            /** 性别*/
            if(user.data.memberInfo.get(0).SEX ==null){
                editor.putString("SEX", "");
            }else{
                editor.putString("SEX", user.data.memberInfo.get(0).SEX);
            }

            /** 邮箱*/
            if(user.data.memberInfo.get(0).EMAIL ==null){
                editor.putString("EMAIL", "");
            }else{
                editor.putString("EMAIL", user.data.memberInfo.get(0).EMAIL);
            }

            /** 手机*/
            if(user.data.memberInfo.get(0).TEL ==null){
                editor.putString("MOBILE", "");
            }else{
                editor.putString("MOBILE", user.data.memberInfo.get(0).TEL);
            }

            /** 行业*/
            if(user.data.memberInfo.get(0).INDUSTRY_NAME ==null){
                editor.putString("INDUSTRY", "");
            }else{
                editor.putString("INDUSTRY", user.data.memberInfo.get(0).INDUSTRY_NAME);
            }

            /** 学历*/
            if(user.data.memberInfo.get(0).EDUCATION_NAME ==null){
                editor.putString("EDUCATION", "");
            }else{
                editor.putString("EDUCATION", user.data.memberInfo.get(0).EDUCATION_NAME);
            }

            /** 删除状态*/
            if(user.data.memberInfo.get(0).ISDELETE ==null){
                editor.putString("ISDELETE", "");
            }else{
                editor.putString("ISDELETE", user.data.memberInfo.get(0).ISDELETE);
            }

            /** 登陆IP地址*/
            if(user.data.memberInfo.get(0).IP ==null){
                editor.putString("IP", "");
            }else{
                editor.putString("IP", user.data.memberInfo.get(0).IP);
            }

            /** 注册时间*/
            if(user.data.memberInfo.get(0).CREATETIME ==null){
                editor.putString("REGTIME", "");
            }else{
                editor.putString("REGTIME", user.data.memberInfo.get(0).CREATETIME);
            }

            /** 注册状态*/
            if(user.data.memberInfo.get(0).STATUS ==null){
                editor.putString("REGSTATIC", "");
            }else{
                editor.putString("REGSTATIC", user.data.memberInfo.get(0).STATUS);
            }

            editor.commit();
        }
    }

    /**
     * 获取用户ID
     * @return
     */
    public String getID() {
        if(sp!=null) {
            return sp.getString("ID", null);
        }else{
            return "";
        }
    }
    /**
     * 获取用户账号
     * @return
     */
    public String getUSERNAME() {
        if(sp!=null) {
            return sp.getString("USERNAME", null);
        }else{
            return "";
        }
    }
    /**
     * 获取用户昵称
     * @return
     */
    public String getALIASNAME() {
        if(sp!=null) {
            return sp.getString("ALIASNAME", null);
        }else{
            return "";
        }
    }
    public void putALIASNAME(String ALIASNAME) {
        Editor editor = sp.edit();
        editor.putString("ALIASNAME", ALIASNAME);
        editor.commit();
    }
    /**
     * 获取密码
     * @return
     */
    public String getPASSWORD() {
        if(sp!=null) {
            return sp.getString("PASSWORD", null);
        }else{
            return "";
        }
    }

    /**
     * 获取头像base64位图
     * @return
     */
    public String getICONSTEAM() {
        if(sp!=null) {
            return sp.getString("ICONSTEAM", null);
        }else{
            return "";
        }
    }
    public void putICONSTEAM(String ICONSTEAM) {
        Editor editor = sp.edit();
        editor.putString("ICONSTEAM", ICONSTEAM);
        editor.commit();
    }

    /**
     * 获取性别
     * @return
     */
    public String getSEX() {
        if(sp!=null) {
            return sp.getString("SEX", null);
        }else{
            return "";
        }
    }
    public void putSEX(String SEX) {
        Editor editor = sp.edit();
        editor.putString("SEX", SEX);
        editor.commit();
    }
    /**
     * 获取EMAIL
     * @return
     */
    public String getEMAIL() {
        if(sp!=null) {
            return sp.getString("EMAIL", null);
        }else{
            return "";
        }
    }
    public void putEMAIL(String EMAIL) {
        Editor editor = sp.edit();
        editor.putString("EMAIL", EMAIL);
        editor.commit();
    }


    /**
     * 获取手机
     * @return
     */
    public String getMOBILE() {
        if(sp!=null) {
            return sp.getString("MOBILE", null);
        }else{
            return "";
        }
    }
    public void putMOBILE(String MOBILE) {
        Editor editor = sp.edit();
        editor.putString("MOBILE", MOBILE);
        editor.commit();
    }

    /**
     * 获取行业
     * @return
     */
    public String getINDUSTRY() {
        if(sp!=null) {
            return sp.getString("INDUSTRY", null);
        }else{
            return "";
        }
    }
    public void putINDUSTRY(String INDUSTRY) {
        Editor editor = sp.edit();
        editor.putString("INDUSTRY", INDUSTRY);
        editor.commit();
    }

    /**
     * 学历
     * @return
     */
    public String getEDUCATION() {
        if(sp!=null) {
            return sp.getString("EDUCATION", null);
        }else{
            return "";
        }
    }
    public void putEDUCATION(String EDUCATION) {
        Editor editor = sp.edit();
        editor.putString("EDUCATION", EDUCATION);
        editor.commit();
    }


    /**
     * 注册状态（默认为0，未激活；1，已激活）
     * @return
     */
    public String getSTATUS() {
        if(sp!=null) {
            return sp.getString("STATUS", null);
        }else{
            return "";
        }
    }



    /**
     * 删除状态   获取账户是否删除 默认0未删除，1已删除
     * @return
     */
    public String getISDELETE() {
        if(sp!=null) {
            return sp.getString("ISDELETE", null);
        }else{
            return "";
        }
    }
    /**
     * 登陆IP地址
     * @return
     */
    public String getIP() {
        if(sp!=null) {
            return sp.getString("IP", null);
        }else{
            return "";
        }
    }

    /**
     * 注册时间
     * @return
     */
    public String getREGTIME() {
        if(sp!=null) {
            return sp.getString("REGTIME", null);
        }else{
            return "";
        }
    }

    /**
     * 注册状态
     * @return
     */
    public String getREGSTATIC() {
        if(sp!=null) {
            return sp.getString("REGSTATIC", null);
        }else{
            return "";
        }
    }

    /**
     * 引导页
     * @return
     */
    public String getWelcome() {
        if(sp!=null) {
            return sp.getString("Welcome", null);
        }else{
            return "";
        }
    }
    public void putWelcome(String Welcome) {
        Editor editor = sp.edit();
        editor.putString("Welcome", Welcome);
        editor.commit();
    }
}
