package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 出资
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridZZAdapter extends BaseAdapter {
	private Context mContext;
	private String[] arrays1;
	private String[] arrays2;
	private String[] arrays3;
	private String[] arrays4;
	private String[] arrays5;

	public MyGridZZAdapter(Context context, String[] array1, String[] array2 ,String[] array3,String[] array4,String[] array5) {
		super();
		this.mContext = context;
		arrays1=array1;
		arrays2=array2;
		arrays3=array3;
		arrays4=array4;
		arrays5=array5;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(arrays1.length%2==0){
			return arrays1.length;
		}else{
			return (2-arrays1.length%2)+arrays1.length;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item_zy, parent, false);
		}

		if(arrays1.length%2!=0){
			if((arrays1.length-1)<position){
				return convertView;
			}
		}
		LinearLayout lla = BaseViewHolder.get(convertView, R.id.lla);

		TextView tv1 = BaseViewHolder.get(convertView, R.id.tvzy_item1);
		TextView tv2 = BaseViewHolder.get(convertView, R.id.tvzy_item2);
		TextView tv3 = BaseViewHolder.get(convertView, R.id.tvzy_item3);
		TextView tv4 = BaseViewHolder.get(convertView, R.id.tvzy_item4);
		TextView tv5 = BaseViewHolder.get(convertView, R.id.tvzy_item5);

		TextView tv1s = BaseViewHolder.get(convertView, R.id.tvzy_item1_s);
		TextView tv2s= BaseViewHolder.get(convertView, R.id.tvzy_item2_s);
		TextView tv3s = BaseViewHolder.get(convertView, R.id.tvzy_item3_s);
		TextView tv4s = BaseViewHolder.get(convertView, R.id.tvzy_item4_s);
//		<!--以下为外商投资合伙企业 特殊字段 -->

		LinearLayout das = BaseViewHolder.get(convertView, R.id.das);
		TextView tv6 = BaseViewHolder.get(convertView, R.id.tvzy_item6);
		TextView tv7 = BaseViewHolder.get(convertView, R.id.tvzy_item7);
		TextView tv8 = BaseViewHolder.get(convertView, R.id.tvzy_item8);
		TextView tv6s = BaseViewHolder.get(convertView, R.id.tvzy_item6_s);
		TextView tv7s = BaseViewHolder.get(convertView, R.id.tvzy_item7_s);
		TextView tv8s = BaseViewHolder.get(convertView, R.id.tvzy_item8_s);
		/**
		 * 个人独资企业——企业类型4540
		 */
		if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,4).indexOf("4540") != -1){
			tv1.setText("名称： ");
			tv2.setText("出资方式：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
		}
		/**
		 * 合伙企业——企业类型453%
		 */
		else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,3).indexOf("453") != -1){
			tv1.setText("合伙人类型： ");
			tv2.setText("合伙人：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
		}

		/**
		 *  外资公司法人
		 */
		else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("51") != -1
				|| (DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("61") != -1){
			tv1.setText("股东类型： ");
			tv2.setText("股东：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
		}
		else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("52") != -1
				|| (DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("62") != -1){
			tv1.setText("发起人类型： ");
			tv2.setText("发起人：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
		}
		/**
		 *  外商投资合伙企业
		 */
		else if((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("54") != -1
				|| (DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,2).indexOf("64") != -1){
			tv1.setText("合伙人类型： ");
			tv2.setText("合伙人：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
			das.setVisibility(View.VISIBLE);
			tv6.setText("国家（地区）：");
			tv7.setText("住所：");
			tv8.setText("承担责任方式：");
			tv6s.setText(""+DataManager.gsxx.data.partnersInfo.get(position).COUNTRY_CN);
			tv7s.setText(""+DataManager.gsxx.data.partnersInfo.get(position).DOM);
			tv8s.setText(""+DataManager.gsxx.data.partnersInfo.get(position).RESPFORM_CN);
		}
		/**
		 *  内资公司法人——企业类型1%
		 */
		else if(((DataManager.QJiugongGList.data.baseInfo.get(0).ENTTYPE).substring(0,1).indexOf("1") != -1)){
			tv1.setText("股东类型： ");
			tv2.setText("股东：");
			tv1s.setText(""+arrays1[position]);
			tv2s.setText(""+arrays2[position]);
		}
		try{
			if(arrays3.length>0){
				tv3.setVisibility(View.VISIBLE);
				tv4.setVisibility(View.VISIBLE);
				tv5.setVisibility(View.VISIBLE);
				tv3s.setVisibility(View.VISIBLE);
				tv4s.setVisibility(View.VISIBLE);
				tv3.setText("证照/证件类型：");
				tv4.setText("证照/证件号码：");
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d1 = sdf.parse(DataManager.gsxx.data.baseInfo.ESTDATE);
					Date d2 = sdf.parse("2014-2-28");
					//比较
					int sizws1=0;//记录股权变更的个数
					int sizws2=0;//记录股权时间小于2014-2-28的个数
					if(d1.getTime() <= d2.getTime()) {
						if(DataManager.gsxx.data.changeRecordsInfo.size()>0 && DataManager.gsxx.data.changeRecordsInfo !=null) {
							for (int i = 0; i < DataManager.gsxx.data.changeRecordsInfo.size(); i++) {
								if (DataManager.gsxx.data.changeRecordsInfo.get(i).ALTITEM_CN.indexOf("股权") != -1) {
									sizws1++;
									Date d3 = sdf.parse(DataManager.gsxx.data.changeRecordsInfo.get(i).ALTDATE);
									Date d4 = sdf.parse("2014-2-28");
									//比较
									if (d3.getTime() <= d4.getTime()) {
										sizws2++;
									}
								}
							}
							if(sizws2==sizws1 || sizws1==0){
								tv5.setText("点击查看详情");
							}
						}else{
							tv5.setText("点击查看详情");
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

				tv3s.setText(""+arrays3[position]);
				tv4s.setText("******");
				RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) lla.getLayoutParams();
				linearParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				lla.setLayoutParams(linearParams);
			}
		}catch (NullPointerException e){
			RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) lla.getLayoutParams();
			linearParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			lla.setLayoutParams(linearParams);
		}

		return convertView;

	}

}
