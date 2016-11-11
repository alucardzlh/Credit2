package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter41 extends BaseAdapter {
	private Context mContext;
	private String[] arrays1;
	LinearLayout.LayoutParams layoutParam;

	public MyGridAdapter41(Context context, String[] array1 ) {
		super();
		this.mContext = context;
		arrays1=array1;
	}
	public void setmargin(LinearLayout.LayoutParams layoutParam){
		this.layoutParam=layoutParam;
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
					R.layout.grid_item4, parent, false);

		}
		if(arrays1.length%2!=0){
			if((arrays1.length-1)<position){
				return convertView;
			}
		}
		if(layoutParam!=null){
			TextView ttemp=BaseViewHolder.get(convertView,R.id.tbv);
			ttemp.setTextSize(13);
		}

		TextView tv1 = BaseViewHolder.get(convertView, R.id.tbv);
		switch (arrays1[position]){
			case "证照到期":
				if(DataManager.AlertInfoS.data.licenseExpires !=null && DataManager.AlertInfoS.data.licenseExpires.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.licenseExpires.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.licenseExpires.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}

				break;
			case "证照过期":
				if(DataManager.AlertInfoS.data.licenseExpired !=null && DataManager.AlertInfoS.data.licenseExpired.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.licenseExpired.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.licenseExpired.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}
				break;
			case "责令改正":
				if(DataManager.AlertInfoS.data.orderCorrection !=null && DataManager.AlertInfoS.data.orderCorrection.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.orderCorrection.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.orderCorrection.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}
				break;
			case "欠贷信息":
				if(DataManager.AlertInfoS.data.oweLoan !=null && DataManager.AlertInfoS.data.oweLoan.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.oweLoan.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.oweLoan.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}
				break;
			case "欠税信息":
				if(DataManager.AlertInfoS.data.oweTax !=null && DataManager.AlertInfoS.data.oweTax.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.oweTax.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.oweTax.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}
				break;
			case "欠薪信息":
				if(DataManager.AlertInfoS.data.oweSalary !=null && DataManager.AlertInfoS.data.oweSalary.size()>0){
					for(int i=0;i<DataManager.AlertInfoS.data.oweSalary.size();i++){
						tv1.setText(arrays1[position]+"("+ DataManager.AlertInfoS.data.oweSalary.size()+")");
					}
				}else{
					tv1.setText(arrays1[position]+"(0)");
				}
				break;
		}
		return convertView;
	}

}
