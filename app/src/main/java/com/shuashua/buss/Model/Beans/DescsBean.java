package com.shuashua.buss.Model.Beans;

import net.gy.SwiftFrameWork.MVVM.Annotations.BindListView;
import net.gy.SwiftFrameWork.MVVM.Annotations.JsonSet;
import net.gy.SwiftFrameWork.MVVM.Interface.IHandler;

import java.util.List;

/**
 * Created by gy on 2016/9/2.
 */
public class DescsBean implements IHandler{

    @JsonSet
    @BindListView({123})
    private List<String> list;

    @Override
    public void handler() {

    }
}
