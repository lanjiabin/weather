package com.android.l.weather;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CityDBService {
    private static CityDBService cityDBService = null;

    private CityDBService() {
    }

    /**
     * 0.单例模式，获得对象
     **/
    public static CityDBService getInstance() {
        if (cityDBService == null) {
            cityDBService = new CityDBService();
        }
        return cityDBService;
    }

    /**
     * 1.根据城市名字查询城市编号
     **/
    public ArrayList<HashMap<String, String>> queryCityInfoByCityZh(Context context, String cityZh) {
        DBManager DBManager = new DBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "SELECT id FROM city WHERE cityZh=?";
        ArrayList<HashMap<String, String>> queryResult = DBManager.querySQLite(sql, new String[]{cityZh});
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
