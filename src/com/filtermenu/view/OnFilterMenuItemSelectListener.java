package com.filtermenu.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public interface OnFilterMenuItemSelectListener {
	public void onItemSelect(AdapterView<?> parent,int position, View convertView,View checkBotton);
}
