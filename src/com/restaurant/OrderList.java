package com.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class OrderList extends Activity {
	  ArrayAdapter <String> adapter ;
	  ModelClass orderModelClass;
	  ListView list;
	  static String s1;
	  static String s2;
	  
	  Button gotomenu,placedorder;
	  DatabaseHelper orderHelper;
	  static String s;
	  String cust_name,phone,email,order,address;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.orderlist);
	       //initialise list view to dispay data in list
	        
	        orderModelClass = new ModelClass();
	        list=(ListView)findViewById(R.id.list);
	        gotomenu = (Button)findViewById(R.id.gotomenu_btn);
	        placedorder =(Button)findViewById(R.id.PlaceOrder_btn);
	        
	        
	      //  orderModelClass = orderHelper.getSingleInfo(LoginActivity.KY_PHONE);
	        
	        
	        //System.out.println(mClass.getPhone());
	        cust_name =orderModelClass.getName();
	        phone = orderModelClass.getPhone();
	        email=orderModelClass.getEmail();
	        address =orderModelClass.getAddress();
	        
	      adapter=new ArrayAdapter<String>(OrderList.this,
	            android.R.layout.simple_list_item_1,
	            ModelClass.al);
	        list.setAdapter(adapter);
	        if(ModelClass.al.isEmpty())
	        {
	        	Toast.makeText(this, "Yet no order Is placed Please go to menu section and add order", Toast.LENGTH_LONG).show();
	        	
	        }
	        else{
	        	s2 = ModelClass.al.get(0).toString();
	        }
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0,
						View view, final int position,
						long id) {
					// TODO Auto-generated method stub
					
					//order remove functioanlity on alert box button
					AlertDialog.Builder builder =new AlertDialog.Builder(OrderList.this);
					builder.setTitle("Order Remove");
					builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							s=(String)list.getItemAtPosition(position);
							ModelClass.al.remove(s);
							adapter.notifyDataSetChanged();
						}
					});
					builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					AlertDialog alt=builder.create();
					alt.show();
				}
			});
	        gotomenu.setOnClickListener(new OnClickListener() {
		
	        	@Override
	        	public void onClick(View v) {
	        		Intent menuIntent = new Intent(OrderList.this,MenuScreen.class);
	        		startActivity(menuIntent);
	        	}
	        });  
	        for(int i=1 ;i<ModelClass.al.size();i++){
	        	 s1 = ModelClass.al.get(i).toString();
	        	 s2 += "," + s1;
	        }
	        
			
	      placedorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					DefaultHttpClient hc=new DefaultHttpClient();  
					ResponseHandler <String> res=new BasicResponseHandler();  
					HttpPost postMethod=new HttpPost("http://10.0.2.2/ci_hardik/index.php/welcome/insert_data");  
					
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
					
					nameValuePairs.add(new BasicNameValuePair("cust_name",cust_name));    
					nameValuePairs.add(new BasicNameValuePair("phone", phone));
					nameValuePairs.add(new BasicNameValuePair("email",email));
					nameValuePairs.add(new BasicNameValuePair("order",s2));
					nameValuePairs.add(new BasicNameValuePair("address",address));
					    
					postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					//receive response
					
					String response=hc.execute(postMethod,res);
					Log.e("data is post", response);
					int r = Integer.parseInt(response);
					if(r == 1){
					Toast.makeText(OrderList.this, "Your order has been received", Toast.LENGTH_LONG).show();
					//pd.dismiss();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
	
			}
		});
	 }
}