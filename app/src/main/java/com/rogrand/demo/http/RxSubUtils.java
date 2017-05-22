package com.rogrand.demo.http;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rogrand.atom.utils.NetUtils;
import com.rogrand.demo.http.exception.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class RxSubUtils<T> extends ResourceSubscriber<T> {

    private Context mContext;
    private MaterialDialog mDialog;
    private String mDialogContent = "";
    private boolean isShowDialog = true;

    protected RxSubUtils(Context context) {
        this(context, "请稍后...");
    }

    protected RxSubUtils(Context context, boolean showDialog) {
        this.mContext = context;
        this.isShowDialog = showDialog;
    }

    protected RxSubUtils(Context context, String dialogContent) {
        this.mContext = context;
        this.mDialogContent = dialogContent;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog) {
            mDialog = new MaterialDialog.Builder(mContext).canceledOnTouchOutside(false).progress(true, 0)
                    .dismissListener(dialog -> {
                        if (!isDisposed()) {
                            dispose();
                        }
                    }).build();

            if (!mDialog.isShowing()) {
                mDialog.setContent(mDialogContent);
                mDialog.show();
            }
        }
    }

    @Override
    public void onComplete() {
        if (isShowDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetUtils.isConnected(mContext)) {
            _onError(ApiException.CODE_NET_EXCEPTION, ApiException.ERROR_MSG_NET_EXCEPTION);
        } else if (e instanceof ApiException) {
            _onError(ApiException.CODE_SERVICE_EXCEPTION, e.getMessage());
        } else {
            _onError(ApiException.CODE_OTHER_EXCEPTION, ApiException.ERROR_MSG_OTHER_EXCEPTION);
        }
        if (isShowDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(int code, String errorMsg);

}
