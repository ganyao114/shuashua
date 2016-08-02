package com.shuashua.buss.Test;

import com.shuashua.buss.Model.Beans.Cards;

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
            cards.setContent("卡号0000000000");
            list.add(cards);
        }
        return list;
    }
}
