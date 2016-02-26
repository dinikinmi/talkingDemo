package com.example.talkingdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.syncSocketTask;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	public static String userName;
	public static String passWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        final EditText Usn=(EditText)findViewById(R.id.username_edit);
        final EditText Psw=(EditText)findViewById(R.id.password_edit);
		((Button)findViewById(R.id.signin_button)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				//测试，直接跳入主界面
//				Intent intent=new Intent(MainActivity.this,MainWinActivity.class);
//				startActivity(intent);
				
                 userName=Usn.getText().toString();
                 passWord=Psw.getText().toString();
				syncSocketTask synctask=new syncSocketTask(MainActivity.this,userName,passWord);
				synctask.execute("182.254.210.160","10000","123","111"); 
				
				//我在后面加了一个String 参数竟然都没有报错，可能与参数是 泛型 有关
				//参数或许是  (string...pram)
				/*
				 * 在使用的时候，有几点需要格外注意：
1.异步任务的实例必须在UI线程中创建。
2.execute(Params... params)方法必须在UI线程中调用。
3.不要手动调用onPreExecute()，doInBackground(Params... params)，onProgressUpdate(Progress... values)，onPostExecute(Result result)这几个方法。
4.不能在doInBackground(Params... params)中更改UI组件的信息。
5.一个任务实例只能执行一次，如果执行第二次将会抛出异常。
				 */
				
				Log.v("execute","execute");
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
