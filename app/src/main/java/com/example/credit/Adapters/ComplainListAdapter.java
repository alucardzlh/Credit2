package com.example.credit.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.Activitys.MycomplaintsListActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.yolanda.nohttp.RequestMethod;

import java.util.List;

/**
 * Created by alucard on 2016-07-01.
 * 我的投诉item适配器
 */
public class ComplainListAdapter extends BaseAdapter {
    private Context context;
    ViewHolder vh = null;
    boolean Tag=false;
    private List<DataManager.MyComplaint.DataBean.ComplaintInfoBean> ComplainList;

    public ComplainListAdapter(Context context, List<DataManager.MyComplaint.DataBean.ComplaintInfoBean> ComplainList) {
        this.context = context;
        this.ComplainList = ComplainList;
    }

    public void setTag(){
        this.Tag=true;
    }
    public void setDataList(List<DataManager.MyComplaint.DataBean.ComplaintInfoBean> ComplainList){
        this.ComplainList=ComplainList;
    }

    @Override
    public int getCount() {
        return ComplainList.size();
    }

    @Override
    public Object getItem(int position) {
        return ComplainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_mycomplaints_list_item, null);
            vh = new ViewHolder();
            vh.complain_img= (ImageView) convertView.findViewById(R.id.complain_img);
            vh.complain_firm= (TextView) convertView.findViewById(R.id.complain_firm);
            vh.complain_title= (TextView) convertView.findViewById(R.id.complain_title);
            vh.complain_type= (TextView) convertView.findViewById(R.id.complain_type);

            vh.complain_time= (TextView) convertView.findViewById(R.id.complain_time);
            vh.complain_status= (TextView) convertView.findViewById(R.id.complain_status);
            vh.complain_cancel= (Button) convertView.findViewById(R.id.complain_cancel);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        //Picasso.with(context).load(ComplainList.get(position).).into(vh.complain_img);
        if(Tag){
            vh.complain_cancel.setVisibility(View.GONE);
            vh.complain_status.setVisibility(View.GONE);
            vh.complain_firm.setText("【投诉企业】："+ComplainList.get(position).ENTNAME);
            vh.complain_title.setText("【投诉主题】："+ComplainList.get(position).TITLE);
            vh.complain_type.setText("【投诉类型】："+ComplainList.get(position).TYPE_NAME);
            vh.complain_time.setText("【投诉时间】："+ComplainList.get(position).CREATETIME);
        }else{
            vh.complain_firm.setText("企业："+ComplainList.get(position).ENTNAME);
            vh.complain_title.setText("主题："+ComplainList.get(position).TITLE);
            vh.complain_type.setText("类型："+ComplainList.get(position).TYPE_NAME);
            vh.complain_time.setText("时间："+ComplainList.get(position).CREATETIME);
        }

        switch (ComplainList.get(position).STATUS_NAME){
            case "审核通过":
                vh.complain_status.setTextColor(context.getResources().getColor(R.color.green));
                vh.complain_status.setText(ComplainList.get(position).STATUS_NAME);
                break;
            case "审核中":
                vh.complain_status.setTextColor(context.getResources().getColor(R.color.orange));
                vh.complain_status.setText(ComplainList.get(position).STATUS_NAME);
                break;
            default:
                vh.complain_status.setTextColor(context.getResources().getColor(R.color.red));
                vh.complain_status.setText(ComplainList.get(position).STATUS_NAME);
                break;
        }
        //vh.complain_status.setText(ComplainList.get(position).COMPLAINSTATUS);
        /**
         * 取消投诉
         */
        vh.complain_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GsonUtil cancelRuerst =new GsonUtil(URLconstant.URLINSER+URLconstant.CANCELCOM, RequestMethod.GET);
                cancelRuerst.add("token", MD5.MD5s(ComplainList.get(position).ID+new Build().MODEL));
                cancelRuerst.add("KeyNo",ComplainList.get(position).ID);
                cancelRuerst.add("deviceId",new Build().MODEL);
                CallServer.getInstance().add(context,cancelRuerst, MyhttpCallBack.getInstance(),0x996,true,false,true);
                MycomplaintsListActivity.pd.show();
                MycomplaintsListActivity.listmycp.remove(position);
            }
        });
        /**
         * Item点击事件
         */
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MycomplaintsListActivity.pd.show();
                if(MycomplaintsListActivity.flg==true){
                    GsonUtil DetailQuerst=new GsonUtil(URLconstant.URLINSER+URLconstant.GETCOMPLAIN, RequestMethod.GET);
                    DetailQuerst.add("token", MD5.MD5s(DataManager.QJiugongGList.data.baseInfo.get(0).PRIPID + ( new Build()).MODEL));
                    DetailQuerst.add("KeyNo",DataManager.QJiugongGList.data.baseInfo.get(0).PRIPID);
                    DetailQuerst.add("deviceId",new Build().MODEL);
                    DetailQuerst.add("id",ComplainList.get(position).ID);
                    CallServer.getInstance().add(context,DetailQuerst, MyhttpCallBack.getInstance(),0x995,true,false,true);
                }else{
                    GsonUtil DetailQuerst=new GsonUtil(URLconstant.URLINSER+URLconstant.GETCOMPLAIN, RequestMethod.GET);
                    DetailQuerst.add("token", MD5.MD5s("" + ( new Build()).MODEL));
                    DetailQuerst.add("KeyNo","");
                    DetailQuerst.add("deviceId",new Build().MODEL);
                    DetailQuerst.add("id",ComplainList.get(position).ID);
                    CallServer.getInstance().add(context,DetailQuerst, MyhttpCallBack.getInstance(),0x995,true,false,true);
                }

            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView complain_img;
        TextView complain_firm;
        TextView complain_title;
        TextView complain_type;
        TextView complain_time;
        TextView complain_status;
        Button complain_cancel;

    }
}
