package com.example.credit.Activitys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.MyGridAdapterClaim;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.base64Util;
import com.example.credit.Views.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Decoder.BASE64Decoder;

/**
 * 认领详情界面
 */
public class ClaimDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.cm_name)
    TextView cm_name;//企业名称
    @ViewInject(R.id.cm_time)
    TextView cm_time;//认领时间
    @ViewInject(R.id.cm_state)
    TextView cm_state;//认领状态
    @ViewInject(R.id.cm_emall)
    TextView cm_emall;//邮箱地址
    @ViewInject(R.id.cm_phone)
    TextView cm_phone;//电话号码
    @ViewInject(R.id.cm_details)
    TextView cm_details;//描述详情
    @ViewInject(R.id.myGridViewtcDD)
    MyGridView myGridViewtcDD;//描述详情

    String [] imgS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_details);
        ViewUtils.inject(this);
        Intent i=getIntent();
        init();
    }
    public void init(){
        b_topname.setText("认领详情");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cm_name.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).ENTNAME);

        cm_time.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).CLAIMTIME);

        cm_state.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).STATUSNAME);

        cm_emall.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).EMAIL);

        cm_phone.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).TEL);

        cm_details.setText(DataManager.NewClaimDilsList.data.claimInfo.get(0).REMARK);

        imgS=new String[DataManager.NewClaimDilsList.data.claimInfo.get(0).attachmentInfo.size()];
        for(int i=0;i<DataManager.NewClaimDilsList.data.claimInfo.get(0).attachmentInfo.size();i++){
            String base64String=DataManager.NewClaimDilsList.data.claimInfo.get(0).attachmentInfo.get(i).ATTACHMENT_PATH;
            //=================
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(base64String);
                String imgpath =Environment.getExternalStorageDirectory() + "/Credit/cache" + "/pag"+i+".jpg";
                //把字节数组的图片写到另一个地方
                File apple = new File(imgpath);
                FileOutputStream fos = new FileOutputStream(apple);

                fos.write(b);
                fos.flush();
                fos.close();
                //==============
                File file = new File(imgpath);
                if (file.exists()) {//获取本地图片路径是否存在
//                    Bitmap bm = BitmapFactory.decodeFile(imgpath);
//                    Drawable drawable = new BitmapDrawable(null, bm);
                    imgS[i]=imgpath;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MyGridAdapterClaim adapters = new MyGridAdapterClaim(ClaimDetailsActivity.this, imgS);
        myGridViewtcDD.setAdapter(adapters);
        MyClaimActivity.wd.dismiss();
        myGridViewtcDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


}
