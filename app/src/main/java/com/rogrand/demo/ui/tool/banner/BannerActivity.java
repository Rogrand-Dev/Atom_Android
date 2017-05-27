package com.rogrand.demo.ui.tool.banner;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rogrand.demo.R;
import com.rogrand.demo.base.SimpleActivity;
import com.rogrand.demo.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class BannerActivity extends SimpleActivity implements OnBannerListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.sp_banner_style)
    Spinner mSpBannerStyle;
    @BindView(R.id.sp_banner_anim)
    Spinner mSpBannerAnim;

    public static List<?> mListImage = new ArrayList<>();
    public static List<String> mListTitle = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "轮播");

        mSpBannerStyle.setOnItemSelectedListener(new BannerStyleListener());
        mSpBannerAnim.setOnItemSelectedListener(new BannerAnimListener());

        String[] urls = getResources().getStringArray(R.array.banner_url);
        String[] titles = getResources().getStringArray(R.array.banner_title);
        List listUrl = Arrays.asList(urls);
        mListImage = new ArrayList(listUrl);
        List listTitle = Arrays.asList(titles);
        mListTitle = new ArrayList(listTitle);

        mBanner.setImages(mListImage) // 设置图片集合
                .setBannerTitles(mListTitle) // 设置标题集合
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) // 设置轮播样式（默认为CIRCLE_INDICATOR）
                .setImageLoader(new GlideImageLoader()) // 设置图片加载器
                .isAutoPlay(true) // 设置是否自动轮播（默认自动）
                .setViewPagerIsScroll(true) // 设置是否允许手动滑动轮播图（默认true）
                .setDelayTime(2500) // 设置轮播图片间隔时间（单位毫秒，默认为2000）
                .setOnBannerListener(this) // 设置点击事件，下标是从0开始
                .start(); // 开始进行banner渲染
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(mContext, "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBanner.startAutoPlay(); // 开始轮播
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBanner.stopAutoPlay(); // 结束轮播
    }

    class BannerStyleListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    mBanner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                    break;
                case 1:
                    mBanner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                    break;
                case 2:
                    mBanner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
                    break;
                case 3:
                    mBanner.updateBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                    break;
                case 4:
                    mBanner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                    break;
                case 5:
                    mBanner.updateBannerStyle(BannerConfig.NOT_INDICATOR);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class BannerAnimListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    mBanner.setBannerAnimation(DefaultTransformer.class);
                    break;
                case 1:
                    mBanner.setBannerAnimation(AccordionTransformer.class);
                    break;
                case 2:
                    mBanner.setBannerAnimation(BackgroundToForegroundTransformer.class);
                    break;
                case 3:
                    mBanner.setBannerAnimation(ForegroundToBackgroundTransformer.class);
                    break;
                case 4:
                    mBanner.setBannerAnimation(CubeInTransformer.class);
                    break;
                case 5:
                    mBanner.setBannerAnimation(CubeOutTransformer.class);
                    break;
                case 6:
                    mBanner.setBannerAnimation(DepthPageTransformer.class);
                    break;
                case 7:
                    mBanner.setBannerAnimation(FlipHorizontalTransformer.class);
                    break;
                case 8:
                    mBanner.setBannerAnimation(FlipVerticalTransformer.class);
                    break;
                case 9:
                    mBanner.setBannerAnimation(RotateDownTransformer.class);
                    break;
                case 10:
                    mBanner.setBannerAnimation(RotateUpTransformer.class);
                    break;
                case 11:
                    mBanner.setBannerAnimation(ScaleInOutTransformer.class);
                    break;
                case 12:
                    mBanner.setBannerAnimation(StackTransformer.class);
                    break;
                case 13:
                    mBanner.setBannerAnimation(TabletTransformer.class);
                    break;
                case 14:
                    mBanner.setBannerAnimation(ZoomInTransformer.class);
                    break;
                case 15:
                    mBanner.setBannerAnimation(ZoomOutTranformer.class);
                    break;
                case 16:
                    mBanner.setBannerAnimation(ZoomOutSlideTransformer.class);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
