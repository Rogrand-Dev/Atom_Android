package com.rogrand.demo.ui.tool.dialog;

import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Thread thread;
    private Toast toast;

    @Override
    protected int getLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "Dialog");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (thread != null && !thread.isInterrupted() && thread.isAlive()) {
            thread.interrupt();
        }
    }

    private void startThread(Runnable run) {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(run);
        thread.start();
    }

    private void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @OnClick(R.id.basicNoTitle)
    public void showBasicNoTitle() {
        new MaterialDialog.Builder(this)
                .content(R.string.shareLocationPrompt)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .show();
    }

    @OnClick(R.id.basic)
    public void showBasic() {
        new MaterialDialog.Builder(this)
                .title("权限申请")
                .content(R.string.shareLocationPrompt)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .show();
    }

    @OnClick(R.id.basicIcon)
    public void showBasicIcon() {
        new MaterialDialog.Builder(this)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title("权限申请")
                .content(R.string.shareLocationPrompt)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .show();
    }

    @OnClick(R.id.listNoTitle)
    public void showListNoTitle() {
        new MaterialDialog.Builder(this)
                .items(R.array.socialNetworks)
                .itemsCallback((dialog, view, which, text) -> showToast(which + ": " + text))
                .show();
    }

    @OnClick(R.id.list)
    public void showList() {
        new MaterialDialog.Builder(this)
                .title(R.string.socialNetworks)
                .items(R.array.socialNetworks)
                .itemsCallback((dialog, view, which, text) -> showToast(which + ": " + text))
                .show();
    }

    @OnClick(R.id.singleChoice)
    public void showSingleChoice() {
        new MaterialDialog.Builder(this)
                .title(R.string.socialNetworks)
                .items(R.array.socialNetworks)
                .itemsDisabledIndices(1, 3)
                .itemsCallbackSingleChoice(
                        2,
                        (dialog, view, which, text) -> {
                            showToast(which + ": " + text);
                            return true; // allow selection
                        })
                .positiveText(R.string.md_choose_label)
                .show();
    }

    @OnClick(R.id.multiChoice)
    public void showMultiChoice() {
        new MaterialDialog.Builder(this)
                .title(R.string.socialNetworks)
                .items(R.array.socialNetworks)
                .itemsCallbackMultiChoice(
                        new Integer[]{1, 2},
                        (dialog, which, text) -> {
                            StringBuilder str = new StringBuilder();
                            for (int i = 0; i < which.length; i++) {
                                if (i > 0) {
                                    str.append('\n');
                                }
                                str.append(which[i]);
                                str.append(": ");
                                str.append(text[i]);
                            }
                            showToast(str.toString());
                            return true; // allow selection
                        })
                .onNeutral((dialog, which) -> dialog.clearSelectedIndices())
                .onPositive((dialog, which) -> dialog.dismiss())
                .alwaysCallMultiChoiceCallback()
                .positiveText(R.string.md_choose_label)
                .autoDismiss(false)
                .neutralText(R.string.clear_selection)
                .show();
    }

    @OnClick(R.id.input)
    public void showInputDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .content(R.string.input_content)
                .inputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                                | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(2, 16)
                .positiveText(R.string.submit)
                .input(
                        R.string.input_hint,
                        R.string.input_hint,
                        false,
                        (dialog, input) -> showToast("Hello, " + input.toString() + "!"))
                .show();
    }

    @OnClick(R.id.progress1)
    public void showProgressDeterminateDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .contentGravity(GravityEnum.CENTER)
                .progress(false, 150, true)
                .cancelListener(
                        dialog -> {
                            if (thread != null) {
                                thread.interrupt();
                            }
                        })
                .showListener(
                        dialogInterface -> {
                            final MaterialDialog dialog = (MaterialDialog) dialogInterface;
                            startThread(
                                    () -> {
                                        while (dialog.getCurrentProgress() != dialog.getMaxProgress()
                                                && !Thread.currentThread().isInterrupted()) {
                                            if (dialog.isCancelled()) {
                                                break;
                                            }
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException e) {
                                                break;
                                            }
                                            dialog.incrementProgress(1);
                                        }
                                        runOnUiThread(
                                                () -> {
                                                    thread = null;
                                                    dialog.setContent(getString(R.string.md_done_label));
                                                });
                                    });
                        })
                .show();
    }

    @OnClick(R.id.progress2)
    public void showProgressIndeterminateDialog() {
        showIndeterminateProgressDialog(false);
    }

    @OnClick(R.id.progress3)
    public void showProgressHorizontalIndeterminateDialog() {
        showIndeterminateProgressDialog(true);
    }

    private void showIndeterminateProgressDialog(boolean horizontal) {
        new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .show();
    }

}
