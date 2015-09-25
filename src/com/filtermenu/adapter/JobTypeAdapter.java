package com.filtermenu.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.filtermenu.R;
import com.filtermenu.adapter.PopupAdapter.ViewHolder;
import com.filtermenu.model.JobType;

public class JobTypeAdapter extends MBaseAdapter<JobType> {
	

	public JobTypeAdapter(Activity context, List<JobType> datas,int normalBg, int pressBg) {
		super(context, datas);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		JobType jobType = (JobType) getItem(position);
	        final View view;
	        ViewHolder holder;
	        if(convertView == null) {
	            view = mInflater.inflate(R.layout.filter_menu_listview_item,null);
	            holder = new ViewHolder();
	            holder.mFilterMenuListItem = (LinearLayout) view.findViewById(R.id.mFilterMenuListItem);
	            holder.tv = (TextView) view.findViewById(R.id.tv);
	            view.setTag(holder);
	        } else {
	            view = convertView;
	            holder = (ViewHolder) view.getTag();
	        }
	        holder.tv.setText(jobType.name);
	        if(position == selection) {
	            holder.tv.setBackgroundResource(pressBg);
	        } else {
	            holder.tv.setBackgroundResource(normalBg);
	        }
	        
	        holder.mFilterMenuListItem.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListenerAdapterView.onItemClick(view, position, (JobType) getItem(position));
				}
			});
	        
	        return view;
	}

	public void setPressPostion(int position) {
        this.selection = position;
    }
	
	class ViewHolder{
        TextView tv;
        LinearLayout mFilterMenuListItem;
    }

	@Override
	public String getText(int position) {
		JobType jobType = (JobType) getItem(position);
		return jobType.name;
	}
}
