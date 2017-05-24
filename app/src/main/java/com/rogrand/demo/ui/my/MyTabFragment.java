package com.rogrand.demo.ui.my;

import android.os.Bundle;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleFragment;
import com.rogrand.demo.event.TabSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyTabFragment extends SimpleFragment {

    public static MyTabFragment newInstance() {
        Bundle args = new Bundle();
        MyTabFragment fragment = new MyTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_my;
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().register(this);
    }


    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
