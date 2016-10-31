package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Views.RoundImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import static com.example.credit.Views.FileUtil.decodeBitmap;

public class MyCommment_listAdapter extends BaseAdapter {
    private Context context;
    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    ViewHolder vh = null;
    CreditSharePreferences csp=CreditSharePreferences.getLifeSharedPreferences();
    public MyCommment_listAdapter(Context context, List<String> list1,List<String> list2,List<String> list3) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
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
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_my_commentlist_item, null);
            vh = new ViewHolder();
            vh.mycomm_img=(RoundImageView) view.findViewById(R.id.mycomm_img);
            vh.mycomm_gongs=(TextView) view.findViewById(R.id.mycomm_gongs);
            vh.mycomm_time=(TextView) view.findViewById(R.id.mycomm_time);
            vh.mycomm_conn=(TextView) view.findViewById(R.id.mycomm_conn);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        if(!csp.getICONSTEAM().equals("")){
            File file = new File(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg");
            if (file.exists()) {//获取本地图片路径是否存在
                vh.mycomm_img.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/loginImg.jpg",35,35));
                //Picasso.with(context).load(file).into(vh.mycomm_img);
            }
        }
        vh.mycomm_gongs.setText(list1.get(position));
        vh.mycomm_time.setText(list2.get(position));
        vh.mycomm_conn.setText(list3.get(position));
        return view;
    }

    public class ViewHolder {
        RoundImageView mycomm_img;//用户头像
        TextView mycomm_gongs;//评论企业
        TextView mycomm_time;//评论时间
        TextView mycomm_conn;//评论内容
    }
}
