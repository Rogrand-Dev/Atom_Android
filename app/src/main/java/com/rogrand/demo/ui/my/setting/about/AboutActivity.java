package com.rogrand.demo.ui.my.setting.about;

import android.support.v7.widget.Toolbar;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;

import butterknife.BindView;

public class AboutActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "关于");
    }
}
