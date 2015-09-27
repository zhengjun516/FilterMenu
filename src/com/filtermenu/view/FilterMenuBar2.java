package com.filtermenu.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.filtermenu.adapter.MBaseAdapter;
import com.filtermenu.adapter.MultiChoiceAdapter;
import com.filtermenu.adapter.MBaseAdapter.OnItemClickListenerAdapterView;
import com.filtermenu.model.City;

/**
 * 筛选栏
 * @author zhengjun1
 */
public class FilterMenuBar2 extends LinearLayout implements OnDismissListener{
    
	private Context mContext;
	private int displayWidth;
	private int displayHeight;
	private Map<Integer,FilterMenu> mFilterMenus = new HashMap<Integer,FilterMenu>();
	private Map<Integer,FilterMenuListAdapter> mMBaseAdapters = new HashMap<Integer,FilterMenuListAdapter>();
	private Map<Integer,View> mContentViews = new HashMap<Integer, View>();
	
	private View selectFilterMenu;
	private int selectPosition;
	
	private PopupWindow popupWindow;
	
	public FilterMenuBar2(Context context) {
		super(context);
		mContext = context;
		init(context);
	}
	
	public FilterMenuBar2(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context);
	}
	
	public FilterMenuBar2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init(context);
	}
	
	public void setFilterMenus(List<FilterMenuListAdapter> baseAdapters){
		mMBaseAdapters.clear();
		if(baseAdapters != null){
			int count = baseAdapters.size();
			for(int i=0;i < count;i++){
				final FilterMenuListAdapter adapter = baseAdapters.get(i);
				if(adapter.filterMenuType == FilterMenuListAdapter.SINGLE_COLUMN){
					adapter.parentAdapter.setTag(i);
					adapter.parentAdapter.setOnItemClickListenerAdapterView(new OnItemClickListenerAdapterView() {
						@Override
						public void onItemClick(View view, int position, Object t) {
							int tag = (Integer) adapter.parentAdapter.getTag();
							FilterMenuListAdapter adapter2 = mMBaseAdapters.get(tag);
							adapter2.parentAdapter.setPressPostion(position);
							adapter2.parentAdapter.notifyDataSetChanged();
							FilterMenu filterMenu = mFilterMenus.get(tag);
							filterMenu.setText(adapter2.parentAdapter.getText(position));
							hidePopup();
						}
					});
					mMBaseAdapters.put(i,adapter);
				}else{
					adapter.parentAdapter.setTag(i);
					adapter.childrenAdapter.setTag(i);
					adapter.parentAdapter.setOnItemClickListenerAdapterView(new OnItemClickListenerAdapterView() {
						@Override
						public void onItemClick(View view, int position, Object t) {
						   int tag = (Integer) adapter.parentAdapter.getTag();
							FilterMenuListAdapter adapter2 = mMBaseAdapters.get(tag);
							adapter2.parentAdapter.setPressPostion(position);
							adapter2.parentAdapter.notifyDataSetChanged();
							List<?> datas = adapter2.getChildren(position);
							adapter2.childrenAdapter.setDatas(datas);
							adapter2.childrenAdapter.setPressPostion(-1);
							adapter2.childrenAdapter.notifyDataSetChanged();
							adapter2.parentAdapter.setPressPostion(position);
						}
					});
					adapter.childrenAdapter.setOnItemClickListenerAdapterView(new OnItemClickListenerAdapterView() {
						@Override
						public void onItemClick(View view, int position, Object t) {
							int tag = (Integer) adapter.childrenAdapter.getTag();
							FilterMenuListAdapter adapter2 = mMBaseAdapters.get(tag);
							adapter2.childrenAdapter.setPressPostion(position);
							adapter2.childrenAdapter.notifyDataSetChanged();
							FilterMenu filterMenu = mFilterMenus.get(tag);
							filterMenu.setText(adapter2.childrenAdapter.getText(position));
							hidePopup();
						}
					});
					mMBaseAdapters.put(i,adapter);
				}
			}
		}
		int count = mMBaseAdapters.size();
		
		mContentViews.clear();
		for(int i=0;i<count;i++){
			FilterMenuListAdapter filterMenuListAdapter = mMBaseAdapters.get(i);
			if(filterMenuListAdapter.filterMenuType == FilterMenuListAdapter.SINGLE_COLUMN){
				FilterMenuList filterMenuList = new FilterMenuList(mContext);
				filterMenuList.setTag(i);
				filterMenuList.setAdapter(filterMenuListAdapter.parentAdapter);
				mContentViews.put(i, filterMenuList);
			}else{
				FilterMenuTwoList filterMenuTwoList = new FilterMenuTwoList(mContext);
				filterMenuTwoList.setTag(i);
				filterMenuTwoList.setParentAdapter(filterMenuListAdapter.parentAdapter);
				filterMenuTwoList.setChildrenAdapter(filterMenuListAdapter.childrenAdapter);
				mContentViews.put(i, filterMenuTwoList);
			}
			
		}
		
		mFilterMenus.clear();
		for(int i=0;i<count;i++){
			FilterMenu filterMenu = new FilterMenu(mContext);
			filterMenu.setTag(i);
			
			filterMenu.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					FilterMenu menu = (FilterMenu) view;
					selectPosition = (Integer) menu.getTag();
					View content = mContentViews.get(selectPosition);
					show(content,view);
					selectFilterMenu = view;
				}
			});
			
			mFilterMenus.put(i, filterMenu);
			addView(filterMenu, i);
		}
		initPopUpWindow();
	}

	public void initPopUpWindow() {
		if (popupWindow == null) {
			popupWindow = new PopupWindow(mContentViews.get(selectPosition), displayWidth, displayHeight/2);
			
			// 为了解决popupWindow内不能获取点击事件
			popupWindow.setFocusable(false);
			popupWindow.setTouchable(true);
			//popupWindow.setOnDismissListener(this);
			//为了点击popupwindow之外的地方，使popupwindow消失
			popupWindow.setBackgroundDrawable(new BitmapDrawable());  
			popupWindow.setOutsideTouchable(true);
			
		}
	}

	public void show(View contentView,View filterMenu){
		initPopUpWindow();
		if(selectFilterMenu == filterMenu
		  && popupWindow.isShowing()){
			hidePopup();
		}else {
			hidePopup();
			showPopup(contentView);
		}
		
	}
	
	private void showPopup(View view) {
		if (popupWindow.getContentView() != view) {
			popupWindow.setContentView(view);
			popupWindow.update();
		}
		popupWindow.showAsDropDown(this, 0, 0);
	}
	
	private void hidePopup(){
		popupWindow.setOnDismissListener(this);
		popupWindow.dismiss();
	}

	/**
	 * 如果菜单成展开状态，则让菜单收回去
	 */
	public boolean onPressBack() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			if (selectFilterMenu != null) {
				selectFilterMenu.setSelected(false);
			}
			return true;
		} else {
			return false;
		}

	}
	
	private void init(Context context) {
		mContext = context;
		displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
		displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
		setOrientation(LinearLayout.HORIZONTAL);
	}

	@Override
	public void onDismiss() {
		//popupWindow.dismiss();
	}

}
