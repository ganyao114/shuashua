package com.shuashua.buss.View.Window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.shuashua.buss.R;

public class SelectShopEditPopupWindow extends PopupWindow {

	private Button changetel,changeposition,changephoto, cancelBtn,changeDesc,changeKind;
	private View mMenuView;

	@SuppressLint("InflateParams")
	public SelectShopEditPopupWindow(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.layout_dialog_shopedit, null);
		changetel = (Button) mMenuView.findViewById(R.id.btn_choose_shop_tel);
		changeposition = (Button) mMenuView.findViewById(R.id.btn_choose_shop_position);
		changephoto = (Button) mMenuView.findViewById(R.id.btn_choose_shop_photo);
		cancelBtn = (Button) mMenuView.findViewById(R.id.shop_cancel_edit);
		changeDesc = (Button) mMenuView.findViewById(R.id.btn_change_shop_desc);
		changeKind = (Button) mMenuView.findViewById(R.id.btn_choose_shop_kind);
		cancelBtn.setOnClickListener(itemsOnClick);
		changeDesc.setOnClickListener(itemsOnClick);
		changeposition.setOnClickListener(itemsOnClick);
		changephoto.setOnClickListener(itemsOnClick);
		changetel.setOnClickListener(itemsOnClick);
		changeKind.setOnClickListener(itemsOnClick);
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.PopupAnimation);
		ColorDrawable dw = new ColorDrawable(0x80000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

}
