package com.rogrand.demo.ui.tool.toast;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.Toast;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.graphics.Typeface.BOLD_ITALIC;

public class ToastActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_error_toast)
    Button mBtnErrorToast;
    @BindView(R.id.btn_success_toast)
    Button mBtnSuccessToast;
    @BindView(R.id.btn_info_toast)
    Button mBtnInfoToast;
    @BindView(R.id.btn_info_toast_with_formatting)
    Button mBtnInfoToastWithFormatting;
    @BindView(R.id.btn_warning_toast)
    Button mBtnWarningToast;
    @BindView(R.id.btn_normal_toast_wo_icon)
    Button mBtnNormalToastWoIcon;
    @BindView(R.id.btn_normal_toast_w_icon)
    Button mBtnNormalToastWIcon;
    @BindView(R.id.btn_custom_config)
    Button mBtnCustomConfig;

    @Override
    protected int getLayout() {
        return R.layout.activity_toast;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "Toast");
    }

    @OnClick(R.id.btn_error_toast)
    void showErrorToast() {
        Toasty.error(ToastActivity.this, "提交信息错误", Toast.LENGTH_SHORT, true).show();
    }

    @OnClick(R.id.btn_success_toast)
    void showSuccessToast() {
        Toasty.success(ToastActivity.this, "支付成功", Toast.LENGTH_SHORT, true).show();
    }

    @OnClick(R.id.btn_info_toast)
    void showInfoToast() {
        Toasty.info(ToastActivity.this, "请输入有效的邮箱", Toast.LENGTH_SHORT, true).show();
    }

    @OnClick(R.id.btn_info_toast_with_formatting)
    void showInfoToastWithFormatting() {
        Toasty.info(ToastActivity.this, getFormattedMessage()).show();
    }

    @OnClick(R.id.btn_warning_toast)
    void showWarningToast() {
        Toasty.warning(ToastActivity.this, "图片选择已达上限", Toast.LENGTH_SHORT, true).show();
    }

    @OnClick(R.id.btn_normal_toast_wo_icon)
    void showNormalToastWoIcon() {
        Toasty.normal(ToastActivity.this, "再按一次，退出程序").show();
    }

    @OnClick(R.id.btn_normal_toast_w_icon)
    void showNormalToastWIcon() {
        Drawable icon = getResources().getDrawable(R.drawable.ic_pets_white_48dp);
        Toasty.normal(ToastActivity.this, "验证码已发送，请查收", icon).show();
    }

    @OnClick(R.id.btn_custom_config)
    void showCustomConfig() {
        Toasty.Config.getInstance()
                .setTextColor(Color.GREEN)
                .setToastTypeface(Typeface.createFromAsset(getAssets(), "PCapTerminal.otf"))
                .apply();
        Toasty.custom(ToastActivity.this, "Rogrand Atom", getResources().getDrawable(R.drawable.ic_laptop),
                Color.BLACK, Toast.LENGTH_SHORT, true, true).show();
        Toasty.Config.reset();
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "您的";
        final String highlight = "手机号";
        final String suffix = " 验证不通过";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
