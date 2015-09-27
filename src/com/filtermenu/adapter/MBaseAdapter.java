package com.filtermenu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MBaseAdapter<？> extends BaseAdapter{
	private Object tag;
	
	protected Activity mContext;
	protected List<？> datas = new ArrayList<？>();
	protected LayoutInflater mInflater;
	
	protected int normalBg;
	protected int pressBg;
	protected int selection;
	
	/**
	 * 通用点击事件回调类
	 */
	protected OnItemClickListenerAdapterView<？> mOnItemClickListenerAdapterView;
	
	public MBaseAdapter(Activity context, List<？> datas) {
		super();
		this.mContext = context;
		if(datas != null){
			this.datas = datas;
		}
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if(datas!=null){
			return datas.size();
		}else{
			return 0;
		}
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public List<？> getDatas(){
		return datas;
	}
	
	public List<?> getChildren(int position){
		return datas;
	};
	
	protected void initParams(int normalBg, int pressBg){
        this.normalBg = normalBg;
        this.pressBg = pressBg;
        this.selection = -1;
    }
	
	public abstract String getText(int position);
	
	public void setPressPostion(int position) {
        this.selection = position;
    }
	
	/**
	 * 在原有数据基础上增加数据
	 * @param datas
	 */
	public void addDatas(List<？> datas){
		if(datas != null){
			getDatas().addAll(datas);
		}
		notifyDataSetChanged();
	}
	
	/**
	 * 重新填充数据
	 * @param datas
	 */
	public void setDatas(List<？> datas){
		if(datas != null){
			getDatas().clear();
			getDatas().addAll(datas);
		}
		notifyDataSetChanged();
	}
	
	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	public void setOnItemClickListenerAdapterView(OnItemClickListenerAdapterView onClickListenerAdapterView) {
		this.mOnItemClickListenerAdapterView = onClickListenerAdapterView;
	}
	
	public static interface OnItemClickListenerAdapterView<T> {
		public void onItemClick(View view,int position, T t);
	}
}
