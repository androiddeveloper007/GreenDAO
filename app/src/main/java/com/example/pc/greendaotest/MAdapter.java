package com.example.pc.greendaotest;

import android.content.Context;
import android.widget.TextView;

import com.example.pc.greendaotest.bean.User;

import java.util.List;

public class MAdapter extends BaseQuickAdapter<User> {
    public MAdapter(Context cxt) {
        super(cxt);
    }

    @Override
    public void setNewData(List<User> data) {
        super.setNewData(data);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item;
    }

    @Override
    protected void convert(BaseViewHolder helper, User item, int position) {
        TextView tv1 = helper.convertView.findViewById(R.id.tv1);
        TextView tv2 = helper.convertView.findViewById(R.id.tv2);
        TextView tv3 = helper.convertView.findViewById(R.id.tv3);
        tv1.setText(item.getName());
        tv2.setText("id"+item.getId());
        tv3.setText("score"+item.getScore());
    }
}
