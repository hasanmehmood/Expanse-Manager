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

public class ViewDay extends Activity implements OnClickListener{

	
	Button b1;
	TextView tv;
	EditText et1, et2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewday);
		
		b1 = (Button) findViewById(R.id.button1);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		tv = (TextView) findViewById(R.id.textView3);
		
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		String dayEx = et1.getText().toString();
		String monEx = et2.getText().toString();
		
		MyDbControl info = new MyDbControl(this);
		try {
			info.open();
			String disp = info.getDayData(dayEx, monEx);
			tv.setText(disp);
			info.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
