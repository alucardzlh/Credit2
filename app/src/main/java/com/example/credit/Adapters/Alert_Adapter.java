package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

/**
 * Created by alucard on 2016-05-30.
 */
public class Alert_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.AlertInfo.DataBean.LicenseExpires> list1;//证照到期
    List<DataManager.AlertInfo.DataBean.LicenseExpiredBean> list2;//证照过期
    List<DataManager.AlertInfo.DataBean.OrderCorrection> list3;//责令改正
    List<DataManager.AlertInfo.DataBean.OweLoan> list4;//欠贷信息
    List<DataManager.AlertInfo.DataBean.OweTax> list5;//欠税信息
    List<DataManager.AlertInfo.DataBean.OweSalary> list6;//欠薪信息
    String str;

    public Alert_Adapter(Context context) {
        this.context = context;
    }

    public void setData( List<DataManager.AlertInfo.DataBean.LicenseExpires> list1,
                         List<DataManager.AlertInfo.DataBean.LicenseExpiredBean> list2,
                         List<DataManager.AlertInfo.DataBean.OrderCorrection> list3,
                         List<DataManager.AlertInfo.DataBean.OweLoan> list4,
                         List<DataManager.AlertInfo.DataBean.OweTax> list5,
                         List<DataManager.AlertInfo.DataBean.OweSalary> list6,
                         String str) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;
        this.str = str;
    }

    @Override
    public int getCount() {
        if (list1 != null) {
            return list1.size();
        } else if (list2 != null) {
            return list2.size();
        }else if (list3 != null) {
            return list3.size();
        }else if (list4 != null) {
            return list4.size();
        }else if (list5 != null) {
            return list5.size();
        }else if (list6 != null) {
            return list6.size();
        } else {
            return 0;
        }
    }


    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_item, null);
            vh.loan_1= (TextView) convertView.findViewById(R.id.loan_1);
            vh.loan_2= (TextView) convertView.findViewById(R.id.loan_2);
            vh.loan_3= (TextView) convertView.findViewById(R.id.loan_3);
            vh.loan_4= (TextView) convertView.findViewById(R.id.loan_4);
            vh.loan_5= (TextView) convertView.findViewById(R.id.loan_5);
            vh.loan_6= (TextView) convertView.findViewById(R.id.loan_6);
            vh.loan_7= (TextView) convertView.findViewById(R.id.loan_7);
            vh.loan_8= (TextView) convertView.findViewById(R.id.loan_8);
            vh.loan_9= (TextView) convertView.findViewById(R.id.loan_9);
            vh.loan_10= (TextView) convertView.findViewById(R.id.loan_10);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        switch (str){
            case "证照到期":
                vh.loan_1.setText(list1.get(position).ORGAN);
                vh.loan_2.setText(list1.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list1.get(position).WARNSTATUS);
                vh.loan_4.setText(list1.get(position).WARNDATE);
                vh.loan_5.setText(list1.get(position).WARNCONTENT);
                if(list1.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list1.get(position).SOURCENAME);
                vh.loan_8.setText(list1.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list1.get(position).BUSINESSATT);
                if(list1.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list1.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list1.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list1.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list1.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list1.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "证照过期":
                vh.loan_1.setText(list2.get(position).ORGAN);
                vh.loan_2.setText(list2.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list2.get(position).WARNSTATUS);
                vh.loan_4.setText(list2.get(position).WARNDATE);
                vh.loan_5.setText(list2.get(position).WARNCONTENT);
                if(list2.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list2.get(position).SOURCENAME);
                vh.loan_8.setText(list2.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list2.get(position).BUSINESSATT);
                if(list2.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list2.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list2.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list2.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list2.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list2.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "责令改正":
                vh.loan_1.setText(list3.get(position).ORGAN+"");
                vh.loan_2.setText(list3.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list3.get(position).WARNSTATUS+"");
                vh.loan_4.setText(list3.get(position).WARNDATE+"");
                vh.loan_5.setText(list3.get(position).WARNCONTENT+"");
                if(list3.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list3.get(position).SOURCENAME);
                vh.loan_8.setText(list3.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list3.get(position).BUSINESSATT);
                if(list3.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list3.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list3.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list3.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list3.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list3.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠贷信息":
                vh.loan_1.setText(list4.get(position).ORGAN);
                vh.loan_2.setText(list4.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list4.get(position).WARNSTATUS);
                vh.loan_4.setText(list4.get(position).WARNDATE);
                vh.loan_5.setText(list4.get(position).WARNCONTENT);
                if(list4.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list4.get(position).SOURCENAME);
                vh.loan_8.setText(list4.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list4.get(position).BUSINESSATT);
                if(list4.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list4.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list4.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list4.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list4.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list4.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠税信息":
                vh.loan_1.setText(list5.get(position).ORGAN);
                vh.loan_2.setText(list5.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list5.get(position).WARNSTATUS);
                vh.loan_4.setText(list5.get(position).WARNDATE);
                vh.loan_5.setText(list5.get(position).WARNCONTENT);
                if(list5.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list5.get(position).SOURCENAME);
                vh.loan_8.setText(list5.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list5.get(position).BUSINESSATT);
                if(list5.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list5.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list5.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list5.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list5.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list5.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠薪信息":
                vh.loan_1.setText(list6.get(position).ORGAN);
                vh.loan_2.setText(list6.get(position).WARNAMOUNT + " 万元");
                vh.loan_3.setText(list6.get(position).WARNSTATUS);
                vh.loan_4.setText(list6.get(position).WARNDATE);
                vh.loan_5.setText(list6.get(position).WARNCONTENT);
                if (list6.get(position).STATE.equals("0")) {
                    vh.loan_6.setText("有效");
                } else {
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list6.get(position).SOURCENAME);
                vh.loan_8.setText(list6.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list6.get(position).BUSINESSATT);
                if (list6.get(position).DATATYPE.equals("2")) {
                    vh.loan_10.setText("欠贷信息");
                } else if (list6.get(position).DATATYPE.equals("4")) {
                    vh.loan_10.setText("欠税信息");
                } else if (list6.get(position).DATATYPE.equals("5")) {
                    vh.loan_10.setText("欠薪信息");
                } else if (list6.get(position).DATATYPE.equals("7")) {
                    vh.loan_10.setText("责令改正");
                } else if (list6.get(position).DATATYPE.equals("8")) {
                    vh.loan_10.setText("证照到期");
                } else if (list6.get(position).DATATYPE.equals("9")) {
                    vh.loan_10.setText("证照过期");
                }
                break;
        }
        return convertView;
    }

    public class ViewHolder {
        TextView loan_1;
        TextView loan_2;
        TextView loan_3;
        TextView loan_4;
        TextView loan_5;
        TextView loan_6;
        TextView loan_7;
        TextView loan_8;
        TextView loan_9;
        TextView loan_10;
    }
}