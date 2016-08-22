package com.shuashua.buss.View.Widgets.PopupMenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DrawableUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.Core.S;

import java.util.List;

public class MenuHelper<T> {
	private PopupWindow popupWindow;
	private ListView listView;
	private List<T> data;
	private Context mContext;
	private View topView;
	private int i = 0;
	private FrameLayout container;
	private Class<T> itemClazz;

	public MenuHelper(Context context, View topView, final OnMenuClick clickListener, List<T> data, FrameLayout containerView,Class<T> itemclazz) {
		mContext = context;
		this.topView = topView;
		this.data = data;
		this.itemClazz = itemclazz;
		this.container = containerView;
		container.getForeground().setAlpha(0);
		
		initListView(clickListener);
		initPopupWindow();
		
	}
	
	private void initListView(final OnMenuClick clickListener) {
		listView = new ListView(mContext);
		listView.setDivider(null);
		listView.setDividerHeight(0);
		listView.setBackgroundColor(Color.WHITE);
		listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		listView.setDivider(mContext.getResources().getDrawable(R.color.gray));
		S.ViewUtils.ListBind(listView).setClass(itemClazz).bind(data);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				i = position;
				clickListener.onPopupMenuClick(position,topView);
				popupWindow.dismiss();
			}
		});
	}
	
	private void initPopupWindow() {
		popupWindow = new PopupWindow(listView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		
		popupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				if (container != null) {
					container.getForeground().setAlpha(0);
				}
			}
		});
	}
	
	public void showMenu() {
		S.ViewUtils.ListBind(listView).refresh();
		if (popupWindow.isShowing()) {
//			popupWindow.dismiss();
		}else {
			popupWindow.setOutsideTouchable(true);
			popupWindow.setTouchable(true);
			popupWindow.showAsDropDown(topView, 0, 0);
			if (container != null) {
				container.getForeground().setAlpha(120);
			}
		}
	}

	public void dismiss(){
		if (popupWindow.isShowing())
			popupWindow.dismiss();
	}

	public void refresh(){
		S.ViewUtils.ListBind(listView).refresh();
	}


}
