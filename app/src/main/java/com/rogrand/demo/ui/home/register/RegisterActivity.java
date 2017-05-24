package com.rogrand.demo.ui.home.register;

import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;

import butterknife.BindView;

/**
 * 创建: 陈剑虹 17-5-10.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_register_input_phone_number)
    TextView mTvInputPhoneNumber;
    @BindView(R.id.tv_register_input_verify_code)
    TextView mTvInputVerifyCode;
    @BindView(R.id.tv_register_input_password)
    TextView mTvInputPassword;
    @BindView(R.id.vg_register_input_phone_number)
    ViewGroup mVgInputPhoneNumber;
    @BindView(R.id.vg_register_input_verify_code)
    ViewGroup mVgInputVerifyCode;
    @BindView(R.id.vg_register_input_password)
    ViewGroup mVgInputPassword;
    @BindView(R.id.et_register_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.btn_register_send_verify_code)
    Button mBtnSendVerifyCode;
    @BindView(R.id.accb_register_user_agreement)
    AppCompatCheckBox mCbUserAgreement;
    @BindView(R.id.et_register_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.btn_register_confirm_verify_code)
    Button mBtnConfirmVerifyCode;
    @BindView(R.id.et_register_password)
    EditText mEtPassword;
    @BindView(R.id.btn_register_submit_password)
    Button mBtnSubmitPassword;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "注册");
        mEtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 11 && mCbUserAgreement.isChecked()) {
                    mBtnSendVerifyCode.setEnabled(true);
                } else {
                    mBtnSendVerifyCode.setEnabled(false);
                }
            }
        });
        mCbUserAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && mEtPhoneNumber.getText().length() >= 11) {
                    mBtnSendVerifyCode.setEnabled(true);
                } else {
                    mBtnSendVerifyCode.setEnabled(false);
                }
            }
        });
        mEtVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 4) {
                    mBtnConfirmVerifyCode.setEnabled(true);
                } else {
                    mBtnConfirmVerifyCode.setEnabled(false);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 4) {
                    mBtnSubmitPassword.setEnabled(true);
                } else {
                    mBtnSubmitPassword.setEnabled(false);
                }
            }
        });
        mBtnSendVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sendVerifyCode(mEtPhoneNumber.getText().toString());
            }
        });
        mBtnConfirmVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.confirmVerifyCode(mEtVerifyCode.getText().toString());
            }
        });
        mBtnSubmitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setPassword(mEtPassword.getText().toString());
            }
        });

        SpannableString spannableString = new SpannableString(mCbUserAgreement.getText());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(mContext, "用户协议", Toast.LENGTH_SHORT).show();
            }
        }, mCbUserAgreement.getText().toString().indexOf("《"), mCbUserAgreement.getText().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mCbUserAgreement.setText(spannableString);
        mCbUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void showInputPhoneNumber() {
        mTvInputPhoneNumber.setTextColor(Color.parseColor("#f34d41"));
        mTvInputVerifyCode.setTextColor(Color.parseColor("#686868"));
        mTvInputPassword.setTextColor(Color.parseColor("#686868"));
        mVgInputPhoneNumber.setVisibility(View.VISIBLE);
        mVgInputVerifyCode.setVisibility(View.GONE);
        mVgInputPassword.setVisibility(View.GONE);
    }

    @Override
    public void showInputVerifyCode() {
        mTvInputPhoneNumber.setTextColor(Color.parseColor("#686868"));
        mTvInputVerifyCode.setTextColor(Color.parseColor("#f34d41"));
        mTvInputPassword.setTextColor(Color.parseColor("#686868"));
        mVgInputPhoneNumber.setVisibility(View.GONE);
        mVgInputVerifyCode.setVisibility(View.VISIBLE);
        mVgInputPassword.setVisibility(View.GONE);
        mEtVerifyCode.requestFocus();
    }

    @Override
    public void showInputPassword() {
        mTvInputPhoneNumber.setTextColor(Color.parseColor("#686868"));
        mTvInputVerifyCode.setTextColor(Color.parseColor("#686868"));
        mTvInputPassword.setTextColor(Color.parseColor("#f34d41"));
        mVgInputPhoneNumber.setVisibility(View.GONE);
        mVgInputVerifyCode.setVisibility(View.GONE);
        mVgInputPassword.setVisibility(View.VISIBLE);
        mEtPassword.requestFocus();
    }

}
