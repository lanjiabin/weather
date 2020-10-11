package com.android.l.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;
    private Button mWeatherBtn, mHistoryBtn;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mTransaction;
    private WeatherFragment mWeatherFragment;
    private HistoryFragment mHistoryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        onClick();
    }

    //初始化
    private void initView() {
        setContentView(R.layout.activity_main);
        mFrameLayout = findViewById(R.id.frameLayout);
        mWeatherBtn = findViewById(R.id.weatherBtn);
        mHistoryBtn = findViewById(R.id.historyBtn);

        mSupportFragmentManager = getSupportFragmentManager();
        mTransaction = mSupportFragmentManager.beginTransaction();

        mWeatherFragment = new WeatherFragment();
        mFragments.add(mWeatherFragment);
        hideOthersFragment(mWeatherFragment, true);

    }

    //点击事件
    public void onClick() {
        mWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWeatherFragment == null) {
                    mWeatherFragment = new WeatherFragment();
                    mFragments.add(mWeatherFragment);
                    hideOthersFragment(mWeatherFragment, true);
                } else {
                    hideOthersFragment(mWeatherFragment, false);
                }
            }
        });

        mHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHistoryFragment == null) {
                    mHistoryFragment = new HistoryFragment();
                    mFragments.add(mHistoryFragment);
                    hideOthersFragment(mHistoryFragment, true);
                } else {
                    hideOthersFragment(mHistoryFragment, false);
                }
            }
        });
    }

    private void hideOthersFragment(Fragment showFragment, boolean add) {
        mTransaction = mSupportFragmentManager.beginTransaction();
        mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        //添加动画
        mTransaction.setCustomAnimations(
                R.anim.from_left,
                R.anim.out_right);

        if (add) {
            mTransaction.add(R.id.frameLayout, showFragment);
        }

        //隐藏和显示Fragment
        for (Fragment fragment : mFragments) {
            if (showFragment.equals(fragment)) {
                mTransaction.show(fragment);
            } else {
                mTransaction.hide(fragment);
            }
        }
        mTransaction.commit();
    }

}