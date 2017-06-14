package com.rogrand.demo.ui.home.imageselector;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 显示图片选择结果界面
 * Created by Ricky on 2017-5-9.
 */

public class PictureSelectorActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_image_result)
    RecyclerView mRecyclerView;

    private List<LocalMedia> mAllDatas = new ArrayList<>();
    private PictureSelectorAdapter mAdapter;
    private static final int maxSelectNum = 9;// 图片最大可选数量

    @Override
    protected int getLayout() {
        return R.layout.activity_picture_selector;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "图片选择");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new PictureSelectorAdapter(this, onAddPicClickListener);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PictureSelectorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureConfig.getInstance().externalPicturePreview(mContext, position, mAllDatas);
            }
        });

    }

    /**
     * 选择或者删除图片操作
     */
    private PictureSelectorAdapter.onAddPicClickListener onAddPicClickListener = new PictureSelectorAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0: // 进入相册
                    FunctionOptions options = new FunctionOptions.Builder()

                            .setType(FunctionConfig.TYPE_IMAGE) // 图片or视频 FunctionConfig.TYPE_IMAGE  TYPE_VIDEO
                            .setCropMode(FunctionConfig.CROP_MODEL_DEFAULT) // 裁剪模式 默认、1:1、3:4、3:2、16:9
//                            .setOffsetX() // 自定义裁剪比例
//                            .setOffsetY() // 自定义裁剪比例
                            .setCompress(true) //是否压缩
                            .setEnablePixelCompress(true) //是否启用像素压缩
                            .setEnableQualityCompress(true) //是否启质量压缩
                            .setMaxSelectNum(maxSelectNum) // 可选择图片的数量
                            .setMinSelectNum(0)// 图片或视频最低选择数量，默认代表无限制
                            .setSelectMode(FunctionConfig.MODE_MULTIPLE) // 单选 or 多选
                            .setShowCamera(true) //是否显示拍照选项 这里自动根据type 启动拍照或录视频
                            .setEnablePreview(true) // 是否打开预览选项
                            .setEnableCrop(false) // 是否打开剪切选项
                            .setCircularCut(false)// 是否采用圆形裁剪
                            .setPreviewVideo(false) // 是否预览视频(播放) mode or 多选有效
                            .setCheckedBoxDrawable(0)
                            .setRecordVideoDefinition(FunctionConfig.HIGH) // 视频清晰度
                            .setRecordVideoSecond(60) // 视频秒数
                            .setCustomQQ_theme(0)// 可自定义QQ数字风格，不传就默认是蓝色风格
                            .setGif(false) // 是否显示gif图片，默认不显示
                            .setCropW(200) // cropW-->裁剪宽度 值不能小于100 如果值大于图片原始宽高 将返回原图大小
                            .setCropH(200) // cropH-->裁剪高度 值不能小于100 如果值大于图片原始宽高 将返回原图大小
                            .setMaxB(202400) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb
                            .setGrade(Luban.THIRD_GEAR) // 压缩档次 默认三档
                            .setCheckNumMode(true)
                            .setCompressQuality(100) // 图片裁剪质量,默认无损
                            .setImageSpanCount(4) // 每行个数
                            .setVideoS(0) // 查询多少秒内的视频 单位:秒
                            .setSelectMedia(mAllDatas) // 已选图片，传入在次进去可选中，不能传入网络图片
                            .setCompressFlag(2) // 1 系统自带压缩 2 luban压缩
                            .setCompressW(300) // 压缩宽 如果值大于图片原始宽高无效
                            .setCompressH(300) // 压缩高 如果值大于图片原始宽高无效
                            .setNumComplete(false) // 0/9 完成  样式
                            .setClickVideo(false) // 点击声音
                            .setFreeStyleCrop(false) // 裁剪是移动矩形框或是图片
//                            .setRotateEnabled(false) // 裁剪时是否旋转图片
//                            .setScaleEnabled(false) // 裁剪时是否放大小图片
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题字体颜色
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题右边字体颜色
//                            .setLeftBackDrawable(R.mipmap.back2) // 设置返回键图标
//                            .setStatusBar(ContextCompat.getColor(MainActivity.this, R.color.white)) // 设置状态栏颜色，默认是和标题栏一致
//                            .setImmersive(false) // 是否改变状态栏字体颜色(黑色)
                            .create();

                    // 只拍照
//                    PictureConfig.getInstance().init(options).startOpenCamera(mContext);

                    // 先初始化参数配置，在启动相册
                    PictureConfig.getInstance().init(options).openPhoto(mContext, resultCallback);
                    break;
                case 1: // 删除图片
                    mAllDatas.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        /**
         * 多选回调
         */
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            mAllDatas = resultList;
            LogUtils.i("callBack_result", mAllDatas.size() + "");
            LocalMedia media = resultList.get(0);
            if (media.isCut() && !media.isCompressed()) {
                String path = media.getCutPath(); // 裁剪过
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                String path = media.getCompressPath();
            } else {
                String path = media.getPath(); // 原图地址
            }
            if (mAllDatas != null) {
                mAdapter.setList(mAllDatas);
                mAdapter.notifyDataSetChanged();
            }
        }

        /**
         * 单选回调
         */
        @Override
        public void onSelectSuccess(LocalMedia media) {
            mAllDatas.add(media);
            if (mAllDatas != null) {
                mAdapter.setList(mAllDatas);
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 単独拍照图片回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == FunctionConfig.CAMERA_RESULT) {
                if (data != null) {
                    mAllDatas = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                    if (mAllDatas != null) {
                        mAdapter.setList(mAllDatas);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

}
