package com.rogrand.demo.ui.my.userinfo.mycity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.rogrand.atom.utils.DateMgr;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MyCityActivity extends BaseActivity<MyCityPresenter> implements AMapLocationListener, MyCityContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_city_name)
    TextView mTvCityName;

    public AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption locationOption = null;
    private static final int RC_LOCATION_PERM = 124;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_city;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "所在城市");
        initLocation();
        locationAndContactsTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopLocation(mLocationClient);
        destroyLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (null != location) {
            StringBuffer sb = new StringBuffer();
            // errCode等于0代表定位成功
            if (location.getErrorCode() == 0) {
                sb.append("定位成功" + "\n");
                sb.append("定位类型: " + location.getLocationType() + "\n");
                sb.append("经    度    : " + location.getLongitude() + "\n");
                sb.append("纬    度    : " + location.getLatitude() + "\n");
                sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                sb.append("提供者    : " + location.getProvider() + "\n");

                sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                sb.append("角    度    : " + location.getBearing() + "\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : " + location.getSatellites() + "\n");
                sb.append("国    家    : " + location.getCountry() + "\n");
                sb.append("省            : " + location.getProvince() + "\n");
                sb.append("市            : " + location.getCity() + "\n");
                mTvCityName.setText(location.getCity());
                sb.append("城市编码 : " + location.getCityCode() + "\n");
                sb.append("区            : " + location.getDistrict() + "\n");
                sb.append("区域 码   : " + location.getAdCode() + "\n");
                sb.append("地    址    : " + location.getAddress() + "\n");
                sb.append("兴趣点    : " + location.getPoiName() + "\n");
                // 定位完成的时间
                sb.append("定位时间: " + DateMgr.formatDateTime(location.getTime()) + "\n");
            } else {
                // 定位失败
                sb.append("定位失败" + "\n");
                sb.append("错误码:" + location.getErrorCode() + "\n");
                sb.append("错误信息:" + location.getErrorInfo() + "\n");
                sb.append("错误描述:" + location.getLocationDetail() + "\n");
            }
            // 定位之后的回调时间
            sb.append("回调时间: " + DateMgr.formatDateTime(System.currentTimeMillis()) + "\n");

            // 解析定位结果，
            String result = sb.toString();
            LogUtils.d(result);
        } else {
            LogUtils.d("定位失败，loc is null");
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationClient = new AMapLocationClient(this.getApplicationContext()); // 初始化client
        locationOption = mPresenter.getDefaultOption();
        mLocationClient.setLocationOption(locationOption); // 设置定位参数
        mLocationClient.setLocationListener(this); // 设置定位监听
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            locationOption = null;
        }
    }

    @AfterPermissionGranted(RC_LOCATION_PERM)
    public void locationAndContactsTask() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            mPresenter.startLocation(mLocationClient, locationOption);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location_contacts), RC_LOCATION_PERM, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        LogUtils.d("onPermissionsGranted:" + requestCode + ":" + perms.size());
        mPresenter.startLocation(mLocationClient, locationOption);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtils.d("onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            mPresenter.startLocation(mLocationClient, locationOption);
        }
    }

}
