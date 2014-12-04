/**
 * 
 */
package com.example.hankycoderecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author mnxoid
 *
 */
public class DetailActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Intent intent = getIntent();
	    MyColor color = (MyColor) intent.getSerializableExtra("color");
	    int id = intent.getIntExtra("id", 0);
	    HankyData DB = new HankyData(this);
	    //setContentView(new DetailView(this, color, "Caption", "Right detail", "Left detail"));
	    setContentView(new DetailView(this, color, DB.getCaptionById(id), DB.getDetailRightById(id), DB.getDetailLeftById(id)));
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
}
