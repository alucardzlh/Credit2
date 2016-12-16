package com.example.credit.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.example.credit.Activitys.MainActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Views.FileUtil;

public class NetUtils {


	// 判断网络连接状态  ys版
	public static boolean isConnectingToInternet(Context context){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null){
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
						return true;
		}
		return false;
	}

	// 判断网络连接状态  hhn版
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	// 判断wifi状态
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	// 判断移动网络
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	// 获取连接类型
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 获取最新版本
	 * @param context
	 * @return
     */
	public static boolean getAppVison(Context context){
		try {
			if (DataManager.MyNewAppS.message != null && DataManager.MyNewAppS.message.equals("success")) {
				if (DataManager.MyNewAppS.data.VersionInfo != null & DataManager.MyNewAppS.data.VersionInfo.size() > 0) {
					for (int i = 0; i < DataManager.MyNewAppS.data.VersionInfo.size(); i++) {
						if (DataManager.MyNewAppS.data.VersionInfo.get(i).TYPE.equals("1")) {
							if (DataManager.MyNewAppS.data.VersionInfo.get(i).VERSION != null & DataManager.MyNewAppS.data.VersionInfo.get(i).PATH != null) {
								double in = Double.parseDouble(DataManager.MyNewAppS.data.VersionInfo.get(i).VERSION);//最新版本号
								double isn = Double.parseDouble(FileUtil.getVersionName(context));//当前版本号
								if (isn < in) {
									//dialog.show();
									getdialog(context).show();
									return true;
								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
     private static AlertDialog getdialog(final Context context){
		 AlertDialog.Builder builder;
		  AlertDialog dialog;
		 builder = new AlertDialog.Builder(context);
		 builder.setTitle("最新版本");
		 builder.setMessage("当前版本不可使用，是否更新最新版本?");
		 builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
			 @Override
			 public void onClick(DialogInterface dialog, int which) {
				 Uri uri = Uri.parse(DataManager.MyNewAppS.data.VersionInfo.get(0).PATH);
				 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				 context.startActivity(intent);
				 ((Activity)context).finish();
			 }
		 });
		 builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
			 @Override
			 public void onClick(DialogInterface dialog, int which) {
				 ((Activity)context).finish();
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
		 return dialog;
	 }


}
