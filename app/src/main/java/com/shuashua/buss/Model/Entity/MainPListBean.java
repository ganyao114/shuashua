package com.shuashua.buss.Model.Entity;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.shuashua.buss.R;

import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.BindText;
import net.gy.SwiftFrameWork.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by pc on 16/8/18.
 */
@ListDataSrc(R.layout.locationpois_item)
public class MainPListBean implements Serializable{
    private String id;
    @BindText(R.id.locationpois_name)
    private String name;
    @BindText(R.id.locationpois_address)
    private String adress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setValue(PoiInfo info){
        this.name = info.name;
        this.adress = info.address;
        this.id = info.uid;
    }
}
