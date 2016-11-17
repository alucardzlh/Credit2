package com.example.credit.Activitys;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Adapters.MyGridAdaptermMain;
import com.example.credit.Adapters.NewClaimListAdapter;
import com.example.credit.Adapters.NewsListAdapter;
import com.example.credit.Dialogs.Apk_updata_dialog;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Map.StartMapActivity;
import com.example.credit.QRCode_app.CaptureActivity;
import com.example.credit.QRCode_util.Util;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.NetUtils;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Utils.base64Util;
import com.example.credit.Views.FileUtil;
import com.example.credit.Views.ImageCycleView;
import com.example.credit.Views.MyGridView;
import com.example.credit.Views.MyListView;
import com.example.credit.Views.MyScrollView;
import com.example.credit.Views.RoundImageView;
import com.example.credit.Views.SlidingMenu;
import com.igexin.sdk.PushManager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Decoder.BASE64Decoder;

import static com.example.credit.R.id.top_search;
import static com.example.credit.Views.FileUtil.decodeBitmap;
import static com.example.credit.Views.FileUtil.deleteDir;

//SwipeRefreshLayout.OnRefreshListener

/**
 * 主界面
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private final int NOHTTP_CITY = 0x021;//获取城市
    private final int NOHTTP_INDUSTRY = 0x023;//获取行业
    private long exitTime = 0;
    private SlidingMenu mLeftMenu;
    private int searchLayoutTop;//顶部位置
    @ViewInject(R.id.search_top1)
    RelativeLayout search_top1;//搜索
    @ViewInject(R.id.search_top2)
    LinearLayout search_top2;//顶部悬浮
    MyScrollView myScrollView;//自定义ScrollView

    @ViewInject(R.id.ewm)
    ImageView ewm;//二维码扫描


    @ViewInject(R.id.tab1)
    LinearLayout tab1;//企业查询
    @ViewInject(R.id.tab2)
    LinearLayout tab2;//著作权查询
    @ViewInject(R.id.tab3)
    LinearLayout tab3;//违法查询
    @ViewInject(R.id.tab4)
    LinearLayout tab4;//股东查询
    RelativeLayout topSearch;
    public static Handler handler;

    @ViewInject(R.id.headimg)
    RoundImageView headimg;//我的头像
    @ViewInject(R.id.UserSz)
    public static TextView UserSz;//用户名

    @ViewInject(R.id.set)
    ImageView set;//设置

    @ViewInject(R.id.togg)
    ImageView togg;//左上角侧滑按钮
    @ViewInject(R.id.Smenu_1)
    RelativeLayout Smenu_1;//我的评价
    @ViewInject(R.id.Smenu_2)
    RelativeLayout Smenu_2;//我的投诉
    @ViewInject(R.id.Smenu_3)
    RelativeLayout Smenu_3;//我的关注
    @ViewInject(R.id.Smenu_4)
    RelativeLayout Smenu_4;//我的认领
    @ViewInject(R.id.Smenu_5)
    RelativeLayout Smenu_5;//服务协议
    @ViewInject(R.id.Smenu_6)
    RelativeLayout Smenu_6;//关于我们
    @ViewInject(R.id.login)
    Button login;//登录

    @ViewInject(R.id.btmore)
    Button btmore;//加载更多

    @ViewInject(R.id.hot_1)
    TextView hot_1;//热点1
    @ViewInject(R.id.hot_2)
    TextView hot_2;//热点2
    @ViewInject(R.id.hot_huan)
    TextView hot_huan;//热点换


    @ViewInject(R.id.scmain)
    ScrollView scmain;
    @ViewInject(R.id.news_list)
    MyListView NewsListview;
    @ViewInject(R.id.NewClaimListview)
    MyListView NewClaimListview;

    @ViewInject(R.id.NewClaimTxT)
    TextView NewClaimTxT;//最新认领暂无数据
    @ViewInject(R.id.News_list_null)
    TextView News_list_null;//最新热点暂无数据

    @ViewInject(R.id.myGridViewMain)
    MyGridView myGridViewMain;

    @ViewInject(R.id.cliam_more)
    TextView cliam_more;//认领-查看更多
    @ViewInject(R.id.news_more)
    TextView news_more;//新闻-查看更多

    NewsListAdapter adapter;
    public static List<DataManager.MyHot.DataBean.SearchHistoryBean> MyHotsList = new ArrayList<>();//热搜

    public static List<DataManager.MyNews.DataBean.NewInformationBean> MyNewsList = new ArrayList<>();//初始新闻集合

    public static List<DataManager.NewClaimUtils.DataBean.ClaimInfoBean> MyCliamList = new ArrayList<>();//初始最新认领集合

    static CreditSharePreferences csp;
    Boolean LoginStatus;
    public static ProgressDialog pd;
    public static WaitDialog ad;
    private ImageCycleView mImageCycleView;
    Apk_updata_dialog apppd;
    int t = 2;
    int str = 1;
    AlertDialog.Builder builder;
    public static AlertDialog dialog;
    public int[] imgs1 = {R.mipmap.maincon_1, R.mipmap.maincon_2,
            R.mipmap.maincon_3, R.mipmap.maincon_4};
    public String[] txt = {"商标查询", "专利查询",
            "招投标", "失信人查询"};
    public static NewClaimListAdapter adapter1;

    int num1, num2;//热点随机数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileUtil.filenewsexists();
        Util.currentActivity = this;
        csp = CreditSharePreferences.getLifeSharedPreferences();
        LoginStatus = csp.getLoginStatus();
        ViewUtils.inject(this);

        try {
            if (csp.getWelcome().equals("")) {
                WelcomeActivity.wd.dismiss();
                csp.putWelcome("yes");
            }
        } catch (NullPointerException e) {
            if (null != WelcomeActivity.wd && WelcomeActivity.wd.equals(null)) {
                WelcomeActivity.wd.dismiss();
            }
            csp.putWelcome("yes");
        }
        ad = new WaitDialog(this);
        boolean falg = NetUtils.isConnectingToInternet(this);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mLeftMenu.setOnScrollStopListner(onScrollStop);
        PushManager.getInstance().initialize(this.getApplicationContext());
        PushManager.getInstance().getClientid(MainActivity.this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        btmore.setVisibility(View.VISIBLE);
                        int por = MyNewsList.size() - 1;
                        adapter = new NewsListAdapter(MainActivity.this, MyNewsList);
                        if (MyNewsList.size() == 0) {
                            News_list_null.setVisibility(View.VISIBLE);
                            NewsListview.setVisibility(View.GONE);
                        } else {
                            NewsListview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        if (str == 2) {
                            NewsListview.setSelection(por - 1);
                        }
                        NewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent i = new Intent(MainActivity.this, NewsContentActivity.class);
                                i.putExtra("id", MyNewsList.get(position).ID);
                                startActivity(i);
                            }
                        });
                        break;
                    case 1://我的评价
                        Intent i1 = new Intent(MainActivity.this, MyCommentlistActivity.class);
                        startActivity(i1);
                        break;
                    case 2://跳我的投诉
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        if (!cn.getClassName().equals(MycomplaintsListActivity.class.getName())) {
                            pd.dismiss();
                            startActivity(new Intent(MainActivity.this, MycomplaintsListActivity.class).putExtra("falgg", 1));
                        } else {
                            MycomplaintsListActivity.handler.sendEmptyMessage(2);
                        }
                        break;
                    case 5://跳我的关注909372219
                        Intent i3 = new Intent(MainActivity.this, MyconcernActivity.class);
                        startActivity(i3);
                        break;
                    case 6://跳我的认领
                        Intent i6 = new Intent(MainActivity.this, MyClaimActivity.class);
                        startActivity(i6);
                        break;
                    case 7:
                        try {
                            if (MyCliamList != null && MyCliamList.size() > 0) {
                                NewClaimTxT.setVisibility(View.GONE);
                                adapter1 = new NewClaimListAdapter(MainActivity.this, MyCliamList, 0);
                                NewClaimListview.setAdapter(adapter1);
                                adapter1.notifyDataSetChanged();
                                NewClaimListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        ad.show();
                                        String KeyNo = MyCliamList.get(position).PRIPID;//市场主体身份代码
                                        String token = MD5.MD5s(KeyNo + (new Build()).MODEL);
                                        GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
                                        requst.add("KeyNo", KeyNo);
                                        requst.add("token", token);
                                        requst.add("deviceId", (new Build()).MODEL);
                                        requst.add("memberId", csp.getID());
//                                requst.add("regnore", MyCliamList.get(position).REGNORE);
//                                requst.add("priptype", MyCliamList.get(position).ENTTYPE);
                                        CallServer.getInstance().add(MainActivity.this, requst, MyhttpCallBack.getInstance(), 0x026, true, false, true);
                                    }
                                });
                            } else {//没有数据
                                cliam_more.setVisibility(View.GONE);
                                btmore.setVisibility(View.GONE);
                                NewClaimTxT.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            cliam_more.setVisibility(View.GONE);
                            btmore.setVisibility(View.GONE);
                            NewClaimTxT.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 8://跳公司详情
                        ad.dismiss();
                        if (Main_NewCliam_MoreListActivity.falg == true) {
                            Main_NewCliam_MoreListActivity.ad.dismiss();
                            Main_NewCliam_MoreListActivity.falg = false;
                        }
                        Intent i = new Intent(MainActivity.this, CompanyDetailsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 9:
                        Intent i2 = new Intent(MainActivity.this, NewsContentActivity.class);
                        startActivity(i2);
                        break;
                    case 101:
                        com.example.credit.Utils.Toast.show("没有数据了!");
                        break;
                    case 10://刷新新闻
                        adapter.notifyDataSetChanged();
                        break;
                    case 11:
                        Intent i1w1 = new Intent(MainActivity.this, Main_NewCliam_MoreListActivity.class);
                        i1w1.putExtra("Tname", "最新认领");
                        startActivity(i1w1);
                        break;
                    case 12://二维码扫描 跳公司详情
                        /**
                         * 记录公司浏览量
                         */
                        GsonUtil request121 = new GsonUtil(URLconstant.URLINSER + URLconstant.SAVESUM, RequestMethod.GET);
                        request121.add("token", MD5.MD5s(DataManager.QJiugongGList.data.baseInfo.get(0).PRIPID + (new Build()).MODEL));
                        request121.add("deviceId", (new Build()).MODEL);
                        request121.add("KeyNo", DataManager.QJiugongGList.data.baseInfo.get(0).PRIPID);
                        if (csp.getLoginStatus()) {
                            request121.add("memberId", csp.getID());
                        }
                        request121.add("regnore", DataManager.QJiugongGList.data.baseInfo.get(0).REGNO);
                        request121.add("entname", DataManager.QJiugongGList.data.baseInfo.get(0).ENTNAME);
                        request121.add("enttype", DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE);
                        CallServer.getInstance().add(MainActivity.this, request121, MyhttpCallBack.getInstance(), 0x12138, true, false, true);
                        ad.dismiss();
                        Intent i23 = new Intent(MainActivity.this, CompanyDetailsActivity.class);
                        i23.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i23);
                        overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                        break;
                    case 13:
                        Intent ie = new Intent(MainActivity.this, Main_SearchActivity.class);
                        ie.putExtra("hit", "商标");
                        startActivity(ie);
                        break;
                    case 14:
                        Intent iw = new Intent(MainActivity.this, Main_SearchActivity.class);
                        iw.putExtra("hit", "专利");
                        startActivity(iw);
                        break;
                    case 15:
                        Intent ia1 = new Intent(MainActivity.this, Main_SearchActivity.class);
                        ia1.putExtra("hit", "失信人");
                        startActivity(ia1);
                        break;
                    default:
                        com.example.credit.Utils.Toast.show("数据正在赶来的路上...");
                        break;
                }

            }
        };
        if (falg == true) {
            initView();
            initData();
        } else {
            this.finish();
            System.exit(0);
        }

        try {
            FileUtil.imgscache();//缓存轮播图base64
        } catch (Exception e) {

        }

        NewsListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    btmore.setVisibility(View.VISIBLE);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        if (t <= Integer.parseInt(DataManager.MyNewsS.data.Paging.TotalPage)) {
                            GsonUtil NewsRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                            NewsRequest.setConnectTimeout(20000);
                            NewsRequest.setReadTimeout(20000);
                            NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                            NewsRequest.add("KeyNo", "");
                            NewsRequest.add("deviceId", (new Build()).MODEL);

                            NewsRequest.add("pageIndex", t);
                            NewsRequest.add("pageSize", 5);
                            CallServer.getInstance().add(MainActivity.this, NewsRequest, MyhttpCallBack.getInstance(), 0x1111, true, false, true);
                            t++;
                            str = 2;
                        } else {
                            btmore.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        try {
            if (DataManager.MyNewAppS.message != null && DataManager.MyNewAppS.message.equals("success")) {
                if (DataManager.MyNewAppS.data.VersionInfo != null & DataManager.MyNewAppS.data.VersionInfo.size() > 0) {
                    for (int i = 0; i < DataManager.MyNewAppS.data.VersionInfo.size(); i++) {
                        if (DataManager.MyNewAppS.data.VersionInfo.get(i).TYPE.equals("1")) {
                            if (DataManager.MyNewAppS.data.VersionInfo.get(i).VERSION != null & DataManager.MyNewAppS.data.VersionInfo.get(i).PATH != null) {
                                double in = Double.parseDouble(DataManager.MyNewAppS.data.VersionInfo.get(i).VERSION);//最新版本号
                                double isn = Double.parseDouble(FileUtil.getVersionName(MainActivity.this));//当前版本号
                                if (isn < in) {
//                                    dialog.show();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        /**
         * 轮播
         */
        mImageCycleView = (ImageCycleView) findViewById(R.id.icv_topView);
        List<ImageCycleView.ImageInfo> list = new ArrayList<ImageCycleView.ImageInfo>();
        try {
            if (WelcomeActivity.red) {
                if (DataManager.LBimgS.data.carouselInfo != null && DataManager.LBimgS.data.carouselInfo.size() > 0) {
                    //SD卡图片资源
                    for (int i = 0; i < DataManager.LBimgS.data.carouselInfo.size(); i++) {

                        list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(), "/Credit/cache/CarouselImg" + i + ".jpg"), "", ""));
//                list.add(new ImageCycleView.ImageInfo(base64Util.stringtoBitmap(DataManager.LBimgS.data.carouselInfo.get(i).PATH),"",""));
                    }
                } else {
                    //res图片资源
                    list.add(new ImageCycleView.ImageInfo(R.drawable.banner1, "", ""));
                    list.add(new ImageCycleView.ImageInfo(R.drawable.banner2, "", ""));
                }
            } else {
                list.add(new ImageCycleView.ImageInfo(R.drawable.banner1, "", ""));
                list.add(new ImageCycleView.ImageInfo(R.drawable.banner2, "", ""));
            }
        } catch (NullPointerException e) {
            list.add(new ImageCycleView.ImageInfo(R.drawable.banner1, "", ""));
            list.add(new ImageCycleView.ImageInfo(R.drawable.banner2, "", ""));
        }

        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

//                //本地图片
//                ImageView imageView=new ImageView(MainActivity.this);
//                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//                return imageView;
//                //使用SD卡图片
//                SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//                smartImageView.setImageURI(Uri.fromFile((File)imageInfo.image));
//                return smartImageView;
//				//使用SmartImageView，既可以使用网络图片也可以使用本地资源
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//				return smartImageView;
//                使用BitmapUtils,只能使用网络图片
                BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this);
                ImageView imageView = new ImageView(MainActivity.this);
                bitmapUtils.display(imageView, imageInfo.image.toString());
                return imageView;
            }
        });
    }

    private void initData() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("最新版本");
        builder.setMessage("当前版本不可使用，是否更新最新版本?");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse(DataManager.MyNewAppS.data.VersionInfo.get(0).PATH);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener(){
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode== KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        };
        dialog.setOnKeyListener(keylistener);
        dialog.setCancelable(false);

        MyGridAdaptermMain adapters = new MyGridAdaptermMain(MainActivity.this, imgs1, txt);
        myGridViewMain.setAdapter(adapters);
        myGridViewMain.setSelector(new ColorDrawable(Color.TRANSPARENT));
        myGridViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GsonUtil Request = null;
                switch (position) {
                    case 0://商标查询
                        Request = new GsonUtil(URLconstant.URLINSER + URLconstant.HOTSPOT, RequestMethod.GET);//最新热点
                        Request.add("token", MD5.MD5s("" + new Build().MODEL));
                        Request.add("KeyNo", "");
                        Request.add("deviceId", (new Build()).MODEL);
                        Request.add("logType", 21);
                        CallServer.getInstance().add(MainActivity.this, Request, MyhttpCallBack.getInstance(), 0x211, true, false, true);
                        break;
                    case 1://专利查询
                        Request = new GsonUtil(URLconstant.URLINSER + URLconstant.HOTSPOT, RequestMethod.GET);//最新热点
                        Request.add("token", MD5.MD5s("" + new Build().MODEL));
                        Request.add("KeyNo", "");
                        Request.add("deviceId", (new Build()).MODEL);
                        Request.add("logType", 22);
                        CallServer.getInstance().add(MainActivity.this, Request, MyhttpCallBack.getInstance(), 0x212, true, false, true);

                        break;
                    case 2://招投标
                        startActivity(new Intent(MainActivity.this, Main_SearchActivity.class).putExtra("hit", "招投标"));
                        break;
                    case 3://失信
                        Request = new GsonUtil(URLconstant.URLINSER + URLconstant.HOTSPOT, RequestMethod.GET);//最新热点
                        Request.add("token", MD5.MD5s("" + new Build().MODEL));
                        Request.add("KeyNo", "");
                        Request.add("deviceId", (new Build()).MODEL);
                        Request.add("logType", 26);
                        CallServer.getInstance().add(MainActivity.this, Request, MyhttpCallBack.getInstance(), 0x213, true, false, true);
                        break;
                }
            }
        });
/**
 * 最新新闻
 */
        if (MyNewsList != null && MyNewsList.size() > 0) {
            handler.sendEmptyMessage(0);
        } else {//没有数据
            news_more.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
            News_list_null.setVisibility(View.VISIBLE);
        }
/**
 * 最新热点
 */
        if (MyHotsList.size() > 1 && !MyHotsList.get(0).KEYWORDS.equals("") && !MyHotsList.get(1).KEYWORDS.equals("")) {
            hot_1.setText(MyHotsList.get(0).KEYWORDS);
            hot_2.setText(MyHotsList.get(1).KEYWORDS);
        }
/**
 * 最新认领 开始只调用一次
 */
        try {
            if (MyCliamList != null && MyCliamList.size() > 0) {
                handler.sendEmptyMessage(7);
            } else {//没有数据
                cliam_more.setVisibility(View.GONE);
                btmore.setVisibility(View.GONE);
                NewClaimTxT.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            cliam_more.setVisibility(View.GONE);
            btmore.setVisibility(View.GONE);
            NewClaimTxT.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        topSearch = (RelativeLayout) findViewById(top_search);
        topSearch.setOnClickListener(this);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);
        ewm.setOnClickListener(listener);
        headimg.setOnClickListener(listener);
        UserSz.setOnClickListener(listener);
        Smenu_1.setOnClickListener(listener);
        Smenu_2.setOnClickListener(listener);
        Smenu_3.setOnClickListener(listener);
        Smenu_4.setOnClickListener(listener);
        Smenu_5.setOnClickListener(listener);
        Smenu_6.setOnClickListener(listener);
        isLogin();
        hot_1.setOnClickListener(listener);
        hot_2.setOnClickListener(listener);
        hot_huan.setOnClickListener(listener);
        login.setOnClickListener(listener);
        tab1.setOnClickListener(listener);
        tab2.setOnClickListener(listener);
        tab3.setOnClickListener(listener);
        tab4.setOnClickListener(listener);
        btmore.setOnClickListener(listener);
        set.setOnClickListener(listener);
        cliam_more.setOnClickListener(listener);
        myScrollView = (MyScrollView) findViewById(R.id.myScrollView1);
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            /**
             * 监听滚动Y值变化，通过addView和removeView来实现悬停效果
             * @param scrollY
             */
            @Override
            public void onScroll(int scrollY) {
                if (scrollY >= searchLayoutTop) {
                    if (topSearch.getParent() != search_top2) {
                        search_top1.removeView(topSearch);
                        search_top2.setVisibility(View.VISIBLE);
                        search_top2.addView(topSearch);
                    }
                } else {
                    if (topSearch.getParent() != search_top1) {
                        search_top2.removeView(topSearch);
                        search_top2.setVisibility(View.GONE);
                        search_top1.addView(topSearch);
                    }
                }
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Smenu_1://我的评价
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.MMOMM, RequestMethod.GET);
                        request14.add("deviceId", (new Build()).MODEL);
                        request14.add("token", MD5.MD5s("" + (new Build()).MODEL));
                        request14.add("KeyNo", "");
                        request14.add("memberId", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, request14, MyhttpCallBack.getInstance(), 0x206, true, false, true);
                    }
                    break;
                case R.id.Smenu_2://我的投诉
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        pd.show();
                        getComplaint(MainActivity.this);
                    }
                    break;
                case R.id.Smenu_3://我的关注
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyconcernRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYFAVORITE, RequestMethod.GET);
                        MyconcernRuerst.add("deviceId", (new Build()).MODEL);
                        MyconcernRuerst.add("token", MD5.MD5s("" + (new Build()).MODEL));
                        MyconcernRuerst.add("KeyNo", "");
                        MyconcernRuerst.add("memberId", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyconcernRuerst, MyhttpCallBack.getInstance(), 0x103, true, false, true);
                    }
                    break;
                case R.id.Smenu_4://我的认领
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        ad.show();
                        GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);
                        MyClaimRuerst.add("deviceId", (new Build()).MODEL);
                        MyClaimRuerst.add("token", MD5.MD5s("" + (new Build()).MODEL));
                        MyClaimRuerst.add("KeyNo", "");
                        MyClaimRuerst.add("memberId", csp.getID());
                        CallServer.getInstance().add(MainActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x303, true, false, true);
                    }
                    break;
                case R.id.Smenu_5://服务协议
                    Intent in12 = new Intent(MainActivity.this, AgreementActivity.class);
                    startActivity(in12);
                    break;
                case R.id.Smenu_6://关于我们
                    Intent in13 = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(in13);
                    break;
                case R.id.login://登录
                    if (!LoginStatus) {//如果当前状态未登录  点登录的跳转
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    } else {//如果当前状态已登录  点击退出登录的操作
                        csp.putUser(null);
                        com.example.credit.Utils.Toast.show("退出登录");
                        csp.putLoginStatus(false);
                        isLogin();
                        UserSz.setText("游客");//用户名
                    }
                    break;
                case R.id.tab1://企业查询
                    Intent in1 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in1.putExtra("type", 0);
                    in1.putExtra("Setname", "");
                    startActivity(in1);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab2://法人查询
                    Intent in2 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in2.putExtra("type", 1);
                    startActivity(in2);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab3://品牌查询
                    Intent in3 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in3.putExtra("type", 2);
                    startActivity(in3);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.tab4://失信信息
                    Intent in4 = new Intent(MainActivity.this, SearchFirmActivty.class);
                    in4.putExtra("type", 3);
                    startActivity(in4);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;

                case R.id.set://个人信息设置
                    if (!csp.getLoginStatus()) {//判定是否登录
                        com.example.credit.Utils.Toast.show("请先登录账号");
                    } else {
                        GsonUtil ComplaintsRuerst1 = new GsonUtil(URLconstant.URLINSER + URLconstant.DISROOM, RequestMethod.GET);
                        ComplaintsRuerst1.add("token", MD5.MD5s("" + (new Build()).MODEL));
                        ComplaintsRuerst1.add("KeyNo", "");
                        ComplaintsRuerst1.add("deviceId", new Build().MODEL);
                        ComplaintsRuerst1.add("pname", "行业");
                        CallServer.getInstance().add(MainActivity.this, ComplaintsRuerst1, MyhttpCallBack.getInstance(), 0x100001, true, false, true);

                        GsonUtil ComplaintsRuerst2 = new GsonUtil(URLconstant.URLINSER + URLconstant.DISROOM, RequestMethod.GET);
                        ComplaintsRuerst2.add("token", MD5.MD5s("" + (new Build()).MODEL));
                        ComplaintsRuerst2.add("KeyNo", "");
                        ComplaintsRuerst2.add("deviceId", new Build().MODEL);
                        ComplaintsRuerst2.add("pname", "学历");
                        CallServer.getInstance().add(MainActivity.this, ComplaintsRuerst2, MyhttpCallBack.getInstance(), 0x100002, true, false, true);

                        Intent is = new Intent(MainActivity.this, UserSetActivity.class);
                        startActivity(is);
                    }
                    break;
                case R.id.btmore://新闻加载更多
                    if (t <= Integer.parseInt(DataManager.MyNewsS.data.Paging.TotalPage)) {
                        GsonUtil NewsRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                        NewsRequest.setConnectTimeout(20000);
                        NewsRequest.setReadTimeout(20000);
                        NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        NewsRequest.add("KeyNo", "");
                        NewsRequest.add("deviceId", (new Build()).MODEL);

                        NewsRequest.add("pageIndex", t);
                        NewsRequest.add("pageSize", 5);
                        CallServer.getInstance().add(MainActivity.this, NewsRequest, MyhttpCallBack.getInstance(), 0x1111, true, false, true);
                        t++;
                        str = 2;
                    } else {
                        com.example.credit.Utils.Toast.show("没有更多数据了！");
                        btmore.setVisibility(View.GONE);
                    }
                    break;
                case R.id.cliam_more:
                    GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
                    NewClaimRequest.add("KeyNo", "");
                    NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                    NewClaimRequest.add("deviceId", new Build().MODEL);
                    NewClaimRequest.add("status", 1);
                    CallServer.getInstance().add(MainActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x1131, true, false, true);
                    break;
                case R.id.hot_huan:
                    if (MyHotsList.size() > 0) {
                        Random random = new Random();//随机数
                        while (true) {
                            num1 = random.nextInt(MyHotsList.size());
                            num2 = random.nextInt(MyHotsList.size());
                            if (num1 != num2) {
                                break;
                            }
                        }
                        hot_1.setText(MyHotsList.get(num1).KEYWORDS);
                        hot_2.setText(MyHotsList.get(num2).KEYWORDS);
                    } else {
                        com.example.credit.Utils.Toast.show("获取数据失败");
                    }
                    break;
                case R.id.hot_1:
                    Intent hot_1s = new Intent(MainActivity.this, SearchFirmActivty.class);
                    hot_1s.putExtra("Setname", hot_1.getText().toString());
                    hot_1s.putExtra("type", 0);
                    startActivity(hot_1s);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.hot_2:
                    Intent hot_2s = new Intent(MainActivity.this, SearchFirmActivty.class);
                    hot_2s.putExtra("Setname", hot_2.getText().toString());
                    hot_2s.putExtra("type", 0);
                    startActivity(hot_2s);
                    overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                    break;
                case R.id.ewm://二维码扫描
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            ActivityCompat.requestPermissions(MainActivity.this, new
                                            String[]{Manifest.permission.CAMERA},
                                    1);
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                        }
                        return;

                    } else {
                        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                    }

                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 获取我的投诉列表方法
     */
    public static void getComplaint(Activity activity) {
        GsonUtil ComplaintsRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCOMPLAIN, RequestMethod.GET);
        ComplaintsRuerst.add("token", MD5.MD5s("" + (new Build()).MODEL));//csp.getID()
        ComplaintsRuerst.add("KeyNo", "");
        ComplaintsRuerst.add("deviceId", new Build().MODEL);
        ComplaintsRuerst.add("memberId", csp.getID());
        ComplaintsRuerst.add("pageIndex", 1);
        ComplaintsRuerst.add("pageSize", 10);
        CallServer.getInstance().add(activity, ComplaintsRuerst, MyhttpCallBack.getInstance(), 0x997, true, false, true);
    }

    public void toggleMenu(View view) {
        mLeftMenu.toggle();
        settogg();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void settogg() {
        if (SlidingMenu.isOpen) {
            togg.setBackground(getResources().getDrawable(R.drawable.gang32));
        } else {
            togg.setBackground(getResources().getDrawable(R.drawable.gang3));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case top_search:
                Intent in = new Intent(this, SearchFirmActivty.class);
                in.putExtra("Setname", "");
                startActivity(in);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
                break;
            default:
                break;
        }
    }

    private void isLogin() {
        LoginStatus = csp.getLoginStatus();
        if (LoginStatus) {//若当前状态为登录
            UserSz.setText(csp.getALIASNAME());
            login.setText("退出登录");
            if (!csp.getICONSTEAM().equals("")) {
//                headimg.setImageBitmap(base64Util.stringtoBitmap(csp.getICONSTEAM()));
                try{
                    File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
                    if (file.exists()) {//获取本地图片路径是否存在
//                        if(csp.getICONSTEAM().substring(11,12).equals("p")){
//                            headimg.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.png", 100, 100));
//                        }else{
                            headimg.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg", 100, 100));
//                        }
//                        headimg.setImageBitmap(Environment.getExternalStorageDirectory()+ "/Credit/loginImg.png");
//                        headimg.setImageBitmap(base64Util.stringtoBitmap(csp.getICONSTEAM()));
                        // Picasso.with(MainActivity.this).load(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg", 80, 80)).into(headimg);
                    }
                }catch (Exception e){
                }
            }
        } else {//若当前状态未未登录
            login.setText("登录");
            UserSz.setText("游客");
            headimg.setImageResource(R.mipmap.me_icon02);
        }
    }

    public static void loginImg(String base64) {
        if (base64 != null) {
            try {
                BASE64Decoder decode = new BASE64Decoder();
                byte[] b = decode.decodeBuffer(base64);
                //把字节数组的图片写到另一个地方
                File apple = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
                FileOutputStream fos = new FileOutputStream(apple);
                fos.write(b);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onRestart() {
        isLogin();
        GsonUtil NewClaimRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
        NewClaimRequest.add("KeyNo", "");
        NewClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
        NewClaimRequest.add("deviceId", new Build().MODEL);
        NewClaimRequest.add("status", 1);
        CallServer.getInstance().add(MainActivity.this, NewClaimRequest, MyhttpCallBack.getInstance(), 0x1133, true, false, true);
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
        initData();
        try {
            if (DataManager.citysList.data.city.size() == 0 || DataManager.citysList.data.city == null) {
                GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCITY, RequestMethod.GET);
                CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_CITY, true, false, true);//获取城市
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            GsonUtil request = new GsonUtil(URLconstant.URLINSER + URLconstant.GETCITY, RequestMethod.GET);
            CallServer.getInstance().add(this, request, MyhttpCallBack.getInstance(), NOHTTP_CITY, true, false, true);//获取城市
        }

        CallServer.getInstance().add(this, new GsonUtil(URLconstant.URLINSER + URLconstant.GETINDUSTRY, RequestMethod.GET), MyhttpCallBack.getInstance(), NOHTTP_INDUSTRY, true, false, true);//获取行业

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                deleteDir(Environment.getExternalStorageDirectory() + "/Credit/cache");//正常退出时，清除缓存
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 横滑置顶监听器
     */
    SlidingMenu.OnScrollStopListner onScrollStop = new SlidingMenu.OnScrollStopListner() {
        public void onScrollToRightEdge() {
            settogg();
        }

        public void onScrollToMiddle() {
        }

        public void onScrollToLeftEdge() {
            settogg();
        }

        public void onScrollStoped() {
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            searchLayoutTop = togg.getBottom();//获取ImageView的顶部位置
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == 0) {// 从二维码照相机回主页
            if (resultCode == RESULT_OK) {

                Bundle bundle = intent.getExtras();
                // 显示扫描到的内容
                String str = bundle.getString("SCAN_RESULT");
                if (str.indexOf("KeyNo") != -1) {
                    TestShow(str);
                } else {
                    com.example.credit.Utils.Toast.show("此二维码不是公司二维码");
                }

            }
            if (resultCode == 300) {
                Bundle bundle = intent.getExtras();
                String str = bundle.getString("result");
                if (str.indexOf("KeyNo") != -1) {
                    TestShow(str);
                } else {
                    com.example.credit.Utils.Toast.show("此二维码不是公司二维码");
                }
            }
        }
    }

    //    http://m.qi315.cn:8282/zhirong.credith5/baseinfo/GoToIndex.do?KeyNo=141000011988038869&localType=1&provinceCode=41
    public void TestShow(String str) {
        String[] sr = str.split("\\?");
        String[] strA = sr[1].split("&");
        String token = "";
        String KeyNo = "";
        String provinceCode = "";
        for (int i = 0; i < strA.length; i++) {
            if ((strA[i]).indexOf("KeyNo") != -1) {
                String[] strB = strA[i].split("=");
                KeyNo = strB[1];
                token = MD5.MD5s(strB[1] + (new Build()).MODEL);
            }
            if ((strA[i]).indexOf("provinceCode") != -1) {
                String[] strB = strA[i].split("=");
                provinceCode = strB[1];
            }
        }
        GsonUtil requst = new GsonUtil(URLconstant.URLINSER + URLconstant.GETITEMNUM, RequestMethod.GET);
        requst.add("KeyNo", KeyNo);
        requst.add("token", token);
        requst.add("provinceCode", provinceCode);
        requst.add("deviceId", (new Build()).MODEL);
        if (csp.getLoginStatus()) {
            requst.add("memberId", csp.getID());//86D9D7F53FCA45DD93E2D83DFCA0CB42
        }
        CallServer.getInstance().add(MainActivity.this, requst, MyhttpCallBack.getInstance(), 0x027, true, false, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {     //拒绝 =0
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
            } else {
                com.example.credit.Utils.Toast.show("权限获取失败!");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
