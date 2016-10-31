package com.example.credit.Activitys;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.Apk_updata_dialog;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.WheelView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.RequestMethod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.example.credit.Views.FileUtil.decodeBitmap;

/**
 * 用户设置界面
 */
public class UserSetActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.user_set_submit)
    Button user_set_submit;//提交按钮
    @ViewInject(R.id.us1)
    RelativeLayout us1;
    @ViewInject(R.id.us_headimg)
    ImageView us_headimg;
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记
    String pic;

    @ViewInject(R.id.us2)
    RelativeLayout us2;
    @ViewInject(R.id.us_name)
    TextView us_name;

    @ViewInject(R.id.us3)
    RelativeLayout us3;
    @ViewInject(R.id.us_sex)
    TextView us_sex;
    String sexs="";

    @ViewInject(R.id.us4)
    RelativeLayout us4;
    @ViewInject(R.id.us_emils)
    TextView us_emils;

    @ViewInject(R.id.us5)
    RelativeLayout us5;
    @ViewInject(R.id.us_hangye)
    TextView us_hangye;

    @ViewInject(R.id.us6)
    RelativeLayout us6;
    @ViewInject(R.id.us_xueli)
    TextView us_xueli;

    @ViewInject(R.id.us7)
    RelativeLayout us7;
    @ViewInject(R.id.us_phone)
    TextView us_phone;

    @ViewInject(R.id.us8)
    RelativeLayout us8;
    CreditSharePreferences csf;
    public static Handler handler;
    WaitDialog wd;
    Intent i;
    CreditSharePreferences csp;
    int type;
    String txt;
    int sa;

    String str=null;
    EditText et;
    String cc="";

    String[] dwName,dwNameID;//行业
    String[] dwName1,dwNameID1;//学历
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set);
        ViewUtils.inject(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        wd=new WaitDialog(this);
        csf=CreditSharePreferences.getLifeSharedPreferences();
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                wd.dismiss();
                switch (msg.what){
                    case 1:
                        Toast.show("修改信息成功！");
                        MainActivity.loginImg(csp.getICONSTEAM());
                        finish();
                        break;
                    case 2:
                        Toast.show("修改信息失败！");
                        break;
                }
            }
        };
    }

    public void init(){

        b_topname.setText("用户设置");
        b_return.setOnClickListener(listener);

        user_set_submit.setOnClickListener(listener);//修改按钮

        /**
         * 获取用户头像
         */
        if(!csp.getICONSTEAM().equals("")){
            File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
            if (file.exists()) {//获取本地图片路径是否存在
                us_headimg.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg",100,100));
//            Picasso.with(UserSetActivity.this).load(file).into(us_headimg);
            }
        }
        us1.setOnClickListener(listener);
        /**
         * 获取用户昵称
         */
        us_name.setText(csf.getALIASNAME());
        us2.setOnClickListener(listener);
        /**
         * 获取用户性别
         */
        if(csf.getSEX().equals("0")){
            us_sex.setText("女");
        }else{
            us_sex.setText("男");
        }
        us3.setOnClickListener(listener);
        /**
         * 获取用户邮箱
         */
        us_emils.setText(csf.getEMAIL());
        us4.setOnClickListener(listener);
        /**
         * 获取用户行业
         */
        String e=csf.getINDUSTRY();
        us_hangye.setText(csf.getINDUSTRY());
        us5.setOnClickListener(listener);
        /**
         * 获取用户学历
         */
        String e2=csf.getEDUCATION();
        us_xueli.setText(csf.getEDUCATION());
        us6.setOnClickListener(listener);
        /**
         * 获取用户手机
         */
        us_phone.setText(csf.getMOBILE());
        us7.setOnClickListener(listener);
        /**
         * 修改密码
         */
        us8.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.user_set_submit://提交按钮
                    wd.show();
                    GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.REVISEUSER, RequestMethod.POST);
                    MyClaimRuerst.add("deviceId", (new Build()).MODEL);
                    MyClaimRuerst.add("token", MD5.MD5s(csf.getID() + (new Build()).MODEL));
                    MyClaimRuerst.add("KeyNo", csf.getID());

                    if(!us_emils.getText().toString().equals(csf.getEMAIL())) {
                        MyClaimRuerst.add("email", us_emils.getText().toString());//邮箱
                        csp.putEMAIL(us_emils.getText().toString());
                    }

                    if(!us_name.getText().toString().equals(csf.getALIASNAME())) {
                        MyClaimRuerst.add("aliasname", us_name.getText().toString());//别名
                        csp.putALIASNAME(us_name.getText().toString());
                    }

                    if(!us_sex.getText().toString().equals(csf.getSEX())) {
                        if(us_sex.getText().toString().equals("男")){
                            sexs="1";
                        }else{
                            sexs="0";
                        }
                        MyClaimRuerst.add("sex", sexs);//性别
                        csp.putSEX(sexs);
                    }

                    if(!us_hangye.getText().toString().equals(csf.getINDUSTRY())){
                        for(int i=0;i<dwName.length;i++){
                            if(us_hangye.getText().toString().equals(dwName[i])){
                                MyClaimRuerst.add("industryId",dwNameID[i]);//行业
                                csp.putINDUSTRY(us_hangye.getText().toString());
                                break;
                            }
                        }
                    }

                    if(!us_xueli.getText().toString().equals(csf.getEDUCATION())){
                        for(int i=0;i<dwName1.length;i++){
                            if(us_xueli.getText().toString().equals(dwName1[i])){
                                MyClaimRuerst.add("educationId",dwNameID1[i]);//学历
                                csp.putEDUCATION(us_xueli.getText().toString());
                                break;
                            }
                        }
                    }
                    try{
                        if(!pic.equals("")){
                            if(!pic.equals(csf.getICONSTEAM())) {
                                MyClaimRuerst.add("headicon", pic);//头像base64位图
                                csp.putICONSTEAM(pic);
                            }
                        }
                    }catch (NullPointerException e){}

                    if(!us_phone.getText().toString().equals(csf.getMOBILE())) {
                        MyClaimRuerst.add("tel",us_phone.getText().toString());//移动电话
                        csp.putMOBILE(us_phone.getText().toString());
                    }
                    MyClaimRuerst.add("openType", "1");//0：注册  1：修改(必须)

                    CallServer.getInstance().add(UserSetActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x401, true, false, true);
                    break;
                case R.id.b_return:
                    finish();
                    break;
                case R.id.us1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(UserSetActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            ActivityCompat.requestPermissions(UserSetActivity.this, new
                                            String[]{Manifest.permission.CAMERA},
                                    1);
                        }else{
                            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                            // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            startActivityForResult(pickIntent, REQUESTCODE_PICK);
                        }
                        return;

                    }else{
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    }
                    break;
                case R.id.us2:
                    showinit(us_name.getText().toString(),"昵称",us_name);
                    break;
                case R.id.us3:
                    showSelect(us_sex.getText().toString(),"性别",getResources().getStringArray(R.array.sexMar),us_sex);
                    break;
                case R.id.us4:
                    showinit(us_emils.getText().toString(),"邮箱",us_emils);
                    break;
                case R.id.us5:
                    int size = DataManager.disRoomMarList.data.dictionarieInfo.size();
                    dwName = new String[size];
                    dwNameID = new String[size];
                    for(int i=0;i<size;i++){
                        dwName[i]=DataManager.disRoomMarList.data.dictionarieInfo.get(i).NAME;
                        dwNameID[i]=DataManager.disRoomMarList.data.dictionarieInfo.get(i).ZD_ID;
                    }

                    showSelect(us_hangye.getText().toString(),"行业",dwName,us_hangye);
                    break;
                case R.id.us6:
                    int size1 = DataManager.disRoomMarList1.data.dictionarieInfo.size();
                    dwName1 = new String[size1];
                    dwNameID1 = new String[size1];
                    for(int j=0;j<size1;j++){
                        dwName1[j]=DataManager.disRoomMarList1.data.dictionarieInfo.get(j).NAME;
                        dwNameID1[j]=DataManager.disRoomMarList1.data.dictionarieInfo.get(j).ZD_ID;
                    }

                    showSelect(us_xueli.getText().toString(),"学历",dwName1,us_xueli);
                    break;
                case R.id.us7:
                    showinit(us_phone.getText().toString(),"手机号码",us_phone);
                    break;
                case R.id.us8:
                    i =new Intent(UserSetActivity.this,PassWordActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            us_headimg.setImageBitmap(photo);
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
                photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.close();
                byte[] buffer = baos.toByteArray();
                //将图片的字节流数据加密成base64字符输出
                pic= Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上下滑动选择器
     */
    public void showSelect(String state, final String title, final String[] con, final TextView tv){
        View outerView = LayoutInflater.from(UserSetActivity.this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);
        wv.setItems(Arrays.asList(con));
        for(int i=0;i<Arrays.asList(con).size();i++){
            if(!state.equals("")){
                if(Arrays.asList(con).get(i).equals(state)){
                    wv.setSeletion(i);
                }
            }else{
                wv.setSeletion(0);
            }
        }
        str=state;
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
//                Toast.show("[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                str=item;
            }
        });

        new AlertDialog.Builder(UserSetActivity.this)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!str.equals("")){
                            tv.setText(str+"");
                        }else{
                            tv.setText(con[0]+"");
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .show();
    }

    /**
     * 修改文本 dialog
     */
    public void showinit(String state, final String title, final TextView tv) {
        final String mr=state;//默认保存
        et = new EditText(UserSetActivity.this);
        et.setGravity(Gravity.CENTER);
        et.setLines(1);
        et.setMaxEms(1);
        et.setMaxLines(1);
        switch (title){
            case "姓名":
                et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                break;
        }
        et.setText(state);
        final TextView ttv = new TextView(UserSetActivity.this);
        new AlertDialog.Builder(UserSetActivity.this)
                .setTitle(title)
                .setView(et)
                .setMessage(ttv.getText().toString())
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (title) {
                            case "邮箱":
                                if (FileUtil.isEmail(et.getText().toString())) {
                                    tv.setText(et.getText().toString());
                                } else {
                                    Toast.show("邮箱格式不正确!!!");
                                }
                                break;
                            case "手机号码":
                                if (!FileUtil.isNumeric(et.getText().toString())) {
                                    Toast.show("手机格式不正确,只能输入纯数字!!!");
                                } else if (et.getText().length() != 11 ) {
                                    Toast.show("手机格式不正确,号码位数不等于11位!!!");
                                } else {
                                    tv.setText(et.getText().toString());
                                }
                                break;
                            default:
                                if(!et.getText().toString().trim().equals("")){
                                    tv.setText(et.getText().toString());
                                }else{
                                    Toast.show(title+"输入不能为空!!!");
                                    tv.setText(mr);
                                }

                                break;
                        }

                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode==1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){     //拒绝 =0
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUESTCODE_PICK);
            } else {
                Toast.show("权限获取失败!");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
