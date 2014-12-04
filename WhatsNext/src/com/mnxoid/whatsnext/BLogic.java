package com.mnxoid.whatsnext;

import java.util.Calendar;
import android.annotation.SuppressLint;

public class BLogic {
	public String classString;
	public String untilString;
	public String audString;
	static int stamps[] = {
		306,
		333,
		336,
		363,
		372,
		399,
		402,
		429,
		438,
		465,
		468,
		495,
		513,
		540,
		543,
		570
	};
	static String classes[] = {
		"5 minute break",				//0
		"15 minute break",				//1
		"30 minute break",				//2
		"Mathematical analysis",		//3
		"Physics",						//4
		"Differential equations",		//5
		"Psychology",					//6
		"Object-oriented programming",	//7
		"Computer system architecture"	//8
	};
	static int monday[][] = {
		{3,1,509,4},
		{0,0,509,4},
		{3,1,509,4},
		{1,0,139,0},
		{4,0,139,0},
		{0,0,139,0},
		{4,0,139,0},
		{1,0,214,4},
		{5,0,214,4},
		{0,0,214,4},
		{5,0,214,4}
	};
	static int tuesday1[][] = {
		{5,0,214,4},
		{0,0,214,4},
		{5,0,214,4},
		{1,0,511,4},
		{3,0,511,4},
		{0,0,511,4},
		{3,0,511,4}
	};
	static int tuesday2[][] = {
		{6,0,117,1},
		{0,0,117,1},
		{6,0,117,1},
		{1,0,511,4},
		{3,0,511,4},
		{0,0,511,4},
		{3,0,511,4},
		{1,0,225,4},
		{7,1,225,4},
		{0,0,225,4},
		{7,1,225,4},
		{2,0,509,4},
		{3,1,509,4},
		{0,0,509,4},
		{3,1,509,4}
	};
	static int wednesday1[][] = {
		{5,1,509,4},
		{0,0,509,4},
		{5,1,509,4},
		{1,0,214,4},
		{8,0,214,4},
		{0,0,214,4},
		{8,0,214,4},
		{1,0,225,4},
		{8,1,225,4},
		{0,0,225,4},
		{8,1,225,4}
	};
	static int wednesday2[][] = {
		{5,1,509,4},
		{0,0,509,4},
		{5,1,509,4},
		{1,0,214,4},
		{7,0,214,4},
		{0,0,214,4},
		{7,0,214,4}
	};
	static int thursday1[][] = {
		{0,0,410,2},
		{0,0,410,2},
		{0,0,410,2},
		{1,0,410,2},
		{4,1,410,2},
		{0,0,410,2},
		{4,1,410,2},
		{1,0,225,4},
		{7,1,225,4},
		{0,0,225,4},
		{7,1,225,4}
	};
	static int thursday2[][] = {
		{4,0,51,10},
		{0,0,51,10},
		{4,0,51,10},
		{1,0,410,2},
		{4,1,410,2},
		{0,0,410,2},
		{4,1,410,2},
		{1,0,225,4},
		{7,1,225,4},
		{0,0,225,4},
		{7,1,225,4}
	};
	static int friday[][] = {
		{0,0,214,4},
		{0,0,214,4},
		{0,0,214,4},
		{1,0,214,4},
		{7,0,214,4},
		{0,0,214,4},
		{7,0,214,4},
		{1,0,403,2},
		{6,1,403,2},
		{0,0,403,2},
		{6,1,403,2}
	};
	static String corpses[] = {
		"M",
		"I",
		"II",
		"III",
		"IV",
		"V",
		"VI",
		"VII",
		"VIII",
		"IX",
		"X"
	};
	@SuppressLint("DefaultLocale")
	public static BLogic get() {
		BLogic retBLogic = new BLogic();
		long millis = System.currentTimeMillis();
		int secondsNow = (int)(millis/1000)+2*3600;
		int minutesNow = secondsNow / 60;
		int hoursNow = minutesNow / 60;
		hoursNow = hoursNow % 24;
		minutesNow = minutesNow % 60;
		secondsNow = secondsNow% 60;
		int currStamp = hoursNow*3600+minutesNow*60+secondsNow;
		int i=0;
		//------------------------------------------------------------------
		int dayOfWeek = (Calendar.getInstance()).get(Calendar.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		int weekOfYear = (Calendar.getInstance()).get(Calendar.WEEK_OF_YEAR);
		System.out.println(weekOfYear);
		//------------------------------------------------------------------
		int table[][];
		int nextable[][];
		int tablelen;
		switch (dayOfWeek) {
		case Calendar.MONDAY:
			table = monday;
			if(weekOfYear%2==1) nextable = tuesday1; else nextable = tuesday2;
			tablelen = 11;
			break;
		case Calendar.TUESDAY:
			if(weekOfYear%2==1)
			{
				table = tuesday1;
				nextable = wednesday1;
				tablelen = 7;
			}
			else {
				table = tuesday2;
				nextable = wednesday2;
				tablelen = 15;
			}
			break;
		case Calendar.WEDNESDAY:
			if(weekOfYear%2==1)
			{
				table = wednesday1;
				nextable = thursday1;
				tablelen = 11;
			}
			else {
				table = wednesday2;
				nextable = thursday2;
				tablelen = 7;
			}
			break;
		case Calendar.THURSDAY:
			if(weekOfYear%2==0) {
				table = thursday1;
				tablelen = 11;
				i+=4;
			} else {
				table = thursday2;
				tablelen = 11;
			}
			nextable = friday;
			break;
		case Calendar.FRIDAY:
			i+=4;
			table = friday;
			nextable = monday;
			tablelen = 11;
			break;
		case Calendar.SATURDAY:
			i=16;
			currStamp -= 2*24*3600;
			table = monday;
			nextable = monday;
			tablelen = 11;
			break;
		default:
			currStamp -= 24*3600;
			i=16;
			table = monday;
			if(weekOfYear%2==1) nextable = tuesday2; else nextable = tuesday1;
			tablelen = 11;
			break;
		}
		for(int j = 0; j<tablelen;j++)
		{
			System.out.println(classes[table[j][0]]);
		}
		boolean secpair;
		if(i>0) secpair = true; else secpair = false;
		System.out.println(secpair);
		//------------------------------------------------------------------
		while(i<16 && currStamp > stamps[i]*100) i++;
		System.out.println("i is" + i);
		boolean nextday = false;
		if (i==0 || ((i<5) && (secpair))) {
			retBLogic.untilString = "Time until classes:\n";
		} else if (i==tablelen) {
			retBLogic.untilString = "Time until the end:\n";
		} else if (i>tablelen) {
			nextday = true;
			if (dayOfWeek==Calendar.FRIDAY) currStamp -= 2*24*3600;
			retBLogic.untilString = "Time until classes:\n";
			i=0;
		} else if (i%2==1) {
			retBLogic.untilString = "Time until break:\n";
		} else if (i%4==0){
			retBLogic.untilString = "Time until class:\n";
		} else {
			retBLogic.untilString = "Time until second half-class:\n";
		}
		System.out.println(retBLogic.untilString);
		if(!nextday) {
			currStamp = stamps[i]*100 - currStamp;
		} else {
			currStamp = 24*3600 + stamps[i]*100 - currStamp;
			table = nextable;
		}
		if(table[1][0]==0)secpair = true;
		if (secpair) currStamp+=2*45*60+20*60;
		secondsNow = currStamp % 60;
		minutesNow = currStamp / 60;
		hoursNow = minutesNow / 60;
		minutesNow = minutesNow % 60;
		if(i>=tablelen)
		{
			table = nextable;
			i=0;
		}
		
		retBLogic.classString = classes[table[i][0]];
		if (table[i][0]>2) {
			if (table[i][1]==0) retBLogic.classString += "\n(lecture)";
			else {
				if (table[i][0]!=7) retBLogic.classString += "\n(practice)";
				else retBLogic.classString += "\n(lab)";
			}
		}
		if(secpair&&i<3) retBLogic.classString = classes[table[4][0]];
		retBLogic.audString = String.format("%d / %s", table[i][2],corpses[table[i][3]]);
		
		System.out.println(retBLogic.untilString);
		retBLogic.untilString = String.format("%s%d:%d:%d",retBLogic.untilString,hoursNow,minutesNow,secondsNow);
		System.out.println(retBLogic.untilString);
		
		System.out.println(retBLogic.audString);
		return retBLogic;
	}
}
