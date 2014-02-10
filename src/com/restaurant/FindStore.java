package com.restaurant;

import com.restaurant.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class FindStore extends Activity {
	WebView mWebView;
	public void onCreate(Bundle savedInstanceState) {
	        
		//webview is used to open google map
			super.onCreate(savedInstanceState);
	        setContentView(R.layout.findstore);
	        mWebView = (WebView) findViewById(R.id.webview);
	        mWebView.getSettings().setJavaScriptEnabled(true);
	        //mWebView.loadUrl("http://maps.google.co.in/maps?hl=en&q=mangalore&bav=on.2,or.r_gc.r_pw.r_qf.,cf.osb&biw=1024&bih=643&um=1&ie=UTF-8&sa=N&tab=wl");
	        mWebView.loadUrl("http://www.maps.google.com");
	        //mWebView.loadUrl("http://maps.google.co.in/maps?hl=en&tab=wl");
	 }

}
