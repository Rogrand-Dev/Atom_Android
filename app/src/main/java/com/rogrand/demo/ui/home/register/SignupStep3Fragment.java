package com.rogrand.demo.ui.home.register;

import com.rogrand.atom.widget.ButtonStyle;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupStep3Fragment extends SimpleFragment {

    @BindView(R.id.btn_submit)
    ButtonStyle mBtnSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signup_step3;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.btn_submit)
    void submit() {

    }
}
