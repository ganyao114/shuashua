package com.shuashua.buss.Presenter.Service;

import com.shuashua.buss.Model.Entity.NotifyType;

/**
 * Created by pc on 16/8/17.
 */
public class NotifyFilter {
    public NotifyType notifyFilter(String notify){
        String action = null;
        NotifyType type = null;
        switch (action){
            case "orderComplete":
                type = NotifyType.ORDER_COMPLETE;
                break;
        }
        return type;
    }

    public String getContent(String notify){
        return notify;
    }
}
