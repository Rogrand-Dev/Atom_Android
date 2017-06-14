package com.rogrand.demo.ui.tool;

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
import com.rogrand.demo.ui.tool.banner.BannerActivity;
import com.rogrand.demo.ui.tool.button.ButtonActivity;
import com.rogrand.demo.ui.tool.dialog.DialogActivity;
import com.rogrand.demo.ui.tool.toast.ToastActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;

public class ToolTabFragment extends SimpleFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_tool_list)
    RecyclerView mRecyclerView;

    private static final Class<?>[] ACTIVITY = {ButtonActivity.class, ToastActivity.class, DialogActivity.class, BannerActivity.class};
    private static final String[] TITLE = {"Button", "Toast", "Dialog", "Banner"};
    private static final int[] IMG = {R.drawable.gv_bottom, R.drawable.gv_dialog, R.drawable.gv_toast, R.drawable.gv_banner};
    private ArrayList<ToolItem> mDataList;

    public static ToolTabFragment newInstance() {
        Bundle args = new Bundle();
        ToolTabFragment fragment = new ToolTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_tool;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "工具");
        EventBus.getDefault().register(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        initData();
        initAdapter();
    }

    private void initAdapter() {
        BaseQuickAdapter toolAdapter = new ToolAdapter(R.layout.item_grid_tool, mDataList);
        toolAdapter.openLoadAnimation();
        View top = mActivity.getLayoutInflater().inflate(R.layout.view_tool_top, (ViewGroup) mRecyclerView.getParent(), false);
        toolAdapter.addHeaderView(top);
        toolAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(mContext, ACTIVITY[position]);
            startActivity(intent);
        });

        mRecyclerView.setAdapter(toolAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            ToolItem item = new ToolItem();
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
