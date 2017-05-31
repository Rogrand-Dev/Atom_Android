package com.rogrand.demo.ui.my.userinfo;

import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.rogrand.atom.widget.CircleImageView;
import com.rogrand.demo.R;
import com.rogrand.demo.base.BaseActivity;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements UserInfoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_img)
    CircleImageView userHeader;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "个人信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 选择头像
     */
    @OnClick(R.id.llyt_user_img)
    public void changeAvatar() {
        FunctionOptions options = new FunctionOptions.Builder()
                .setCropMode(FunctionConfig.CROP_MODEL_1_1)//设置裁剪模式
                .setCompress(true)//设置是否压缩
                .setEnablePixelCompress(true)//是否启用像素压缩
                .setEnableQualityCompress(true)//是否启用质量压缩
                .setSelectMode(FunctionConfig.MODE_SINGLE)//单选还是多选
                .setShowCamera(true)//是否显示相机
                .setEnablePreview(true)//是否启用预览
                .setEnableCrop(true)//是否启用裁剪
                .setGrade(Luban.THIRD_GEAR)//压缩等级
                .setCropW(100)//裁剪宽度
                .setCropH(100)//裁剪高度
                .setMaxB(400)//最大压缩值，单位：kb
                .setCompressFlag(2)//使用Luban压缩
                .setCompressH(100)//压缩高
                .setCompressW(100)//要锁宽
                .create();
        PictureConfig.getInstance().init(options).openPhoto(UserInfoActivity.this, resultCallback);
    }

    //图片选择回调方法
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> list) {
            //多选回调

        }

        @Override
        public void onSelectSuccess(LocalMedia localMedia) {
            //单选回调
            String path = "";
            if (localMedia.isCut() && !localMedia.isCompressed()) {
                //裁剪过
                path = localMedia.getCutPath();
            } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
                //压缩过，或者裁剪过后压缩过，以是否压缩为准
                path = localMedia.getCompressPath();
            } else {
                //原图
                path = localMedia.getPath();
            }
            Glide.with(UserInfoActivity.this)
                    .load(path)
//                    .placeholder(R.drawable.image_default_user_header)
//                    .error(R.drawable.image_default_user_header)
                    .into(userHeader);
        }
    };

}

