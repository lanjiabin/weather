package com.android.l.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String ADMIN_DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, ADMIN_DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //查询历史表
        db.execSQL("CREATE TABLE IF NOT EXISTS seachertime" +
                "(create_time VARCHAR PRIMARY KEY," +
                "cityZh VARCHAR," +
                "id VARCHAR)");

        //城市列表
        db.execSQL("CREATE TABLE IF NOT EXISTS city" +
                "(id VARCHAR PRIMARY KEY," +
                "cityEn VARCHAR," +
                "cityZh VARCHAR," +
                "provinceEn VARCHAR," +
                "provinceZh VARCHAR," +
                "countryEn VARCHAR," +
                "countryZh VARCHAR," +
                "leaderEn VARCHAR," +
                "leaderZh VARCHAR," +
                "lat VARCHAR," +
                "lon VARCHAR)");

        //插入城市数据
        db.execSQL(
                "INSERT INTO city(id, cityEn, cityZh, provinceEn, provinceZh, countryEn, countryZh, leaderEn, leaderZh, lat, lon)" +
                        "VALUES" +
                        "('101010100','beijing','北京','beijing','北京','China','中国','beijing','北京','39.904989','116.405285')," +
                        "('101010200','haidian','海淀','beijing','北京','China','中国','beijing','北京','39.956074','116.310316')," +
                        "('101010300','chaoyang','朝阳','beijing','北京','China','中国','beijing','北京','39.921489','116.486409')," +
                        "('101010400','shunyi','顺义','beijing','北京','China','中国','beijing','北京','40.128936','116.653525')," +
                        "('101010500','huairou','怀柔','beijing','北京','China','中国','beijing','北京','40.324272','116.637122')," +
                        "('101010600','tongzhou','通州','beijing','北京','China','中国','beijing','北京','39.902486','116.658603')," +
                        "('101010700','changping','昌平','beijing','北京','China','中国','beijing','北京','40.218085','116.235906')," +
                        "('101010800','yanqing','延庆','beijing','北京','China','中国','beijing','北京','40.465325','115.985006')," +
                        "('101010900','fengtai','丰台','beijing','北京','China','中国','beijing','北京','39.863642','116.286968')," +
                        "('101011000','shijingshan','石景山','beijing','北京','China','中国','beijing','北京','39.914601','116.195445')," +
                        "('101011100','daxing','大兴','beijing','北京','China','中国','beijing','北京','39.728908','116.338033')," +
                        "('101011200','fangshan','房山','beijing','北京','China','中国','beijing','北京','39.735535','116.139157')," +
                        "('101011300','miyun','密云','beijing','北京','China','中国','beijing','北京','40.377362','116.843352')," +
                        "('101011400','mentougou','门头沟','beijing','北京','China','中国','beijing','北京','39.937183','116.105381')," +
                        "('101011500','pinggu','平谷','beijing','北京','China','中国','beijing','北京','40.144783','117.112335')," +
                        "('101011600','dongcheng','东城','beijing','北京','China','中国','dongcheng','东城','39.917544','116.418757')," +
                        "('101011700','xicheng','西城','beijing','北京','China','中国','xicheng','西城','39.915309','116.366794')," +
                        "('101020100','shanghai','上海','shanghai','上海','China','中国','shanghai','上海','31.231706','121.472644')," +
                        "('101020200','minhang','闵行','shanghai','上海','China','中国','shanghai','上海','31.111658','121.375972')," +
                        "('101020300','baoshan','宝山','shanghai','上海','China','中国','shanghai','上海','31.398896','121.489934')," +
                        "('101020400','huangpu','黄浦','shanghai','上海','China','中国','huangpu','黄浦','31.222771','121.490317')," +
                        "('101020500','jiading','嘉定','shanghai','上海','China','中国','shanghai','上海','31.383524','121.250333')," +
                        "('101020600','pudongxinqu','浦东新区','shanghai','上海','China','中国','shanghai','上海','31.245944','121.567706')," +
                        "('101020700','jinshan','金山','shanghai','上海','China','中国','shanghai','上海','30.724697','121.330736')," +
                        "('101020800','qingpu','青浦','shanghai','上海','China','中国','shanghai','上海','31.151209','121.113021')," +
                        "('101020900','songjiang','松江','shanghai','上海','China','中国','shanghai','上海','31.03047','121.223543')," +
                        "('101021000','fengxian','奉贤','shanghai','上海','China','中国','shanghai','上海','30.912345','121.458472')," +
                        "('101021100','chongming','崇明','shanghai','上海','China','中国','shanghai','上海','31.626946','121.397516')," +
                        "('101021200','xuhui','徐汇','shanghai','上海','China','中国','shanghai','上海','31.179973','121.43752')," +
                        "('101021300','changning','长宁','shanghai','上海','China','中国','changning','长宁','31.218123','121.4222')," +
                        "('101021400','jingan','静安','shanghai','上海','China','中国','jingan','静安','31.229003','121.448224')," +
                        "('101021500','putuo','普陀','shanghai','上海','China','中国','putuo','普陀','31.241701','121.392499')," +
                        "('101021600','hongkou','虹口','shanghai','上海','China','中国','hongkou','虹口','31.26097','121.491832')," +
                        "('101021700','yangpu','杨浦','shanghai','上海','China','中国','yangpu','杨浦','31.270755','121.522797')," +
                        "('101030100','tianjin','天津','tianjin','天津','China','中国','tianjin','天津','39.125596','117.190182')," +
                        "('101030200','wuqing','武清','tianjin','天津','China','中国','tianjin','天津','39.376925','117.057959')," +
                        "('101030300','baodi','宝坻','tianjin','天津','China','中国','tianjin','天津','39.716965','117.308094')," +
                        "('101030400','dongli','东丽','tianjin','天津','China','中国','tianjin','天津','39.087764','117.313967')," +
                        "('101030500','xiqing','西青','tianjin','天津','China','中国','tianjin','天津','39.139446','117.012247')," +
                        "('101030600','beichen','北辰','tianjin','天津','China','中国','tianjin','天津','39.225555','117.13482')," +
                        "('101030700','ninghe','宁河','tianjin','天津','China','中国','tianjin','天津','39.328886','117.82828')," +
                        "('101030800','heping','和平','tianjin','天津','China','中国','heping','和平','39.118327','117.195907')," +
                        "('101030900','jinghai','静海','tianjin','天津','China','中国','tianjin','天津','38.935671','116.925304')," +
                        "('101031000','jinnan','津南','tianjin','天津','China','中国','tianjin','天津','38.989577','117.382549')," +
                        "('101031100','binhaixinqu','滨海新区','tianjin','天津','China','中国','tianjin','天津','39.032846','117.654173')," +
                        "('101031200','hedong','河东','tianjin','天津','China','中国','hedong','河东','39.122125','117.226568')," +
                        "('101031300','hexi','河西','tianjin','天津','China','中国','hexi','河西','39.101897','117.217536')," +
                        "('101340403','zhanghua','彰化','taiwan','台湾','China','中国','taizhong','台中','24.077','120.535')," +
                        "('101340404','nantou','南投','taiwan','台湾','China','中国','taizhong','台中','23.916','120.685')," +
                        "('101340405','hualian','花莲','taiwan','台湾','China','中国','taizhong','台中','23.983','121.603')," +
                        "('101340406','yunlin','云林','taiwan','台湾','China','中国','taizhong','台中','23.718','120.538')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
