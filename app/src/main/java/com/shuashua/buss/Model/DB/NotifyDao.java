package com.shuashua.buss.Model.DB;

import com.shuashua.buss.Model.Entity.Message;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by gy on 2016/9/3.
 */
public class NotifyDao {

    static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig==null){
            daoConfig=new DbManager.DaoConfig()
                    .setDbName("notify.db")
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }

    public static void saveMsg(List<Message> list){
        DbManager dbManager = x.getDb(getDaoConfig());
        for (Message db:list) {
            try {
                dbManager.save(db);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Message> getAllMsg(){
        DbManager dbManager = x.getDb(getDaoConfig());
        List<Message> list = null;
        try {
            list = dbManager.findAll(Message.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void deMsgl(int id){
        DbManager dbManager = x.getDb(getDaoConfig());
        try {
            dbManager.deleteById(Message.class,id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
