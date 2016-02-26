package utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.talkingdemo.MainActivity;
import com.example.talkingdemo.MainWinActivity;


import Global.globalVar;
import Global.mySocket;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class syncSocketTask extends AsyncTask<String, Integer, String> {
	
	Context context;
	int id;
	String Usn;
	String pwd;
	String loginmsg;
	public static boolean Login=false;

	
	//seems like the id is not used
	
	
	public syncSocketTask(Context context,String userName,String pwd)
	{
		
		this.context=context;
		this.Usn=userName;
		this.pwd=pwd;
		
		}

	
    @Override  
    protected void onPreExecute() { 
    	
    	//when i press login,it jump to here
    	JSONObject json=new JSONObject();  
        try {
			json.put("userName", Usn);
			json.put("Password", pwd); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        loginmsg=json.toString();
        Log.v("Sign","Sign"+loginmsg);
    }  

	@Override
	protected String doInBackground(String... params) {
		try {			
			//第一次运行时，onpreExcecute执行完后跳到此，第二次按sign的时候就不跳了。
			//我猜传进来的两个参数，一个是 Socket的IP地址，一个是 端口吧?
			//但是这两个参数是何时传入来?这个 doInBackgroud的方法是在哪里被调用的呢？？
			//从Log 上看来，应该是 excecute（“IP”，Port）时传入来的 
			Log.v("doInBack","do in back ground start");
			Log.v("test 123",params[2]+" "+params[3]);
			
			
			
			final Socket client = new Socket( params[0],Integer.valueOf(params[1]));
			    			     
			//the program block here
				final InputStream in = client.getInputStream();
				final OutputStream out = client.getOutputStream();	
				out.write(loginmsg.getBytes("gb2312"));
				//将onPreExcute获得的密码send给 服务器
			    Reader reader = new InputStreamReader(in,"gb2312");  
			    //creat a reader to a certain inputStream and set charSet as gb2312
			    //change the byte flow into char flow
			    Log.v("reader","ready to read the stream from server");
			    CharBuffer charBuffer = CharBuffer.allocate(20000);  
			    //use allocate to set the capacity=1024
                StringBuilder serverRespone=new StringBuilder();
                int i;
                while((i=reader.read(charBuffer))!=-1)
                {
                    charBuffer.flip(); 
                	serverRespone.append(charBuffer,0,i);
                	Log.v("Respone","Respone"+charBuffer.position());
                	//I guess the start point 0 means 
                	//the distance from the cursor,but not the distance from 
                	//the beginnin g of charBuffer
                }
               
                //now to check whether the login is success.if sever response "1"
                //that it means success, if not ,login fail
                switch(Integer.valueOf(serverRespone.toString()))
                {
                case 1:
                //if response is 1 ,means it is 	
                this.Login=true;
                break;
                case 2:
                	//if login fail ,do nothing
                	
                	break;
                }
                //Flips this buffer. The limit is set to the current position 
                //and then the position is set to zero.
                //If the mark is defined then it is discarded.
                
                //now convert the cahrBuffer to string,
                //and use a switch-case to judge it
                
                
                
// client.close(); if login success. then we keep using this socket
                
                in.close();
                out.close();
                
                return charBuffer.toString();
            
             } 
		catch (Exception e) {  
                    e.printStackTrace();  
                }
		return null;
	}


    @Override  
    protected void onPostExecute(String result) {  
//        if (result != null) {      	
        	try {      		
        		//JSONObject  dataJson=new JSONObject(result);
            	//JSONObject  dt=dataJson.getJSONObject("detail");
            	//JSONArray dt=dataJson.getJSONArray("detail");
            	//JSONObject info=dt.getJSONObject(i);
        		if(this.Login){		        
				//if login success,Login=ture...go to MainWin
//        		Intent intent = new Intent(context, MainWinActivity.class);
//				intent.putExtra("json"," result");
//				context.startActivity(intent);
        		}else{
        			//show a dialog to tell user Login fail
        			}
        		
			} 
        	//catch (JSONException e) {
			//	e.printStackTrace();
			//} 
        	catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
//        else {  
            //Toast.makeText(MainActivity.this,  
            //        "get image from network error", Toast.LENGTH_SHORT).show();  
//        }

//    }  

	@Override  
    protected void onCancelled() {  
    	
    }  

}
