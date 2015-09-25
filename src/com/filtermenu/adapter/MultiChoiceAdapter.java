package com.filtermenu.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.filtermenu.R;
import com.filtermenu.model.City;
import com.filtermenu.view.OnFilterMenuItemSelectListener;

public class MultiChoiceAdapter extends MBaseAdapter<City>{
	
	private Map<Integer,City> mCheckedData = new HashMap<Integer,City>();
	
	private OnFilterMenuItemSelectListener mOnFilterMenuItemSelectListener;
	
	public MultiChoiceAdapter(Activity context, List<City> datas) {
		super(context, datas);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.filter_menu_multichoice_listview_item,null);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			holder.mCheckBtn = (CheckBox) convertView.findViewById(R.id.mCheckBtn);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();
		
		City city = (City) getItem(position);
		
		holder.tv.setText(city.name);
		holder.mCheckBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							mCheckedData
									.put(position, (City) getItem(position));
						} else {
							mCheckedData.remove(position);
						}
					}
				});

		return convertView;
	}
	
	public void setOnFilterMenuItemSelectListener(OnFilterMenuItemSelectListener onFilterMenuItemSelectListener){
		mOnFilterMenuItemSelectListener = onFilterMenuItemSelectListener;
	}

	public Map<Integer,City> getCheckeds(){
		return mCheckedData;
	}
	
	class ViewHolder{
        TextView tv;
        CheckBox mCheckBtn;
    }

	@Override
	public String getText(int position) {
		return "";
	}
}
