package com.example.credit.Activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;


public class WelcomeActivity extends BaseActivity {
    @ViewInject(R.id.welcom)
    ImageView iv;

    CreditSharePreferences esp;
    public static Handler handler;
    public static boolean fl;
    public static boolean red=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewUtils.inject(this);
        CreditSharePreferences.init(this);
        esp = CreditSharePreferences.getLifeSharedPreferences();

        GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWAPP, RequestMethod.GET);//获取最新版本
        NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
        NewClaimRequest.add("KeyNo", "");
        NewClaimRequest.add("deviceId", (new Build()).MODEL);
        CallServer.getInstance().add(WelcomeActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x110, true, false, true);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        fl = true;
                        GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
                        NewClaimRequest.add("KeyNo", "");
                        NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        NewClaimRequest.add("deviceId", new Build().MODEL);
                        NewClaimRequest.add("status", 1);
                        CallServer.getInstance().add(WelcomeActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x113, true, false, true);

                        break;
                    case 1:
                        GsonUtil LUNboimgRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.LUNBOIMH, RequestMethod.GET);//获取轮播图
                        LUNboimgRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        LUNboimgRequest.add("KeyNo", "");
                        LUNboimgRequest.add("deviceId", (new Build()).MODEL);
                        CallServer.getInstance().add(WelcomeActivity.this, LUNboimgRequest, MyhttpCallBack.getInstance(), 0x112, true, false, true);
                        break;
                    case 2:
                        GsonUtil hOTClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.HOTSPOT, RequestMethod.GET);//最新热点
                        hOTClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        hOTClaimRequest.add("KeyNo", "");
                        hOTClaimRequest.add("deviceId", (new Build()).MODEL);
                        CallServer.getInstance().add(WelcomeActivity.this, hOTClaimRequest, MyhttpCallBack.getInstance(), 0x114, true, false, true);
                        break;
                    case 3:
                        GsonUtil NewsRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                        NewsRequest.setConnectTimeout(10000);
                        NewsRequest.setReadTimeout(10000);
                        NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        NewsRequest.add("KeyNo", "");
                        NewsRequest.add("deviceId", (new Build()).MODEL);
                        NewsRequest.add("pageSize", 10);
                        NewsRequest.add("pageIndex", 1);
                        CallServer.getInstance().add(WelcomeActivity.this, NewsRequest, MyhttpCallBack.getInstance(), 0x111, true, false, true);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                //申请WRITE_EXTERNAL_STORAGE权限
                                ActivityCompat.requestPermissions(WelcomeActivity.this, new
                                                String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        1);
                            }else{
                                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                                finish();
                                overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
                            }
                            return;

                        }
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        finish();
                        overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);

                        break;
                }
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode==1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){     //拒绝 =0
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
            } else {
                red=false;
                com.example.credit.Utils.Toast.show("权限获取失败，部分功能无法使用，请到设置中开放权限");
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}