package com.rogrand.demo.ui.home.register;

import com.rogrand.atom.widget.ButtonStyle;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleFragment;
import com.rogrand.demo.event.SignupStepEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupStep2Fragment extends SimpleFragment {

    @BindView(R.id.btn_submit_code)
    ButtonStyle mBtnSubmitCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signup_step2;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.btn_submit_code)
    void submitCode() {
        EventBus.getDefault().post(new SignupStepEvent(2));
    }

}
