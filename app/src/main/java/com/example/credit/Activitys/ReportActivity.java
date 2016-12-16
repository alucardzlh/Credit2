package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Dialogs.WaitDialog_pdf;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 信用报告
 */
public class ReportActivity extends Activity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_topLLt)
    LinearLayout b_topLLt;
    @ViewInject(R.id.b_topY)
    TextView b_topY;

    /**
     * 【工商信息】 选择模块
     */
    @ViewInject(R.id.gs1)
    TextView gs1;
    @ViewInject(R.id.gs2)
    TextView gs2;
    @ViewInject(R.id.gs3)
    TextView gs3;
    @ViewInject(R.id.gs4)
    TextView gs4;
    /**
     * 【对外投资信息】 选择模块
     */
    @ViewInject(R.id.dw1)
    TextView dw1;
    @ViewInject(R.id.dw2)
    TextView dw2;
    @ViewInject(R.id.dw3)
    TextView dw3;
    @ViewInject(R.id.dw4)
    TextView dw4;

    /**
     * 【司法文书信息】 选择模块
     */
    @ViewInject(R.id.sf1)
    TextView sf1;
    @ViewInject(R.id.sf2)
    TextView sf2;
    @ViewInject(R.id.sf3)
    TextView sf3;
    @ViewInject(R.id.sf4)
    TextView sf4;
    @ViewInject(R.id.sf5)
    TextView sf5;

    /**
     * 【风险信息】 选择模块
     */
    @ViewInject(R.id.fx1)
    TextView fx1;
    @ViewInject(R.id.fx2)
    TextView fx2;
    @ViewInject(R.id.fx3)
    TextView fx3;
    @ViewInject(R.id.fx4)
    TextView fx4;
    @ViewInject(R.id.fx5)
    TextView fx5;
    @ViewInject(R.id.fx6)
    TextView fx6;
    @ViewInject(R.id.fx7)
    TextView fx7;
    @ViewInject(R.id.fx8)
    TextView fx8;
    @ViewInject(R.id.fx9)
    TextView fx9;


    /**
     * 【年报信息】 选择模块
     */
    @ViewInject(R.id.nb1)
    TextView nb1;

    /**
     * 【知识产权信息】 选择模块
     */
    @ViewInject(R.id.zzcq1)
    TextView zzcq1;
    @ViewInject(R.id.zzcq2)
    TextView zzcq2;
    @ViewInject(R.id.zzcq3)
    TextView zzcq3;
    @ViewInject(R.id.zzcq4)
    TextView zzcq4;
    /**
     * 【经营信息】 选择模块
     */
    @ViewInject(R.id.qt1)
    TextView qt1;
    @ViewInject(R.id.qt2)
    TextView qt2;


    @ViewInject(R.id.xx1)
    EditText xx1;
    @ViewInject(R.id.xx2)
    EditText xx2;

    @ViewInject(R.id.bt_send)
    Button bt_send;
    public static Handler handler;
    public static List<Integer> list=new ArrayList<>();//点击事件
    public static WaitDialog wd;
    WaitDialog_pdf wp;
    int max=29;
    CreditSharePreferences csp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ViewUtils.inject(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        wd=new WaitDialog(this);
        wp=new WaitDialog_pdf(this);
        b_topLLt.setVisibility(View.VISIBLE);
        b_topY.setVisibility(View.VISIBLE);
        b_topY.setText("全选");
        b_topY.setTextSize(16);
        b_topname.setText("信用报告");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        wd.dismiss();
                        wp.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER+URLconstant.REPORTURL2, RequestMethod.GET);
                        request.add("fileName",  DataManager.ReportText);
                        request.add("sendTo",xx1.getText().toString());//
                        request.add("provinceCode", CompanyDetailsActivity.ProvinceCode);
                        CallServer.getInstance().add(ReportActivity.this, request, MyhttpCallBack.getInstance(), 0x702, true, false, true);
                        break;
                    case  1:
                        wd.dismiss();
                        Toast.show("信用报告请求失败!请重试!");
                        break;
                    case  2:
                        wp.dismiss();
                        Toast.show("信用报告发送失败!请重试!");
                        break;
                    case 3:
                        wp.dismiss();
                        Toast.show("信用报告发送成功!");
                        finish();
                        break;
                }

            }
        };
        init();
    }
    public void init(){
        for(int i=1;i<=max;i++){
            list.add(0);
        }
        gs1.setOnClickListener(listener);
        gs2.setOnClickListener(listener);
        gs3.setOnClickListener(listener);
        gs4.setOnClickListener(listener);

        dw1.setOnClickListener(listener);
        dw2.setOnClickListener(listener);
        dw3.setOnClickListener(listener);
        dw4.setOnClickListener(listener);

        sf1.setOnClickListener(listener);
        sf2.setOnClickListener(listener);
        sf3.setOnClickListener(listener);
        sf4.setOnClickListener(listener);
        sf5.setOnClickListener(listener);

        fx1.setOnClickListener(listener);
        fx2.setOnClickListener(listener);
        fx3.setOnClickListener(listener);
        fx4.setOnClickListener(listener);
        fx5.setOnClickListener(listener);
        fx6.setOnClickListener(listener);
        fx7.setOnClickListener(listener);
        fx8.setOnClickListener(listener);
        fx9.setOnClickListener(listener);

        nb1.setOnClickListener(listener);

        zzcq1.setOnClickListener(listener);
        zzcq2.setOnClickListener(listener);
        zzcq3.setOnClickListener(listener);
        zzcq4.setOnClickListener(listener);

        qt1.setOnClickListener(listener);
        qt2.setOnClickListener(listener);

        bt_send.setOnClickListener(listener);

        b_topY.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.gs1:
                    if(list.get(0)==0){
                        list.set(0, 1);
                        gs1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(0, 0);
                        gs1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.gs2:
                    if(list.get(1)==0){
                        list.set(1, 1);
                        gs2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(1, 0);
                        gs2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.gs3:
                    if(list.get(2)==0){
                        list.set(2, 1);
                        gs3.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(2, 0);
                        gs3.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.gs4:
                    if(list.get(3)==0){
                        list.set(3, 1);
                        gs4.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(3, 0);
                        gs4.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.dw1:
                    if(list.get(4)==0){
                        list.set(4, 1);
                        dw1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(4, 0);
                        dw1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.dw2:
                    if(list.get(5)==0){
                        list.set(5, 1);
                        dw2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(5, 0);
                        dw2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.dw3:
                    if(list.get(6)==0){
                        list.set(6, 1);
                        dw3.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(6, 0);
                        dw3.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.dw4:
                    if(list.get(7)==0){
                        list.set(7, 1);
                        dw4.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(7, 0);
                        dw4.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.sf1:
                    if(list.get(8)==0){
                        list.set(8, 1);
                        sf1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(8, 0);
                        sf1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.sf2:
                    if(list.get(9)==0){
                        list.set(9, 1);
                        sf2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(9, 0);
                        sf2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.sf3:
                    if(list.get(10)==0){
                        list.set(10, 1);
                        sf3.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(10, 0);
                        sf3.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.sf4:
                    if(list.get(11)==0){
                        list.set(11, 1);
                        sf4.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(11, 0);
                        sf4.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.sf5:
                    if(list.get(12)==0){
                        list.set(12, 1);
                        sf5.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(12, 0);
                        sf5.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx1:
                    if(list.get(13)==0){
                        list.set(13, 1);
                        fx1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(13, 0);
                        fx1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx2:
                    if(list.get(14)==0){
                        list.set(14, 1);
                        fx2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(14, 0);
                        fx2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx3:
                    if(list.get(15)==0){
                        list.set(15, 1);
                        fx3.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(15, 0);
                        fx3.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx4:
                    if(list.get(16)==0){
                        list.set(16, 1);
                        fx4.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(16, 0);
                        fx4.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx5:
                    if(list.get(17)==0){
                        list.set(17, 1);
                        fx5.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(17, 0);
                        fx5.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx6:
                    if(list.get(18)==0){
                        list.set(18, 1);
                        fx6.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(18, 0);
                        fx6.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx7:
                    if(list.get(19)==0){
                        list.set(19, 1);
                        fx7.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(19, 0);
                        fx7.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx8:
                    if(list.get(20)==0){
                        list.set(20, 1);
                        fx8.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(20, 0);
                        fx8.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.fx9:
                    if(list.get(21)==0){
                        list.set(21, 1);
                        fx9.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(21, 0);
                        fx9.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.nb1:
                    if(list.get(22)==0){
                        list.set(22, 1);
                        nb1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(22, 0);
                        nb1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;

                case R.id.zzcq1:
                    if(list.get(23)==0){
                        list.set(23, 1);
                        zzcq1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(23, 0);
                        zzcq1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.zzcq2:
                    if(list.get(24)==0){
                        list.set(24, 1);
                        zzcq2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(24, 0);
                        zzcq2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.zzcq3:
                    if(list.get(25)==0){
                        list.set(25, 1);
                        zzcq3.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(25,0);
                        zzcq3.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.zzcq4:
                    if(list.get(26)==0){
                        list.set(26, 1);
                        zzcq4.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(26, 0);
                        zzcq4.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.qt1:
                    if(list.get(27)==0){
                        list.set(27, 1);
                        qt1.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(27, 0);
                        qt1.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.qt2:
                    if(list.get(28)==0){
                        list.set(28, 1);
                        qt2.setBackgroundResource(R.drawable.xxbaogao_tit);
                        nexshow();
                    }else{
                        list.set(28, 0);
                        qt2.setBackgroundResource(R.drawable.maintop_shape);
                        if(b_topY.getText().toString().equals("取消全选")) {
                            b_topY.setText("全选");
                        }
                    }
                    break;
                case R.id.b_topY://全选or取消全选
                    list.clear();
                    if(b_topY.getText().toString().equals("全选")){
                        b_topY.setText("取消全选");
                        for(int i=1;i<=max;i++){
                            list.add(1);
                        }
                        for (int i = R.id.gs1; i <= R.id.qt2; i++) {
                            findViewById(i).setBackgroundResource(R.drawable.xxbaogao_tit);
                        }
                    }else{
                        b_topY.setText("全选");
                        for(int i=1;i<=max;i++){
                            list.add(0);
                        }
                        for (int i = R.id.gs1; i <= R.id.qt2; i++) {
                            findViewById(i).setBackgroundResource(R.drawable.maintop_shape);
                        }
                    }
                    break;
                case R.id.bt_send:
                    String selectStr="";
                    for(int i=0;i<list.size();i++){
                        if(list.get(i)==1){
                            switch (i){
                                case 0:
                                    selectStr=selectStr+"股东信息,";
                                    break;
                                case 1:
                                    selectStr=selectStr+"主要人员信息,";
                                    break;
                                case 2:
                                    selectStr=selectStr+"分支机构信息,";
                                    break;
                                case 3:
                                    selectStr=selectStr+"变更记录信息,";
                                    break;
                                case 4:
                                    selectStr=selectStr+"企业对外投资信息,";
                                    break;
                                case 5:
                                    selectStr=selectStr+"法人对外投资信息,";
                                    break;
                                case 6:
                                    selectStr=selectStr+"主要人员对外投资信息,";
                                    break;
                                case 7:
                                    selectStr=selectStr+"股东对外投资信息,";
                                    break;
                                case 8:
                                    selectStr=selectStr+"司法文书信息,";
                                    break;
                                case 9:
                                    selectStr=selectStr+"失信被执行人信息,";
                                    break;
                                case 10:
                                    selectStr=selectStr+"股权冻结信息,";
                                    break;
                                case 11:
                                    selectStr=selectStr+"股权变更信息,";
                                    break;
                                case 12:
                                    selectStr=selectStr+"法院判决文书信息,";
                                    break;
                                case 13:
                                    selectStr=selectStr+"荣誉信息,";
                                    break;
                                case 14:
                                    selectStr=selectStr+"经营异常信息,";
                                    break;
                                case 15:
                                    selectStr=selectStr+"股权出质信息,";
                                    break;
                                case 16:
                                    selectStr=selectStr+"动产抵押信息,";
                                    break;
                                case 17:
                                    selectStr=selectStr+"行政处罚信息,";
                                    break;
                                case 18:
                                    selectStr=selectStr+"行政许可信息,";
                                    break;
                                case 19:
                                    selectStr=selectStr+"预警信息,";
                                    break;
                                case 20:
                                    selectStr=selectStr+"广告资质信息,";
                                    break;
                                case 21:
                                    selectStr=selectStr+"守合同重信用信息,";
                                    break;
                                case 22:
                                    selectStr=selectStr+"年报,";
                                    break;
                                case 23:
                                    selectStr=selectStr+"著作权信息,";
                                    break;
                                case 24:
                                    selectStr=selectStr+"软件著作权信息,";
                                    break;
                                case 25:
                                    selectStr=selectStr+"专利信息,";
                                    break;
                                case 26:
                                    selectStr=selectStr+"商标信息,";
                                    break;
                                case 27:
                                    selectStr=selectStr+"招投标信息,";
                                    break;
                                case 28:
                                    selectStr=selectStr+"招聘,";
                                    break;
                            }
                        }
                    }
                    if(selectStr.equals("")){
                        Toast.show("请选择模块!");
                    }else if(xx1.getText().toString().trim().equals("")){
                        Toast.show("邮箱地址不能为空!");
                    }else if(!FileUtil.isEmail(xx1.getText().toString().trim())){
                        Toast.show("邮箱地址格式不正确!");
                    } else if(xx2.getText().toString().trim().equals("")){
                        Toast.show("手机号码不能为空!");
                    }else if(!FileUtil.isNumeric(xx2.getText().toString().trim()) || (xx2.getText().toString().trim()).length()!=11){
                        Toast.show("手机号码格式不正确!");
                    } else{
                        wd.show();
                        GsonUtil request = new GsonUtil(URLconstant.URLINSER+URLconstant.REPORTURL1, RequestMethod.GET);
                        request.add("pripid",  DataManager.QJiugongGList.data.baseInfo.get(0).PRIPID);
                        request.add("select",selectStr);//
                        request.add("email",xx1.getText().toString().trim());//
                        request.add("tel",xx2.getText().toString().trim());//
                        if(csp.getLoginStatus()){//判定用户是否登录
                            request.add("memberId",csp.getID());//
                        }
                        request.add("provinceCode", CompanyDetailsActivity.ProvinceCode);
                        request.add("entname",  DataManager.QJiugongGList.data.baseInfo.get(0).ENTNAME);//
                        request.add("priptype",  DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE);//
                        CallServer.getInstance().add(ReportActivity.this, request, MyhttpCallBack.getInstance(), 0x701, true, false, true);

                    }
                    break;
            }
        }
    };
    public void nexshow(){
        String str="";
        for(int i=0;i<list.size();i++){
            if(list.get(i)==1){
                str=str+1;
            }
        }
        if(str.equals("11111111111111111111111111111")){
            b_topY.setText("取消全选");
        }else{
            b_topY.setText("全选");
        }
    }
}
