package com.shuashua.buss.View.Widgets.CitySelect;

import com.shuashua.buss.View.Widgets.CitySelect.Model.AreaBase;

import java.util.List;

/**
 * Created by pc on 16/8/10.
 */
public interface IUpdateView {

    public void onDone(Type type, List<AreaBase> areas);

    enum Type{
        Province,
        City,
        Desc
    }
}
