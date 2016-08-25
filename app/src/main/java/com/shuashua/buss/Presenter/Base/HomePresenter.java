package com.shuashua.buss.Presenter.Base;

import android.content.Context;

import com.shuashua.buss.Model.Beans.ADListBean;
import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.Model.Beans.Mem;
import com.shuashua.buss.Model.Beans.Order;
import com.shuashua.buss.Model.Beans.Shop;

import net.gy.SwiftFrameWork.MVP.Presenter.Presenter;
import net.gy.SwiftFrameWork.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by pc on 16/8/3.
 */
public class HomePresenter extends Presenter{

    public List<Cards> mainFraglist;

    public List<Shop> mngshoplist;

    public List<Order> mngOrderList;

    public List<Mem> mngMemList;

    public List<Cards> mngCardsList;

    public List<ADListBean> adlist;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {

    }
}
