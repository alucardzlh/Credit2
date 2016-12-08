package com.example.credit.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
 * 找回密码----重置密码
 * @author yusi
 */
public class czpwd3Activity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.cz1)
    TextView cz1;
    @ViewInject(R.id.cz2)
    EditText cz2;
    @ViewInject(R.id.cz3)
    EditText cz3;
    @ViewInject(R.id.czbut)
    Button czbut;
    EditText txbut;
    WaitDialog wd;
    public static Handler handler;

    AlertDialog.Builder builder2;
    public static AlertDialog dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czpwd3);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        b_topname.setText("找回密码");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(czpwd3Activity.this,czpwd2Activity.class));
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        cz1.setText(DataManager.getczpwdList.data.get(0).USERNAME+"");

        /**
         * 提示弹窗
         */
        builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("提示");
        builder2.setMessage("密码重置成功，请重新登录!");
        builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                czpwd3Activity.this.finish();
            }
        });
        dialog2 = builder2.create();
        dialog2.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        czbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (cz2.getText().toString().trim().length() == 0 || cz3.getText().toString().trim().length() == 0) {
                    Toast.show("密码不能为空...");

                } else if (cz2.getText().length() < 6 || cz3.getText().length() < 6) {
                    Toast.show("密码长度至少6位...");

                } else if (!cz3.getText().toString().equals(cz2.getText().toString())) {
                    Toast.show("两次密码输入不一致...");
                } else {
                    wd.show();
                    GsonUtil LoginRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.FORGETPASSWORD, RequestMethod.GET);
                    LoginRequest.add("token", MD5.MD5s(DataManager.getczpwdList.data.get(0).ID + new Build().MODEL));
                    LoginRequest.add("KeyNo", DataManager.getczpwdList.data.get(0).ID);
                    LoginRequest.add("deviceId", new Build().MODEL);
                    LoginRequest.add("username", DataManager.getczpwdList.data.get(0).USERNAME);
                    LoginRequest.add("czpwd", cz3.getText().toString());
                    LoginRequest.add("type", 1);
                    CallServer.getInstance().add(czpwd3Activity.this, LoginRequest, MyhttpCallBack.getInstance(), 0x215, true, false, true);
                }
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                wd.dismiss();
                switch (msg.what){
                    case 0:
                        if(DataManager.getczpwdresultList.status.equals("1")){
                            if(DataManager.getczpwdresultList.data.result.equals("1")) {
                                dialog2.show();
                            }else{
                                Toast.show("密码重置失败，请重试!");
                            }
                        }else{
                            Toast.show(DataManager.getczpwdresultList.message+"");
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
