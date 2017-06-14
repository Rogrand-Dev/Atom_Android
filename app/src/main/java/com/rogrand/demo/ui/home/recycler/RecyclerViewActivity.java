package com.rogrand.demo.ui.home.recycler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;
import com.rogrand.demo.bean.MovieBean;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class RecyclerViewActivity extends BaseActivity<RecyclerViewPresenter> implements RecyclerViewContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private static final int PAGE_SIZE = 10;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "RecyclerView");

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRvList.setLayoutManager(new LinearLayoutManager(this));

        mPresenter.getMovieList(mContext);
    }

    @Override
    public void movieListResult(List<MovieBean> mData) {
        initAdapter(mData);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        mRecyclerViewAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(() -> {
            mPresenter.getMovieList(mContext);
            mSwipeRefreshLayout.setRefreshing(false);
            mRecyclerViewAdapter.setEnableLoadMore(true);
        }, 1000);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mSwipeRefreshLayout.setEnabled(false);
        if (mRecyclerViewAdapter.getData().size() < PAGE_SIZE) {
            mRecyclerViewAdapter.loadMoreEnd(true);
        } else {
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    private void initAdapter(List<MovieBean> mData) {
        mRecyclerViewAdapter = new RecyclerViewAdapter(mData);
        mRecyclerViewAdapter.setOnLoadMoreListener(this, mRvList);
        mRecyclerViewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRvList.setAdapter(mRecyclerViewAdapter);

        // item 点击事件
        mRvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toasty.normal(RecyclerViewActivity.this, "item点击 -- >" + mData.get(position).getNm()).show();
            }
        });

        // 按钮点击事件
        mRvList.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toasty.normal(RecyclerViewActivity.this, "button点击 -- >" + mData.get(position).getNm()).show();
            }
        });
    }

}
