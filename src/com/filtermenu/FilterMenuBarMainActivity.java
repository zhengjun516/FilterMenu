package com.filtermenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.filtermenu.adapter.CityAdapter;
import com.filtermenu.adapter.DistrictAdapter;
import com.filtermenu.adapter.JobTypeAdapter;
import com.filtermenu.adapter.MBaseAdapter;
import com.filtermenu.adapter.MultiChoiceAdapter;
import com.filtermenu.model.City;
import com.filtermenu.model.District;
import com.filtermenu.model.JobType;
import com.filtermenu.view.FilterMenu;
import com.filtermenu.view.FilterMenuBar;
import com.filtermenu.view.FilterMenuBar2;
import com.filtermenu.view.FilterMenuList;
import com.filtermenu.view.FilterMenuListAdapter;
import com.filtermenu.view.FilterMenuTwoList;
import com.filtermenu.view.OnFilterMenuItemClickListener;

public class FilterMenuBarMainActivity extends Activity {

	private FilterMenuBar2 mFilterMenuBar;
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_filtermenu_bar);
		mFilterMenuBar  = (FilterMenuBar2)findViewById(R.id.mFilterMenuBar2);
		
		List<FilterMenuListAdapter> baseAdapters = new ArrayList<FilterMenuListAdapter>();
		
		FilterMenuListAdapter filterMenuListAdapter = new FilterMenuListAdapter();
		filterMenuListAdapter.filterMenuType = FilterMenuListAdapter.MULTI_COLUMN;
		final CityAdapter cityAdapter = new CityAdapter(this,DataManager.getCities(), R.drawable.normal, R.drawable.press);
		final DistrictAdapter districtAdapter = new DistrictAdapter(this,DataManager.getCities().get(0).districts, R.drawable.normal,R.drawable.press);
		filterMenuListAdapter.parentAdapter = cityAdapter;
		filterMenuListAdapter.childrenAdapter= districtAdapter;
		
		FilterMenuListAdapter filterMenuListAdapter2  = new FilterMenuListAdapter();
		filterMenuListAdapter2.parentAdapter = new JobTypeAdapter(this,DataManager.getJobTypes(), R.drawable.normal, R.drawable.press);
		
		FilterMenuListAdapter filterMenuListAdapter3  = new FilterMenuListAdapter();
		filterMenuListAdapter3.parentAdapter = new MultiChoiceAdapter(this, DataManager.getCities());
		
		FilterMenuListAdapter filterMenuListAdapter4  = new FilterMenuListAdapter();
		filterMenuListAdapter4.parentAdapter = new MultiChoiceAdapter(this, DataManager.getCities());
		
		baseAdapters.add(filterMenuListAdapter);
		baseAdapters.add(filterMenuListAdapter2);
		baseAdapters.add(filterMenuListAdapter3);
		baseAdapters.add(filterMenuListAdapter4);
		
		mFilterMenuBar.setFilterMenus(baseAdapters);
		
	}

}
