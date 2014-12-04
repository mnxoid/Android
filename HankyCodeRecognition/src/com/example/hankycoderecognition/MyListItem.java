/**
 * 
 */
package com.example.hankycoderecognition;

import android.content.Context;
import android.graphics.Canvas;

import com.example.hankycoderecognition.MyColor;

import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author mnxoid
 *
 */
public class MyListItem extends LinearLayout {

	/**
	 * @param context
	 */
	public MyListItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MyListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public MyListItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public MyListItem(Context context, MyColor c, String caption) {
		super(context);
		this.setGravity(Gravity.CENTER_VERTICAL);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.setOrientation(LinearLayout.HORIZONTAL);
		DrawView dvView = new DrawView(context, c);
		dvView.setLayoutParams(new LayoutParams(100,100));
		this.addView(dvView);
        TextView tView = new TextView(context);
        tView.setText(caption);
        tView.setTextSize(15);
        tView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        this.addView(tView);
	}

}
