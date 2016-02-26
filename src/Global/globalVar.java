package Global;

import java.net.Socket;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class globalVar {
	public static String usn;
public static String serverIp="111.111.111.111";
public static int Port=111;
public static Cursor Student_c;
public static Cursor Chatting_c;
public static SQLiteDatabase sb;
public static String nowTalkingWith;
public static String myId="123";
public static String myAvatar;
public static Socket sk;



public static void buildDatabaseTable(String tableName){
	 String Name=tableName;
	 String str_sqlBuildTable ="CREATE TABLE " +Name+
                " (REMOTE_ID VARCHAR(20), LOCAL_ID VARCHAR(20),REMOTE_AUDIO VERCHAR, LOCAL_AUDIO VARCHAR,"+
	 	    		 "REMOTE_SAY VARCHAR,LOCAL_SAY VARCHAR,MESSAGE_TYPE VARCHAR(10),"
	 	    		  +"SEND_OUT_TIME VARCHAR ,DURATION varchar);" ;
	 	      
	 globalVar.sb.execSQL(str_sqlBuildTable);
} 
    
}




