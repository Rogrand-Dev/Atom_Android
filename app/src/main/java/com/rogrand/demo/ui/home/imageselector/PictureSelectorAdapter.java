package com.rogrand.demo.ui.home.imageselector;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.rogrand.atom.utils.LogUtils;
import com.rogrand.demo.R;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.luck.picture.lib.adapter.PictureImageGridAdapter.TYPE_CAMERA;
import static com.luck.picture.lib.adapter.PictureImageGridAdapter.TYPE_PICTURE;

/**
 * 显示选择后的图片
 * Created by Ricky on 2017-5-9.
 */

public class PictureSelectorAdapter extends RecyclerView.Adapter<PictureSelectorAdapter.ViewHolder> {

    private List<LocalMedia> mAllDatas = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private int maxShow = 9;

    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick(int type, int position);
    }

    public PictureSelectorAdapter(Context mContext, onAddPicClickListener mOnAddPicClickListener) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    public void setSelectMax(int selectMax) {
        this.maxShow = selectMax;
    }

    public void setList(List<LocalMedia> list) {
        this.mAllDatas = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView mImg;
        LinearLayout ll_del;

        public ViewHolder(View view) {
            super(view);
            mImg = (AppCompatImageView) view.findViewById(R.id.fiv);
            ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_grid_picture_selector, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        // itemView 的点击事件
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), v);
                }
            });
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mAllDatas.size() < maxShow) {
            return mAllDatas.size() + 1;
        } else {
            return mAllDatas.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    private boolean isShowAddItem(int position) {
        int size = mAllDatas.size() == 0 ? 0 : mAllDatas.size();
        return position == size;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        // 少于8张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.mImg.setImageResource(R.drawable.ic_add_service_pic);
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddPicClickListener.onAddPicClick(0, viewHolder.getAdapterPosition());
                }
            });
            viewHolder.ll_del.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.ll_del.setVisibility(View.VISIBLE);
            viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnAddPicClickListener.onAddPicClick(1, viewHolder.getAdapterPosition());
                }
            });
            LocalMedia media = mAllDatas.get(position);
            int type = media.getType();
            String path = "";
            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }

            switch (type) {
                case 1:
                    // 图片
                    if (media.isCompressed()) {
                        LogUtils.i("compress image result", new File(media.getCompressPath()).length() / 1024 + "k");
                        LogUtils.i("原图地址::", media.getPath());
                        LogUtils.i("压缩地址::", media.getCompressPath());
                    }

                    Glide.with(viewHolder.itemView.getContext())
                            .load(path)
//                            .centerCrop()
//                            .placeholder(R.color.color_f6)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(viewHolder.mImg);
                    break;
                case 2:
                    // 视频
                    LogUtils.i("时长:", media.getDuration() + "");
                    Glide.with(viewHolder.itemView.getContext()).load(path).thumbnail(0.5f).into(viewHolder.mImg);
                    break;
                default:

                    break;
            }
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
