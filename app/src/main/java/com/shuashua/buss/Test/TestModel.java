package com.shuashua.buss.Test;

import android.util.Log;

import com.shuashua.buss.Model.Beans.Cards;
import com.shuashua.buss.Model.Beans.Mem;
import com.shuashua.buss.Model.Beans.Order;
import com.shuashua.buss.Model.Beans.Shop;
import com.shuashua.buss.Model.Entity.CardPropertys;

import net.gy.SwiftFrameWork.IOC.Core.impl.IOC;
import net.gy.SwiftFrameWork.IOC.Core.kernel.KernelLang;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

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

    public static List<Mem> getMems(){
        List<Mem> list = new ArrayList<>();
        for (int i = 0;i < 20; i++){
            list.add(new Mem());
        }
        return list;
    }

    public static void test(){
        BaseDexClassLoader classLoader = (BaseDexClassLoader) IOC.getInstance().getApplication().getClassLoader();
        //(BaseDexClassLoader) Thread.currentThread().getContextClassLoader();
        try {
            Field field = BaseDexClassLoader.class.getDeclaredField("pathList");
            field.setAccessible(true);
            Class clazz = Class.forName("dalvik.system.DexPathList");
            Object dexpathlist = field.get(classLoader);
            Field elementsfield = clazz.getDeclaredField("dexElements");
            elementsfield.setAccessible(true);
            Class clazz2 = Class.forName("dalvik.system.DexPathList$Element");
            Object[] elements = (Object[]) elementsfield.get(dexpathlist);
            Field dexField = clazz2.getDeclaredField("dexFile");
            dexField.setAccessible(true);
            DexFile dexFile = (DexFile) dexField.get(elements[0]);
            Method[] methods = DexFile.class.getDeclaredMethods();
            Method tarMethod = null;
            for (Method method:methods){
                if (method.getName().equalsIgnoreCase("getClassNameList")) {
                    tarMethod = method;
                    break;
                }
            }
            tarMethod.setAccessible(true);
            Field CookieField = DexFile.class.getDeclaredField("mCookie");
            CookieField.setAccessible(true);
            Object mCookie = CookieField.get(dexFile);
            String[] classNames = (String[]) tarMethod.invoke(dexFile,mCookie);
            for (String name:classNames){
                Log.e("gy",name);
            }
//            Log.e("gy",);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
