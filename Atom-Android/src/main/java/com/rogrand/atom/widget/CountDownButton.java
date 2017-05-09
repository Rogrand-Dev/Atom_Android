package com.rogrand.atom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.rogrand.atom.R;

import java.util.Locale;

public class CountDownButton extends AppCompatButton {

    private static final long DEFAULT_INTERVAL = 1000; // 默认时间间隔 1000 ms
    private static final long DEFAULT_COUNT = 60 * 1000; // 默认时长 60 s
    private static final String DEFAULT_COUNT_FORMAT = "%d"; // 默认倒计时文字格式(显示秒数)
    private String mDefaultText; // 默认按钮文字 {@link #getText()}
    private long mCount; // 倒计时时长，单位为毫秒
    private long mInterval; // 时间间隔，单位为毫秒
    private String mCountDownFormat = DEFAULT_COUNT_FORMAT; // 倒计时文字格式
    private boolean mEnableCountDown = true; // 倒计时是否可用
    private OnClickListener uClickListener; // 点击事件监听器
    private CountDownTimer mCountDownTimer; // 倒计时
    private boolean isCountDownNow; // 是否正在执行倒计时

    public CountDownButton(Context context) {
        super(context);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        // 获取自定义属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownButton);
        mCountDownFormat = typedArray.getString(R.styleable.CountDownButton_countDownFormat);
        if (typedArray.hasValue(R.styleable.CountDownButton_countDown)) {
            mCount = (int) typedArray.getFloat(R.styleable.CountDownButton_countDown, DEFAULT_COUNT);
        }
        mInterval = (int) typedArray.getFloat(R.styleable.CountDownButton_countDownInterval, DEFAULT_INTERVAL);
        mEnableCountDown = (mCount > mInterval) && typedArray.getBoolean(R.styleable.CountDownButton_enableCountDown, false);
        typedArray.recycle();
        // 初始化倒计时Timer
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(mCount, mInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(String.format(Locale.CHINA, mCountDownFormat, millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    isCountDownNow = false;
                    mEnableCountDown = false;
                    setEnabled(true);
                    setClickable(true);
                    setText(mDefaultText);
                }
            };
        }

    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.uClickListener = onClickListener;
        super.setOnClickListener(mOnClickListener);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (uClickListener != null)
                uClickListener.onClick(v);
            if (mEnableCountDown) {
                mDefaultText = getText().toString();
                // 设置按钮不可点击
                setEnabled(false);
                setClickable(false);
                // 开始倒计时
                mCountDownTimer.start();
                isCountDownNow = true;
            }
        }
    };

    public void setEnableCountDown(boolean enableCountDown) {
        this.mEnableCountDown = (mCount > mInterval) && enableCountDown;
    }

    public void setCountDownFormat(String countDownFormat) {
        this.mCountDownFormat = countDownFormat;
    }

    public void setCount(long count) {
        this.mCount = count;
    }

    public void setInterval(long interval) {
        mInterval = interval;
    }

    /**
     * 是否正在执行倒计时
     *
     * @return 倒计时期间返回true否则返回false
     */
    public boolean isCountDownNow() {
        return isCountDownNow;
    }

    /**
     * 设置倒计时数据
     *
     * @param count           时长
     * @param interval        间隔
     * @param countDownFormat 文字格式
     */
    public void setCountDown(long count, long interval, String countDownFormat) {
        this.mCount = count;
        this.mCountDownFormat = countDownFormat;
        this.mInterval = interval;
        setEnableCountDown(true);
    }

    /**
     * 移除倒计时
     */
    public void removeCountDown() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        isCountDownNow = false;
        setText(mDefaultText);
        setEnabled(true);
        setClickable(true);
    }
}
