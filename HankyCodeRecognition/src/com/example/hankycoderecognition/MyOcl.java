package com.example.hankycoderecognition;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;

public class MyOcl implements OnClickListener {
	private MyColor color;
	private Context context;
	private int id;
	public MyOcl(Context con, MyColor col, int ide) {
		color = col;
		context = con;
		id = ide;
	}
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, DetailActivity.class);
		intent.putExtra("color", color);
		intent.putExtra("id", id);
		context.startActivity(intent);
	}

}
