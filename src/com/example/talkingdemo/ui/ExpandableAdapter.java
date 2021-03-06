package com.example.talkingdemo.ui;

import com.example.talkingdemo.R;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
   
	Context context;
	//String[] generalsTypes;
    //String[][] generals;
	
    String[] generalsTypes = new String[] { "aa", "bbb", "ccc" };
    public static String[][] generals = new String[][] {
            { "rr" },
            { "wwew","fcxc","ecxz","ddfd","deee"},
            {"sdfsda", "dsfsf", "eeee", "sdfsdf", "ddd"}
    };
    
	public ExpandableAdapter(Context c)
	{
		this.context=c;
		//String[] generalsTypes = s1;,String[] s1,String[][] s2
        //String[][] generals=s2; 
	}
	
	//
    TextView getTextView() 
    //when will this method be called?
    {   	
    	AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
        //i think this lp is to set the params to control a layout
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(36, 0, 0, 0);
        textView.setTextSize(20);
        textView.setTextColor(Color.YELLOW);
        return textView;
    }
    
	@Override
	public int getGroupCount() {
		Log.v("getGroupCount","GroupCount ="+generalsTypes.length);
		return generalsTypes.length;
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return generals[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return generalsTypes[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) 
	{//where is the value of groupPositon and childPosition from?
		//i didn't see any sentence that assgin any value to these value
		return generals[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
	
		Log.v("groupPosition","groupPosiont is"+groupPosition);
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) 
	{
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) 
	{    
		//i guess this  method is to make a Outline of ListView Item
		/*LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(0);
        TextView textView = getTextView();
        textView.setTextColor(Color.BLACK);
        textView.setText(getGroup(groupPosition).toString());
        
        ll.addView(textView);
        return ll;*/
		
		if (convertView == null) 
		{   Log.v("converView null","converView is null");
            convertView = ((LayoutInflater) context
            		.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
            		.inflate(R.layout.mianwin_elv_group, null);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.mainwin_elv_groupname);
        tv1.setText(getGroup(groupPosition).toString());
 
        //getGroup
        /*
         * public Object getGroup(int groupPosition) {
		   return generalsTypes[groupPosition];
	           }
	*/
        TextView tv2 = (TextView) convertView.findViewById(R.id.mainwin_elv_groupsize);
        tv2.setText("["+String.valueOf(getChildrenCount(groupPosition))+"]");
        //getChildrencount()return generals[groupPosition].length;
        return convertView;
	}

	//when will getChildView  and getGroupView be Called? that is a question
	@Override
	public View getChildView(
			int groupPosition, int childPosition,
			boolean isLastChild, View convertView, 
			ViewGroup parent
			) 
	{
		/*
		 * LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(0);
        TextView textView = getTextView();
        textView.setText(getChild(groupPosition, childPosition) .toString());
        ll.addView(textView);
       
        return ll;
        */
		if (convertView == null) 
			//where is the params from ? what make convertView=null
		{
            convertView = ((LayoutInflater) context
            		.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
            		.inflate(R.layout.mianwin_elv_item, null);
        }
		TextView tv = (TextView)convertView.findViewById(R.id.mainwin_elv_itemname);
		tv.setText(getChild(groupPosition, childPosition).toString());
		
        return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
