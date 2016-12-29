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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.credit.Dialogs.WaitDialog;
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
import com.yolanda.nohttp.error.NetworkError;

import java.util.ArrayList;
import java.util.List;


public class WelcomeActivity extends BaseActivity {
    @ViewInject(R.id.welcom)
    ImageView iv;
    @ViewInject(R.id.pager)
    RelativeLayout pager;

    CreditSharePreferences esp;
    public static Handler handler;
    public static boolean red=true;

    //ViwePager
    private ViewPager viewPager;
    private Button btn;
    //导航页资源
    private int[] images = new int[]{
            R.mipmap.ydd1,
            R.mipmap.ydd2,
            R.mipmap.ydd3,
    };
    //圆点与圆点之间的边距
    private int left;
    //用来存放导航图片实例（保证唯一性，滑动的时候不重复创建）
    private List<ImageView> imageViews;
    //存放三个灰色圆点的线性布局
    private LinearLayout ll;
    //用来存放红色圆点和灰色圆点的相对布局
    private RelativeLayout rl;
    //红色圆点ImageView
    private ImageView red_Iv;
   public static WaitDialog wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewUtils.inject(this);
        CreditSharePreferences.init(this);
        wd=new WaitDialog(this);
        esp = CreditSharePreferences.getLifeSharedPreferences();
        try {
            if (esp.getWelcome().equals("")) {
                pager.setVisibility(View.VISIBLE);
                iv.setVisibility(View.GONE);
                showdy();
            } else {
                GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWAPP, RequestMethod.GET);//获取最新版本
                NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                NewClaimRequest.add("KeyNo", "");
                NewClaimRequest.add("dowmload", "true");
                NewClaimRequest.add("deviceId", (new Build()).MODEL);
                CallServer.getInstance().add(WelcomeActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x110, true, false, true);
            }
        } catch (NullPointerException e){
            pager.setVisibility(View.VISIBLE);
            iv.setVisibility(View.GONE);
            showdy();
        }




        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
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
    public void showdy(){
        initView();
        //初始化导航页面和灰色圆点
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(WelcomeActivity.this);
            iv.setImageResource(images[i]);
            imageViews.add(iv);
            //动态加载灰色圆点
            ImageView gray_Iv = new ImageView(this);
            gray_Iv.setImageResource(R.drawable.dian_sred);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(20,
                            20);
            //从第二个开始有边距
            if (i > 0) {
                layoutParams.leftMargin = 20;   //注意单位是px
            }
            gray_Iv.setLayoutParams(layoutParams);
            ll.addView(gray_Iv);
        }
        red_Iv = new ImageView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(20, 20);
        red_Iv.setImageResource(R.drawable.dian_red);
        red_Iv.setLayoutParams(layoutParams2);
        rl.addView(red_Iv);
        //任何一个组件都可以得到视图树
        red_Iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //视图完成绘制的时候调用
            @Override
            public void onGlobalLayout() {
                left = ll.getChildAt(1).getLeft() - ll.getChildAt(0).getLeft();
                System.out.println(left);
                //移除视图树的监听
                red_Iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        //为ViewPager添加适配器
        viewPager.setAdapter(new WelcomeActivity.MyAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //导航页被选择的时候调用
            @Override
            public void onPageSelected(int position) {
                if (position == images.length - 1) {
                    btn.setVisibility(View.VISIBLE);
                    rl.setVisibility(View.GONE);
                }else {
                    btn.setVisibility(View.GONE);
                    rl.setVisibility(View.VISIBLE);
                }
            }
            //导航页滑动的时候调用
            //positionOffset:滑动的百分比（[0,1}）
            @Override
            public void onPageScrolled(int position, float positionOffset, int arg2) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) red_Iv.getLayoutParams();
                layoutParams.leftMargin = (int) (left * positionOffset + position * left);
                red_Iv.setLayoutParams(layoutParams);
            }
            //导航页滑动的状态改变的时候调用
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        try {
            if (esp.getWelcome().equals("")) {
                if (WelcomeActivity.wd!=null&&WelcomeActivity.wd.isShowing()) {
                    WelcomeActivity.wd.dismiss();
                }
                esp.putWelcome("yes");
            }
        } catch (NullPointerException e) {
            if (null != WelcomeActivity.wd && WelcomeActivity.wd.equals(null)) {
                if (WelcomeActivity.wd!=null&&WelcomeActivity.wd.isShowing()) {
                    WelcomeActivity.wd.dismiss();
                }
            }
            esp.putWelcome("yes");
        }

    }
    //初始化组件
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageViews = new ArrayList<ImageView>();
        ll = (LinearLayout) findViewById(R.id.ll);
        rl = (RelativeLayout) findViewById(R.id.rl);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wd.show();
                GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWAPP, RequestMethod.GET);//获取最新版本
                NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                NewClaimRequest.add("KeyNo", "");
                NewClaimRequest.add("dowmload", "true");
                NewClaimRequest.add("deviceId", (new Build()).MODEL);
                CallServer.getInstance().add(WelcomeActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x110, true, false, true);

//                }
            }
        });
    }
    //PagerAdapter有四个方法
    class MyAdapter extends PagerAdapter {
        //返回导航页的个数
        @Override
        public int getCount() {
            return images.length;
        }
        //判断是否由对象生成
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        //加载页面
        //ViewGroup:父控件指ViewPager
        //position:当前子控件在父控件中的位置
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(iv);
            return iv;
        }
        //移除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}