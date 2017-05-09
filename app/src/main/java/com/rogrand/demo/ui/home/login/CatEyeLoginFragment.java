package com.rogrand.demo.ui.home.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rogrand.demo.R;
import com.rogrand.demo.bean.LoginBean;
import com.rogrand.demo.http.HttpManager;
import com.rogrand.demo.http.callback.OnResultCallBack;
import com.rogrand.demo.http.subscriber.HttpSubscriber;


/**
 * 创建: 陈剑虹 17-5-5.
 */

public class CatEyeLoginFragment extends Fragment {
    private HttpSubscriber mHttpObserver;

    EditText mEtAccount;
    ImageView mIvAccountClear;
    EditText mEtPassword;
    ImageView mIvPasswordClear;
    ImageView mIvPasswordWatch;
    Button mBtnSubmit;
    TextView mTvLoginQuestions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cat_eye_login, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }

    private void initView(View view) {
        mEtAccount = (EditText) view.findViewById(R.id.et_cat_eye_login_account);
        mEtPassword = (EditText) view.findViewById(R.id.et_cat_eye_login_password);
        mIvAccountClear = (ImageView) view.findViewById(R.id.iv_cat_eye_login_account_clear);
        mIvPasswordClear = (ImageView) view.findViewById(R.id.iv_cat_eye_login_password_clear);
        mIvPasswordWatch = (ImageView) view.findViewById(R.id.iv_cat_eye_login_password_watch);
        mBtnSubmit = (Button) view.findViewById(R.id.btn_cat_eye_login_submit);
        mTvLoginQuestions = (TextView) view.findViewById(R.id.tv_cat_eye_login_login_questions);
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
                if (mEtPassword.getText().length() > 0 && s.length() > 0) {
                    mBtnSubmit.setEnabled(true);
                } else {
                    mBtnSubmit.setEnabled(false);
                }
                if (s.length() < 1) {
                    mIvAccountClear.setVisibility(View.INVISIBLE);
                } else {
                    mIvAccountClear.setVisibility(View.VISIBLE);
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
                if (mEtAccount.getText().length() > 0 && s.length() > 0) {
                    mBtnSubmit.setEnabled(true);
                } else {
                    mBtnSubmit.setEnabled(false);
                }
                if (s.length() < 1) {
                    mIvPasswordClear.setVisibility(View.INVISIBLE);
                } else {
                    mIvPasswordClear.setVisibility(View.VISIBLE);
                }
            }
        });

        mIvAccountClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtAccount.setText("");
            }
        });

        mIvPasswordClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPassword.setText("");
            }
        });

        mIvPasswordWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtPassword.getInputType() == (EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT)) {
                    mEtPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
                    mEtPassword.setSelection(mEtPassword.length());
                } else {
                    mEtPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
                    mEtPassword.setSelection(mEtPassword.length());
                }
            }
        });

        mHttpObserver = new HttpSubscriber(new OnResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

            @Override
            public void onError(int code, String errorMsg) {

            }
        });

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().loginNoCache(mHttpObserver, mEtAccount.getText().toString(), mEtPassword.getText().toString());
            }
        });

        mTvLoginQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "你遇到什么问题?不妨说来听听.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHttpObserver.unSubscribe();
    }
}
