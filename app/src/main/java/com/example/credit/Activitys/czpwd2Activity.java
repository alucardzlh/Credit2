package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 找回密码----验证信息
 * @author yusi
 */
public class czpwd2Activity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.yz1)
    TextView yz1;
    @ViewInject(R.id.yz2)
    TextView yz2;
    @ViewInject(R.id.yz3)
    EditText yz3;
    @ViewInject(R.id.yzbut)
    Button yzbut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czpwd2);
        ViewUtils.inject(this);
        b_topname.setText("找回密码");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(czpwd2Activity.this,czpwd1Activity.class));
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        yz1.setText(DataManager.getczpwdList.data.get(0).USERNAME+"");
        yz2.setText(DataManager.getczpwdList.data.get(0).EMAIL+"");
        yzbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yz3.getText().toString().trim().equals(DataManager.getczpwdList.code)){
                    startActivity(new Intent(czpwd2Activity.this,czpwd3Activity.class));
                    czpwd2Activity.this.finish();
                }else{
                    Toast.show("邮箱验证码错误!");
                }
            }
        });
    }
}
