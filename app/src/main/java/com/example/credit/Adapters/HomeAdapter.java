package com.example.credit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.R;

import java.util.List;

/**
 * Created by 章龙海 on 2016/12/2 15:42.
 *
 * @descript (描述)
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    List<String> DataList;
    Context context;

    public HomeAdapter(Context context, List<String> DataList) {
        this.context=context;
        this.DataList=DataList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.local_history_item
                , parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv.setText(DataList.get(position));


    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.local_text);
        }
    }
}
