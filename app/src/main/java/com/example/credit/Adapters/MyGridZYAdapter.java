package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

/**
 * 主要
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridZYAdapter extends BaseAdapter {
	private Context mContext;
	private String[] arrays1;
	private String[] arrays2;

	public MyGridZYAdapter(Context context, String[] array1, String[] array2 ) {
		super();
		this.mContext = context;
		arrays1=array1;
		arrays2=array2;
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
		TextView tv1s = BaseViewHolder.get(convertView, R.id.tvzy_item1_s);
		if(arrays2 !=null && arrays2.length>0 ){
			tv1.setText(arrays1[position]+"：");
			tv1s.setText(arrays2[position]);
		}else{
			tv1.setPadding(0,20,0,0);
			tv1.setText(arrays1[position]+"");
			tv1s.setVisibility(View.GONE);
		}
//		RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) lla.getLayoutParams();
//		linearParams.height = 50;
//		lla.setLayoutParams(linearParams);
		return convertView;

	}

}
