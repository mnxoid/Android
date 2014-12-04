package com.example.hankycoderecognition;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailView extends ScrollView {

	public DetailView(Context context, MyColor color, String caption, String detailRight, String detailLeft) {
		super(context);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		//-------------------------------------------------
		LinearLayout outerVert = new LinearLayout(context);
		outerVert.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		outerVert.setOrientation(LinearLayout.VERTICAL);
		//-------------------------------------------------
		LinearLayout innerHor = new LinearLayout(context);
		innerHor.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		innerHor.setOrientation(LinearLayout.HORIZONTAL);
		//-------------------------------------------------
		LinearLayout innerVert = new LinearLayout(context);
		innerVert.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		innerVert.setOrientation(LinearLayout.VERTICAL);
		//-------------------------------------------------
		TextView tView = new TextView(context);
		tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tView.setText(caption);
		tView.setTextSize(15);
		innerVert.addView(tView);
		tView = new TextView(context);
		tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tView.setText("Hex code: " + color.toString());
		tView.setTextSize(15);
		innerVert.addView(tView);
		tView = new TextView(context);
		tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tView.setText("HSV code: " + color.toHSVString());
		tView.setTextSize(15);
		innerVert.addView(tView);
		//-------------------------------------------------
		DrawView dw = new DrawView(context, color);
		dw.setLayoutParams(new LayoutParams(200, 200));
		innerHor.addView(dw);
		innerHor.addView(innerVert);
		outerVert.addView(innerHor);
		//-------------------------------------------------
		tView = new TextView(context);
		tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tView.setText("Worn on Right: " + detailRight);
		tView.setTextSize(15);
		outerVert.addView(tView);
		//-------------------------------------------------
		tView = new TextView(context);
		tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tView.setText("Worn on Left: " + detailLeft);
		tView.setTextSize(15);
		outerVert.addView(tView);
		//---------------------------------------------------
		HankyData dBData = new HankyData(context);
		for(Integer i = 0; i < dBData.q ; i++)
        {
        	
				MyColor cl = dBData.getColorById(i.intValue());
				int dif = cl.diff(color,false);
				if (dif == 0) {
					//MyColor cl = new MyColor(i.byteValue(),(byte) (i.byteValue() * 2), (byte) (i.byteValue() * 3));
					MyListItem mL = new MyListItem(context, cl, dBData.getCaptionById(i.intValue()));
					mL.setPadding(5, 5, 5, 0);
					mL.setOnClickListener(new MyOcl(context, cl, i));
					mL.setOnTouchListener(new OnTouchListener() {

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							Animation animation = new ScaleAnimation(1f, 0.9f, 1f, 0.9f);
							//animation.setFillAfter(true);
							animation.setDuration(250);
							v.startAnimation(animation);
							return false;
						}
					});
					mL.setOnLongClickListener(new OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {
							Animation animation = new ScaleAnimation(1f, 0.9f, 1f, 0.9f);
							animation.setDuration(250);
							//animation.setFillAfter(true);
							v.startAnimation(animation);
							return false;
						}
					});
					outerVert.addView(mL);
					/*}/**/
				}
        }
		this.addView(outerVert);
	}

}
