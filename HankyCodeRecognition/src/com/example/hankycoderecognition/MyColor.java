package com.example.hankycoderecognition;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.graphics.Color;


public class MyColor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4153739858800513813L;
	private byte red,green,blue;
	public MyColor() {
		// TODO Auto-generated constructor stub
	}
	public MyColor(byte r, byte g, byte b) {
		red = r;
		green = g;
		blue = b;
	}
	public MyColor(int c) {
		red = (byte)((c & 0x00FF0000)>>16);
		green = (byte)((c & 0x0000FF00)>>8);
		blue = (byte)(c & 0x000000FF);
	}
	public MyColor(int i, int j, int k) {
		red = (byte)i;
		green = (byte)j;
		blue = (byte)k;
	}
	public int getIntValue() {
		int R = (int)red;
	    int G = (int)green;
	    int B = (int)blue;

	    R = (R << 16) & 0x00FF0000;
	    G = (G << 8) & 0x0000FF00;
	    B = B & 0x000000FF;

	    return 0xFF000000 | R | G | B;
	}
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append('#');
		sBuilder.append(String.format("%02X ", red));
		sBuilder.append(String.format("%02X ", green));
		sBuilder.append(String.format("%02X ", blue));
		return sBuilder.toString();	
	}
	
	public String toHSVString() {
		StringBuilder sBuilder = new StringBuilder();
		float[] hsv = {0,0,0};
		//RGBtoHSV(Byte.valueOf(red).floatValue(), Byte.valueOf(green).floatValue(), Byte.valueOf(blue).floatValue(), hsv);
		//Color.RGBToHSV(Byte.valueOf(red).intValue(), Byte.valueOf(green).intValue(), Byte.valueOf(blue).intValue(), hsv);
		Color.colorToHSV(this.getIntValue(), hsv);
		sBuilder.append('{');
		sBuilder.append(Float.toString(hsv[0]));
		sBuilder.append(',');
		sBuilder.append(Float.toString(hsv[1]*100));
		sBuilder.append(',');
		sBuilder.append(Float.toString(hsv[2]*100));
		sBuilder.append('}');
		return sBuilder.toString();	
	}
	
	public int diff(MyColor dColor, boolean fromcam) {
//		int[] lab1={0,0,0},lab2={0,0,0};
//		rgb2lab(red, green, blue, lab1);
//		
//		//System.err.printf("%i,%i,%i\n", lab1[0],lab1[1],lab1[2]);
//		rgb2lab(dColor.red, dColor.green, dColor.blue, lab2);
//		
		
		//System.err.printf("%i,%i,%i\n", lab2[0],lab2[1],lab2[2]);
//		int rdif = Math.abs(lab1[0]/100-lab2[0]/100);
//		int gdif = Math.abs(lab1[1]/100-lab2[1]/100);
//		int bdif = Math.abs(lab1[2]/100-lab2[2]/100);
		float[] hsv1 = new float[3];
//		Color.RGBToHSV((int)red,(int)green,(int)blue,hsv1);
		float[] hsv2 = new float[3];
		Color.colorToHSV(this.getIntValue(), hsv1);
		Color.colorToHSV(dColor.getIntValue(), hsv2);
		int i1,i2;
		i1 = Float.valueOf(hsv1[0]).intValue()+30;
		i2 = Float.valueOf(hsv2[0]).intValue()+30;
		if (i1>360) i1 -= 360;
		if (i2>360) i2 -= 360;
		i1 /= 60;
		i2 /= 60;
		/*
		//int max1 = Math.max( Math.max(red, green), blue );
		//int max2 = Math.max( Math.max(dColor.red, dColor.green), dColor.blue );
		if (red > 100 && green < 100 && blue < 100) {
			i1 = 1;
		} else if (red < 100 && green > 100 && blue < 100) {
			i1 = 2;
		} else {
			i1 = 3;
		}
		if (dColor.red > 100 && dColor.green < 100 && dColor.blue < 100) {
			i2 = 1;
		} else if (dColor.red < 100 && dColor.green > 100 && dColor.blue < 100) {
			i2 = 2;
		} else {
			i2 = 3;
		}
//		Color.RGBToHSV((int)dColor.red,(int)dColor.green,(int)dColor.blue,hsv2);
		RGBtoHSV(Byte.valueOf(red).floatValue(), Byte.valueOf(green).floatValue(), Byte.valueOf(blue).floatValue(), hsv1);
		RGBtoHSV(Byte.valueOf(dColor.red).floatValue(), Byte.valueOf(dColor.green).floatValue(), Byte.valueOf(dColor.blue).floatValue(), hsv2);
//		StringBuilder sd = new StringBuilder();
//		sd.append("First array: ");
//		sd.append(hsv1[0]);
//		sd.append(" ");
//		sd.append(hsv1[1]);
//		sd.append(" ");
//		sd.append(hsv1[2]);
//		sd.append(" \n");
//		Logger.getAnonymousLogger().log(Level.WARNING, sd.toString());
//		sd = new StringBuilder();
//		sd.append("Second array: ");
//		sd.append(hsv2[0]);
//		sd.append(" ");
//		sd.append(hsv2[1]);
//		sd.append(" ");
//		sd.append(hsv2[2]);
//		sd.append(" \n");
//		Logger.getAnonymousLogger().log(Level.WARNING, sd.toString());
		*/
		int rdif = Math.abs(dColor.red-red);
		int gdif = Math.abs(dColor.green-green);
		int bdif = Math.abs(dColor.blue-blue);
		/*
//		int hrdif = (Float.valueOf( hsv1[0]-hsv2[0])).intValue();
//		int hgdif = (Float.valueOf( hsv1[1]-hsv2[1])).intValue();
//		int hbdif = (Float.valueOf( hsv1[2]-hsv2[2])).intValue();
		int dlab[]={Math.abs(lab1[0]-lab2[0]),Math.abs(lab1[1]-lab2[1]),Math.abs(lab1[2]-lab2[2])};
//		sd = new StringBuilder();
//		sd.append("Diffs array: ");
//		sd.append(Float.valueOf(hsv1[0])-Float.valueOf(hsv2[0]));
//		sd.append(" ");
//		sd.append(hsv1[1]-hsv2[1]);
//		sd.append(" ");
//		sd.append(hsv1[2]-hsv2[2]);
//		sd.append(" \n");
//		Logger.getAnonymousLogger().log(Level.WARNING, sd.toString());
		 */
 
		if (fromcam) {
			float hdif,sdif,vdif;
			hdif = hsv1[0]-hsv2[0];
			
			sdif = hsv1[1]-hsv2[1];
			
			vdif = hsv1[2]-hsv2[2];
			
			return Float.valueOf(hdif*hdif+sdif*sdif+vdif*vdif).intValue();
		} else {
			if (hsv1[2] < 0.3f && hsv2[2] < 0.3f) return 0;
			if (hsv1[1] < 0.3f && hsv2[1] < 0.3f) return 0;
			if (i1==i2) {
				return 0;
			} else {
				return 1;
			}
		}
//		return Math.max(Math.max(rdif, bdif), gdif);
		//return (int)(Math.sqrt(hrdif*hrdif+hgdif*hgdif+hbdif*hbdif));
		
	}
	public void rgb2lab(int R, int G, int B, int[] lab) {
	    //http://www.brucelindbloom.com

	    float r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
	    float Ls, as, bs;
	    float eps = 216.f/24389.f;
	    float k = 24389.f/27.f;

	    float Xr = 0.964221f;  // reference white D50
	    float Yr = 1.0f;
	    float Zr = 0.825211f;

	    // RGB to XYZ
	    r = R/255.f; //R 0..1
	    g = G/255.f; //G 0..1
	    b = B/255.f; //B 0..1

	    // assuming sRGB (D65)
	    if (r <= 0.04045)
	        r = r/12;
	    else
	        r = (float) Math.pow((r+0.055)/1.055,2.4);

	    if (g <= 0.04045)
	        g = g/12;
	    else
	        g = (float) Math.pow((g+0.055)/1.055,2.4);

	    if (b <= 0.04045)
	        b = b/12;
	    else
	        b = (float) Math.pow((b+0.055)/1.055,2.4);


	    X =  0.436052025f*r     + 0.385081593f*g + 0.143087414f *b;
	    Y =  0.222491598f*r     + 0.71688606f *g + 0.060621486f *b;
	    Z =  0.013929122f*r     + 0.097097002f*g + 0.71418547f  *b;

	    // XYZ to Lab
	    xr = X/Xr;
	    yr = Y/Yr;
	    zr = Z/Zr;

	    if ( xr > eps )
	        fx =  (float) Math.pow(xr, 1/3.);
	    else
	        fx = (float) ((k * xr + 16.) / 116.);

	    if ( yr > eps )
	        fy =  (float) Math.pow(yr, 1/3.);
	    else
	    fy = (float) ((k * yr + 16.) / 116.);

	    if ( zr > eps )
	        fz =  (float) Math.pow(zr, 1/3.);
	    else
	        fz = (float) ((k * zr + 16.) / 116);

	    Ls = ( 116 * fy ) - 16;
	    as = 500*(fx-fy);
	    bs = 200*(fy-fz);

	    lab[0] = (int) (2.55*Ls + .5);
	    lab[1] = (int) (as + .5); 
	    lab[2] = (int) (bs + .5);       
	} 


	public void RGBtoHSV( float r, float g, float b, float[] hsv ) {
		StringBuilder sd = new StringBuilder();
		sd.append("Second array: ");
		sd.append(r);
		sd.append(" ");
		sd.append(g);
		sd.append(" ");
		sd.append(b);
		sd.append(" \n");
		Logger.getAnonymousLogger().log(Level.WARNING, sd.toString());
		float min, max, delta;
		float h,s,v;
		min = Math.min( Math.min(r, g), b );
		max = Math.max( Math.max(r, g), b );
		v = max;				// v
		delta = max - min;
		if( max != 0 )
			s = delta / max;		// s
		else {
			// r = g = b = 0		// s = 0, v is undefined
			s = 0;
			h = -1;
			return;
		}
		float cr,cg,cb;
		cr = (v-r)/delta;
		cg = (v-g)/delta;
		cb = (v-b)/delta;
		if( r == v )
			h = cb-cg;		// between yellow & magenta
		else if( g == v )
			h = 2 + cr-cb;	// between cyan & yellow
		else
			h = 4 + cg-cr;	// between magenta & cyan
		h *= 60;				// degrees
		if( h < 0 )
			h += 360;
		s*=100;
		v*=100;
		hsv[0]=h;
		hsv[1]=s;
		hsv[2]=v;
	}
}