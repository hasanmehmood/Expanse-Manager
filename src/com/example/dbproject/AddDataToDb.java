package com.example.dbproject;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddDataToDb extends Activity implements OnClickListener{

	
	Button button;
	EditText et1, et2, et3, et4, et5, et6, et7, et8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddataxml);
		
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		et4 = (EditText) findViewById(R.id.editText4);
		et5 = (EditText) findViewById(R.id.editText5);
		et6 = (EditText) findViewById(R.id.editText6);
		et7 = (EditText) findViewById(R.id.editText7);
		et8 = (EditText) findViewById(R.id.editText8);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		boolean check = true;
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		String day = "" + today.monthDay;
		int mon = today.month;
		String month = CalMonth(mon+1);
		try{
		String homeRent = et1.getText().toString();
		String foodExapnse = et2.getText().toString();
		String electricityCharges = et3.getText().toString();
		String shopping = et4.getText().toString();
		String mdeical = et5.getText().toString();
		String telephoneCharges = et6.getText().toString();
		String familyCharges = et7.getText().toString();
		String others = et8.getText().toString();
		
		MyDbControl entry = new MyDbControl(AddDataToDb.this);
		entry.open();
		entry.createEntry(day, month, homeRent, foodExapnse, electricityCharges, shopping, mdeical, telephoneCharges, familyCharges, others);
		entry.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			check = false;
			Dialog d = new Dialog(this);
			d.setTitle("fAILED");
			TextView tv = new TextView(this);
			tv.setText("Successfull");
			tv.setTextColor(new Color().WHITE);
			d.setContentView(tv);
			d.show();
		} finally {
			if (check){
				Dialog d = new Dialog(this);
				d.setTitle("Expenses Added");
				TextView tv = new TextView(this);
				tv.setText("Successfull");
				tv.setTextColor(new Color().WHITE);
				d.setContentView(tv);
				d.show();
			}
		}
		
	}
	
	private String CalMonth(int mon) {
		// TODO Auto-generated method stub
		String month = "";
		if(mon == 1){
			month = "January";
		}else if(mon == 2){
			month = "February";
		}else if(mon == 3){
			month = "March";
		}else if(mon == 4){
			month = "April";
		}else if(mon == 5){
			month = "May";
		}else if(mon == 6){
			month = "June";
		}else if(mon == 7){
			month = "July";
		}else if(mon == 8){
			month = "August";
		}else if(mon == 9){
			month = "September";
		}else if(mon == 10){
			month = "October";
		}else if(mon == 11){
			month = "November";
		}else if(mon == 12){
			month = "December";
		}
		return month;
	}

}
