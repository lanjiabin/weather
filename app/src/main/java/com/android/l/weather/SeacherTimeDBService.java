package com.android.l.weather;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SeacherTimeDBService {
    private static SeacherTimeDBService seacherTimeDBService = null;

    private SeacherTimeDBService() {
    }

    /**
     * 0.单例模式，获得对象
     **/
    public static SeacherTimeDBService getInstance() {
        if (seacherTimeDBService == null) {
            seacherTimeDBService = new SeacherTimeDBService();
        }
        return seacherTimeDBService;
    }

    /**
     * 1.添加一个记录
     **/
    public void addHistory(Context context,
                           String id,
                           String cityZh) {
        String sql = "INSERT INTO seachertime(id,cityZh,create_time) VALUES(?,?,?)";
        DBManager DBManager = new DBManager(context);
        DBHelper helper = new DBHelper(context);

        DBManager.updateSQLite(sql, new String[]{
                id,
                cityZh,
                getLocalTime()});
    }

    /**
     * 2.查询所有记录
     **/
    public ArrayList<HashMap<String, String>> queryHistoryInfoByID(Context context) {
        DBManager DBManager = new DBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "SELECT * FROM seachertime";
        ArrayList<HashMap<String, String>> queryResult = DBManager.querySQLite(sql, null);
        return queryResult;
    }


    /**
     * 6.获得本系统时间
     **/
    public String getLocalTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date.getTime());
        return time;
    }

}
