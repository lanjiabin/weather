package com.android.l.weather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryFragment extends Fragment {
    private ArrayList<HashMap<String, String>> mHistoryList;
    private Context mContext;
    private ListView mListView;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_history, null);
        mContext = getActivity().getApplicationContext();
        mListView = mView.findViewById(R.id.history_list);
        setListViewAdapter();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //刷新数据
        setListViewAdapter();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //刷新数据
        if (mView != null && !hidden) setListViewAdapter();
    }

    //显示数据到ListView上
    public void setListViewAdapter() {
        mHistoryList = SeacherTimeDBService.getInstance().queryHistoryInfoByID(mContext);
        if (mHistoryList == null) return;
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext,
                mHistoryList,
                R.layout.history_info_item,
                new String[]{
                        "cityZh",
                        "create_time"},
                new int[]{
                        R.id.cityTv,
                        R.id.update_timeTv});
        mListView.setAdapter(simpleAdapter);
    }
}
