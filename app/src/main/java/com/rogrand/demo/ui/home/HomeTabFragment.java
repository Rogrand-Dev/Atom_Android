package com.rogrand.demo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleFragment;
import com.rogrand.demo.event.TabSelectedEvent;
import com.rogrand.demo.ui.home.imageselector.PictureSelectorActivity;
import com.rogrand.demo.ui.home.login.LoginActivity;
import com.rogrand.demo.ui.home.recycler.RecyclerViewActivity;
import com.rogrand.demo.ui.home.webview.WebViewActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeTabFragment extends SimpleFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    private static final Class<?>[] ACTIVITY = {LoginActivity.class, PictureSelectorActivity.class, RecyclerViewActivity.class, LoginActivity.class, WebViewActivity.class};
    private static final String[] TITLE = {"登录注册", "图片选择", "RecyclerView加载", "多状态UI", "WebView交互"};
    private static final int[] IMG = {R.drawable.gv_animation, R.drawable.gv_multipleltem, R.drawable.gv_header_and_footer, R.drawable.gv_pulltorefresh, R.drawable.gv_section, R.drawable.gv_empty, R.drawable.gv_drag_and_swipe, R.drawable.gv_item_click, R.drawable.gv_expandable, R.drawable.gv_databinding,};
    private ArrayList<HomeItem> mDataList;

    public static HomeTabFragment newInstance() {
        Bundle args = new Bundle();
        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_home;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "界面");
        EventBus.getDefault().register(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        initData();
        initAdapter();
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.item_grid_home, mDataList);
        homeAdapter.openLoadAnimation();
        View top = mActivity.getLayoutInflater().inflate(R.layout.view_home_top, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addHeaderView(top);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mContext, ACTIVITY[position]);
            startActivity(intent);
        });

        mRecyclerView.setAdapter(homeAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
        EventBus.getDefault().unregister(this);
    }
}
