package com.example.talkingdemo.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TalkAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<HashMap<String, String>> al;
	private int[] layid;
	private String[] itemname;
	private int[][] itemid;
	private int kind;

	public TalkAdapter(Context context, ArrayList<HashMap<String,String>> al, 
			int[] layid, String[] itemname, int[][] itemid)
	{
		this.context=context;
		this.al=al;
		this.layid=layid;
		this.itemname=itemname;
		this.itemid=itemid;
		this.kind=0;
	}

	@Override
	public int getCount() {
		return al.size();
	}

	@Override
	public Object getItem(int position) {
		return al.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		RelativeLayout layout=null;
		layout = (RelativeLayout) inflater.inflate(layid[kind], null);
		
		for(int i=0;i<itemname.length;i++)
		{
			TextView tv = (TextView) layout.findViewById(itemid[kind][i]);
			tv.setText(al.get(position).get(itemname[i]));
		}
		
		//TextView time = (TextView) layout.findViewById(R.id.time);
		
		//time.setText(lists.get(position).getTime());
		return layout;

	}

}
