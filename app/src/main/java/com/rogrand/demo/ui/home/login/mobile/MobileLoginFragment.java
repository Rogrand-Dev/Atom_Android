package com.rogrand.demo.ui.home.login.mobile;

import android.widget.EditText;

import com.rogrand.atom.widget.ButtonStyle;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建: 陈剑虹 17-5-5.
 */

public class MobileLoginFragment extends BaseFragment<MobileLoginPresenter> implements MobileLoginContract.View {

    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_v_code)
    EditText mEtVCode;
    @BindView(R.id.btn_get_code)
    ButtonStyle mBtnGetCode;
    @BindView(R.id.btn_code_login)
    ButtonStyle BtnCodeLogin;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_mobile;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.btn_get_code)
    void getVerificationCode() {
        String mTel = mEtMobile.getText().toString();
        mPresenter.getVCode(mContext, mTel);
    }

    @OnClick(R.id.btn_code_login)
    void codeLogin() {
        String mTel = mEtMobile.getText().toString();
        String mVCode = mEtVCode.getText().toString();
        mPresenter.loginVCode(mContext, mTel, mVCode);
    }

    @Override
    public void VCodeloginSuccess() {

    }

}
