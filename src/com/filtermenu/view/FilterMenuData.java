package com.filtermenu.view;

import com.filtermenu.adapter.MBaseAdapter;

public class FilterMenuData {
	public static final int SINGLE_COLUMN = 1;
	public static final int MULTI_COLUMN = 2;
	public int filterMenuType = SINGLE_COLUMN;
	
	public MBaseAdapter parentAdapter;
	public MBaseAdapter childrenAdapter;
	

}
