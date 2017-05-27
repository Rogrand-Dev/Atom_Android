package com.rogrand.demo.ui.tool;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rogrand.demo.R;

import java.util.List;

public class ToolAdapter extends BaseQuickAdapter<ToolItem, BaseViewHolder> {

    public ToolAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToolItem item) {
        helper.setText(R.id.text, item.getTitle());
        helper.setImageResource(R.id.icon, item.getImageResource());
    }
}
