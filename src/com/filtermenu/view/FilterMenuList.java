package com.filtermenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.filtermenu.R;

/**
 * 单列
 * @author zhengjun1
 */
public class FilterMenuList extends LinearLayout {
	
	private ListView listView;
	
	public FilterMenuList(Context context) {
		super(context);
		initView(context);
	}

	public FilterMenuList(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public FilterMenuList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	private void initView(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.filter_menu_listview, this, true);
		listView = (ListView) findViewById(R.id.lv);
	}
	
	public void setAdapter(BaseAdapter baseAdapter){
		if(listView != null){
			listView.setAdapter(baseAdapter);
		}
	}
	
	public void setOnFilterMenuItemClickListener(final OnFilterMenuItemClickListener onItemOnClickListener){
		if(listView != null){
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(onItemOnClickListener != null){
						onItemOnClickListener.onItemClick(parent, view, position, id);
					}
				}
			});
		}
	}
}
