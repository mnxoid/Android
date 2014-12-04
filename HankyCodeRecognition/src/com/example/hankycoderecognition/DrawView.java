/**
 * 
 */
package com.example.hankycoderecognition;

import android.content.Context;
import android.graphics.Canvas;
import com.example.hankycoderecognition.MyColor;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author mnxoid
 *
 */
public class DrawView extends View {
	Paint paint = new Paint();
	MyColor cl = null;
	public DrawView(Context context, MyColor color) {
		super(context);
		cl = color;
	}
	/**
	 * @param context
	 */
	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onDraw(Canvas canvas) {
		paint.setColor(cl.getIntValue());
        paint.setStrokeWidth(3);
        canvas.drawRect(0, 0, getLayoutParams().height, getLayoutParams().width, paint);
	}

}
