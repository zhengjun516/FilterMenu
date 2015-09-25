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
import com.filtermenu.view.FilterMenuList;
import com.filtermenu.view.FilterMenuTwoList;
import com.filtermenu.view.OnFilterMenuItemClickListener;

public class FilterMenuBarMainActivity extends Activity {

	private FilterMenuBar mFilterMenuBar;
	private LayoutInflater inflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_filtermenu_bar);
		mFilterMenuBar  = (FilterMenuBar)findViewById(R.id.mFilterMenuBar);
		
		List<MBaseAdapter> baseAdapters = new ArrayList<MBaseAdapter>();
		
		JobTypeAdapter adapter = new JobTypeAdapter(this,DataManager.getJobTypes(), R.drawable.normal, R.drawable.press);
		JobTypeAdapter adapter2 = new JobTypeAdapter(this,DataManager.getJobTypes(), R.drawable.normal, R.drawable.press);
		
		MultiChoiceAdapter multiChoiceAdapter = new MultiChoiceAdapter(this, DataManager.getCities());
		MultiChoiceAdapter multiChoiceAdapter2 = new MultiChoiceAdapter(this, DataManager.getCities());
		
		baseAdapters.add(adapter);
		baseAdapters.add(adapter2);
		baseAdapters.add(multiChoiceAdapter);
		baseAdapters.add(multiChoiceAdapter2);
		
		mFilterMenuBar.setFilterMenus(baseAdapters);
	}

}
