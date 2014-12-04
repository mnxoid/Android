/**
 * 
 */
package com.mnxoid.whatsnext;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author mnxoid
 *
 */
public class MainActivity extends Activity {
	private static final String fName = "roadrage.ttf";
	MyTextView tv;
	MyTextView cvMyTextView;
	MyTextView aud;
	Handler timerHandler = new Handler();
	Runnable timerRunnable = new Runnable() {
		
		@Override
		public void run() {
			BLogic bLogic = BLogic.get();
			tv.setText(bLogic.untilString);
			cvMyTextView.setText(bLogic.classString);
			aud.setText(bLogic.audString);
			timerHandler.postDelayed(this, 1000);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrollView sView = new ScrollView(this);
		sView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll.setOrientation(LinearLayout.VERTICAL);
		MyTextView t = new MyTextView(this,fName);
		t.setText("Differential equations");
		cvMyTextView = t;
		t.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		t.setPadding(3, 5, 3, 0);
		t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 80);
		t.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.addView(t);
		t = new MyTextView(this,fName);
		aud = t;
		t.setText("IV / 1337");
		t.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		t.setPadding(3, 5, 3, 0);
		t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
		ll.addView(t);
		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t = new MyTextView(this,fName);
		tv = t;
		t.setText("15:30");
		t.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		t.setPadding(3, 5, 3, 0);
		t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
		t.setGravity(Gravity.CENTER_HORIZONTAL);
		ll.addView(t);
		sView.addView(ll);
		setContentView(sView);
		timerHandler.postDelayed(timerRunnable, 0);
	}
}
