package com.rogrand.demo.ui.home.recycler;

import android.content.Context;

import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;
import com.rogrand.demo.bean.MovieBean;

import java.util.List;

interface RecyclerViewContract {

    interface View extends BaseView {

        void movieListResult(List<MovieBean> mData);
    }

    interface Presenter extends BasePresenter<View> {

        void getMovieList(Context mContext);

    }
}
