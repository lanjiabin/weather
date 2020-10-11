package com.android.l.weather;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherFragment extends Fragment {
    static final private String HTTPS = "https://tianqiapi.com/api?version=v6";
    static final private String APPID = "&appid=81165643";
    static final private String APPSECRET = "&appsecret=VOH36NAE";
    static final private String CITYID = "&cityid=";
    static final private String URL = HTTPS + APPID + APPSECRET + CITYID;

    private Handler mHandler;
    private TextView mCityTv, mDate, mWeek, mUpdate_time, mWea, mTem, mTem1, mTem2, mWin, mWin_speed, mWin_meter, mHumidity, mVisibility, mPressure, mAir, mCountry;
    private EditText mCityEt;
    private Button mSearchBtn;

    String cityid, date, week, update_time, city, cityEn, country, countryEn, wea, wea_img, tem, tem1, tem2, win, win_speed, win_meter, humidity, visibility, pressure, air, air_pm25, air_level, air_tips;

    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        //默认查询北京
        queryWeather("101010100");
        mDate = view.findViewById(R.id.date);
        mWeek = view.findViewById(R.id.week);
        mUpdate_time = view.findViewById(R.id.update_time);
        mWea = view.findViewById(R.id.wea);
        mTem = view.findViewById(R.id.tem);
        mTem1 = view.findViewById(R.id.tem1);
        mTem2 = view.findViewById(R.id.tem2);
        mCityTv = view.findViewById(R.id.city);
        mWin = view.findViewById(R.id.win);
        mWin_speed = view.findViewById(R.id.win_speed);
        mWin_meter = view.findViewById(R.id.win_meter);
        mHumidity = view.findViewById(R.id.humidity);
        mVisibility = view.findViewById(R.id.visibility);
        mPressure = view.findViewById(R.id.pressure);
        mAir = view.findViewById(R.id.air);
        mCountry = view.findViewById(R.id.country);
        mCityEt = view.findViewById(R.id.cityEt);
        mSearchBtn = view.findViewById(R.id.searchBtn);
        mContext = getActivity().getApplicationContext();

        updateUI();
        onClick();
        return view;
    }

    private void onClick() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityZh = mCityEt.getText().toString().trim();
                ArrayList<HashMap<String, String>> result = CityDBService.getInstance().queryCityInfoByCityZh(mContext, cityZh);
                if (result.get(0).get("id") == null) {
                    Toast.makeText(mContext, "该城市没有录入！", Toast.LENGTH_SHORT);
                } else {
                    String cityid = result.get(0).get("id");
                    queryWeather(cityid);
                    SeacherTimeDBService.getInstance().addHistory(mContext, cityid, cityZh);
                }
            }
        });
    }

    //更新UI
    public void updateUI() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.arg1 == 110) {
                    mDate.setText(date);
                    mWeek.setText(week);
                    mUpdate_time.setText(update_time);
                    mWea.setText(wea);
                    mTem.setText(tem);
                    mTem1.setText(tem1);
                    mTem2.setText(tem2);
                    mWin.setText(win);
                    mWin_speed.setText(win_speed);
                    mWin_meter.setText(win_meter);
                    mHumidity.setText(humidity);
                    mVisibility.setText(visibility);
                    mPressure.setText(pressure);
                    mAir.setText(air);
                    mCountry.setText(country);
                    mCityTv.setText(city);
                    return true;
                }
                return false;
            }
        });
    }

    //传递城市id
    public void queryWeather(String cityid) {
        okHttpRequest(URL + cityid);
    }

    //查询天气
    public void okHttpRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String s = response.body().string();
                    JSONObject result = new JSONObject(s);

                    cityid = result.getString("cityid").trim();
                    date = result.getString("date").trim();
                    week = result.getString("week").trim();
                    update_time = result.getString("update_time").trim();
                    city = result.getString("city").trim();
                    cityEn = result.getString("cityEn").trim();
                    country = result.getString("country").trim();
                    countryEn = result.getString("countryEn").trim();
                    wea = result.getString("wea").trim();
                    wea_img = result.getString("wea_img").trim();
                    tem = result.getString("tem").trim();
                    tem1 = result.getString("tem1").trim();
                    tem2 = result.getString("tem2").trim();
                    win = result.getString("win").trim();
                    win_speed = result.getString("win_speed").trim();
                    win_meter = result.getString("win_meter").trim();
                    humidity = result.getString("humidity").trim();
                    pressure = result.getString("pressure").trim();
                    visibility = result.getString("visibility").trim();
                    air = result.getString("air").trim();
                    air_pm25 = result.getString("air_pm25").trim();
                    air_level = result.getString("air_level").trim();
                    air_tips = result.getString("air_tips").trim();
                    Message message = new Message();
                    message.arg1 = 110;
                    mHandler.sendMessage(message);


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
