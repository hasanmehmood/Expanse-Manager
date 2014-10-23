package com.example.dbproject;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;

public class DbView extends Activity{

	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		tv = (TextView) findViewById(R.id.textView3);
		MyDbControl info = new MyDbControl(this);
		try {
			info.open();
			String disp = info.getData();
			tv.setText(disp);
			info.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
