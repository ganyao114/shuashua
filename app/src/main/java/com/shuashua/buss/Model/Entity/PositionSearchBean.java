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
@ListDataSrc(R.layout.poisearch_item)
public class PositionSearchBean implements Serializable{

    private String id;
    @BindText(R.id.poisearch_name)
    private String name;
    @BindText(R.id.poisearch_address)
    private String address;
    @BindText(R.id.poisearch_distance)
    private String dist;

    private LatLng latLng;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setValue(PoiInfo info, LatLng locationLatLng){
        this.name = info.name;
        this.address = info.address;
        this.dist = (int) DistanceUtil.getDistance(locationLatLng, info.location)+"米";
        this.id = info.uid;
        this.latLng = locationLatLng;
    }

}
