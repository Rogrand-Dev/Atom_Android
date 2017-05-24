package com.rogrand.demo.ui.home.imageselector;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.R.attr.width;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * 显示图片选择结果界面
 * Created by Ricky on 2017-5-9.
 */

public class ImageResultActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar ;
    @BindView(R.id.recycler_image_result)
    RecyclerView mRecyclerView;

    private List<LocalMedia> mAllDatas = new ArrayList<>();
    private ShowImageAdapter mAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_image_result;
    }

    @Override
    protected void initEventAndData() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ShowImageAdapter(this, mAllDatas, onItemControlListener);
        mAdapter.setMaxShow(9);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ShowImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                PictureConfig.getInstance().externalPicturePreview(mContext, position, mAllDatas);
            }
        });
    }

    /**
     * 选择或者删除图片操作
     */
    private ShowImageAdapter.OnItemControlListener onItemControlListener = new ShowImageAdapter.OnItemControlListener() {
        @Override
        public void onItemControl(int type, int position) {
            switch (type) {
                case ShowImageAdapter.TYPE_SELECT:
                    //打开选择照片
                    FunctionOptions options = new FunctionOptions.Builder()
                            .setType(FunctionConfig.TYPE_IMAGE)//图片or视频
//                            .setCropMode()//设置裁剪模式
                            .setCompress(false)//设置是否压缩
                            .setEnablePixelCompress(true)//是否启用像素压缩
                            .setEnableQualityCompress(true)//是否启用质量压缩
                            .setMaxSelectNum(9)//设置最大选择数量
                            .setMinSelectNum(0)//设置最小选择数量
                            .setSelectMode(FunctionConfig.MODE_MULTIPLE)//单选or多选
                            .setShowCamera(true)//是否显示拍照
                            .setEnablePreview(true)//是否启用预览效果
                            .setEnableCrop(false)//是否启用裁剪
                            .setCircularCut(false)//是否采用圆形裁剪
                            .setPreviewVideo(false)//是否预览视频
                            //.setCheckedBoxDrawable(0)//设置选择框样式
//                            .setRecordVideoDefinition(FunctionConfig.HIGH)//设置视频清晰程度
//                            .setRecordVideoSecond(60)//设置视频秒数
                            .setGif(true)//是否显示GIF，默认不显示
                            .setCropW(100) // cropW-->裁剪宽度 值不能小于100  如果值大于图片原始宽高 将返回原图大小
                            .setCropH(100) // cropH-->裁剪高度 值不能小于100 如果值大于图片原始宽高 将返回原图大小
                            .setMaxB(400) // 压缩最大值 例如:200kb  就设置202400，202400 / 1024 = 200kb
                            .setPreviewColor(Color.WHITE)//设置"预览"字体颜色
                            .setCompleteColor(Color.WHITE)//设置"已完成"字体颜色
                            .setPreviewBottomBgColor(Color.BLACK)//设置预览图片底部背景颜色
                            .setPreviewTopBgColor(ContextCompat.getColor(mContext, R.color.colorPrimary))//设置预览界面标题栏背景色
                            .setBottomBgColor(ContextCompat.getColor(mContext, R.color.colorPrimary))//设置图片列表底部背景色
                            .setGrade(Luban.THIRD_GEAR)//设置压缩等级
                            .setCheckNumMode(false)//?
//                            .setCustomQQ_theme(0)//可自定义QQ数字风格，不传就默认是蓝色风格
//                            .setCompressQuality(100)//图片裁剪质量，默认无损
                            .setImageSpanCount(4)//每行显示图片个数
//                            .setVideoS(0)//设置查询多少秒以内的视频
                            .setSelectMedia(mAllDatas)//已选图片，不能是网络图片
                            .setCompressFlag(2)// 1 系统自带压缩 2 luban压缩
                            .setCompressW(100) // 压缩宽 如果值大于图片原始宽高无效
                            .setCompressH(100) // 压缩高 如果值大于图片原始宽高无效
                            .setThemeStyle(ContextCompat.getColor(mContext, R.color.colorPrimary)) // 设置主题样式
                            .setNumComplete(true) // 0/9 完成  样式
                            .setClickVideo(false)// 点击声音
//                            .setPicture_title_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题字体颜色
//                            .setPicture_right_color(ContextCompat.getColor(MainActivity.this, R.color.black)) // 设置标题右边字体颜色
//                            .setLeftBackDrawable(R.mipmap.back2) // 设置返回键图标
                            .setStatusBar(ContextCompat.getColor(mContext, R.color.colorPrimaryDark)) // 设置状态栏颜色，默认是和标题栏一致
//                            .setImmersive(false)// 是否改变状态栏字体颜色(黑色)
                            .create();
                    PictureConfig.getInstance().init(options).openPhoto(ImageResultActivity.this, resultCallback);
                    break;
                case ShowImageAdapter.TYPE_SHOW:
                    //删除
                    mAllDatas.remove(position);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    //图片选择回调方法
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> list) {
            mAllDatas.clear();
            //多选回调
            if (list != null && list.size() > 0) {
                mAllDatas.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onSelectSuccess(LocalMedia localMedia) {
            //单选回调
            mAllDatas.add(localMedia);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
