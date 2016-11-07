package com.example.credit.Activitys;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.Details_content_mylistAdapter;
import com.example.credit.Adapters.FeiZhiAdapter;
import com.example.credit.Adapters.MyGridAdapter3;
import com.example.credit.Adapters.MyGridZYAdapter;
import com.example.credit.Adapters.MyGridZZAdapter;
import com.example.credit.Adapters.gfhzAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.Toast;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.MyGridView;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 工商信息主界面
 */
public class DetailsContentActivity extends BaseActivity {
//    @ViewInject(R.id.about_top)
//    LinearLayout about_top;//返回

    @ViewInject(R.id.b_return)
    LinearLayout b_return;//返回

    @ViewInject(R.id.b_topname)
    TextView b_topname;//企业名称

    @ViewInject(R.id.c_tab1)
    Button c_tab1;
    @ViewInject(R.id.c_tab2)
    Button c_tab2;
    @ViewInject(R.id.c_tab3)
    Button c_tab3;
    @ViewInject(R.id.c_tab4)
    Button c_tab4;
    @ViewInject(R.id.c_tab5)
    Button c_tab5;
    @ViewInject(R.id.c_tab6)
    Button c_tab6;

    @ViewInject(R.id.c_scr)
    ScrollView c_scr;

    @ViewInject(R.id.tabLa)
    LinearLayout tabLa;
    @ViewInject(R.id.c_tv1)
    TextView c_tv1;
    @ViewInject(R.id.c_tv2)
    TextView c_tv2;
    @ViewInject(R.id.c_tv3)
    TextView c_tv3;
    @ViewInject(R.id.c_tv4)
    TextView c_tv4;
    @ViewInject(R.id.c_tv5)
    TextView c_tv5;
    @ViewInject(R.id.c_tv6)
    TextView c_tv6;


    private boolean hasMeasure = false;
    private int pointY[];

    @ViewInject(R.id.myGridView3)
    MyGridView myGridView3;//基本信息

    @ViewInject(R.id.c_daimatit)
    TextView c_daimatit;//统一社会信用代码TITLE
    @ViewInject(R.id.c_daima)
    TextView c_daima;//统一社会信用代码

    @ViewInject(R.id.c_nametit)
    TextView c_nametit;//  名称小标题
    @ViewInject(R.id.c_name)
    TextView c_name;//  名称

    @ViewInject(R.id.c_limoney)
    LinearLayout c_limoney;//  出资布局[ 农民专业合作社法人——企业类型9100 ]
    @ViewInject(R.id.c_money)
    TextView c_money;//  出资总额[ 农民专业合作社法人——企业类型9100 ]

    @ViewInject(R.id.c_type)
    TextView c_type;//企业类型

    @ViewInject(R.id.c_stateLin)
    LinearLayout c_stateLin;//经营状态小标题
    @ViewInject(R.id.c_state)
    TextView c_state;//经营状态

    @ViewInject(R.id.c_fangwei)
    TextView c_fangwei;//经营范围

    @ViewInject(R.id.c_addresstit)
    TextView c_addresstit;//企业地址小标题
    @ViewInject(R.id.c_address)
    TextView c_address;//企业地址

    @ViewInject(R.id.c_djjg)
    TextView c_djjg;//登记机关

    @ViewInject(R.id.myGridViewp)
    MyGridView myGridViewp;//投资列表

    @ViewInject(R.id.c_people)
    LinearLayout c_people;//投资列表null

    @ViewInject(R.id.myGridViewZY)
    MyGridView myGridViewZY;//主要人员

    @ViewInject(R.id.c_Cpeople)
    LinearLayout c_Cpeople;//主要人员null

    @ViewInject(R.id.GSmylist)
    MyListView GSmylist;//工商变更

    @ViewInject(R.id.c_GS)
    LinearLayout c_GS;//工商变更null

    @ViewInject(R.id.myGridViewZYfz)
    MyListView myGridViewZYfz;//分支机构

    @ViewInject(R.id.c_fzjg)
    LinearLayout c_impPeoc_fzjgple;//分支机构null

    @ViewInject(R.id.c_qsuan)
    LinearLayout c_qsuan;//清算null

    public String[] arrays3 = {"注册资本：", "法定代表人：","成立日期：","核准日期：",
            "营业期限自：", "营业期限至："};
    public String[] arrays4;
    MyGridAdapter3 adapter2;
    MyGridZZAdapter adapterzy123;
    MyGridZYAdapter adapterzy;
    String Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_content);
        ViewUtils.inject(this);
        Intent i = getIntent();
        Tname = i.getStringExtra("Tname");
        init();
        ScrIndex();
        GSchages();
        enttypeShow();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void ScrIndex() {
        pointY = new int[6];
        ViewTreeObserver observer = c_tv1.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (hasMeasure) {

                } else {
                    int height = c_tv1.getMeasuredHeight();
                    LayoutParams params = c_tv1.getLayoutParams();
                    pointY[0] = c_tv1.getTop();
                    pointY[1] = c_tv2.getTop();
                    pointY[2] = c_tv3.getTop();
                    pointY[3] = c_tv4.getTop();
                    pointY[4] = c_tv5.getTop();
                    pointY[5] = c_tv6.getTop();
                    hasMeasure = true;
                }
                return true;
            }

        });

        c_scr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int s = c_scr.getScrollY();
                int x = pointY[2];
                if (c_scr.getScrollY() < pointY[0] || (c_scr.getScrollY() >= pointY[0] && c_scr.getScrollY() < pointY[1])) {
                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab1.setTextColor(getResources().getColor(R.color.white));

                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab6.setTextColor(getResources().getColor(R.color.black));

                } else if (c_scr.getScrollY() >= pointY[1] && c_scr.getScrollY() < pointY[2]) {
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab2.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab6.setTextColor(getResources().getColor(R.color.black));

                } else if (c_scr.getScrollY() >= pointY[2] && c_scr.getScrollY() < pointY[3]) {
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab3.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab6.setTextColor(getResources().getColor(R.color.black));
                } else if (c_scr.getScrollY() >= pointY[3] && c_scr.getScrollY() < pointY[4]) {
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab4.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab6.setTextColor(getResources().getColor(R.color.black));
                } else if (c_scr.getScrollY() >= pointY[4] && c_scr.getScrollY() < pointY[5]) {
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab5.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab6.setTextColor(getResources().getColor(R.color.black));
                }else if (c_scr.getScrollY() >= pointY[5]) {
                    c_tab6.setBackgroundResource(R.drawable.details_con_tabbg1);
                    c_tab6.setTextColor(getResources().getColor(R.color.white));

                    c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab1.setTextColor(getResources().getColor(R.color.black));
                    c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab2.setTextColor(getResources().getColor(R.color.black));
                    c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab3.setTextColor(getResources().getColor(R.color.black));
                    c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab4.setTextColor(getResources().getColor(R.color.black));
                    c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                    c_tab5.setTextColor(getResources().getColor(R.color.black));
                }
                return false;
            }
        });

    }

    public void tabshow(View v) {
        switch (v.getId()) {
            case R.id.c_tab1:
                c_scr.smoothScrollTo(0, pointY[0]);
                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab1.setTextColor(getResources().getColor(R.color.white));

                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab6.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab2:
                c_scr.smoothScrollTo(0, pointY[1]);
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab2.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab6.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab3:
                c_scr.smoothScrollTo(0, pointY[2]);
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab3.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab6.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab4:
                c_scr.smoothScrollTo(0, pointY[3]);
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab4.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab6.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab5:
                c_scr.smoothScrollTo(0, pointY[4]);
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab5.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab6.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.c_tab6:
                c_scr.smoothScrollTo(0, pointY[5]);
                c_tab6.setBackgroundResource(R.drawable.details_con_tabbg1);
                c_tab6.setTextColor(getResources().getColor(R.color.white));

                c_tab1.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab1.setTextColor(getResources().getColor(R.color.black));
                c_tab2.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab2.setTextColor(getResources().getColor(R.color.black));
                c_tab3.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab3.setTextColor(getResources().getColor(R.color.black));
                c_tab4.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab4.setTextColor(getResources().getColor(R.color.black));
                c_tab5.setBackgroundResource(R.drawable.details_con_tabbg2);
                c_tab5.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }

    public void init() {
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        /**
         * 基本信息
         */
        List<String> userList1 = Arrays.asList(getResources().getStringArray(R.array.ENTTYPE_id));
        List<String> userList2 = Arrays.asList(getResources().getStringArray(R.array.ENTTYPE_name));
        for(int z=0;z<userList1.size();z++){
            int size=userList1.get(z).length();
            try{
                if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,size).indexOf(userList1.get(z)) != -1){
                    arrays3[1]=userList2.get(z);
                    break;
                }
            }catch (Exception e){}
        }


        List<String> lt = new ArrayList<String>();
        if(DataManager.gsxx.data.baseInfo.REGCAP.equals("") || DataManager.gsxx.data.baseInfo.REGCAP.equals("null") || DataManager.gsxx.data.baseInfo.REGCAP==null){
            lt.add("暂无信息");
        }else{
            try{
                if(DataManager.gsxx.data.baseInfo.REGCAP.indexOf(".") == -1){
                    lt.add(DataManager.gsxx.data.baseInfo.REGCAP+ "万元人民币");
                }else{
                    lt.add(DataManager.gsxx.data.baseInfo.REGCAP.substring(0, DataManager.gsxx.data.baseInfo.REGCAP.indexOf(".")) + "万元人民币");
                }
            }catch (Exception e){}

        }

        if(DataManager.gsxx.data.baseInfo.NAME!=null&&!DataManager.gsxx.data.baseInfo.NAME.equals("")&&!DataManager.gsxx.data.baseInfo.NAME.equals(null)){
            lt.add(DataManager.gsxx.data.baseInfo.NAME);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.baseInfo.ESTDATE!=null&&!DataManager.gsxx.data.baseInfo.ESTDATE.equals(null)&&!DataManager.gsxx.data.baseInfo.ESTDATE.equals("")){
            lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.baseInfo.APPRDATE!=null&&!DataManager.gsxx.data.baseInfo.APPRDATE.equals(null)&&!DataManager.gsxx.data.baseInfo.APPRDATE.equals("")){
            lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
        }else{
            lt.add("暂无信息");
        }
        if(DataManager.gsxx.data.baseInfo.OPFROM!=null&&!DataManager.gsxx.data.baseInfo.OPFROM.equals(null)&&!DataManager.gsxx.data.baseInfo.OPFROM.equals("")){
            lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
        }else{
            lt.add("暂无信息");
        }

        if(DataManager.gsxx.data.baseInfo.OPTO!=null&&!DataManager.gsxx.data.baseInfo.OPTO.equals(null)&&!DataManager.gsxx.data.baseInfo.OPTO.equals("")){
            lt.add(DataManager.gsxx.data.baseInfo.OPTO);
        }else{
            lt.add("暂无信息");
        }

        int size = lt.size();
        arrays4 = lt.toArray(new String[size]);
        adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
        myGridView3.setAdapter(adapter2);
        myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));

        b_topname.setText(Tname);
        /**
         * 判断统一社会信用代码是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.UNISCID!=null&&!DataManager.gsxx.data.baseInfo.UNISCID.equals(null)&&!(DataManager.gsxx.data.baseInfo.UNISCID).equals("")){
                c_daima.setText(DataManager.gsxx.data.baseInfo.UNISCID);//
            }else{
                c_daima.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         *判断名称是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.ENTNAME!=null&&!DataManager.gsxx.data.baseInfo.ENTNAME.equals(null)&&!(DataManager.gsxx.data.baseInfo.ENTNAME).equals("")){
                c_name.setText(DataManager.gsxx.data.baseInfo.ENTNAME);//
            }else{
                c_name.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        /**
         *判断名称是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.ENTNAME!=null&&!DataManager.gsxx.data.baseInfo.ENTNAME.equals(null)&&!(DataManager.gsxx.data.baseInfo.ENTNAME).equals("")){
                c_name.setText(DataManager.gsxx.data.baseInfo.ENTNAME);//
            }else{
                c_name.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        /**
         * 判断企业类型是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.ENTTYPE.equals("9999") || DataManager.gsxx.data.baseInfo.ENTTYPE.equals("9500")){
                c_type.setText("个体工商户");//个体工商户
            }else{
                if(DataManager.gsxx.data.baseInfo.ENTTYPE_CN!=null&&!DataManager.gsxx.data.baseInfo.ENTTYPE_CN.equals(null)&&!(DataManager.gsxx.data.baseInfo.ENTTYPE_CN).equals("")){
                    c_type.setText(DataManager.gsxx.data.baseInfo.ENTTYPE_CN);//企业类型
                }else{
                    c_type.setText("暂无信息");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断经营状态是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.REGSTATE_CN!=null&&!DataManager.gsxx.data.baseInfo.REGSTATE_CN.equals(null)&&!(DataManager.gsxx.data.baseInfo.REGSTATE_CN).equals("")){
                c_state.setText(DataManager.gsxx.data.baseInfo.REGSTATE_CN);//经营状态/
            }else{
                c_state.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断经营范围是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.OPSCOPE!=null&&!DataManager.gsxx.data.baseInfo.OPSCOPE.equals(null)&&!(DataManager.gsxx.data.baseInfo.OPSCOPE).equals("")){
                c_fangwei.setText(DataManager.gsxx.data.baseInfo.OPSCOPE);//经营范围
            }else{
                c_fangwei.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断企业地址是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.DOM!=null&&!DataManager.gsxx.data.baseInfo.DOM.equals(null)&&!(DataManager.gsxx.data.baseInfo.DOM).equals("")){
                c_address.setText(DataManager.gsxx.data.baseInfo.DOM);//企业地址
            }else{
                c_address.setText("暂无信息");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        /**
         * 判断登记机关是否为空
         */
        try{
            if(DataManager.gsxx.data.baseInfo.REGORG_CN==null|| DataManager.gsxx.data.baseInfo.REGORG_CN.equals(null)||(DataManager.gsxx.data.baseInfo.REGORG_CN).equals("")){
                c_djjg.setText("暂无信息");
            }else{
                c_djjg.setText(DataManager.gsxx.data.baseInfo.REGORG_CN);//登记机关
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        /**
         * 股东及出资
         */
        List<String> p1 = new ArrayList<String>();//股东类型list
        List<String> p2 = new ArrayList<String>();//人员list
        List<String> p3 = new ArrayList<String>();//证件类型list
        List<String> p4 = new ArrayList<String>();//号码list
        List<String> p5 = new ArrayList<String>();//详情list
        if((DataManager.gsxx.data.partnersInfo)!=null && (DataManager.gsxx.data.partnersInfo).size()>0){
            for (DataManager.GSXX.DataBean.PartnersInfoBean p: DataManager.gsxx.data.partnersInfo) {
                p1.add(p.INVTYPE_CN);
                p2.add(p.INV);
                p3.add(p.CERTYPE_CN);
                p4.add(p.INVID);
                p5.add("");
            }
            String[] arrayszy11 = p1.toArray(new String[p1.size()]);
            String[] arrayszy21 = p2.toArray(new String[ p2.size()]);
            String[] arrayszy31 = p3.toArray(new String[ p3.size()]);
            String[] arrayszy41 = p4.toArray(new String[ p4.size()]);
            String[] arrayszy51 = p5.toArray(new String[ p5.size()]);
            adapterzy123 = new MyGridZZAdapter(DetailsContentActivity.this, arrayszy11, arrayszy21,arrayszy31,arrayszy41,arrayszy51);
            myGridViewp.setAdapter(adapterzy123);
            myGridViewp.setSelector(new ColorDrawable(Color.TRANSPARENT));
        }else{
            c_people.setVisibility(View.VISIBLE);
            myGridViewp.setVisibility(View.GONE);
        }
        myGridViewp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = sdf.parse(DataManager.gsxx.data.baseInfo.ESTDATE);
                    Date d2 = sdf.parse("2014-2-28");
                    //比较
                    int sizws1=0;//记录股权变更的个数
                    int sizws2=0;//记录股权时间小于2014-2-28的个数
                    if(d1.getTime() <= d2.getTime()) {
                        if(DataManager.gsxx.data.changeRecordsInfo.size()>0 && DataManager.gsxx.data.changeRecordsInfo !=null){
                            for(int i=0;i< DataManager.gsxx.data.changeRecordsInfo.size();i++){
                                if(DataManager.gsxx.data.changeRecordsInfo.get(i).ALTITEM_CN.indexOf("股权") != -1){
                                    sizws1++;
                                    Date d3 = sdf.parse(DataManager.gsxx.data.changeRecordsInfo.get(i).ALTDATE);
                                    Date d4 = sdf.parse("2014-2-28");
                                    //比较
                                    if(d3.getTime() <= d4.getTime()) {
                                        sizws2++;
                                    }
                                }
                            }
                            if(sizws2==sizws1 || sizws1==0){
                                tsdetailsshow(position);
                            }
                        }else{
                            tsdetailsshow(position);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        /**
         * 主要人员
         */
        List<String> zy1 = new ArrayList<String>();//职位list
        List<String> zy2 = new ArrayList<String>();//人员list
        if((DataManager.gsxx.data.employeesInfo)!=null && (DataManager.gsxx.data.employeesInfo).size()>0){
            for (DataManager.GSXX.DataBean.EmployeesInfoBean e : DataManager.gsxx.data.employeesInfo) {
                zy1.add(e.POSITION_CN);
                zy2.add(e.NAME);
            }
            int size1 = zy1.size();
            int size2 = zy2.size();
            String[] arrayszy1 = zy1.toArray(new String[size1]);
            String[] arrayszy2 = zy2.toArray(new String[size2]);
            adapterzy = new MyGridZYAdapter(DetailsContentActivity.this, arrayszy1, arrayszy2);
            myGridViewZY.setAdapter(adapterzy);
            myGridViewZY.setSelector(new ColorDrawable(Color.TRANSPARENT));
        }else{
            c_Cpeople.setVisibility(View.VISIBLE);
            myGridViewZY.setVisibility(View.GONE);
        }
        /**
         * 分支机构
         */
        if((DataManager.gsxx.data.annualReportsInfo)!=null && (DataManager.gsxx.data.annualReportsInfo).size()>0){
            FeiZhiAdapter hcadapter2=new FeiZhiAdapter(DetailsContentActivity.this, DataManager.gsxx.data.annualReportsInfo);
            myGridViewZYfz.setAdapter(hcadapter2);
        }else{
            c_impPeoc_fzjgple.setVisibility(View.VISIBLE);
            myGridViewZYfz.setVisibility(View.GONE);
        }

    }

    /**
     * 工商变更数据
     */
    public void GSchages() {
        List<DataManager.ChangeTime> clisttimeD = new ArrayList<>();//变更集合
        List<DataManager.ChangeData> clistd1 = new ArrayList<>();//变更信息临时仓库

        List<DataManager.GSXX.DataBean.ChangeRecordsInfoBean> clistc = DataManager.gsxx.data.changeRecordsInfo;//工商变更信息
        if(( DataManager.gsxx.data.changeRecordsInfo)!=null && ( DataManager.gsxx.data.changeRecordsInfo).size()>0){//判断数据是否为空
            for (int i = 0; i <clistc.size(); i++) {//数据打乱并按时间重新分组
                if (i > 0) {
                    if (clistc.get(i).ALTDATE.equals(clistc.get(i - 1).ALTDATE)) {//当下标为i的日期与下标为i-1的日期 相等 时
                        DataManager.ChangeData cc2 = new DataManager.ChangeData();
                        cc2.ALTDATE = clistc.get(i).ALTDATE;
                        cc2.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                        cc2.ALTBE = clistc.get(i).ALTBE;
                        cc2.ALTAF = clistc.get(i).ALTAF;
                        clistd1.add(cc2);//把数据追加入临时仓库

                        if(i==clistc.size()-1){//当i==数据最大size时(数据的最后结算位置，直接加入变更集合)
                            DataManager.ChangeTime ccr = new DataManager.ChangeTime();
                            ccr.ALTDATE = clistd1.get(0).ALTDATE;
                            ccr.changedata = clistd1;
                            clisttimeD.add(ccr);//则把最后变更信息临时仓库中的全部数据加入变更集合中
                        }
                    } else {//当下标为i的日期与下标为i-1的日期 不相等 时（因为这里是结算的位置，所以要使用中转仓库，不然直接使用临时仓库会使变更集合中的数据错乱）
                        DataManager.ChangeTime ccr = new DataManager.ChangeTime();//变更中转仓库1
                        ccr.ALTDATE = clistd1.get(0).ALTDATE;
                        ccr.changedata = clistd1;
                        clisttimeD.add(ccr);//先把临时仓库中的数据加入变更集合中

                        List<DataManager.ChangeData> clistdtest = new ArrayList<>();//变更中转仓库2
                        DataManager.ChangeData cc3 = new DataManager.ChangeData();
                        cc3.ALTDATE = clistc.get(i).ALTDATE;
                        cc3.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                        cc3.ALTBE = clistc.get(i).ALTBE;
                        cc3.ALTAF = clistc.get(i).ALTAF;
                        clistdtest.add(cc3);//把数据加入临时仓库
                        clistd1 = clistdtest;
                    }
                } else {//下标为0的数据直接加入临时仓库
                    DataManager.ChangeData cc1 = new DataManager.ChangeData();
                    cc1.ALTDATE = clistc.get(i).ALTDATE;
                    cc1.ALTITEM_CN = clistc.get(i).ALTITEM_CN;
                    cc1.ALTBE = clistc.get(i).ALTBE;
                    cc1.ALTAF = clistc.get(i).ALTAF;
                    clistd1.add(cc1);
                }
            }
            //size > 1 ,结算 所有数据   ，将变更信息临时仓库中数据加入变更仓库
            if(clistd1.size() ==1 && clistd1 !=null && clisttimeD.size()>0){
                DataManager.ChangeTime ccr = new DataManager.ChangeTime();//变更中转仓库1
                ccr.ALTDATE = clistd1.get(0).ALTDATE;
                ccr.changedata = clistd1;
                clisttimeD.add(ccr);//先把临时仓库中的数据加入变更集合中
            }

            Details_content_mylistAdapter adapter1;
            if(DataManager.gsxx.data.changeRecordsInfo.size()==1){//当size==1时，则执行另外算法
                DataManager.ChangeTime ccr = new DataManager.ChangeTime();
                ccr.ALTDATE = clistd1.get(0).ALTDATE;
                ccr.changedata = clistd1;
                clisttimeD.add(ccr);
                adapter1 = new Details_content_mylistAdapter(DetailsContentActivity.this, clisttimeD);
            }else{
                adapter1 = new Details_content_mylistAdapter(DetailsContentActivity.this, clisttimeD);
            }
            GSmylist.setAdapter(adapter1);
        }else{
            GSmylist.setVisibility(View.GONE);
            c_GS.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 根据企业类型修改
     */
    public void enttypeShow(){
//        Toast.show((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE));
        try{
            /**
             *  个体工商户（9999 9500）
             */
            if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("9999") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("9500") != -1) ){
                String[] arrays31 = {"经营者：", "登记状态：","成立日期：","核准日期："};
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays31, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_daimatit.setText("注册号：");
                c_daima.setText(DataManager.gsxx.data.baseInfo.REGNO);
                c_nametit.setText("名称：");
                c_addresstit.setText("住所：");
                c_tab2.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tab5.setVisibility(View.GONE);
                c_tab6.setText("参加经营的家庭成员");

                c_tv2.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                c_tv5.setVisibility(View.GONE);
                c_tv6.setText("参加经营的家庭成员");

                myGridViewp.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                myGridViewZYfz.setVisibility(View.GONE);

                c_people.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_impPeoc_fzjgple.setVisibility(View.GONE);

            }

            /**
             * 个人独资企业——企业类型4540
             */
            else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("4540") != -1){
                arrays3[0]="登记状态：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_tab2.setText("投资人");
                c_tv2.setText("投资人");
                c_nametit.setText("名称：");
                c_addresstit.setText("住所：");
                c_stateLin.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
                /**
                 * 投资人
                 */
                List<String> p1 = new ArrayList<String>();//投资人名称
                List<String> p2 = new ArrayList<String>();//出资类型
                if((DataManager.gsxx.data.partnersInfo)!=null && (DataManager.gsxx.data.partnersInfo).size()>0){
                    for (DataManager.GSXX.DataBean.PartnersInfoBean p: DataManager.gsxx.data.partnersInfo) {
                        p1.add(p.INV);
                        p2.add(p.SCONFORM);
                    }
                    int size11 = p1.size();
                    int size21 = p2.size();
                    String[] arrayszy11 = p1.toArray(new String[size11]);
                    String[] arrayszy21 = p2.toArray(new String[size21]);
                    adapterzy123 = new MyGridZZAdapter(DetailsContentActivity.this, arrayszy11, arrayszy21,null,null,null);
                    myGridViewp.setAdapter(adapterzy123);
                    myGridViewp.setSelector(new ColorDrawable(Color.TRANSPARENT));
                }else{
                    c_people.setVisibility(View.VISIBLE);
                    myGridViewp.setVisibility(View.GONE);
                }

            }
            /**
             * 农民专业合作社法人——企业类型9100
             */
            else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("9100") != -1){
                String[] arrays31 = {"法定代表人：", "登记状态：","成立日期：","核准日期："};
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays31, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_nametit.setText("名称：");
                c_addresstit.setText("住所：");
                c_limoney.setVisibility(View.VISIBLE);
                c_money.setText(DataManager.gsxx.data.baseInfo.REGCAP.substring(0, DataManager.gsxx.data.baseInfo.REGCAP.indexOf("."))+"万元"+DataManager.gsxx.data.baseInfo.REGCAPCUR_CN);


                c_tab2.setVisibility(View.GONE);
                c_tv2.setVisibility(View.GONE);
                myGridViewp.setVisibility(View.GONE);
                c_people.setVisibility(View.GONE);
                c_stateLin.setVisibility(View.GONE);
                c_tab3.setText("成员名册");
                c_tv3.setText("成员名册");
                c_tab5.setVisibility(View.GONE);
                c_tv5.setVisibility(View.GONE);
                myGridViewZYfz.setVisibility(View.GONE);
                c_impPeoc_fzjgple.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
                /**
                 * 成员名册
                 */

                List<String> zy2 = new ArrayList<String>();//人员list
                if((DataManager.gsxx.data.partnersInfo)!=null && (DataManager.gsxx.data.partnersInfo).size()>0){
                    for (DataManager.GSXX.DataBean.PartnersInfoBean p: DataManager.gsxx.data.partnersInfo) {
                        zy2.add(p.INV);
                    }
                    int size2 = zy2.size();
                    String[] arrayszy2 = zy2.toArray(new String[size2]);
                    adapterzy = new MyGridZYAdapter(DetailsContentActivity.this, arrayszy2, null);
                    myGridViewZY.setAdapter(adapterzy);
                    myGridViewZY.setSelector(new ColorDrawable(Color.TRANSPARENT));
                    c_Cpeople.setVisibility(View.GONE);
                    myGridViewZY.setVisibility(View.VISIBLE);
                }else{
                    c_Cpeople.setVisibility(View.VISIBLE);
                    myGridViewZY.setVisibility(View.GONE);
                }

            }
            /**
             * 合伙企业——企业类型453%
             */
            else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,3).indexOf("453") != -1){
                arrays3[0]="登记状态：";
                arrays3[4]="合伙期限自：";
                arrays3[5]="合伙期限至：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_tab2.setText("合伙人");
                c_tv2.setText("合伙人");
                c_nametit.setText("名称：");
                c_addresstit.setText("主要经营场所：");
                c_stateLin.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
            }
            /**
             *  合伙企业分支机构——企业类型455%
             */
            else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,3).indexOf("455") != -1){
                arrays3[0]="登记状态：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_nametit.setText("名称：");
                c_addresstit.setText("经营场所：");
                c_stateLin.setVisibility(View.GONE);
                c_tab2.setVisibility(View.GONE);
                c_tv2.setVisibility(View.GONE);
                myGridViewp.setVisibility(View.GONE);
                c_people.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_tab5.setVisibility(View.GONE);
                c_tv5.setVisibility(View.GONE);
                myGridViewZYfz.setVisibility(View.GONE);
                c_impPeoc_fzjgple.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
            }
            /**
             *  内资非公司企业法人
             */
            else if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,1).indexOf("3") != -1)){
                c_tab2.setText("主管部门");
                c_tv2.setText("主管部门（出资人）");
//            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) tabLa.getLayoutParams();
//            linearParams.height = LayoutParams.WRAP_CONTENT;
//            tabLa.setLayoutParams(linearParams);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_tab5.setVisibility(View.GONE);
                c_tv5.setVisibility(View.GONE);
                myGridViewZYfz.setVisibility(View.GONE);
                c_impPeoc_fzjgple.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
            }


            /**
             *  外商投资合伙企业
             */
            else  if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("54") != -1
                    || (DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("64") != -1){
                arrays3[0]="执行事务合伙人：";
                arrays3[1]="登记状态：";
                arrays3[4]="合伙期限自：";
                arrays3[5]="合伙期限至：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_tab2.setText("合伙人");
                c_tv2.setText("合伙人");
                c_nametit.setText("名称：");
                c_addresstit.setText("主要经营场所：");
                c_stateLin.setVisibility(View.GONE);
                c_people.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
            }
            /**
             *  中外合作非法人企业
             */
            else  if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("53") != -1
                    || (DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("63") != -1){
                arrays3[0]="登记状态：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_tab2.setVisibility(View.GONE);
                c_tv2.setVisibility(View.GONE);
                myGridViewp.setVisibility(View.GONE);
                c_people.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
                c_tab5.setText("各方合作");
                c_tv5.setText("各方合作");
                c_nametit.setText("名称：");
                c_addresstit.setText("主要经营场所：");
                c_stateLin.setVisibility(View.GONE);
                List<String> list1=new ArrayList<>();
                List<String> list2=new ArrayList<>();
                for(int i = 0; i< DataManager.gsxx.data.partnersInfo.size(); i++){
                    list1.add(DataManager.gsxx.data.partnersInfo.get(i).INV);
                    list2.add("");
                }
                gfhzAdapter hcadapter2=new gfhzAdapter(DetailsContentActivity.this,list1,list2);
                myGridViewZYfz.setVisibility(View.VISIBLE);
                myGridViewZYfz.setAdapter(hcadapter2);
            }
            /**
             *  内资公司法人——企业类型1%
             */
            else if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,1).indexOf("1") != -1)){

            }
            /**
             *  内资分公司——企业类型2%
             *  内资非公司企业法人分支机构——企业类型43%
             *  内资非法人企业——企业类型41%、42%、44%、46%、47%
             *  外资分支机构——企业类型5810、5820、5830、5890、6810、6820、6830、6890、71%
             *  外国（地区）企业在中国境内从事生产经营活动——企业类型73%
             *  个人独资企业分支机构——企业类型4560
             *  农民专业合作社分支机构——企业类型9200
             */
            else if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,1).indexOf("2") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("43") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("41") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("42") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("44") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("46") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("47") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("5810") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("5820") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("5830") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("5890") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("6810") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("6820") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("6830") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("6890") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("71") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("73") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("4560") != -1)
                    || ((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("9200") != -1)){
                arrays3[0]="负责人：";
                arrays3[1]="登记状态：";
                List<String> lt = new ArrayList<String>();
                lt.add(DataManager.gsxx.data.baseInfo.NAME);
                lt.add(DataManager.gsxx.data.baseInfo.REGSTATE_CN);
                lt.add(DataManager.gsxx.data.baseInfo.ESTDATE);
                lt.add(DataManager.gsxx.data.baseInfo.APPRDATE);
                lt.add(DataManager.gsxx.data.baseInfo.OPFROM);
                lt.add(DataManager.gsxx.data.baseInfo.OPTO);
                int size = lt.size();
                arrays4 = lt.toArray(new String[size]);
                adapter2 = new MyGridAdapter3(DetailsContentActivity.this, arrays3, arrays4);
                myGridView3.setAdapter(adapter2);
                myGridView3.setSelector(new ColorDrawable(Color.TRANSPARENT));
                c_nametit.setText("名称：");
                c_addresstit.setText("经营场所：");
                c_stateLin.setVisibility(View.GONE);
                c_tab2.setVisibility(View.GONE);
                c_tv2.setVisibility(View.GONE);
                myGridViewp.setVisibility(View.GONE);
                c_people.setVisibility(View.GONE);
                c_tab3.setVisibility(View.GONE);
                c_tv3.setVisibility(View.GONE);
                myGridViewZY.setVisibility(View.GONE);
                c_Cpeople.setVisibility(View.GONE);
                c_tab5.setVisibility(View.GONE);
                c_tv5.setVisibility(View.GONE);
                myGridViewZYfz.setVisibility(View.GONE);
                c_impPeoc_fzjgple.setVisibility(View.GONE);
                c_tab6.setVisibility(View.GONE);
                c_tv6.setVisibility(View.GONE);
                c_qsuan.setVisibility(View.GONE);
                if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("43") != -1)){
                    c_daimatit.setText("注册号：");
                    try{
                        if(DataManager.gsxx.data.baseInfo.REGNO!=null&&!DataManager.gsxx.data.baseInfo.REGNO.equals(null)&&!(DataManager.gsxx.data.baseInfo.REGNO).equals("")){
                            c_daima.setText(DataManager.gsxx.data.baseInfo.REGNO);//
                        }else{
                            c_daima.setText("暂无信息");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){}
    }

    public void tsdetailsshow(int i){
        View view = LayoutInflater.from(DetailsContentActivity.this).inflate(R.layout.activity_ts_details, null);
        TextView tsd_name= (TextView) view.findViewById(R.id.tsd_name);
        tsd_name.setText("股东："+DataManager.gsxx.data.partnersInfo.get(i).INV);
        TextView tsd_renjiao= (TextView) view.findViewById(R.id.tsd_renjiao);
        tsd_renjiao.setText("认缴额(万元)："+DataManager.gsxx.data.partnersInfo.get(i).SUBCONAM);
        TextView tsd_shijiao= (TextView) view.findViewById(R.id.tsd_shijiao);
        tsd_shijiao.setText("实缴额(万元)："+DataManager.gsxx.data.partnersInfo.get(i).ACCONAM);

        TextView tsd_renjiao_fanshi= (TextView) view.findViewById(R.id.tsd_renjiao_fanshi);
        tsd_renjiao_fanshi.setText("认缴出资方式："+DataManager.gsxx.data.partnersInfo.get(i).SUBCONFORM);
        TextView tsd_renjiao_money= (TextView) view.findViewById(R.id.tsd_renjiao_money);
        tsd_renjiao_money.setText("认缴出资额(万元)："+DataManager.gsxx.data.partnersInfo.get(i).SUBCONAMUSD);
        TextView tsd_renjiao_time= (TextView) view.findViewById(R.id.tsd_renjiao_time);
        tsd_renjiao_time.setText("认缴出资日期："+DataManager.gsxx.data.partnersInfo.get(i).CONDATE);

        TextView tsd_shijiao_fanshi= (TextView) view.findViewById(R.id.tsd_shijiao_fanshi);
        tsd_shijiao_fanshi.setText("实缴出资方式："+DataManager.gsxx.data.partnersInfo.get(i).ACCONAM);
        TextView tsd_shijiao_money= (TextView) view.findViewById(R.id.tsd_shijiao_money);
        tsd_shijiao_money.setText("实缴出资额(万元)："+DataManager.gsxx.data.partnersInfo.get(i).ACCONAMUSD);
        TextView tsd_shijiao_time= (TextView) view.findViewById(R.id.tsd_shijiao_time);
        tsd_shijiao_time.setText("实缴额(万元)实缴出资日期：******");

        new AlertDialog.Builder(DetailsContentActivity.this)
                .setTitle("股东及出资")
                .setView(view)
                .setPositiveButton("确认", null)
                .setCancelable(false)
                .show();
    }
}
