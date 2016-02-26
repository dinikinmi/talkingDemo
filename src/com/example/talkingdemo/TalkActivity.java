package com.example.talkingdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.talkingdemo.ui.ExpandableAdapter;
import com.example.talkingdemo.ui.TalkAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ExpandableListView.OnChildClickListener;

public class TalkActivity extends Activity{
	
	private ArrayList<HashMap<String, String>> talkData;
	private TalkAdapter talkAdapter;
	protected String post;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.talkwin);
		
		talkData= new ArrayList<HashMap<String, String>>();
		
		talkAdapter= new TalkAdapter
				(
				this, talkData, 
				new int[]{R.layout.talk_rightitem,R.layout.talk_leftitem}, 
    			new String[]{"name"}, 
    			new int[][]{{R.id.talkright_tv},{R.id.talkleft_tv}}
				);
		
		ListView lv=((ListView)findViewById(R.id.talk_lv));
		lv.setAdapter(talkAdapter);
    	Button sendbtn=(Button)this.findViewById(R.id.talk_sendbtn);
		sendbtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText et=(EditText)findViewById(R.id.talk_sendText);
				post=et.getText().toString()+" ";
				
				if(post.replace(" ", "")=="")
					return;
				
				//SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd  HH:mm:ss");
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("name", post);
				//hashMap.put("comment_ptime", formatter.format(new Date(System.currentTimeMillis())));
				talkData.add(hashMap);
				talkAdapter.notifyDataSetChanged();
				et.setText("");
				((InputMethodManager)et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
				.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}			
		});
		post=" ";
	}
}
		
		/*
		 * 现在用的是HandleUNsend  和 DownLoadMessage来完成这个过程。
		Thread SendMsgTD = new Thread(new Runnable()
		{
			@Override
			public void run() 
			{						
				
				try {
					
					final Socket client = new Socket( "119.33.123.103", 10000);
					final InputStream in = client.getInputStream();
			        final OutputStream out = client.getOutputStream();	
			        
					
			        Thread GetMsgTD = new Thread(new Runnable()
					{
						@Override
						public void run() 
						{
							try {                     
								System.out.println("��ȡ��ʼ");
						        Reader reader = new InputStreamReader(in,"gb2312");  
			                    CharBuffer charBuffer = CharBuffer.allocate(20000);  
			                    int index = -1;  
			                    while((index=reader.read(charBuffer))!=-1)
			                    {
			                    	System.out.println(String.valueOf(index));
			                        charBuffer.flip();  
			                        System.out.println("client:"+charBuffer.toString());  
			                    }
					            
			                    
			                } catch (Exception e) {  
			                    e.printStackTrace();  
			                }
							
						}
					});
			        GetMsgTD.start();
			        
				        while(true)
			        {
				        	
				        if(post.replace(" ", "")!="")
				        {
				        	out.write(post.getBytes("gb2312"));
				        	post=" ";
				        }


				     }   
				        //BufferedReader is = new BufferedReader(new InputStreamReader(in,"gb2312"));
			            //String gets = is.readLine();
			            //if(gets.trim()!="")
				        //{
				        //	System.out.println("�յ�:" + gets);
				        //}
			            //out.close();
				        //in.close();
				        //client.close();
				        
			        
			        
			        
			        
				} 
				catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}					
		},"");
		SendMsgTD.start();
	}
*/


