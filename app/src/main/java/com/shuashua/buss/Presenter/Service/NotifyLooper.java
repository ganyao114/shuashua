package com.shuashua.buss.Presenter.Service;

import com.shuashua.buss.Model.Entity.Notify;
import com.shuashua.buss.Presenter.OnNotifyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/17.
 */
public class NotifyLooper implements OnNotifyListener{

    private List<Notify> notifys;

    public boolean canShow = false;

    private void init(){
        notifys = new ArrayList<>();
    }

    public void put(Notify notify){
        notifys.add(notify);
    }

    public Notify loop(){
        if (notifys.size()>0)
            return notifys.remove(0);
        else
            return null;
    }

    @Override
    public void onNotify(String str) {

    }


    public interface INotifyLoop{
        public void loop();
    }

}
