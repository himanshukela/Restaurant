package com.restaurant;


import com.restaurant.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View.OnClickListener;;

public class OrderScreen extends Activity implements OnClickListener {
	TextView orderitem,devidetextview,temp;
	EditText Quantity,devide_quanity;
	 Button Place_Order;
	 String tempstring;
	 Integer i;
	 String s;
	 String quanityforsoup;
	 StringBuilder orderdata=null;
	DatabaseHelper orderHelper;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        orderHelper = new DatabaseHelper(OrderScreen.this);
        
     // initialise form widget
        setContentView(R.layout.orderscren);
        devide_quanity=(EditText)findViewById(R.id.devide_quanity);
        devidetextview=(TextView)findViewById(R.id.devidetextview);
    
        
        orderitem =(TextView)findViewById(R.id.orderitem);
        Quantity =(EditText)findViewById(R.id.Quantity);
        Place_Order =(Button)findViewById(R.id.Place_Order_btn);
       // show_order =(Button)findViewById(R.id.Show_Order_btn);
        
        s= getIntent().getStringExtra("order");
      
        String[] temp;
    
        String delimiter = "-";
        
        // given string will be split by the argument delimiter provided. 
        temp = s.split(delimiter);
        // print substrings 
      
        s=temp[0];
       
        // s=orderdata.toString();
       
        if(s.equals("Veg Manchaw")||s.equals("Tomato")){	
    	   devide_quanity.setVisibility(View.VISIBLE);
    	   devidetextview.setVisibility(View.VISIBLE);
    	}
        else{
    	   devide_quanity.setVisibility(View.INVISIBLE);
    	   devidetextview.setVisibility(View.INVISIBLE);
       }
       orderitem.setText("You have ordered "+s);
      
       
       Quantity.setRawInputType(InputType.TYPE_CLASS_NUMBER);
       devide_quanity.setRawInputType(InputType.TYPE_CLASS_NUMBER);
      
      
       Place_Order.setClickable(true);
      
     
       Place_Order.setOnClickListener(this);
      
   	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Place_Order.setClickable(true);
		  	if(s.equals("Veg Manchaw")||s.equals("Tomato")){	
		  		devide_quanity.setVisibility(View.VISIBLE);
		  		devidetextview.setVisibility(View.VISIBLE);
		   }
	       else{
	    	   devide_quanity.setVisibility(View.INVISIBLE);
	    	   devidetextview.setVisibility(View.INVISIBLE);
	       }
		  orderHelper = new DatabaseHelper(OrderScreen.this);
			//orderHelper.getWritableDatabase();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Place_Order.setClickable(true);
		  	if(s.equals("Veg Manchaw")||s.equals("Tomato")){	
		  		devide_quanity.setVisibility(View.VISIBLE);
		    	devidetextview.setVisibility(View.VISIBLE);
		    }
	       else{
	    	   devide_quanity.setVisibility(View.INVISIBLE);
	    	   devidetextview.setVisibility(View.INVISIBLE);
	       }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//on button click place order
			
			Place_Order.setClickable(false);
			tempstring=Quantity.getText().toString();
		
			i=Integer.parseInt(tempstring); 
			Toast.makeText(OrderScreen.this, s, Toast.LENGTH_LONG);
			String  temps=s;
			s=s+"  "+ "quantity"+"  "+ i;
	      
			if((temps.equals("Tomato")||temps.equals("Veg Manchaw")) && !(devide_quanity.getText().toString().equals("")))  {	
				Log.e("temp","iside");
				s=s+"/"+devide_quanity.getText().toString();
			}	
	   
			//add data to model class object
			boolean add = ModelClass.al.add(s);
		
			//	adding the data 
			orderHelper.addOrder(s);
			/*Intent mIntent = new Intent(OrderScreen.this,MenuScreen.class);
			startActivity(mIntent);*/
			finish();
		
	}
	public boolean checkType()
	{
		return false;
	}
	
	public void onStop(){
		super.onStop();
		orderHelper.close();
		//orderHelper.cursor.close();
	}
}