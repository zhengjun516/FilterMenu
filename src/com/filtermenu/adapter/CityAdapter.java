package com.filtermenu.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.filtermenu.R;
import com.filtermenu.model.City;
import com.filtermenu.model.JobType;

public class CityAdapter extends MBaseAdapter<City> {

	public CityAdapter(Activity context, List<City> datas,int normalBg, int pressBg) {
		super(context, datas);
		  initParams(normalBg, pressBg);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		City city = (City) getItem(position);
	        View view;
	        ViewHolder holder;
	        if(convertView == null) {
	            view = mInflater.inflate(R.layout.filter_menu_listview_item,null);
	            holder = new ViewHolder();
	            holder.tv = (TextView) view.findViewById(R.id.tv);
	            view.setTag(holder);
	        } else {
	            view = convertView;
	            holder = (ViewHolder) view.getTag();
	        }
	        holder.tv.setText(city.name);
	        if(position == selection) {
	            holder.tv.setBackgroundResource(pressBg);
	        } else {
	            holder.tv.setBackgroundResource(normalBg);
	        }
	        return view;
	}

	class ViewHolder{
        TextView tv;
    }

	@Override
	public String getText(int position) {
		City city = (City) getItem(position);
		return city.name;
	}
}
