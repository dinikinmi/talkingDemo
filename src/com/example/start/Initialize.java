package com.example.start;

import java.util.Random;

import com.example.sqlite.MySqlite;
import com.example.sqlite.Select;
import com.example.talkingdemo.MainActivity;
import com.example.talkingdemo.MainWinActivity;

import BackGround.FileReceive;
import BackGround.HandleUnsend;
import Global.globalVar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public  class Initialize extends Activity {
	public  globalVar gb=new globalVar();
    public Select sl=new Select();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
		
        super.onCreate(savedInstanceState);
        MySqlite mySqlite=new MySqlite(this);
//        FileReceive fileReceive=new FileReceive();
//        fileReceive.start();
        globalVar.sb=mySqlite.getWritableDatabase();
      ////ramdon test
        String str[]={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
                ,"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    		   Random e = new Random();
    		   int n=0;
    	       String id="";
    	       for(int i=0;i<10;i++){
    	           n=e.nextInt(str.length-1);
    	           id+=str[n];}
        ////////////////////
        int c=2;
       /*
        for(c=0;c<10;c++){
        	String II=Regist.generateId();
            String strc="insert into MS(STUDENTS_ID,Name,Unread) values('"+II+"','"+II+"','0');"; 
        	Global.sb.execSQL(strc);      
        }
        */
        int b=0;
        int a=0;
        for(b=0;b<10;b++){
        String sql2="insert into CHATTING(REMOTE_ID,LOCAL_ID,REMOTE_AUDIO,LOCAL_AUDIO,REMOTE_SAY,LOCAL_SAY,MESSAGE_TYPE,SEND_OUT_TIME) values ('RmI"  +b+   "','LCID"  +b+   "','RmA"+b+"','d','e','f','g','"+b+"');";
        Log.v("sql2",""+sql2);
        globalVar.sb.execSQL(sql2);}
        globalVar.Student_c=Select.WholeTableToRAM(globalVar.sb, "MS");
        globalVar.Chatting_c=Select.WholeTableToRAM(globalVar.sb, "CHATTING");
        
//        HandleUnsend handleUnsend=new HandleUnsend();
        Log.i("handleUnsend Thread","handle Unsend Thread build and start");
//        handleUnsend.start();
        
        //start MainWinActivity for testing,
        //the right activity should be started is MainActiviy
        Intent i=new Intent(Initialize.this,MainWinActivity.class);
        startActivity(i);
        
	}
}
