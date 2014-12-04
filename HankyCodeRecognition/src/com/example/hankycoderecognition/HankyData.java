package com.example.hankycoderecognition;

import android.content.Context;

public class HankyData {
	
	private Context context;
	public int q;
	
	public HankyData() {
		context = null;
		q = 0;
	}
	
	public HankyData(Context c) {
		context = c;
		q = getIntResourceByName("i1");
	}
	
	public String getStringResourceByName(String aString) {
	      String packageName = context.getPackageName();
	      int resId = context.getResources().getIdentifier(aString, "string", packageName);
	      return context.getString(resId);
	}

	public int getIntResourceByName(String aString) {
	      String packageName = context.getPackageName();
	      int resId = context.getResources().getIdentifier(aString, "integer", packageName);
	      return context.getResources().getInteger(resId);
	    }
	
	public String getCaptionById(int id) {
		if (id>q) {
			return "[Caption]";
		} else {
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append("C_");
			sbBuilder.append(id);
			return getStringResourceByName(sbBuilder.toString());
		}
	}

	public String getDetailRightById(int id) {
		if (id>q) {
			return "[Right detail]";
		} else {
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append("R_");
			sbBuilder.append(id);
			return getStringResourceByName(sbBuilder.toString());
		}
	}

	public String getDetailLeftById(int id) {
		if (id>q) {
			return "[Left detail]";
		} else {
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append("L_");
			sbBuilder.append(id);
			return getStringResourceByName(sbBuilder.toString());
		}
	}
	
	public MyColor getColorById(int id) {
		if (id>q) {
			return new MyColor(0);
		} else {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("c");
			sBuilder.append(id);
			String packageName = context.getPackageName();
		    int resId = context.getResources().getIdentifier(sBuilder.toString(), "color", packageName);
			return new MyColor(context.getResources().getColor(resId));
		}
	}

}
