package com.xxxh;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btn,btn_gotoc,btn_getR;
	EditText et;
	TextView tw;
	ScrollView sv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.button1);
		btn_gotoc = (Button) findViewById(R.id.btn_gotoc);
		btn_getR = (Button) findViewById(R.id.btn_getRoot);
		et = (EditText) findViewById(R.id.editText1);
		tw = (TextView) findViewById(R.id.textView1);
		sv = (ScrollView)findViewById(R.id.scrollView);
		btn_getR.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SystemMgr.upgradeRootPermission(getPackageCodePath());  
			}
		});
		btn_gotoc.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 /* 新建一个Intent对象 */
		        Intent intent = new Intent();
		        intent.putExtra("name","LeiPei");    
		        /* 指定intent要启动的类 */
		        intent.setClass(MainActivity.this, CCommandLineActivity.class);
		        /* 启动一个新的Activity */
		        MainActivity.this.startActivity(intent);
		        /* 关闭当前的Activity */
		        MainActivity.this.finish();
			}
		});
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				tw.setText("");
				String cmdstr = et.getText().toString();
				tw.setText(tw.getText()+"\n$"+cmdstr+"\n\n");
				

				String result="";
				try {  
		            Runtime rt = Runtime.getRuntime();  
		            Process pr = rt.exec(cmdstr); // cmd /c calc  
		            // Process pr = rt.exec("D:\\xunlei\\project.aspx");  
		  
		            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));  
		  
		            String line = null;  
		  
		            while ((line = input.readLine()) != null) {  
		                System.out.println(line+"\n");
		                result+=line+"\n";
		            }  
		  
		            int exitVal = pr.waitFor();  
		            System.out.println("\nExited with error code " + exitVal); 
		            result+="\n$Exited with error code:" + exitVal;
		  
		        } catch (Exception e) {  
		        	result+=e.getMessage();
		            e.printStackTrace();  
		        }  
				
				tw.setText(tw.getText()+result);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sv.fullScroll(ScrollView.FOCUS_DOWN);
					}
				}).start();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	static{
//		System.loadLibrary("hello");
//	}
//	
//	public native String sayHello();
//	
//	public native void system(String s);

}
