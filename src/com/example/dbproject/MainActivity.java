package com.example.dbproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	Button b1, b2, b3, b4, b5, b6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		b5 = (Button) findViewById(R.id.button5);
		b6 = (Button) findViewById(R.id.button6);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		
		
		
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.button1:
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			
			Boolean check1 = true;
			try {
				MyDbControl info = new MyDbControl(this);
				info.open();
				check1 = info.getIfNotEntered(today.monthDay);
				info.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(check1 == false){
				Dialog d = new Dialog(this);
				d.setTitle("You Already Entered Today's Exapanse");
				TextView tv = new TextView(this);
				tv.setText("Successfull");
				d.setContentView(tv);
				d.show();
				tv.setText("Sir \n You Already Entered today's Exapnse");
				return;
				
			} else {
				try {
					Intent i = new Intent("com.example.dbproject.ADDDATATODB");
					startActivity(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.button2:
			try {
				Intent i1 = new Intent("com.example.dbproject.DBVIEW");
				startActivity(i1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.button3:
			try {
				Intent i2 = new Intent("com.example.dbproject.ABOUTME");
				startActivity(i2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.button4:
			try {
				finish();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.button5:
			try {
				Intent i3 = new Intent("com.example.dbproject.VIEWMONTHEXPANSE");
				startActivity(i3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.button6:
			try {
				Intent i4 = new Intent("com.example.dbproject.VIEWDAY");
				startActivity(i4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
