package com.example.credit.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Activitys.UserSetActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.WheelView;
import com.yolanda.nohttp.RequestMethod;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

/**
 * 企业定位
 */
public class StartMapActivity extends Activity implements LocationSource, AMapLocationListener {
    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    double lng,lat,nlng,nlat;
    String city;
    Button walkbtn,drivbtn,busbtn;
    public static android.os.Handler handler;
    CheckBox maptyp1e;
    List<String> list;
    List<Double> list1,list2;
    List<Double> list1t,list2t;
    boolean flag=false;
    LinearLayout Textbtn;
    TextView Text1,Text2;
    MarkerOptions markerOption;
    UiSettings mUiSettings;
    String km="";
    String time="";
    int index;
    boolean TrFlag=false;
    RadioGroup.OnCheckedChangeListener radioButtonListener;
    List<String> listtx=new ArrayList<>();//路线途径点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_map);
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.map);

        maptyp1e = (CheckBox) findViewById(R.id.maptyp1e);//交通路况
        Textbtn = (LinearLayout) findViewById(R.id.Textbtn);//详情
        Text1 = (TextView) findViewById(R.id.Text1);//
        Text2 = (TextView) findViewById(R.id.Text2);//
        Text1.setOnClickListener(listener);
        Textbtn.setOnClickListener(listener);
        maptyp1e.setOnClickListener(listener);
        //在activity执行onCreate时执行mapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        String [] init=DataManager.getMapList.geocodes.get(0).location.split(",");
        lng=Double.parseDouble(init[0]);
        lat=Double.parseDouble(init[1]);

        if (aMap == null) {
            aMap = mapView.getMap();
            LatLng latLng = new LatLng(lat,lng);
            markerOption = new MarkerOptions();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setCompassEnabled(true);//显示指南针
            mUiSettings.setScaleControlsEnabled(true);//显示比例尺
//            //设置缩放级别
            aMap.moveCamera(CameraUpdateFactory.zoomTo(18));


            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }

        RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup);
        radioButtonListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                GsonUtil MyClaimRuerst=null;
                switch (checkedId){
                    case R.id.moren:
                        try{ list1t.clear(); }catch (NullPointerException e){}
                        aMap.clear();
                        flag=false;
                        isFirstLoc = true;
                        break;
                    case R.id.walkbtn:
                        MyClaimRuerst = new GsonUtil(URLconstant.MAPWALKING + "&origin="+nlng+","+nlat+"&destination="+lng+","+lat, RequestMethod.GET);
                        CallServer.getInstance().add(StartMapActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x1141, true, false, true);
                        break;
                    case R.id.drivbtn:
                        MyClaimRuerst = new GsonUtil(URLconstant.MAPDRIVING + "&origin="+nlng+","+nlat+"&destination="+lng+","+lat, RequestMethod.GET);
                        CallServer.getInstance().add(StartMapActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x1142, true, false, true);
                        break;
                    case R.id.busbtn:
                        try{ list1t.clear(); }catch (NullPointerException e){}
                        try{
                            if(DataManager.getBusList.route.transits.size()>0 && DataManager.getBusList.route.transits != null){

                                List<String> listx=new ArrayList<>();
                                for(int a=0;a<DataManager.getBusList.route.transits.size();a++) {
                                    for (DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.BusBean.BuslinesBean bus : DataManager.getBusList.route.transits.get(a).segments.get(0).bus.buslines) {
                                        listx.add(bus.name + "");
                                    }
                                }
                                String[] arr = listx.toArray(new String[listx.size()]);
                                showSelect("乘车路线选择",arr);
                            }else{
                                if(DataManager.getMapList.geocodes.get(0).citycode.equals(city)){
                                    MyClaimRuerst = new GsonUtil(URLconstant.MAPBUS + "&origin="+nlng+","+nlat+"&destination="+lng+","+lat+"&city="+city, RequestMethod.GET);
                                    CallServer.getInstance().add(StartMapActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x1143, true, false, true);
                                }else{
                                    com.example.credit.Utils.Toast.show("您与该企业不在同一城市，乘车无法直达，请选择其他路线!");
                                }
                            }
                        }catch (Exception e){
                            if(DataManager.getMapList.geocodes.get(0).citycode.equals(city)){
                                MyClaimRuerst = new GsonUtil(URLconstant.MAPBUS + "&origin="+nlng+","+nlat+"&destination="+lng+","+lat+"&city="+city, RequestMethod.GET);
                                CallServer.getInstance().add(StartMapActivity.this, MyClaimRuerst, MyhttpCallBack.getInstance(), 0x1143, true, false, true);
                            }else{
                                com.example.credit.Utils.Toast.show("您与该企业不在同一城市，乘车无法直达，请选择其他路线!");
                            }
                        }
                        break;
                }
            }
        };
        group.setOnCheckedChangeListener(radioButtonListener);


        //开始定位
        location();
        handler=new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                flag=true;
                Textbtn.setVisibility(View.VISIBLE);
                LatLng latLng;
                List<LatLng> latLngs;
//                aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
                switch (msg.what){
                    case 0://步行
                        try{ list1t.clear(); }catch (NullPointerException e){}
                        aMap.clear();
                        Text1.setText("步行路线");
                        if(DataManager.getwalkingList.route.paths.get(0).distance.length()>3){
                            km=Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).distance)/1000+"";
                            if(km.indexOf(".") == -1){
                                km=Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).distance)/1000+" 千米/km";
                            }else{
                                km=km.substring(0, km.indexOf(".")+2) +" 千米/km";
                            }
                        }else{
                            km=Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).distance) +" 米/m";
                        }
                        if((Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).duration)/60)/60 > 0){
                            time=(Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).duration)/60)/60+"";
                            if(time.indexOf(".") == -1){
                                time=(Double.parseDouble(DataManager.getwalkingList.route.paths.get(0).duration)/60)/60+"时";
                            }else{
                                time=time.substring(0, time.indexOf(".")+2) +"时";
                            }
                        }else{
                            time=Integer.parseInt(DataManager.getwalkingList.route.paths.get(0).distance)/60+"分";
                        }
                        Text2.setText("全程 "+km+" 预计历时约 "+time);

                        //获取坐标
                        list=new ArrayList<>();//路况
                        list1=new ArrayList<>();//经度坐标
                        list2=new ArrayList<>();//纬度坐标
                        for(DataManager.getwalking.RouteBean.PathsBean.StepsBean wa:DataManager.getwalkingList.route.paths.get(0).steps){
                            list.add(wa.instruction);
                            String [] str=wa.polyline.split(";");
                            for(int i=0;i<str.length;i++){
                                String [] ss=str[i].split(",");
                                list1.add(Double.parseDouble(ss[0]));
                                list2.add(Double.parseDouble(ss[1]));
                            }
                        }
                        //绘制线
                        latLngs = new ArrayList<LatLng>();
                        latLngs.add(new LatLng(nlat,nlng));
                        for(int t=0;t<list1.size();t++){
                            latLngs.add(new LatLng(list2.get(t),list1.get(t)));
                        }
                        latLngs.add(new LatLng(lat,lng));
                        aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(getResources().getColor(R.color.red)));
                        break;
                    case 1://驾车
                        try{ list1t.clear(); }catch (NullPointerException e){}
                        aMap.clear();
                        Text1.setText("驾车路线");
                        if(DataManager.getDrivingList.route.paths.get(0).distance.length()>3){
                            km=Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).distance)/1000+"";
                            if(km.indexOf(".") == -1){
                                km=Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).distance)/1000+" 千米/km";
                            }else{
                                km=km.substring(0, km.indexOf(".")+2) +" 千米/km";
                            }
                        }else{
                            km=Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).distance)+" 米/m";
                        }
                        if((Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).duration)/60)/60 >= 1){
                            time=(Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).duration)/60)/60+"";
                            if(time.indexOf(".") == -1){
                                time=(Double.parseDouble(DataManager.getDrivingList.route.paths.get(0).duration)/60)/60+"时";
                            }else{
                                time=time.substring(0, time.indexOf(".")+2) +"时";
                            }
                        }else{
                            time=Integer.parseInt(DataManager.getDrivingList.route.paths.get(0).distance)/60+"分";
                        }

                        Text2.setText("全程 "+km+"  预计历时约 "+time);
//
                        //获取坐标
                        list=new ArrayList<>();//路况
                        list1=new ArrayList<>();//经度坐标
                        list2=new ArrayList<>();//纬度坐标
                        for(DataManager.getDriving.RouteBean.PathsBean.StepsBean wa:DataManager.getDrivingList.route.paths.get(0).steps){
                            list.add(wa.instruction);
                            String [] str=wa.polyline.split(";");
                            for(int i=0;i<str.length;i++){
                                String [] ss=str[i].split(",");
                                list1.add(Double.parseDouble(ss[0]));
                                list2.add(Double.parseDouble(ss[1]));
                            }
                        }
                        //绘制线
                        latLngs = new ArrayList<LatLng>();
                        latLngs.add(new LatLng(nlat,nlng));
                        for(int t=0;t<list1.size();t++){
                            latLngs.add(new LatLng(list2.get(t),list1.get(t)));
                        }
                        latLngs.add(new LatLng(lat,lng));
                        aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(getResources().getColor(R.color.blue2)));
                        break;
                    case 2://乘车
                        aMap.clear();
                        List<String> listx=new ArrayList<>();
                        for(int a=0;a<DataManager.getBusList.route.transits.size();a++) {
                            for (DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.BusBean.BuslinesBean bus : DataManager.getBusList.route.transits.get(a).segments.get(0).bus.buslines) {
                                listx.add(bus.name + "");
                            }
                        }
                        String[] arr = listx.toArray(new String[listx.size()]);
                        showSelect("乘车路线选择",arr);
                        break;
                }
            }
        };
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Textbtn://详情
                    int size = list.size();
                    String[] arr = list.toArray(new String[size]);
                    showLK(Text1.getText().toString(),arr);
                    break;
                case R.id.Text1://路线选择
                    List<String> listx=new ArrayList<>();
                    for(int a=0;a<DataManager.getBusList.route.transits.size();a++) {
                        for (DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.BusBean.BuslinesBean bus : DataManager.getBusList.route.transits.get(a).segments.get(0).bus.buslines) {
                            listx.add(bus.name + "");
                        }
                    }
                    String[] arr1 = listx.toArray(new String[listx.size()]);
                    showSelect("乘车路线选择",arr1);
                    break;
                case R.id.maptyp1e:
                    if(TrFlag==false){
                        aMap.setTrafficEnabled(true);//显示实时路况图层
                        TrFlag=true;
                    }else{
                        aMap.setTrafficEnabled(false);//关闭实时路况图层
                        TrFlag=false;
                    }
                    break;
            }
        }
    };
    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
//            if (aMapLocation.getErrorCode() == 0) {
//                企业位置经纬度
            //GPS定位自己
            nlng=aMapLocation.getLongitude();
            nlat=aMapLocation.getLatitude();


            //定位成功回调信息，设置相关消息
            aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
            if(!flag){
                aMapLocation.setLatitude(lat);//获取纬度
                aMapLocation.setLongitude(lng);//获取经度
                MyLocationStyle locationStyle = new MyLocationStyle();
                locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding));
                locationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
                locationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
                locationStyle.strokeWidth(0);
                aMap.setMyLocationStyle(locationStyle);
            }else{
                //绘制起点
                LatLng x = new LatLng(nlat,nlng);//第一个参数是：latitude，第二个参数是longitude
                //添加标记
                markerOption.position(x);
                markerOption.title("我在这");
                markerOption.snippet("我在这");
                markerOption.perspective(true);
                markerOption.draggable(true);
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.start));//设置图标
                aMap.addMarker(markerOption);
                //绘制终点
                LatLng x1 = new LatLng(lat,lng);//第一个参数是：latitude，第二个参数是longitude
                //添加标记
                markerOption.position(x1);
                markerOption.title("目的地");
                markerOption.snippet("目的地");
                markerOption.perspective(true);
                markerOption.draggable(true);
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.end));//设置图标
                aMap.addMarker(markerOption);

                if(list1t !=null && list1t.size()>0){
                    for(int y=0;y<list1t.size();y++){
                        LatLng x12 = new LatLng(list2t.get(y),list1t.get(y));//第一个参数是：latitude，第二个参数是longitude
                        //添加标记
                        markerOption.position(x12);
                        markerOption.title("目的地");
                        markerOption.snippet("目的地");
                        markerOption.perspective(true);
                        markerOption.draggable(true);
                        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.way));//设置图标
                        aMap.addMarker(markerOption);
                    }

                }
            }
            aMapLocation.getAccuracy();//获取精度信息
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(aMapLocation.getTime());
            df.format(date);//定位时间
            aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
            aMapLocation.getCountry();//国家信息
            aMapLocation.getProvince();//省信息
            aMapLocation.getCity();//城市信息
            aMapLocation.getDistrict();//城区信息
            aMapLocation.getStreet();//街道信息
            aMapLocation.getStreetNum();//街道门牌号信息
            city=aMapLocation.getCityCode();//城市编码
            aMapLocation.getAdCode();//地区编码
            // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
            if (isFirstLoc) {
                //将地图移动到定位点
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                //点击定位按钮 能够将地图的中心移动到定位点
                mListener.onLocationChanged(aMapLocation);

                //添加图钉
//                      aMap.addMarker(getMarkerOptions(amapLocation));
                //获取定位信息
                StringBuffer buffer = new StringBuffer();
                buffer.append(aMapLocation.getCountry() + ""
                        + aMapLocation.getProvince() + ""
                        + aMapLocation.getCity() + ""
                        + aMapLocation.getProvince() + ""
                        + aMapLocation.getDistrict() + ""
                        + aMapLocation.getStreet() + ""
                        + aMapLocation.getStreetNum());
//                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();GPS定位
                Toast.makeText(getApplicationContext(),  DataManager.getMapList.geocodes.get(0).formatted_address+"", Toast.LENGTH_LONG).show();//企业经纬度定位
                isFirstLoc = false;
            }


//            } else {
//
//                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                Log.e("AmapError", "location Error, ErrCode:"
//                        + aMapLocation.getErrorCode() + ", errInfo:"
//                        + aMapLocation.getErrorInfo());
//                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
//            }
        }
    }

    /**
     * 路线详情查看器
     */
    public void showLK(final String title, final String[] con){
        View outerView = LayoutInflater.from(StartMapActivity.this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);
        wv.setItems(Arrays.asList(con));
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
//                Toast.show("[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
//                str=item;
            }
        });

        new AlertDialog.Builder(StartMapActivity.this)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }
    /**
     * 路线选择器
     */
    public void showSelect(final String title, final String[] con){
        View outerView = LayoutInflater.from(StartMapActivity.this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setItems(Arrays.asList(con));
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
//                Toast.show("[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                index=selectedIndex-1;
//                com.example.credit.Utils.Toast.show(selectedIndex+"");
            }
        });

        new AlertDialog.Builder(StartMapActivity.this)
                .setTitle(title)
                .setView(outerView)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aMap.clear();
                        try{ list1t.clear(); }catch (NullPointerException e){}
                        Text1.setText("乘车路线");
                        //获取坐标
                        list=new ArrayList<>();//路况
                        list1=new ArrayList<>();//经度坐标
                        list2=new ArrayList<>();//纬度坐标
                        for(int a=0;a<DataManager.getBusList.route.transits.get(index).segments.size();a++){
                            if(DataManager.getBusList.route.transits.get(index).segments.get(a).walking.steps.size()>0 && DataManager.getBusList.route.transits.get(index).segments.get(a).walking.steps != null){
                                for(DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.WalkingBean.StepsBean wa:DataManager.getBusList.route.transits.get(index).segments.get(a).walking.steps){
                                    String [] str=wa.polyline.split(";");
                                    for(int i=0;i<str.length;i++){
                                        String [] ss=str[i].split(",");
                                        list1.add(Double.parseDouble(ss[0]));
                                        list2.add(Double.parseDouble(ss[1]));
                                    }
                                    list.add(wa.instruction);
                                }
                                for(DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.BusBean.BuslinesBean bus:DataManager.getBusList.route.transits.get(index).segments.get(a).bus.buslines){
                                    String [] str=bus.polyline.split(";");
                                    for(int i=0;i<str.length;i++){
                                        String [] ss=str[i].split(",");
                                        list1.add(Double.parseDouble(ss[0]));
                                        list2.add(Double.parseDouble(ss[1]));
                                    }
                                    if(bus.via_stops.size()>0 && bus.via_stops!=null) {
                                        list.add(bus.departure_stop.name);
                                        listtx.add(bus.departure_stop.location);
                                        for(int t=0;t<bus.via_stops.size();t++){
                                            listtx.add(bus.via_stops.get(t).location);
                                            list.add(bus.via_stops.get(t).name);
                                        }
                                        list.add(bus.arrival_stop.name);
                                        listtx.add(bus.arrival_stop.location);
                                    }else{
                                        list.add(bus.departure_stop.name);
                                        list.add(bus.arrival_stop.name);
                                        listtx.add(bus.departure_stop.location);
                                        listtx.add(bus.arrival_stop.location);
                                    }
                                }

                            } else {
                                for(DataManager.getBus.RouteBean.TransitsBean.SegmentsBean.BusBean.BuslinesBean bus:DataManager.getBusList.route.transits.get(index).segments.get(a).bus.buslines){
                                    String [] str=bus.polyline.split(";");
                                    for(int i=0;i<str.length;i++){
                                        String [] ss=str[i].split(",");
                                        list1.add(Double.parseDouble(ss[0]));
                                        list2.add(Double.parseDouble(ss[1]));
                                    }
                                    if(bus.via_stops.size()>0 && bus.via_stops!=null) {
                                        listtx.add(bus.departure_stop.location);
                                        list.add(bus.departure_stop.name);
                                        for(int t=0;t<bus.via_stops.size();t++){
                                            listtx.add(bus.via_stops.get(t).location);
                                            list.add(bus.via_stops.get(t).name);
                                        }
                                        list.add(bus.arrival_stop.name);
                                        listtx.add(bus.arrival_stop.location);
                                    }else{
                                        list.add(bus.departure_stop.name);
                                        list.add(bus.arrival_stop.name);
                                        listtx.add(bus.departure_stop.location);
                                        listtx.add(bus.arrival_stop.location);
                                    }
                                }
                            }
                        }
                        //绘制线
                        List<LatLng> latLngs = new ArrayList<LatLng>();
                        latLngs.add(new LatLng(nlat,nlng));
                        for(int t=0;t<list1.size();t++){
                            latLngs.add(new LatLng(list2.get(t),list1.get(t)));
                        }
                        latLngs.add(new LatLng(lat,lng));
                        aMap.addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(getResources().getColor(R.color.red)));

                        //绘制途经点
                        list1t=new ArrayList<>();
                        list2t=new ArrayList<>();
                        for(int tl=0;tl<listtx.size();tl++){
                            String [] str=listtx.get(tl).split(";");
                            for(int i=0;i<str.length;i++){
                                String [] ss=str[i].split(",");
                                list1t.add(Double.parseDouble(ss[0]));
                                list2t.add(Double.parseDouble(ss[1]));
                            }
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }
}
