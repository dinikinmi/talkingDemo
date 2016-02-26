package com.example.talkingdemo.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;  
import android.os.Build;
import android.util.AttributeSet;  
import android.util.TypedValue;  
import android.view.MotionEvent;  
import android.view.ViewGroup;  
import android.widget.HorizontalScrollView;  
import android.widget.LinearLayout;  

public class SlidingMenu extends HorizontalScrollView  
{  
  /** 
   * 屏幕宽度 
   */  
  private int mScreenWidth;  
  /** 
   * dp 
   */  
  private int mMenuRightPadding = 50;  
  /** 
   * 菜单的宽度 
   */  
  private int mMenuWidth;  
  private int mHalfMenuWidth;  

  private boolean once;
  private boolean isOpen;  

  public SlidingMenu(Context context, AttributeSet attrs)  
  {  
      super(context, attrs);  
      mScreenWidth = ScreenUtils.getScreenWidth(context);  
  }  

  @Override  
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
  {  
      /** 
       * 显示的设置一个宽度 
       */  
      if (!once)  
      {  
          LinearLayout wrapper = (LinearLayout) getChildAt(0);  
          ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);  
          ViewGroup content = (ViewGroup) wrapper.getChildAt(1);  
          // dp to px  
          mMenuRightPadding = (int) TypedValue.applyDimension(  
                  TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, content  
                          .getResources().getDisplayMetrics());  

          mMenuWidth = mScreenWidth - mMenuRightPadding;  
          mHalfMenuWidth = mMenuWidth / 2;  
          menu.getLayoutParams().width = mMenuWidth;  
          content.getLayoutParams().width = mScreenWidth;  

      }  
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);  

  }  

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  @Override  
  protected void onScrollChanged(int l, int t, int oldl, int oldt)  
  {  
	  super.onScrollChanged(l, t, oldl, oldt);  
      float scale = l * 1.0f / mMenuWidth;   
      
      LinearLayout wrapper = (LinearLayout) getChildAt(0);  
      ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);  
      ViewGroup content = (ViewGroup) wrapper.getChildAt(1); 
      
      menu.setScaleX(1 - 0.3f * scale);
      menu.setScaleY(1 - 0.3f * scale);
      menu.setAlpha(0.6f + 0.4f * (1 - scale));
      menu.setTranslationX(mMenuWidth * scale * 0.6f);

      content.setPivotX(0);
      content.setPivotY(content.getHeight() / 2);
      content.setScaleX(0.8f + scale * 0.2f);
      content.setScaleY(0.8f + scale * 0.2f);
  }
  
  @Override  
  protected void onLayout(boolean changed, int l, int t, int r, int b)  
  {  
      super.onLayout(changed, l, t, r, b);  
      if (changed)  
      {  
          // 将菜单隐藏  
          this.scrollTo(mMenuWidth, 0);  
          once = true;  
      }  
  }  

  @Override  
  public boolean onTouchEvent(MotionEvent ev)  
  {  
      int action = ev.getAction();  
      switch (action)  
      {  
      // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏  
      case MotionEvent.ACTION_UP:  
          int scrollX = getScrollX();  
          if (scrollX > mHalfMenuWidth) 
          {
              this.smoothScrollTo(mMenuWidth, 0); 
              isOpen = false;  
          }
          else  
          {
              this.smoothScrollTo(0, 0); 
              isOpen = true;  
          }
          return true;  
      }  
      return super.onTouchEvent(ev);  
  }  

  /** 
   * 切换菜单状态 
   */  
  public void toggle()  
  {  
      if (isOpen)  
      {  
    	  this.smoothScrollTo(mMenuWidth, 0); 
          isOpen = false;   
      } 
      else  
      {  
    	  this.smoothScrollTo(0, 0); 
          isOpen = true;    
      }  
  }  
}  