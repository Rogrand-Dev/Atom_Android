package com.rogrand.demo.ui.my.userinfo.mycity;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.rogrand.demo.base.RxPresenter;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;

import javax.inject.Inject;

class MyCityPresenter extends RxPresenter<MyCityContract.View> implements MyCityContract.Presenter {

    @Inject
    public MyCityPresenter() {

    }

    @Override
    public AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationMode.Hight_Accuracy); // 可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false); // 可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000); // 可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(5000); // 可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true); // 可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false); // 可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false); // 可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP); // 可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false); // 可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); // 可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); // 可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    @Override
    public void startLocation(AMapLocationClient mLocationClient, AMapLocationClientOption locationOption) {
        mLocationClient.setLocationOption(locationOption); // 设置定位参数
        mLocationClient.startLocation(); // 启动定位
    }

    @Override
    public void stopLocation(AMapLocationClient mLocationClient) {
        mLocationClient.stopLocation(); // 停止定位
    }
}
