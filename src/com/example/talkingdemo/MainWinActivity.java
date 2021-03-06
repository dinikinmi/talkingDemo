package com.example.talkingdemo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.talkingdemo.ui.ExpandableAdapter;
import com.example.talkingdemo.ui.SlidingMenu;

import BackGround.HandleUnsend;
import Global.globalVar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.PopupWindow;

public class MainWinActivity extends Activity{

	private PopupWindow popwin;
	private ArrayList<HashMap<String, String>> MsgData;
	private SimpleAdapter MsgAdapter;
	public Socket sk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//to build a socket,and use as parama in Thread HandleUnsend and Thread
		//DownLoad
		//建立一个公共socket，然后 作为参数 传递 给 HandleUnsed(处理待发送数据的线程)
		//和 DowanLoad（从服务器获取消息的线程）
		
		try{
//			sk=new Socket(globalVar.serverIp,globalVar.Port);
//			HandleUnsend hdl=new HandleUnsend(this.sk);
//			hdl.start();
		}catch(Exception e)
		{
			e.printStackTrace();
			}		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainview);
       
		
		//shall not tranfer prama in that way,the User Id shall be saved in Local disk
//		String json=getIntent().getExtras().getString("json");
		
		ExpandableListView myExpandableListView = (ExpandableListView)findViewById(R.id.mainwin_body_elv);   
        myExpandableListView.setAdapter(new ExpandableAdapter(MainWinActivity.this)); 
        
        myExpandableListView.setOnChildClickListener(new OnChildClickListener()
        {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id)
			{   String talkingWith=ExpandableAdapter.generals[groupPosition][childPosition];
				Log.v("onClick","groupPosition is"+groupPosition+"childPosion"+childPosition+" "+talkingWith);
				Intent intent = new Intent(MainWinActivity.this,TalkActivity.class);
				startActivity(intent);
				return false;
			}       	
        });

        Button slidingmenubtn=(Button)findViewById(R.id.mainwin_title_btn);
        
        slidingmenubtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				SlidingMenu mMenu = (SlidingMenu) findViewById(R.id.mainview);
				mMenu.toggle(); 
			}        	
        });
        

        MsgData= new ArrayList<HashMap<String, String>>();
		for(int i=0;i<15;i++)
		{
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("name","iiiis");
			MsgData.add(hm);
		}
		
		MsgAdapter= new SimpleAdapter
				(this, MsgData, R.layout.mianwin_elv_item, 
    			new String[]{"name"}, //{}is to initialize
    			new int[]{R.id.mainwin_elv_itemname});
		
		final View customView = getLayoutInflater().inflate(
				R.layout.popwin_getmsg, null, false);  
        ListView lv=((ListView) customView.findViewById(R.id.popwin_lv));
    	lv.setAdapter(MsgAdapter);
    	
        Button popmsg=(Button)findViewById(R.id.mainwin_title_btn_pop);
        popmsg.setOnClickListener(new OnClickListener()
        {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if (popwin != null && popwin.isShowing()) 
				{  
	                popwin.dismiss();  
	            } 
				else
				{  
	                popwin = new PopupWindow(customView,LayoutParams.FILL_PARENT, 
	                		LayoutParams.FILL_PARENT, true);
	                MsgAdapter.notifyDataSetChanged(); 
	                //popwin.showAsDropDown(v, -400, 0);
	                //.setOutsideTouchable(true);
	                //showAsDropDown(v, 0, 5);  
	                popwin.setAnimationStyle(R.style.popAnimationFade);
	                popwin.showAtLocation(getLayoutInflater().inflate(R.layout.mainwin,null),
	                		Gravity.LEFT | Gravity.TOP, 0, 0);
	            }  
			}        	
        });
     
        Button popclose=(Button)customView.findViewById(R.id.popwin_close);
        popclose.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				if (popwin != null && popwin.isShowing()) 
				{  
	                popwin.dismiss();  
	            } 
			}
        });
        
        Button groupbtn=(Button)findViewById(R.id.mainwin_body_btn_group);
        groupbtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainWinActivity.this, GroupWinActivity.class);
				startActivity(intent);
			}
        });
	}
	
}
