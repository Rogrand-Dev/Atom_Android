package com.rogrand.demo.ui.my.userinfo.mycity;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.rogrand.demo.base.BasePresenter;
import com.rogrand.demo.base.BaseView;

interface MyCityContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 默认的定位参数
         *
         * @return AMapLocationClientOption
         */
        AMapLocationClientOption getDefaultOption();

        /**
         * 开始定位
         */
        void startLocation(AMapLocationClient mLocationClient, AMapLocationClientOption locationOption);

        /**
         * 停止定位
         */
        void stopLocation(AMapLocationClient mLocationClient);
    }
}
