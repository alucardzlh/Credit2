package com.example.credit.Dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.credit.Activitys.Main_SearchActivity;
import com.example.credit.Adapters.syTypeAdapter;
import com.example.credit.R;

import java.util.List;

/**
 * 自定义popupWindow
 *
 * @author wwj
 *
 *
 */
public class AddPopWindow extends PopupWindow {
	private View conentView;

	public AddPopWindow(final Activity context, final List<String> list) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(R.layout.add_popup_dialog, null);
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);
		//设置数据源
		syTypeAdapter adapter=new syTypeAdapter(context,list);
		ListView typelist1= (ListView) conentView.findViewById(R.id.typelist1);
		typelist1.setAdapter(adapter);
		typelist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Main_SearchActivity.text2img1.setVisibility(View.VISIBLE);
				Main_SearchActivity.text2img2.setVisibility(View.GONE);
				Main_SearchActivity.text2.setText(list.get(position)+"");
				AddPopWindow.this.dismiss();
			}
		});
	}

	/**
	 * 显示popupWindow
	 *
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 10);
		} else {
			this.dismiss();
		}
	}
}
