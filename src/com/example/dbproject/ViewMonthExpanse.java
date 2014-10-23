package com.example.dbproject;

import org.w3c.dom.Text;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewMonthExpanse extends Activity implements OnClickListener{

	Button b1;
	EditText et;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monthiew);
		
		b1 = (Button) findViewById(R.id.button1);
		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView2);
		
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		String monEx = et.getText().toString();
		MyDbControl info = new MyDbControl(this);
		try {
			info.open();
			String disp = info.getMonthData(monEx);
			tv.setText(disp);
			info.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
