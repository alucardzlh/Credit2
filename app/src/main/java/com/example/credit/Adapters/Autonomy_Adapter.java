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

import java.util.List;

/**
 * Created by alucard on 2016/5/29.
 */
public class Autonomy_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.ZZGS.DataBean.AnbaseInfoBean> reportList;//企业年报
    List<DataManager.ZZGS.DataBean.ShareholdersandInvestmentBean> fundedList;//股东出资
    List<DataManager.ZZGS.DataBean.AnSubcapitalInfoBean> stockList;//股权变更
    List<DataManager.ZZGS.DataBean.EimpermitInfoBean> permitList;//行政许可信
    List<DataManager.ZZGS.DataBean.AimippldgInfoBean> loreList;//知识产权
    List<DataManager.ZZGS.DataBean.EimcaseInfoBean> punishList;//行政处罚

    public Autonomy_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.ZZGS.DataBean.AnbaseInfoBean> reportList, List<DataManager.ZZGS.DataBean.ShareholdersandInvestmentBean> fundedList, List<DataManager.ZZGS.DataBean.AnSubcapitalInfoBean> stockList, List<DataManager.ZZGS.DataBean.EimpermitInfoBean> permitList, List<DataManager.ZZGS.DataBean.AimippldgInfoBean> loreList, List<DataManager.ZZGS.DataBean.EimcaseInfoBean> punishList) {
        this.reportList = reportList;
        this.fundedList = fundedList;
        this.stockList = stockList;
        this.permitList = permitList;
        this.loreList = loreList;
        this.punishList = punishList;
    }

    @Override
    public int getCount() {
        if (reportList != null) {
            return reportList.size();
        }
        if (fundedList != null) {
            return fundedList.size();
        }
        if (stockList != null) {
            return stockList.size();
        }
        if (permitList != null) {
            return permitList.size();
        }
        if (loreList != null) {
            return loreList.size();
        }
        if (punishList != null) {
            return punishList.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        if (reportList != null) {
            return reportList.get(position);
        }
        if (fundedList != null) {
            return fundedList.get(position);
        }
        if (stockList != null) {
            return stockList.get(position);
        }
        if (permitList != null) {
            return permitList.get(position);
        }
        if (loreList != null) {
            return loreList.get(position);
        }
        if (punishList != null) {
            return punishList.get(position);
        } else {
            return null;
        }
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

            /**
             * 企业年报UI
             */
            if (reportList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.activity_c_list_item, null);
                convertView.findViewById(R.id.report_item).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.detail).setVisibility(View.GONE);
                convertView.findViewById(R.id.time).setVisibility(View.VISIBLE);
                vh.report_year = (TextView) convertView.findViewById(R.id.report_year);
                vh.report_date = (TextView) convertView.findViewById(R.id.date);

            }
            /**
             * 股东出资UI
             */
            if (fundedList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_funded_item, null);
                vh.partenr = (TextView) convertView.findViewById(R.id.partenr);
                vh.money = (TextView) convertView.findViewById(R.id.money);
                vh.true_money = (TextView) convertView.findViewById(R.id.true_money);
                vh.subscribed_type = (TextView) convertView.findViewById(R.id.subscribed_type);
                vh.subscribed = (TextView) convertView.findViewById(R.id.subscribed);
                vh.subscribed_time = (TextView) convertView.findViewById(R.id.subscribed_time);
                vh.paid_type = (TextView) convertView.findViewById(R.id.paid_type);
                vh.paid = (TextView) convertView.findViewById(R.id.paid);
                vh.paid_time = (TextView) convertView.findViewById(R.id.paid_time);
                vh.funded_public_time = (TextView) convertView.findViewById(R.id.funded_public_time);

            }
            /**
             * 股权变更UI
             */
            if (stockList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_stock_item, null);
                vh.stock_time = (TextView) convertView.findViewById(R.id.stock_time);
                vh.BeforeContent = (TextView) convertView.findViewById(R.id.BeforeContent);
                vh.AfterContent = (TextView) convertView.findViewById(R.id.AfterContent);
            }
            /**
             * 行政许可UI
             */
            if (permitList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_permit_item, null);
                vh.permit_xh = (TextView) convertView.findViewById(R.id.permit_xh);
                vh.permit_name = (TextView) convertView.findViewById(R.id.permit_name);
                vh.permit_no = (TextView) convertView.findViewById(R.id.permit_no);
                vh.permit_date = (TextView) convertView.findViewById(R.id.permit_date);
                vh.permit_office = (TextView) convertView.findViewById(R.id.permit_office);
                vh.permit_time = (TextView) convertView.findViewById(R.id.permit_time);
                vh.permit_content = (TextView) convertView.findViewById(R.id.permit_content);

                vh.permit_zxtime = (TextView) convertView.findViewById(R.id.permit_zxtime);
                vh.permit_zxyuany = (TextView) convertView.findViewById(R.id.permit_zxyuany);
                vh.permit_bdxtime = (TextView) convertView.findViewById(R.id.permit_bdxtime);
                vh.permit_bdxyuany = (TextView) convertView.findViewById(R.id.permit_bdxyuany);
                vh.permit_qtendtime = (TextView) convertView.findViewById(R.id.permit_qtendtime);
                vh.permit_qtendyuany = (TextView) convertView.findViewById(R.id.permit_qtendyuany);
            }
            /**
             * 知识产权UI
             */
            if (loreList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_lore_item, null);
                vh.lore_xh = (TextView) convertView.findViewById(R.id.lore_xh);
                vh.lore_pripid = (TextView) convertView.findViewById(R.id.lore_pripid);
                vh.lore_name = (TextView) convertView.findViewById(R.id.lore_name);
                vh.lore_no = (TextView) convertView.findViewById(R.id.lore_no);

            }
            /**
             * 行政处罚UI
             */
            if (punishList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_punish_item, null);
                vh.punish_xh = (TextView) convertView.findViewById(R.id.punish_xh);
                vh.punish_pripid = (TextView) convertView.findViewById(R.id.punish_pripid);
                vh.punish_name = (TextView) convertView.findViewById(R.id.punish_name);
                vh.punish_registerno = (TextView) convertView.findViewById(R.id.punish_registerno);
                vh.punish_creditno = (TextView) convertView.findViewById(R.id.punish_creditno);
                vh.punish_no = (TextView) convertView.findViewById(R.id.punish_no);
                vh.punish_illtype = (TextView) convertView.findViewById(R.id.punish_illtype);
                vh.punish_type_cn = (TextView) convertView.findViewById(R.id.punish_type_cn);
                vh.punish_money = (TextView) convertView.findViewById(R.id.punish_money);
                vh.punish_cash = (TextView) convertView.findViewById(R.id.punish_cash);
                vh.punihs_context = (TextView) convertView.findViewById(R.id.punihs_context);
                vh.punish_office = (TextView) convertView.findViewById(R.id.punish_office);
                vh.punish_time = (TextView) convertView.findViewById(R.id.punish_time);
                vh.punish_publicdate = (TextView) convertView.findViewById(R.id.punish_publicdate);
                vh.punish_mark = (TextView) convertView.findViewById(R.id.punish_mark);
            } else {
            }
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        /**
         * 企业年报
         */
        if (reportList != null) {

            DataManager.ZZGS.DataBean.AnbaseInfoBean report = reportList.get(position);
            vh.report_year.setText(report.ANCHEYEAR);
            vh.report_date.setText(report.ANCHEDATE);
        }
        /**
         * 股东出资
         */
        if (fundedList != null) {
            DataManager.ZZGS.DataBean.ShareholdersandInvestmentBean funded = fundedList.get(position);
            vh.partenr.setText(funded.INV);
            vh.money.setText(String.valueOf(funded.SUBCONAM));
            vh.true_money.setText(String.valueOf(funded.ACCONAM));
            vh.subscribed_type.setText(funded.CONFORM_CN);
            vh.subscribed.setText(String.valueOf( funded.SUBCONAM));
            vh.subscribed_time.setText(funded.PUBLICDATE);
            vh.paid_type.setText(funded.ACCONDATE);
            vh.paid.setText(String.valueOf(funded.ACCONAM));
            vh.paid_time.setText(funded.ACCONDATE);
            vh.funded_public_time.setText(funded.PUBLICDATE);
        }
        /**
         * 股权变更
         */
        if (stockList != null) {
            DataManager.ZZGS.DataBean.AnSubcapitalInfoBean stock = stockList.get(position);
            vh.stock_time.setText(stock.ALTDATE);
            vh.AfterContent.setText(stock.TRANSAMPRAF);
            vh.BeforeContent.setText(stock.TRANSAMPRBF);
        }
        /**
         * 行政许可信
         */
        if (permitList != null) {
            DataManager.ZZGS.DataBean.EimpermitInfoBean permit = permitList.get(position);
            vh.permit_xh.setText(position+1+"");
            vh.permit_name.setText(permit.LICNAME_CN);
            vh.permit_no.setText(permit.LICNO);
            vh.permit_date.setText(permit.invalidDate);
            vh.permit_office.setText(permit.LICANTH);
            vh.permit_time.setText(permit.PUBLICDATE);
            vh.permit_content.setText(permit.LICITEM);

        }
        /**
         * 知识产权
         */
        if (loreList != null) {
            DataManager.ZZGS.DataBean.AimippldgInfoBean lore = loreList.get(position);
            vh.lore_xh.setText(position+1+"");
            vh.lore_pripid.setText(lore.PRIPID);
            vh.lore_name.setText(lore.ENTNAME);
            vh.lore_no.setText(lore.REGNO);
        }
        /**
         * 行政处罚
         */
        if (punishList != null) {
            DataManager.ZZGS.DataBean.EimcaseInfoBean punish = punishList.get(position);
            vh.punish_pripid.setText(punish.PRIPID);
            vh.punish_name.setText(punish.ENTNAME);
            vh.punish_registerno.setText(punish.REGNO);
            vh.punish_creditno.setText(punish.UNISCID);
            vh.punish_no.setText(punish.PENDECNO);
            vh.punish_illtype.setText(punish.ILLEGACTTYPE);
            vh.punish_type_cn.setText(punish.PENTYPE_CN);
            vh.punish_money.setText(punish.PENAM+"");
            vh.punish_cash.setText(punish.FORFAM+"");
            vh.punihs_context.setText(punish.PENCONTENT);
            vh.punish_office.setText(punish.JUDAUTH);
            vh.punish_time.setText(punish.PENDECISSDATE);
            vh.punish_publicdate.setText(punish.PUBLICDATE);
            vh.punish_mark.setText(punish.REMARK);
        } else {
        }

        return convertView;
    }

    class ViewHolder {
        TextView report_year, report_date;
        TextView partenr, money, true_money, subscribed_type, subscribed, subscribed_time, paid_type, paid, paid_time, funded_public_time;
        TextView stock_time, BeforeContent, AfterContent;
        TextView permit_xh,permit_name, permit_no, permit_date, permit_office, permit_time, permit_content,permit_zxtime,permit_zxyuany,permit_bdxtime,permit_bdxyuany,permit_qtendtime,permit_qtendyuany;
        TextView lore_xh,lore_pripid, lore_name, lore_no;
        TextView punish_xh,punish_pripid, punish_name, punish_registerno, punish_creditno, punish_no, punish_illtype, punish_type_cn, punish_money, punish_cash, punihs_context, punish_office, punish_time, punish_publicdate, punish_mark;
    }

}
