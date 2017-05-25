package com.rogrand.demo.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleFragment;
import com.rogrand.demo.event.TabSelectedEvent;
import com.rogrand.demo.ui.my.setting.SettingActivity;
import com.rogrand.demo.ui.my.userinfo.UserInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class MyTabFragment extends SimpleFragment {

    @BindView(R.id.llyt_setting)
    LinearLayout mLlytSetting;
    @BindView(R.id.rlyt_user_info)
    RelativeLayout mRlytUserInfo;

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

    @OnClick(R.id.llyt_setting)
    void toSettingActivity() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.rlyt_user_info)
    void toUserInfoActivity() {
        Intent intent = new Intent(mContext, UserInfoActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
