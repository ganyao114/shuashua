package com.shuashua.buss.Presenter.Notify.Handler;

import com.shuashua.buss.Model.Entity.Message;
import com.shuashua.buss.Presenter.IMsgHandler;

/**
 * Created by gy on 2016/9/3.
 */
public class MsgHandlerFactory {

    public final static String MSG_ACTION_NEWDD = "newdd";
    public final static String MSG_ACTION_NEWDY = "newstaff";

    public static IMsgHandler obtain(Message msg){
        IMsgHandler handler = null;
        String action = msg.getAction();
        if (action.equals(MSG_ACTION_NEWDD)){
            handler = new OrderMsgHandler();
        }else if (action.equals(MSG_ACTION_NEWDY)){

        }
        return handler;
    }


}
