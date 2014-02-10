package com.restaurant;

import com.restaurant.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InfoScreen extends Activity {
	//used to displya info of app
	Button help;
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //load layout
	        setContentView(R.layout.info);
	        help=(Button)findViewById(R.id.Help);
	        help.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//starting new activity on button click
					Intent i=new Intent(InfoScreen.this,HelpGuide.class);
					startActivity(i);
					
				}
			});

}
}