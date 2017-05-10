package com.rogrand.demo.ui.imageselector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rogrand.atom.utils.DensityUtils;
import com.rogrand.demo.R;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 显示选择后的图片
 * Created by Ricky on 2017-5-9.
 */

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.ViewHolder> {

    private Context mContext;
    private List<LocalMedia> mAllDatas;
    private LayoutInflater mLayoutInflater;
    private int maxShow = 9;

    public static final int TYPE_SELECT = 1;//选择照片
    public static final int TYPE_SHOW = 2;//显示照片

    private OnItemControlListener onItemControlListener;
    private OnItemClickListener onItemClickListener;

    public ShowImageAdapter(Context mContext, List<LocalMedia> mAllDatas, OnItemControlListener onItemControlListener) {
        this.mContext = mContext;
        this.mAllDatas = mAllDatas;
        this.onItemControlListener = onItemControlListener;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_image_display, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //动态计算照片的宽度进行显示
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.imageView.getLayoutParams();
        layoutParams.width = (DensityUtils.getScreenWidth(mContext) - DensityUtils.dp2px(mContext, 6) * 2) / 3;
        layoutParams.height = layoutParams.width;
        holder.imageView.setLayoutParams(layoutParams);

        if (getItemViewType(position) == TYPE_SELECT) {
            holder.imageView.setImageResource(R.drawable.ic_add_service_pic);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemControlListener != null) {
                        onItemControlListener.onItemControl(TYPE_SELECT, holder.getAdapterPosition());
                    }
                }
            });
            holder.mDelete.setVisibility(View.GONE);
        } else {
            holder.mDelete.setVisibility(View.VISIBLE);
            holder.mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemControlListener != null) {
                        onItemControlListener.onItemControl(TYPE_SHOW, holder.getAdapterPosition());
                    }
                }
            });
            LocalMedia media = mAllDatas.get(position);
            String path = "";
            if (media.isCut() && !media.isCompressed()) {
                //裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                //压缩过，或者裁剪过后压缩过，以是否压缩为准
                path = media.getCompressPath();
            } else {
                //原图
                path = media.getPath();
            }
            int type = media.getType();
            switch (type) {
                case 1:
                    //图片
                    Glide.with(mContext)
                            .load(path)
                            .centerCrop()
                            .placeholder(R.drawable.ic_add_service_pic)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imageView);
                    break;
                case 2:
                    //视频
                    Glide.with(mContext).load(path).thumbnail(0.5f).into(holder.imageView);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mAllDatas == null) {
            return 1;
        }
        if (mAllDatas.size() < maxShow) {
            return mAllDatas.size() + 1;
        } else {
            return mAllDatas.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_SELECT;
        } else {
            return TYPE_SHOW;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relative_container)
        RelativeLayout mContainer;
        @BindView(R.id.iv_image_show)
        ImageView imageView;
        @BindView(R.id.linear_delete_image)
        LinearLayout mDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    /**
     * 设置最大显示照片数量
     *
     * @param max 最大值
     */
    public void setMaxShow(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max select picture should more than zero");
        }
        if (max >= 0) {
            this.maxShow = max;
        }
    }

    /**
     * 设置显示的照片集合
     *
     * @param list 照片集合
     */
    private void setListData(List<LocalMedia> list) {
        this.mAllDatas.addAll(list);
    }

    private boolean isShowAddItem(int position) {
        int size = mAllDatas.size() == 0 ? 0 : mAllDatas.size();
        return position == size;
    }

    public interface OnItemControlListener {
        void onItemControl(int type, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener callback) {
        this.onItemClickListener = callback;
    }
}
