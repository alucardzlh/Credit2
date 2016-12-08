package com.example.credit.Activitys;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yolanda.nohttp.RequestMethod;

/**
 * 用户注册
 */

public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    ImageView b_return;

    @ViewInject(R.id.Ruser)
    android.support.v7.widget.AppCompatEditText Ruser;//用户名
    @ViewInject(R.id.Ruser_c)
    ImageView Ruser_c; //用户名 消除


    @ViewInject(R.id.Remail)
    android.support.v7.widget.AppCompatEditText Remail;//邮箱
    @ViewInject(R.id.Remail_c)
    ImageView Remail_c; //邮箱 消除

    @ViewInject(R.id.Rpwd)
    android.support.v7.widget.AppCompatEditText Rpwd;//密码
    @ViewInject(R.id.Rpwd_c)
    ImageView Rpwd_c; //密码 消除

    @ViewInject(R.id.Rrpwds)
    android.support.v7.widget.AppCompatEditText Rrpwds;//确认密码
    @ViewInject(R.id.Rrpwds_c)
    ImageView Rrpwds_c; //确认密码 消除

    @ViewInject(R.id.regiest_ing)
    LinearLayout regiest_ing;//注册

    public static Handler handler;

    @ViewInject(R.id.phone)
    android.support.v7.widget.AppCompatEditText phone;//填写短信验证码
    @ViewInject(R.id.setad)
    Button setad; //获取验证码



    @OnClick({R.id.Ruser_c,R.id.Remail_c, R.id.Rpwd_c, R.id.Rrpwds_c,R.id.setad})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ruser_c:
                Ruser.setText("");
                break;
            case R.id.Remail_c:
                Remail.setText("");
                break;
            case R.id.Rpwd_c:
                Rpwd.setText("");
                break;
            case R.id.Rrpwds_c:
                Rrpwds.setText("");
                break;
            case R.id.setad://暂时关闭
                if (Ruser.getText().toString().trim().equals("") ) {
                    Toast.show("手机号不能为空...");
                } else if (!FileUtil.isNumeric(Ruser.getText().toString().trim())) {
                    Toast.show("手机号格式不正确...");
                } else if ( Ruser.getText().length() != 11) {
                    Toast.show("手机号格式不正确...");
                } else {
                    setad.setEnabled(false);
                    timer.start();
                    GsonUtil regiestRquerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETAD, RequestMethod.GET);
                    regiestRquerst.setConnectTimeout(15000);
                    regiestRquerst.setReadTimeout(15000);

                    regiestRquerst.add("token", MD5.MD5s(Ruser.getText() + new Build().MODEL));
                    regiestRquerst.add("KeyNo", Ruser.getText().toString());
                    regiestRquerst.add("deviceId", new Build().MODEL);
                    CallServer.getInstance().add(RegisterActivity.this, regiestRquerst, MyhttpCallBack.getInstance(), 0x9981, true, false, true);
                }
                break;
        }
    }

    public static ProgressDialog pd,pd1;
    public static boolean flg=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        pd = new ProgressDialog(this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);

        pd1 = new ProgressDialog(this);
        pd1 = new ProgressDialog(this);
        pd1.setMessage("注册成功,正在登陆跳转,请稍后...");
        pd1.setCancelable(false);
        init();

        handler = new Handler() {//注册成功
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        pd.dismiss();
                        Toast.show("注册成功！");
                        finish();
                        pd1.show();
                        flg=true;
                        GsonUtil LoginRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.USERLOGIN, RequestMethod.GET);
                        LoginRequest.add("token", MD5.MD5s(Ruser.getText().toString() + new Build().MODEL));
                        LoginRequest.add("KeyNo", Ruser.getText().toString());
                        LoginRequest.add("deviceId", new Build().MODEL);
                        LoginRequest.add("password", Rrpwds.getText().toString());
                        CallServer.getInstance().add(RegisterActivity.this, LoginRequest, MyhttpCallBack.getInstance(), 0x999, true, false, true);

                        break;
                    case 1:
//                        setad.setEnabled(false);
//                        timer.start();
                        break;
                    case 2:

                        break;
                }
                super.handleMessage(msg);
            }
        };
    }
    private CountDownTimer timer = new CountDownTimer(120000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            setad.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            setad.setEnabled(true);
            setad.setText("获取验证码");
        }
    };

    public void init() {
        b_return.setOnClickListener(listener);
        regiest_ing.setOnClickListener(listener);

        Ruser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Ruser_c.setVisibility(View.VISIBLE);
                } else {
                    Ruser_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Ruser_c.setVisibility(View.VISIBLE);
                } else {
                    Ruser_c.setVisibility(View.GONE);
                }
            }
        });
        Remail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Remail_c.setVisibility(View.VISIBLE);
                } else {
                    Remail_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Remail_c.setVisibility(View.VISIBLE);
                } else {
                    Remail_c.setVisibility(View.GONE);
                }
            }
        });
        Rpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Rpwd_c.setVisibility(View.VISIBLE);
                } else {
                    Rpwd_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Rpwd_c.setVisibility(View.VISIBLE);
                } else {
                    Rpwd_c.setVisibility(View.GONE);
                }
            }
        });

        Rrpwds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Rrpwds_c.setVisibility(View.VISIBLE);
                } else {
                    Rrpwds_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Rrpwds_c.setVisibility(View.VISIBLE);
                } else {
                    Rrpwds_c.setVisibility(View.GONE);
                }
            }
        });


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.regiest_ing://注册按钮
                    if (Ruser.getText().toString().trim().equals("") ) {
                        Toast.show("手机号不能为空...");
                    } else if (!FileUtil.isNumeric(Ruser.getText().toString().trim())) {
                        Toast.show("手机号格式不正确...");
                    } else if ( Ruser.getText().length() != 11) {
                        Toast.show("手机号格式不正确...");
                    } else if (Remail.getText().toString().trim().equals("") ) {
                        Toast.show("绑定邮箱不能为空...");
                    }else if (!FileUtil.isEmail(Remail.getText().toString().trim())) {
                        Toast.show("绑定邮箱格式不正确...");
                    }  else if (Rpwd.getText().length() < 6 ) {
                        Toast.show("密码长度至少6位...");
                    } else if (!Rpwd.getText().toString().trim().equals(Rrpwds.getText().toString().trim()) ) {
                        Toast.show("两次密码输入不一致...");
                    }
//                    else if (phone.getText().toString().trim().equals("")) {
//                        Toast.show("验证码不能为空...");
//                    }
                    else {
                        pd.show();
                        GsonUtil regiestRquerst = new GsonUtil(URLconstant.URLINSER + URLconstant.REVISEUSER, RequestMethod.GET);
                        regiestRquerst.add("token", MD5.MD5s(Ruser.getText() + new Build().MODEL));
                        regiestRquerst.add("KeyNo", Ruser.getText().toString());
                        regiestRquerst.add("deviceId", new Build().MODEL);
                        regiestRquerst.add("openType", 0);//0为注册 1为修改
//                        regiestRquerst.add("code", phone.getText().toString());//验证码
                        regiestRquerst.add("email", Remail.getText().toString());//绑定邮箱
                        regiestRquerst.add("password", Rrpwds.getText().toString());
                        CallServer.getInstance().add(RegisterActivity.this, regiestRquerst, MyhttpCallBack.getInstance(), 0x998, true, false, true);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
