package com.restaurant;

import java.util.ArrayList;

public class ModelClass {
	//used to save app data

	static String name = null;
	static String email= null;
	static String phone= null;
	static String address = null;
	
	
	static ArrayList<String> al;
	
	public static void createlist(){
		al=new ArrayList<String>();
	}
	

/*	public ModelClass(String name,String phone,String email,String address){
		ModelClass.name = name;
		ModelClass.phone = phone;
		ModelClass.email = email;
		ModelClass.address = address;
	}*/
	
	
	//gettig name
	public String getName(){
		return ModelClass.name;
	}
	//setting name
	public void setName(String name){
		ModelClass.name = name;
	}
	
	public String getEmail(){
		return ModelClass.email;
		
	}
	
	public void setEmail(String email){
		ModelClass.email = email;
	}
	
	public String getPhone(){
		return ModelClass.phone;
	}
	
	public void setPhone(String phone2){
		ModelClass.phone = phone2;
	}
	
	public String getAddress(){
		return ModelClass.address;
	}
	
	public void setAddress(String address){
		ModelClass.address = address;
	}

}
