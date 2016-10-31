package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Adapters.MyCommment_listAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的评论
 */
public class MyCommentlistActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.Mycomm_list)
    ListView Mycomm_list;//评论列表
    MyCommment_listAdapter adapter;
    @ViewInject(R.id.commentNull)
    LinearLayout commentNull;//空
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commentlist);
        ViewUtils.inject(this);
        init();
    }

    public void init() {
        b_topname.setText("我的评论");
        b_return.setOnClickListener(listener);
        if (DataManager.MyCommentlistrS.data == null || DataManager.MyCommentlistrS.data.equals(null) || DataManager.MyCommentlistrS.data.commentInfo.size() == 0) {
            Mycomm_list.setVisibility(View.GONE);
            commentNull.setVisibility(View.VISIBLE);
        }
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        for(int i=0;i<DataManager.MyCommentlistrS.data.commentInfo.size();i++){
            list1.add("评论企业：【"+DataManager.MyCommentlistrS.data.commentInfo.get(i).ENTNAME+"】");
            list2.add("评论时间："+DataManager.MyCommentlistrS.data.commentInfo.get(i).CREATETIME);
            list3.add("评论内容："+DataManager.MyCommentlistrS.data.commentInfo.get(i).CONTENT);

            for(int j=0;j<DataManager.MyCommentlistrS.data.commentInfo.get(i).commentSonInfo.size();j++){
                list1.add("回复评论：【"+DataManager.MyCommentlistrS.data.commentInfo.get(i).CONTENT+"】");
                list2.add("回复评论时间："+DataManager.MyCommentlistrS.data.commentInfo.get(i).commentSonInfo.get(j).CREATETIME);
                list3.add("回复评论内容："+DataManager.MyCommentlistrS.data.commentInfo.get(i).commentSonInfo.get(j).CONTENT);
            }
        }

        adapter = new MyCommment_listAdapter(MyCommentlistActivity.this, list1,list2,list3);
        Mycomm_list.setAdapter(adapter);
        MainActivity.ad.dismiss();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_return://返回键
                    finish();
                    break;
            }
        }
    };
}
