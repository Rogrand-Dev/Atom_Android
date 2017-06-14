package com.rogrand.demo.ui.home.recycler;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rogrand.demo.R;
import com.rogrand.demo.bean.MovieBean;

import java.util.List;

public class RecyclerViewAdapter extends BaseQuickAdapter<MovieBean, BaseViewHolder> {

    public RecyclerViewAdapter(List data) {
        super(R.layout.item_list_recyclerview, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieBean item) {
        helper.setText(R.id.title, item.getNm())
                .addOnClickListener(R.id.action)
                .setText(R.id.comment, item.getScm())
                .setText(R.id.showings, item.getShowInfo());
        Glide.with(mContext).load(item.getImg()).into((ImageView) helper.getView(R.id.image));
    }

}
