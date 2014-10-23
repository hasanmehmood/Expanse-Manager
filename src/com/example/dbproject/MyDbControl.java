package com.example.dbproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbControl {
	
	public static final String KEY_DAY = "_day";
	public static final String KEY_MONTH = "_month";
	public static final String KEY_MOB_EXP = "mob_exp";
	public static final String KEY_TEL_EXP = "tele_exp";
	public static final String KEY_RENT_EXP = "rent_exp";
	public static final String KEY_SHOPPING = "shop_charges";
	public static final String KEY_FOOD = "food_exp";
	public static final String KEY_MEDICAL = "medical_exp";
	public static final String KEY_ELECTRICITY = "elect_exp";
	public static final String KEY_FAMILY = "family_exp";
	public static final String KEY_OTHERS = "others_exp";
	
	public static final String KEY_NAME = "person_name";
	public static final String KEY_AGE = "person_age";
	
	private static final String DATABASE_NAME = "ExapnsDb";
	private static final String DATABASE_TABLE = "MyTable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDataBase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_DAY + " TEXT NOT NULL, " +
			KEY_MONTH + " TEXT NOT NULL, " +
			KEY_RENT_EXP + " TEXT NOT NULL, " +
			KEY_FOOD + " TEXT NOT NULL, " +
			KEY_ELECTRICITY + " TEXT NOT NULL, " +
			KEY_SHOPPING + " TEXT NOT NULL, " +
			KEY_MEDICAL + " TEXT NOT NULL, " +
			KEY_TEL_EXP + " TEXT NOT NULL, " +
			KEY_FAMILY + " TEXT NOT NULL, " +
			KEY_OTHERS + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
		
	}
	
	public MyDbControl(Context c){
		ourContext = c;
	}
	
	public MyDbControl open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDataBase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String day, String month, String homeRent, String foodExapnse, String electricityCharges, String shopping, String mdeical, String telephoneCharges, String familyCharges, String others) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_DAY, day);
		cv.put(KEY_MONTH, month);
		cv.put(KEY_RENT_EXP, homeRent);
		cv.put(KEY_FOOD, foodExapnse);
		cv.put(KEY_ELECTRICITY, electricityCharges);
		cv.put(KEY_SHOPPING, shopping);
		cv.put(KEY_MEDICAL, mdeical);
		cv.put(KEY_TEL_EXP, telephoneCharges);
		cv.put(KEY_FAMILY, familyCharges);
		cv.put(KEY_OTHERS, others);
		
		return ourDataBase.insert(DATABASE_TABLE, null, cv);
		
	}

	public String getData() {
		String[] col = new String[]{KEY_DAY, KEY_MONTH, KEY_RENT_EXP, KEY_FOOD, KEY_ELECTRICITY, KEY_SHOPPING, KEY_MEDICAL, KEY_TEL_EXP, KEY_FAMILY, KEY_OTHERS};
		Cursor c = ourDataBase.query(DATABASE_TABLE, col, null, null, null, null, null);
		String res = "";
		int iday = c.getColumnIndex(KEY_DAY);
		int imonth = c.getColumnIndex(KEY_MONTH);
		int irent = c.getColumnIndex(KEY_RENT_EXP);
		int ifood = c.getColumnIndex(KEY_FOOD);
		int ielect = c.getColumnIndex(KEY_ELECTRICITY);
		int ishop = c.getColumnIndex(KEY_SHOPPING);
		int imed = c.getColumnIndex(KEY_MEDICAL);
		int itel = c.getColumnIndex(KEY_TEL_EXP);
		int ifam = c.getColumnIndex(KEY_FAMILY);
		int ioth = c.getColumnIndex(KEY_OTHERS);
		
		
		
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			res = res + c.getString(imonth) + " " + c.getString(iday) + "\n" + "Home Rent*\t\t\t\t\t\t" + c.getString(irent) + "\n" +
					"Food Expanse*\t\t\t\t\t\t" + c.getString(ifood) + "\n" +
					"Electricity Charges*\t\t\t\t" + c.getString(ielect) + "\n" +
					"Shopping+\t\t\t\t\t\t\t" + c.getString(ishop) + "\n" +
					"Medical+\t\t\t\t\t\t\t\t" + c.getString(imed) + "\n" +
					"Telephone Charges+\t\t\t\t" + c.getString(itel) + "\n" +
					"Family Charges+\t\t\t\t\t" + c.getString(ifam) + "\n" +
			"Others+\t\t\t\t\t\t\t\t" + c.getString(ioth) + "\n\n";
		}
		return res;		
	}

	public Boolean getIfNotEntered(int monthDay) {
		// TODO Auto-generated method stub
		String[] col = new String[]{KEY_DAY, KEY_MONTH, KEY_RENT_EXP, KEY_FOOD, KEY_ELECTRICITY, KEY_SHOPPING, KEY_MEDICAL, KEY_TEL_EXP, KEY_FAMILY, KEY_OTHERS};
		Cursor c = ourDataBase.query(DATABASE_TABLE, col, null, null, null, null, null);
		String res = "";
		int iday = c.getColumnIndex(KEY_DAY);
		int day;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			res = c.getString(iday);
			day = Integer.parseInt(res);
			if (day == monthDay){
				return false;
			}
		}
		
		return true;
	}

	public String getMonthData(String monEx) {
		// TODO Auto-generated method stub
		String[] col = new String[]{KEY_DAY, KEY_MONTH, KEY_RENT_EXP, KEY_FOOD, KEY_ELECTRICITY, KEY_SHOPPING, KEY_MEDICAL, KEY_TEL_EXP, KEY_FAMILY, KEY_OTHERS};
		Cursor c = ourDataBase.query(DATABASE_TABLE, col, null, null, null, null, null);
		String res = "";
		int iday = c.getColumnIndex(KEY_DAY);
		int imonth = c.getColumnIndex(KEY_MONTH);
		int irent = c.getColumnIndex(KEY_RENT_EXP);
		int ifood = c.getColumnIndex(KEY_FOOD);
		int ielect = c.getColumnIndex(KEY_ELECTRICITY);
		int ishop = c.getColumnIndex(KEY_SHOPPING);
		int imed = c.getColumnIndex(KEY_MEDICAL);
		int itel = c.getColumnIndex(KEY_TEL_EXP);
		int ifam = c.getColumnIndex(KEY_FAMILY);
		int ioth = c.getColumnIndex(KEY_OTHERS);
		
		String sDay;
		String sMonth;
		String sRent;
		String sFood;
		String sElect;
		String sShop;
		String sMed;
		String sTel;
		String sFam;
		String sOth;
		
		int nDay = 0;
		int nMonth = 0;
		int nRent = 0;
		int nFood = 0;
		int nElect = 0;
		int nShop = 0;
		int nMed = 0;
		int nTel = 0;
		int nFam = 0;
		int nOth = 0;
		
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			sMonth = c.getString(imonth);
			if (sMonth.contentEquals(monEx)){
				sRent = c.getString(irent);
				nRent = nRent + Integer.parseInt(sRent);
				sFood = c.getString(ifood);
				nFood = nFood + Integer.parseInt(sFood);
				sElect = c.getString(ielect);
				nElect = nElect + Integer.parseInt(sElect);
				sShop = c.getString(ishop);
				nShop = nShop + Integer.parseInt(sShop);
				sMed = c.getString(imed);
				nMed = nMed + Integer.parseInt(sMed);
				sTel = c.getString(itel);
				nTel = nTel + Integer.parseInt(sTel);
				sFam = c.getString(ifam);
				nFam = nFam + Integer.parseInt(sFam);
				sOth = c.getString(ioth);
				nOth= nOth + Integer.parseInt(sOth);
			}
		}
		
		res = res + monEx + " " + "" + "\n" + "Home Rent*\t\t\t\t\t\t" + nRent + "\n" +
				"Food Expanse*\t\t\t\t\t\t" + nFood + "\n" +
				"Electricity Charges*\t\t\t\t" + nElect + "\n" +
				"Shopping+\t\t\t\t\t\t\t" + nShop + "\n" +
				"Medical+\t\t\t\t\t\t\t\t" + nMed + "\n" +
				"Telephone Charges+\t\t\t\t" + nTel + "\n" +
				"Family Charges+\t\t\t\t\t" + nFam + "\n" +
		"Others+\t\t\t\t\t\t\t\t" + nOth + "\n\n";
		
		
		return res;
	}

	public String getDayData(String dayEx, String monEx) {
		// TODO Auto-generated method stub
		String[] col = new String[]{KEY_DAY, KEY_MONTH, KEY_RENT_EXP, KEY_FOOD, KEY_ELECTRICITY, KEY_SHOPPING, KEY_MEDICAL, KEY_TEL_EXP, KEY_FAMILY, KEY_OTHERS};
		Cursor c = ourDataBase.query(DATABASE_TABLE, col, null, null, null, null, null);
		String res = "";
		int iday = c.getColumnIndex(KEY_DAY);
		int imonth = c.getColumnIndex(KEY_MONTH);
		int irent = c.getColumnIndex(KEY_RENT_EXP);
		int ifood = c.getColumnIndex(KEY_FOOD);
		int ielect = c.getColumnIndex(KEY_ELECTRICITY);
		int ishop = c.getColumnIndex(KEY_SHOPPING);
		int imed = c.getColumnIndex(KEY_MEDICAL);
		int itel = c.getColumnIndex(KEY_TEL_EXP);
		int ifam = c.getColumnIndex(KEY_FAMILY);
		int ioth = c.getColumnIndex(KEY_OTHERS);
		
		String sDay;
		String sMonth;
		String sRent;
		String sFood;
		String sElect;
		String sShop;
		String sMed;
		String sTel;
		String sFam;
		String sOth;
		
		int nDay = 0;
		int nMonth = 0;
		int nRent = 0;
		int nFood = 0;
		int nElect = 0;
		int nShop = 0;
		int nMed = 0;
		int nTel = 0;
		int nFam = 0;
		int nOth = 0;
		
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			sMonth = c.getString(imonth);
			sDay = c.getString(iday);
			
			if (sMonth.contentEquals(monEx) && sDay.contentEquals(dayEx)){
				sRent = c.getString(irent);
				nRent = Integer.parseInt(sRent);
				sFood = c.getString(ifood);
				nFood = Integer.parseInt(sFood);
				sElect = c.getString(ielect);
				nElect = Integer.parseInt(sElect);
				sShop = c.getString(ishop);
				nShop = Integer.parseInt(sShop);
				sMed = c.getString(imed);
				nMed = Integer.parseInt(sMed);
				sTel = c.getString(itel);
				nTel = Integer.parseInt(sTel);
				sFam = c.getString(ifam);
				nFam = Integer.parseInt(sFam);
				sOth = c.getString(ioth);
				nOth= Integer.parseInt(sOth);
				break;
			}
		}
		
		res = res + monEx + " " + dayEx + "\n" + "Home Rent*\t\t\t\t\t\t" + nRent + "\n" +
				"Food Expanse*\t\t\t\t\t\t" + nFood + "\n" +
				"Electricity Charges*\t\t\t\t" + nElect + "\n" +
				"Shopping+\t\t\t\t\t\t\t" + nShop + "\n" +
				"Medical+\t\t\t\t\t\t\t\t" + nMed + "\n" +
				"Telephone Charges+\t\t\t\t" + nTel + "\n" +
				"Family Charges+\t\t\t\t\t" + nFam + "\n" +
		"Others+\t\t\t\t\t\t\t\t" + nOth + "\n\n";
		

		
		return res;
	}

}
