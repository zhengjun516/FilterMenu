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
import com.filtermenu.adapter.MultiChoiceAdapter;
import com.filtermenu.model.City;
import com.filtermenu.model.District;
import com.filtermenu.model.JobType;
import com.filtermenu.view.FilterMenu;
import com.filtermenu.view.FilterMenuList;
import com.filtermenu.view.FilterMenuTwoList;
import com.filtermenu.view.OnFilterMenuItemClickListener;

public class MainActivity extends Activity {
	private FilterMenu mFilterMenu;
	private FilterMenu mFilterMenu2;
	private FilterMenu mFilterMenu3;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFilterMenu = (FilterMenu) findViewById(R.id.btn);
		// inflater = LayoutInflater.from(this);

		FilterMenuList menuList = new FilterMenuList(this);
		final JobTypeAdapter adapter = new JobTypeAdapter(this,DataManager.getJobTypes(), R.drawable.normal, R.drawable.press);
		menuList.setAdapter(adapter);
		menuList.setOnFilterMenuItemClickListener(new OnFilterMenuItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.setPressPostion(position);
				adapter.notifyDataSetChanged();
				JobType jobType = (JobType) parent.getItemAtPosition(position);
				mFilterMenu.setText(jobType.name);
				mFilterMenu.hidePopup();
			}
		});
		mFilterMenu.setPopupView(menuList);

		mFilterMenu2 = (FilterMenu) findViewById(R.id.btn2);
		final FilterMenuTwoList filterMenuMultiList = new FilterMenuTwoList(
				this);
		final CityAdapter cityAdapter = new CityAdapter(this,DataManager.getCities(), R.drawable.normal, R.drawable.press);
		final DistrictAdapter districtAdapter = new DistrictAdapter(this,DataManager.getCities().get(0).districts, R.drawable.normal,
				R.drawable.press);

		filterMenuMultiList.setParentAdapter(cityAdapter);
		filterMenuMultiList.setChildrenAdapter(districtAdapter);
		filterMenuMultiList.setOnParentFilterMenuItemClickListener(new OnFilterMenuItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						cityAdapter.setPressPostion(position);
						cityAdapter.notifyDataSetChanged();
						City city = (City) parent.getItemAtPosition(position);
						districtAdapter.setDatas(city.districts);
						districtAdapter.setPressPostion(-1);
						filterMenuMultiList.setParentSelection(0);
					}
				});
		filterMenuMultiList.setOnChildrenFilterMenuItemClickListener(new OnFilterMenuItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						districtAdapter.setPressPostion(position);
						districtAdapter.notifyDataSetChanged();
						District district = (District) parent.getItemAtPosition(position);
						mFilterMenu2.setText(district.name);
						mFilterMenu2.hidePopup();
					}
				});
		mFilterMenu2.setPopupView(filterMenuMultiList);
		
		mFilterMenu3 = (FilterMenu) findViewById(R.id.mFilterMenu3);
		MultiChoiceAdapter multiChoiceAdapter = new MultiChoiceAdapter(this, DataManager.getCities());
		FilterMenuList filterMenuList = new FilterMenuList(this);
		filterMenuList.setAdapter(multiChoiceAdapter);
		mFilterMenu3.setPopupView(filterMenuList);
	}

}
