/**
 * 
 */
package com.example.hankycoderecognition;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * @author mnxoid
 *
 */
public class CameraActivity extends Activity implements OnClickListener {

	final int CAMERA_CAPTURE = 1;
	final int PIC_CROP = 2;
	//captured picture uri
	private Uri picUri;
	//private View currentView = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
//			try {
//				Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				startActivityForResult(captureIntent, CAMERA_CAPTURE);
//			} catch (ActivityNotFoundException e) {
//				String errorMessage = "Whoops - your device doesn't support capturing images";
//				Toast toast = Toast.makeText( this , errorMessage, Toast.LENGTH_SHORT);
//				toast.show();
//			}
		
//	    //if (currentView==null) {
	    	setContentView(R.layout.camera_layout);
//	    //} else {
//	    	//setContentView(currentView);
//	    //}
	    ImageButton captureBtn = (ImageButton)findViewById(R.id.imageButton1);
	    //handle button clicks
	    captureBtn.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imageButton1) {
			try {
				Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(captureIntent, CAMERA_CAPTURE);
			} catch (ActivityNotFoundException e) {
				String errorMessage = "Whoops - your device doesn't support capturing images";
				Toast toast = Toast.makeText( this , errorMessage, Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}
	
	protected void onActivityResult( int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == CAMERA_CAPTURE) {
				picUri = data.getData();
				Bundle extras = data.getExtras();
		        Bitmap imageBitmap = (Bitmap) extras.get("data");
		        //ImageView picView = (ImageView)findViewById(R.id.picture);
		        //picView.setImageBitmap(imageBitmap);
		        //int clAvg = getDominantColor(imageBitmap);
				performCrop(imageBitmap);
				//addAvgDisplay();
			} else if (requestCode == PIC_CROP) {
				Bundle extras = data.getExtras();
				//get the cropped bitmap
				Bitmap thePic = extras.getParcelable("data");
				//retrieve a reference to the ImageView
				//ImageView picView = (ImageView)findViewById(R.id.picture);
				//display the returned cropped image
				//picView.setImageBitmap(thePic);
				//int clAvg = getDominantColor(thePic);
				//picUri = data.getData();
				getContentResolver().delete(picUri, null, null);
				addAvgDisplay(thePic);
			}
		} else if (requestCode == PIC_CROP) {
			//addAvgDisplay(nu);
		}
		
	}

	private void addAvgDisplay(Bitmap b) {
		Bitmap picBitmap =  b;
		int clAvg = getDominantColor(picBitmap);
		//here we search most similar color
		HankyData dBData = new HankyData(this);
		int index = 0;
		float mindiff = Float.MAX_VALUE;
		for(Integer i = 0; i < dBData.q ; i++)
        {
        		MyColor cl = dBData.getColorById(i.intValue());
        		float dif = cl.diff(new MyColor(clAvg),true);
				if (mindiff > dif) {
					mindiff = dif;
					index = i.intValue();
				}
        }
		MyColor acl = dBData.getColorById(index);
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra("color", acl);
		intent.putExtra("id", index);
		startActivity(intent);
		//---------------------------------
//		DrawView dw = new DrawView(this, new MyColor(clAvg));
//	    dw.setLayoutParams(new LayoutParams(200,200));
//		((LinearLayout)(((ImageView)findViewById(R.id.picture)).getParent())).addView(dw);
//		View currentView = (ScrollView)((LinearLayout)(((ImageView)findViewById(R.id.picture)).getParent())).getParent();
//		setContentView(currentView);
	}
	
	private void performCrop(Bitmap b) {
		try {
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			//indicate image type and Uri
			cropIntent.setDataAndType(picUri, "image/*");
			//set crop properties
			cropIntent.putExtra("crop", "true");
			//indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 1);
			cropIntent.putExtra("aspectY", 1);
			//indicate output X and Y
			cropIntent.putExtra("outputX", 256);
			cropIntent.putExtra("outputY", 256);
			//retrieve data on return
			cropIntent.putExtra("return-data", true );
			//start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, PIC_CROP);
		} catch (ActivityNotFoundException e) {
			//display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action";
			Toast toast = Toast.makeText( this , errorMessage, Toast.LENGTH_SHORT);
			toast.show();
			//fallback
			
		}
	}
	public static int getDominantColor(Bitmap bitmap) {
		   if (null == bitmap) return Color.BLACK;

		   int redBucket = 0;
		   int greenBucket = 0;
		   int blueBucket = 0;
		   int alphaBucket = 0;

		   boolean hasAlpha = bitmap.hasAlpha();
		   int pixelCount = bitmap.getWidth() * bitmap.getHeight();
		   int[] pixels = new int[pixelCount];
		   bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

		   for (int y = 0, h = bitmap.getHeight(); y < h; y++)
		   {
		       for (int x = 0, w = bitmap.getWidth(); x < w; x++)
		       {
		           int color = pixels[x + y * w]; // x + y * width
		           redBucket += (color >> 16) & 0xFF; // Color.red
		           greenBucket += (color >> 8) & 0xFF; // Color.greed
		           blueBucket += (color & 0xFF); // Color.blue
		           if (hasAlpha) alphaBucket += (color >>> 24); // Color.alpha
		       }
		   }

		   return Color.argb(
		           (hasAlpha) ? (alphaBucket / pixelCount) : 255,
		           redBucket / pixelCount,
		           greenBucket / pixelCount,
		           blueBucket / pixelCount);
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