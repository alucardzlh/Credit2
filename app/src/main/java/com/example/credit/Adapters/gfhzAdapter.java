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

public class gfhzAdapter extends BaseAdapter {
    private Context context;
    private List<String> list1;
    private List<String> list2;

    public gfhzAdapter(Context context, List<String> list1,List<String> list2) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public int getCount() {
        return list1.size();
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
            vh.fz_tv1CX1=(TextView) view.findViewById(R.id.fz_tv1CX1);
            vh.fz_tv1CX2=(TextView) view.findViewById(R.id.fz_tv1CX2);
            vh.fz_tv1CX3=(TextView) view.findViewById(R.id.fz_tv1CX3);

            vh.hitem_tv1C=(TextView) view.findViewById(R.id.fz_tv1C);
            vh.hitem_tv2C=(TextView) view.findViewById(R.id.fz_tv2C);
            vh.hitem_tv3C=(TextView) view.findViewById(R.id.fz_tv3C);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.fz_tv1CXH.setText(position+"");
        vh.fz_tv1CX1.setText("合作各方的名称：");
        vh.fz_tv1CX2.setText("责任形式：");
        vh.hitem_tv1C.setText(list1.get(position));
        vh.hitem_tv2C.setText(list2.get(position));
        vh.fz_tv1CX3.setVisibility(View.GONE);
        vh.hitem_tv3C.setVisibility(View.GONE);
        return view;
    }

    public class ViewHolder {
        TextView fz_tv1CXH;

        TextView fz_tv1CX1;
        TextView fz_tv1CX2;
        TextView fz_tv1CX3;
        TextView hitem_tv1C;
        TextView hitem_tv2C;
        TextView hitem_tv3C;
    }
}
