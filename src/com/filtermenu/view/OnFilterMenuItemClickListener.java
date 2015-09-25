package com.filtermenu.view;


import android.view.View;
import android.widget.AdapterView;

public interface OnFilterMenuItemClickListener {
	void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
