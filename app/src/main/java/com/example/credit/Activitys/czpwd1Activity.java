package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 找回密码----填写信息
 * @author yusi
 */
public class czpwd1Activity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.tx1)
    EditText tx1;
    @ViewInject(R.id.tx2)
    EditText tx2;
    @ViewInject(R.id.txbut)
    Button txbut;
    WaitDialog wd;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czpwd1);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        b_topname.setText("找回密码");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        txbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tx1.getText().toString().trim().equals("")){
                    wd.show();
                    GsonUtil LoginRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.FORGETPASSWORD, RequestMethod.GET);
                    LoginRequest.add("token", MD5.MD5s(tx1.getText().toString().trim() + new Build().MODEL));
                    LoginRequest.add("KeyNo", tx1.getText().toString().trim());
                    LoginRequest.add("deviceId", new Build().MODEL);
//                LoginRequest.add("email", tx2.getText().toString().trim());
                    LoginRequest.add("type", 0);
                    CallServer.getInstance().add(czpwd1Activity.this, LoginRequest, MyhttpCallBack.getInstance(), 0x214, true, false, true);
                }else{
                    Toast.show("账号不能为空!");
                }
            }
        });
         handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                wd.dismiss();
                switch (msg.what){
                    case 0:
//                        {"message":"账号 或 邮箱 错误","status":"10","version":"v1.0"}
                        if(DataManager.getczpwdList.status.equals("1")){
                            Toast.show("邮件发送成功!");
                            startActivity(new Intent(czpwd1Activity.this,czpwd2Activity.class));
                            czpwd1Activity.this.finish();
                        }else{
                            Toast.show(DataManager.getczpwdList.message+"");
                        }
                        break;
                    case 1:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }
}
