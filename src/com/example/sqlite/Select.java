package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class Select {
	public static Cursor WholeTableToRAM(SQLiteDatabase db,String Table){
    String sql="select * from "+Table;
    //db.query(MS,"*", null, null, groupBy, having, orderBy, limit);
    Cursor c=db.rawQuery(sql,null);
 	return c;
	}
}
