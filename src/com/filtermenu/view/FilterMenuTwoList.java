package com.filtermenu.view;

import com.filtermenu.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author zhengjun1
 *
 */
public class FilterMenuTwoList  extends LinearLayout {

	private ListView mParentListView;
	private ListView mChildrenListView;
	
	public FilterMenuTwoList(Context context) {
		super(context);
		initView(context);
	}
	
	public FilterMenuTwoList(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	public FilterMenuTwoList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}
	
	private void initView(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.filter_menutwo_listview, this, true);
		mParentListView = (ListView) findViewById(R.id.parent_listview);
		mChildrenListView = (ListView) findViewById(R.id.children_listview);
	}
	
	public void setParentAdapter(BaseAdapter parentAdapter){
		if(mParentListView != null){
			mParentListView.setAdapter(parentAdapter);
		}
		
	}
	
	public void setParentSelection(int position){
		if(mParentListView != null){
			mParentListView.setSelection(position);
		}
	}
	
	public void setChildrenSelection(int position){
		if(mChildrenListView != null){
			mChildrenListView.setSelection(position);
		}
		
	}
	
	public void setChildrenAdapter(BaseAdapter childrenAdapter){
		if(mChildrenListView != null){
			mChildrenListView.setAdapter(childrenAdapter);
		}
	}
	
	public void setOnParentFilterMenuItemClickListener(final OnFilterMenuItemClickListener onItemOnClickListener){
		if(mParentListView != null){
			mParentListView.setOnItemClickListener(new OnItemClickListener() {

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
	
	public void setOnChildrenFilterMenuItemClickListener(final OnFilterMenuItemClickListener onItemOnClickListener){
		if(mChildrenListView != null){
			mChildrenListView.setOnItemClickListener(new OnItemClickListener() {

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
