package com.rogrand.demo.ui.home.register;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;
import com.rogrand.demo.event.SignupStepEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * 创建: 陈剑虹 17-5-10.
 */

public class RegisterActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.step_tip)
    RadioGroup mRgStepTip;
    @BindView(R.id.container)
    FrameLayout mContainer;

    private SignupStep1Fragment mStep1Fragment;
    private SignupStep2Fragment mStep2Fragment;
    private SignupStep3Fragment mStep3Fragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "注册");
        EventBus.getDefault().register(this);
        mStep1Fragment = new SignupStep1Fragment();
        mStep2Fragment = new SignupStep2Fragment();
        mStep3Fragment = new SignupStep3Fragment();
        mRgStepTip.check(R.id.step1);
        addFragment(mStep1Fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void signupStep(SignupStepEvent event) {
        if (event.getPosition() == 1) {
            mRgStepTip.check(R.id.step2);
            addFragment(mStep2Fragment);
        } else if (event.getPosition() == 2) {
            mRgStepTip.check(R.id.step3);
            addFragment(mStep3Fragment);
        }
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
