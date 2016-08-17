package com.shuashua.buss.View.Dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shuashua.buss.Model.Entity.NotifyType;
import com.shuashua.buss.Presenter.OnNotifyListener;
import com.shuashua.buss.Presenter.Service.NotifyFilter;

import net.gy.SwiftFrameWork.UI.customwidget.materaldialog.MaterialDialog;

import java.lang.ref.WeakReference;

/**
 * Created by pc on 16/8/17.
 */
public class NotifyDialog implements OnNotifyListener{

    private WeakReference<MaterialDialog> dialog;
    private WeakReference<TextView> content;
    private NotifyFilter filter;
    private NotifyType curtype;

    public void init(Activity context){
        filter = new NotifyFilter();
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.setTitle("我是通知");
        TextView textView = new TextView(context);
        dialog.setContentView(textView);
        dialog.setPositiveButton("查看详情", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        this.dialog = new WeakReference<MaterialDialog>(dialog);
        this.content = new WeakReference<TextView>(textView);
    }

    public void showNotidy(NotifyType type,String Content){
        if (dialog==null||dialog.get() == null)
            return;
        if (content==null||content.get()==null)
            return;
        switch (type){
            case ORDER_COMPLETE:
                break;
        }
    }


    @Override
    public void onNotify(String str) {
        NotifyType type = filter.notifyFilter(str);
        String content = filter.getContent(str);
        type = curtype;
        showNotidy(type,content);
    }

    public interface OnChooseListener{
        public void onChoose(NotifyType type);
    }

}
