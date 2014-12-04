package com.example.hankycoderecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.hankycoderecognition.HankyData;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HankyData dBData = new HankyData(this);
        ScrollView sView = new ScrollView(this);
        sView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LinearLayout linLay = new LinearLayout(this);
        linLay.setOrientation(LinearLayout.VERTICAL);
        linLay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //-----------------------------------------------
        for(Integer i = 0; i < dBData.q ; i++)
        {
        	/*if (i.intValue()>dBData.q) {
				MyColor cl = new MyColor(i.byteValue(),(byte) (i.byteValue() * 2), (byte) (i.byteValue() * 3));
				MyListItem mL = new MyListItem(this, cl, i.toString());
				mL.setPadding(5, 5, 5, 0);
				mL.setOnClickListener(new MyOcl(this, cl, i));
				linLay.addView(mL);*/
			/*} else {*/
				MyColor cl = dBData.getColorById(i.intValue());
				//MyColor cl = new MyColor(i.byteValue(),(byte) (i.byteValue() * 2), (byte) (i.byteValue() * 3));
				MyListItem mL = new MyListItem(this, cl, dBData.getCaptionById(i.intValue()));
				mL.setPadding(5, 5, 5, 0);
				mL.setOnClickListener(new MyOcl(this, cl, i));
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
				linLay.addView(mL);
			/*}/**/
        }
        //-----------------------------------------------
        sView.addView(linLay);
        setContentView(sView);
        //setContentView(R.layout.activity_main);
        /*
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	int id = item.getItemId();
        if (id == R.id.action_about) {
        	Intent intent = new Intent(this, AboutActivity.class);
        	startActivity(intent);
        } else if (id == R.id.action_capture) {
        	Intent intent = new Intent(this, CameraActivity.class);
        	startActivity(intent);
        } else if (id == R.id.action_home) {
        	Intent intent = new Intent(this, MainActivity.class);
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    /*
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }*/

}
