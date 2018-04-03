package com.xyc.selectpop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hasee on 2018/4/2.
 */

public class ChildrenCategoryAdapter extends BaseAdapter {
    private Context mContext;
    /*    private String[] str;*/
    private List<SelectModel> childInList;

    public ChildrenCategoryAdapter(Context context) {
        mContext = context;
    }

   /* public void setDatas(String[] str) {
        this.str = str;
    }*/

    public void setChildInList(List<SelectModel> childInList) {
        this.childInList = childInList;
    }

    @Override
    public int getCount() {
        if (childInList == null) {
            return 0;
        }
        return childInList.size();
    }

    @Override
    public Object getItem(int position) {
        return childInList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return childInList.get(position).getSelectId();
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
        SelectModel selectModel = childInList.get(position);
        if (selectModel != null) {
            holder.tvChildrenCategoryName.setText(selectModel.getItemContent());
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvChildrenCategoryName;
    }
}
