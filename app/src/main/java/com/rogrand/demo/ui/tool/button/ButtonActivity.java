package com.rogrand.demo.ui.tool.button;

import android.support.v7.widget.Toolbar;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;

import butterknife.BindView;

public class ButtonActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_button;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "按钮");
    }
}
