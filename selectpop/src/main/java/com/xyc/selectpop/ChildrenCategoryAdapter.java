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

public class ChildrenCategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<SelectModel> selectModelList;

    public ChildrenCategoryAdapter(Context context) {
        mContext = context;
    }

    public void setChildInList(List<SelectModel> selectModelList) {
        this.selectModelList = selectModelList;
    }

    @Override
    public int getCount() {
        if (selectModelList == null) {
            return 0;
        }
        return selectModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return selectModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return selectModelList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_children_category_item, null);
            holder.tvChildrenCategoryName = convertView.findViewById(R.id.tv_children_category_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SelectModel selectModel = selectModelList.get(position);
        if (selectModel != null) {
            holder.tvChildrenCategoryName.setText(selectModel.getName());
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvChildrenCategoryName;
    }
}
