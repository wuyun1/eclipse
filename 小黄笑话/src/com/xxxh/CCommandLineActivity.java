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

public class CCommandLineActivity extends Activity {

	Button btn,btn_gotoj;
	EditText et;
	TextView tw;
	ScrollView sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commandline);
		
		btn = (Button) findViewById(R.id.c_button1);
		btn_gotoj = (Button) findViewById(R.id.c_btn_gotoj);
		et = (EditText) findViewById(R.id.c_editText1);
		tw = (TextView) findViewById(R.id.c_textView1);
		sv = (ScrollView)findViewById(R.id.c_scrollView);
		
		btn_gotoj.setOnClickListener(new OnClickListener() {					
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 /* 新建一个Intent对象 */
		        Intent intent = new Intent();
		        intent.putExtra("name","LeiPei");    
		        /* 指定intent要启动的类 */
		        intent.setClass(CCommandLineActivity.this, MainActivity.class);
		        /* 启动一个新的Activity */
		        CCommandLineActivity.this.startActivity(intent);
		        /* 关闭当前的Activity */
		        CCommandLineActivity.this.finish();
			}
		});
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				tw.setText("");
				String cmdstr = et.getText().toString();
				tw.setText(tw.getText()+"\n\n$"+cmdstr+"\n\n");
				
				
				String result="";
				try {  
					CmdResult cr = runCommand(cmdstr);
					result = cr.consoleStr;
					System.out.println(result);
					System.out.println("\nExited with error code:" + cr.levelError); 
		            result+="\n$Exited with error code:" + cr.levelError;
		  
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
		getMenuInflater().inflate(R.menu.ccommand_line, menu);
		return true;
	}
	
	public native CmdResult runCommand(String cmdStr);
	static{
		System.loadLibrary("CCommandLineActivity");
	}

}
