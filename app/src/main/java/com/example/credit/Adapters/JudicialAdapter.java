package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;

import java.util.ArrayList;
import java.util.List;

public class JudicialAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.JudicialDocumentsMar.DataBean.JudgmentInfoBean> list1;
    private List<DataManager.JudicialDocumentsMar.DataBean.CourtcaseInfoBean> list2;
    private List<DataManager.JudicialDocumentsMar.DataBean.SfAlterBean> list3;
    private List<DataManager.JudicialDocumentsMar.DataBean.SfInfoBean> list4;
    private List<String> lists;

    public JudicialAdapter(Context context, List<DataManager.JudicialDocumentsMar.DataBean.JudgmentInfoBean> list1, List<DataManager.JudicialDocumentsMar.DataBean.CourtcaseInfoBean> list2,List<DataManager.JudicialDocumentsMar.DataBean.SfAlterBean> list3,List<DataManager.JudicialDocumentsMar.DataBean.SfInfoBean> list4,List<String> lists) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        if(list1!=null && list1.size()>0){
            return list1.size();
        }
        if(list2!=null && list2.size()>0){
            return list2.size();
        }
        if(list3!=null && list3.size()>0){
            return list3.size();
        }
        if(list4!=null && list4.size()>0){
            return list4.size();
        }else{
            return 0;
        }

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
            view = LayoutInflater.from(context).inflate(R.layout.activity_judicial__c_item, null);
            vh = new ViewHolder();
            vh.jListView_item = (MyListView) view.findViewById(R.id.jListView_item);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        List<String> listst=new ArrayList<>();
        if(list1!=null && list1.size()>0){
            listst.add(list1.get(position).SENTENCECONMENT);
            listst.add(list1.get(position).CASENUM);
            listst.add(list1.get(position).SUPDEPARTMENT);
            listst.add(list1.get(position).SENTENCEDATE);
            listst.add(list1.get(position).REDECORG_CN);
        }
        if(list2!=null && list2.size()>0){
            listst.add(list2.get(position).COURT_NAME);
            listst.add(list2.get(position).REG_DATE);
            listst.add(list2.get(position).COURTCASEID);
            listst.add(list2.get(position).GIST_CID);
            listst.add(list2.get(position).PERFORMANCE);
            listst.add(list2.get(position).DISREPUT_TYPE_NAME);
            listst.add(list2.get(position).DUTY);
        }
        if(list3!=null && list3.size()>0){
            listst.add(list3.get(position).REGNO);
            listst.add(list3.get(position).INV);
            listst.add(list3.get(position).FROAM);
            listst.add(list3.get(position).ALIEN);
            listst.add(list3.get(position).FROAUTH);
        }
        if(list4!=null && list4.size()>0){
            listst.add(list4.get(position).INV);
            listst.add(list4.get(position).EXECUTEITEM_CN);
            listst.add(list4.get(position).FROAUTH);
            listst.add(list4.get(position).FROAM);
            listst.add(list4.get(position).FROZSTATE_CN+"");
            listst.add(list4.get(position).FROFROM+" 至 "+list4.get(position).FROTO);
        }
        Judicial_cItemAdapter adapter=new Judicial_cItemAdapter(context,listst,lists);
        vh.jListView_item.setAdapter(adapter);
        return view;
    }

    public class ViewHolder {
        MyListView jListView_item;
    }
}
