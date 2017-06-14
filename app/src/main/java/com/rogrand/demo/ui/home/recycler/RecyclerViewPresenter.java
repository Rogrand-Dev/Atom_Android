package com.rogrand.demo.ui.home.recycler;

import android.content.Context;

import com.rogrand.demo.base.RxPresenter;
import com.rogrand.demo.bean.MovieListBean;
import com.rogrand.demo.component.DataManager;
import com.rogrand.demo.http.RxSubUtils;
import com.rogrand.demo.http.api.ApiResponse;
import com.rogrand.demo.utils.RxUtils;

import javax.inject.Inject;

class RecyclerViewPresenter extends RxPresenter<RecyclerViewContract.View> implements RecyclerViewContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RecyclerViewPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getMovieList(Context mContext) {
        addSubscribe(mDataManager.getMovieList()
                .compose(RxUtils.<ApiResponse<MovieListBean>>rxSchedulerHelper())
                .compose(RxUtils.<MovieListBean>handleResult())
                .subscribeWith(new RxSubUtils<MovieListBean>(mContext, false) {

                    @Override
                    public void _onNext(MovieListBean mMovieListBean) {
                        mView.movieListResult(mMovieListBean.getMovies());
                    }

                    @Override
                    public void _onError(int errorCode, String message) {
                        mView.showErrorMsg(message);
                    }
                }));
    }

}
