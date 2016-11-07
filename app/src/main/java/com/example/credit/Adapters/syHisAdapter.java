package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

public class syHisAdapter extends BaseAdapter {
    private Context context;
    private List< DataManager.getSgHis.DataBean.SearchHistoryBean> list;

    public syHisAdapter(Context context, List<DataManager.getSgHis.DataBean.SearchHistoryBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sy_his_item, null);
            vh = new ViewHolder();
            vh.tetr=(TextView) view.findViewById(R.id.tetr);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tetr.setText(list.get(position).KEYWORDS+"");
        if(position%2 == 0){
            vh.tetr.setTextColor(Color.parseColor("#FF0000"));
        }else{
            vh.tetr.setTextColor(Color.parseColor("#2BA8D8"));
        }
        return view;
    }

    public class ViewHolder {
        TextView tetr;
    }
}
