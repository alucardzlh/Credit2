package com.example.credit.QRCode_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.QRCode_util.Util;
import com.example.credit.R;


public class TestActivity extends Activity implements OnClickListener {

	Button btn;
	ImageView imageView;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Util.currentActivity = this;
		setContentView(R.layout.activity_erweimamain);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		textView = (TextView) findViewById(R.id.result);
		imageView = (ImageView) findViewById(R.id.qrcode_bitmap);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivityForResult(new Intent(this, CaptureActivity.class), 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == 0)// 从二维码照相机回主页
		{
			if (resultCode == RESULT_OK) {

				Bundle bundle = intent.getExtras();
				// 显示扫描到的内容
				textView.setText(bundle.getString("SCAN_RESULT"));
				// 显示
				imageView.setImageBitmap((Bitmap) intent
						.getParcelableExtra("bitmap"));
			}
			if (resultCode == 300) {
				Bundle bundle = intent.getExtras();
				String str=bundle.getString("result");
				String[] sr=str.split("\\?");
				textView.setText(str);
			}
			if (resultCode == 200) {

				imageView.setImageBitmap((Bitmap) intent
						.getParcelableExtra("QR_CODE"));
			}
		}
	}

}
