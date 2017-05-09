package com.rogrand.demo.ui.home.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 创建: 陈剑虹 17-5-5.
 */

public class CatEyeLoginPagerAdapter extends FragmentPagerAdapter {
    public CatEyeLoginPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CatEyeLoginFragment();
        } else {
            return new CatEyeMobileLoginFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "帐号密码登录";
        } else {
            return "手机号快捷登录";
        }
    }
}
