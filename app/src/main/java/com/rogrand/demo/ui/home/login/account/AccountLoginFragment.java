package com.rogrand.demo.ui.home.login.account;

import android.widget.AutoCompleteTextView;

import com.rogrand.atom.widget.ButtonStyle;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建: 陈剑虹 17-5-5.
 */

public class AccountLoginFragment extends BaseFragment<AccountLoginPresenter> implements AccountLoginContract.View {

    @BindView(R.id.username)
    AutoCompleteTextView mEtAccount;
    @BindView(R.id.password)
    AutoCompleteTextView mEtPassword;
    @BindView(R.id.login)
    ButtonStyle mBtnSubmit;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_account;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.login)
    void loginSubmit() {

        String mAccount = mEtAccount.getText().toString();
        String mPassword = mEtPassword.getText().toString();

        mPresenter.loginAccount(mContext, mAccount, mPassword);
    }

    @Override
    public void loginAccountSuccess() {

    }
}
