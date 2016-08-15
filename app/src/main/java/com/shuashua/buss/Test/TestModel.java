package com.shuashua.buss.Test;

import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.Model.Beans.Order;
import com.shuashua.buss.Model.Beans.Shop;
import com.shuashua.buss.Model.Entity.CardPropertys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 16/8/1.
 */
public class TestModel {
    public static List<Cards> getCards(){
        List<Cards> list = new ArrayList<>();
        for (int i = 0;i < 10;i ++){
            Cards cards = new Cards();
            cards.setName("测试会员卡");
            cards.setCoverurl("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
            cards.setContent("卡号0000000000"+i);
            list.add(cards);
        }
        return list;
    }

    public static List<Shop> getShops(){
        List<Shop> list = new ArrayList<>();
        for (int i = 0;i < 10;i ++){
            Shop shop = new Shop();
            shop.setName("测试店铺"+i);
            shop.setCoverUrl("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
            shop.setDesc("南京市江宁XXXX路XXX号");
            shop.setGrade("评分4.5分");
            list.add(shop);
        }
        return list;
    }

    public static List<CardPropertys> getProperty(){
        List<CardPropertys> propertyses = new ArrayList<>();
        propertyses.add(new CardPropertys());
        propertyses.add(new CardPropertys());
        propertyses.add(new CardPropertys());
        return propertyses;
    }

    public static List<Order> getOeder(){
        List<Order> orders = new ArrayList<>();
        for (int i = 0;i < 20; i++){
            orders.add(new Order());
        }
        return orders;
    }


}
