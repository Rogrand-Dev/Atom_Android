package com.rogrand.demo.ui.home.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogrand.atom.widget.CountDownButton;
import com.rogrand.demo.R;


/**
 * 创建: 陈剑虹 17-5-5.
 */

public class CatEyeMobileLoginFragment extends Fragment {
    EditText mEtAccount;
    ImageView mIvAccountClear;
    CountDownButton mBtnGetVCode;
    EditText mEtVCode;
    ImageView mIvVCodeClear;
    Button mBtnSubmit;
    TextView mTvQuestions;
    TextView mTvUserAgreement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cat_eye_mobile_login, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }

    private void initListener() {
        mEtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 1) {
                    mIvAccountClear.setVisibility(View.INVISIBLE);
                    mBtnSubmit.setEnabled(false);
                } else {
                    mIvAccountClear.setVisibility(View.VISIBLE);
                    if (mEtVCode.length() > 0) {
                        mBtnSubmit.setEnabled(true);
                    }
                }
                if (s.length() < 11) {
                    mBtnGetVCode.setEnabled(false);
                    mBtnGetVCode.setTextColor(Color.parseColor("#BCBCBC"));
                } else {
                    mBtnGetVCode.setEnabled(true);
                    mBtnGetVCode.setTextColor(Color.parseColor("#666666"));
                }
            }
        });

        mEtVCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 1) {
                    mIvVCodeClear.setVisibility(View.INVISIBLE);
                    mBtnSubmit.setEnabled(false);
                } else {
                    mIvVCodeClear.setVisibility(View.VISIBLE);
                    if (mEtAccount.length() > 0) {
                        mBtnSubmit.setEnabled(true);
                    }
                }
            }
        });

        mIvAccountClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtAccount.setText("");
            }
        });
        mBtnGetVCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtVCode.setText("88656");
                mBtnGetVCode.setEnableCountDown(true);
            }
        });
        mIvVCodeClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtVCode.setText("");
            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HttpManager.getInstance().loginNoCache(mHttpObserver, mEtAccount.getText().toString(), mEtVCode.getText().toString());
            }
        });

        mTvQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "老铁,没毛病!", Toast.LENGTH_SHORT).show();
            }
        });
        SpannableString spannableString = new SpannableString(mTvUserAgreement.getText());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getContext(), "用户协议", Toast.LENGTH_SHORT).show();
            }
        }, mTvUserAgreement.getText().toString().indexOf("《"), mTvUserAgreement.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvUserAgreement.setText(spannableString);
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initView(View view) {
        mEtAccount = (EditText) view.findViewById(R.id.et_cat_eye_mobile_login_account);
        mIvAccountClear = (ImageView) view.findViewById(R.id.iv_cat_eye_mobile_login_account_clear);
        mBtnGetVCode = (CountDownButton) view.findViewById(R.id.btn_cat_eye_mobile_login_get_vcode);
        mEtVCode = (EditText) view.findViewById(R.id.et_cat_eye_mobile_login_vcode);
        mIvVCodeClear = (ImageView) view.findViewById(R.id.iv_cat_eye_mobile_login_vcode_clear);
        mBtnSubmit = (Button) view.findViewById(R.id.btn_cat_eye_mobile_login_submit);
        mTvQuestions = (TextView) view.findViewById(R.id.tv_cat_eye_mobile_login_questions);
        mTvUserAgreement = (TextView) view.findViewById(R.id.tv_cat_eye_mobile_login_user_agreement);
    }
}
