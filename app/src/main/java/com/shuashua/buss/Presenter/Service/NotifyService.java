package com.shuashua.buss.Presenter.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.shuashua.buss.Presenter.OnNotifyListener;

import java.util.List;

/**
 * Created by pc on 16/8/17.
 */
public class NotifyService extends Service{

    private List<OnNotifyListener> listeners;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
