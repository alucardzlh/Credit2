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

public class FeiZhiAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.GSXX.DataBean.AnnualReportsInfoBean> list;

    public FeiZhiAdapter(Context context, List<DataManager.GSXX.DataBean.AnnualReportsInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_c_details_fenzhijigou, null);
            vh = new ViewHolder();
            vh.fz_tv1CXH=(TextView) view.findViewById(R.id.fz_tv1CXH);
            vh.hitem_tv1C=(TextView) view.findViewById(R.id.fz_tv1C);
            vh.hitem_tv2C=(TextView) view.findViewById(R.id.fz_tv2C);
            vh.hitem_tv3C=(TextView) view.findViewById(R.id.fz_tv3C);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.fz_tv1CXH.setText(position+1+"");
        if(!list.get(position).REGNO.equals("")){
            vh.hitem_tv1C.setText(list.get(position).REGNO);
        }else {
            vh.hitem_tv1C.setText(list.get(position).UNISCID);
        }

        vh.hitem_tv2C.setText(list.get(position).BRNAME);
        vh.hitem_tv3C.setText(list.get(position).REGORG_CN);
        return view;
    }

    public class ViewHolder {
        TextView fz_tv1CXH;
        TextView hitem_tv1C;
        TextView hitem_tv2C;
        TextView hitem_tv3C;
    }
}
