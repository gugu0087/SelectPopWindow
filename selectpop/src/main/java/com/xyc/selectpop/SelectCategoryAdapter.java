package com.xyc.selectpop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyc.selectpop.model.SelectModel;

import java.util.List;

/**
 * Created by hasee on 2018/4/2.
 */

public class SelectCategoryAdapter extends BaseAdapter {
    private Context mContext;

    private int pos;
    private List<SelectModel> parentList;

    public SelectCategoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return parentList.size();
    }

    @Override
    public Object getItem(int position) {
        return parentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_parent_category_item, null);
            holder.tvParentCategoryName = convertView.findViewById(R.id.tv_parent_category_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SelectModel selectModel = parentList.get(position);
        if (selectModel != null) {
            holder.tvParentCategoryName.setText(selectModel.getName());
        }
        if (pos == position) {
            holder.tvParentCategoryName.setTextColor(mContext.getResources().getColor(R.color.green_29aca3));
            //   convertView.setBackgroundColor(mContext.getResources().getColor(R.color.zu_choose_right_item_bg));
        } else {
            holder.tvParentCategoryName.setTextColor(mContext.getResources().getColor(R.color.text_color));
            // convertView.setBackgroundColor(mContext.getResources().getColor(R.color.zu_choose_left_item_bg));
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvParentCategoryName;
    }

    public void setSelectDataList(List<SelectModel> parentList) {
        this.parentList = parentList;
    }

    public void setSelectedPosition(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}
