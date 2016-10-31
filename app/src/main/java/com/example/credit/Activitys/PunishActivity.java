package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 行政处罚主界面
 */
public class PunishActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.pu_sc)
    ScrollView pu_sc;
    @ViewInject(R.id.puListView1)
    MyListView puListView1;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punish);
        ViewUtils.inject(this);

        pu_sc.smoothScrollTo(0, 20);
        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        b_topname.setText(Tname);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = new ArrayList<>();
        for (DataManager.punishMar.DataBean.OtCaseInfoBean p : DataManager.punishMarList.data.otCaseInfo) {
            list.add(p.PENDECNO);//
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(PunishActivity.this, list, "punish", null,null);
        puListView1.setAdapter(adapter);
        puListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.PUNISHURL, RequestMethod.GET);
                request.add("token", MD5.MD5s( DataManager.punishMarList.data.otCaseInfo.get(position).PRIPID + new Build().MODEL));
                request.add("deviceId", new Build().MODEL);
                request.add("KeyNo",  DataManager.punishMarList.data.otCaseInfo.get(position).PRIPID);
                request.add("caseId", DataManager.punishMarList.data.otCaseInfo.get(position).CASEID);
                CallServer.getInstance().add(PunishActivity.this, request, MyhttpCallBack.getInstance(), 0x0081, true, false, true);
            }
        });

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent i = new Intent(PunishActivity.this, Public_Detail_ctivity.class);
                i.putExtra("state", "punish");
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        };
    }
}
