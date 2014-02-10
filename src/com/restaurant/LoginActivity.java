package com.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText nameText,phoneText;
	Button registeredButton,newUser;
	ModelClass loginModelClass;
	DatabaseHelper dbHelper;
	static final String KEY_NAME = "name";
	static final String KY_PHONE = "phone";
	boolean validate;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginModelClass = new ModelClass();
		
		try{
			dbHelper = new DatabaseHelper(LoginActivity.this);
		//	dbHelper.getWritableDatabase();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		nameText = (EditText)findViewById(R.id.user_text);
		phoneText =(EditText)findViewById(R.id.pass_text);
		
		registeredButton = (Button)findViewById(R.id.registerd_user);
		newUser = (Button)findViewById(R.id.new_user);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String uname = pref.getString(KEY_NAME, "Your Name");
		String mphone = pref.getString(KY_PHONE, "Your phone no");
		nameText.setText(uname);
		phoneText.setText(mphone);
		
		newUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String cust_name = nameText.getText().toString();
				String mphone =phoneText.getText().toString();
				
				if(cust_name.equals("")||mphone.equals("")){
					Toast.makeText(LoginActivity.this, "Please enter the data", Toast.LENGTH_SHORT).show();	
				
				}
				
				else if(dbHelper.validateUser(cust_name, mphone)){
					//Toast.makeText(LoginActivity.this, "allowed", Toast.LENGTH_SHORT).show();
					startManagingCursor(dbHelper.cursor);
					Intent i =new Intent(LoginActivity.this,TimmyRestaurantActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("cust_name", cust_name);
					i.putExtras(bundle);
					startActivity(i);
				}
				
				 
				else {
					Toast.makeText(LoginActivity.this, "Your are not registered pls click on Registration", Toast.LENGTH_SHORT).show();
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
					Editor ed = preferences.edit();
					ed.putString(KEY_NAME, cust_name);
					ed.putString(KY_PHONE, mphone);
					ed.commit();
				}
				
			}
		});
		registeredButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iL =new Intent(LoginActivity.this,custinfo.class);
				startActivity(iL);
			}
		});
	}
	public void onResume(){
		super.onResume();
		dbHelper = new DatabaseHelper(LoginActivity.this);
		//dbHelper.getWritableDatabase();
	}
	public void onStop(){
		super.onStop();
		dbHelper.close();
		//dbHelper.cursor.close();
	}
}
