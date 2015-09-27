package com.filtermenu.view;

import java.util.List;

import com.filtermenu.adapter.MBaseAdapter;

public class FilterMenuListAdapter {
	public static final int SINGLE_COLUMN = 1;
	public static final int MULTI_COLUMN = 2;
	public int filterMenuType = SINGLE_COLUMN;
	
	public MBaseAdapter parentAdapter;
	public MBaseAdapter childrenAdapter;

	public List<?> getChildren(int position){
		return parentAdapter.getChildren(position);
	}

}
