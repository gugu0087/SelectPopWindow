package com.xyc.selectpop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * Created by hasee on 2018/4/2.
 */

public class SelectPopupWindow extends PopupWindow {
    private SelectCategory selectCategory;

    private String[] parentStrings;
    private String[][] childrenStrings;

    private ListView lvParentCategory = null;
    private ListView lvChildrenCategory = null;
    private ParentCategoryAdapter parentCategoryAdapter = null;
    private ChildrenCategoryAdapter childrenCategoryAdapter = null;
    private Activity activity;

    /**
     * @param parentStrings   字类别数据
     * @param childrenStrings 字类别二位数组
     * @param activity
     * @param selectCategory  回调接口注入
     */
    public SelectPopupWindow(String[] parentStrings, String[][] childrenStrings, final Activity activity, SelectCategory selectCategory) {
        this.selectCategory = selectCategory;
        this.parentStrings = parentStrings;
        this.childrenStrings = childrenStrings;
        this.activity = activity;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_quyu_choose_view, null);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小

        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels * 7 / 10);

        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
		/* 设置背景显示 */
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.popwindow_anim_style);
        /* 设置触摸外面时消失 */
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */

        /**
         * 1.解决再次点击MENU键无反应问题
         */
        contentView.setFocusableInTouchMode(true);

        //父类别适配器
        lvParentCategory = (ListView) contentView.findViewById(R.id.lv_parent_category);
        parentCategoryAdapter = new ParentCategoryAdapter(activity, parentStrings);
        lvParentCategory.setAdapter(parentCategoryAdapter);

        //子类别适配器
        lvChildrenCategory = (ListView) contentView.findViewById(R.id.lv_children_category);
        childrenCategoryAdapter = new ChildrenCategoryAdapter(activity);
        lvChildrenCategory.setAdapter(childrenCategoryAdapter);

        lvParentCategory.setOnItemClickListener(parentItemClickListener);
        lvChildrenCategory.setOnItemClickListener(childrenItemClickListener);
        parentCategoryAdapter.setSelectedPosition(0);
    }

    public void showPopAsDropDown(View anchor, int xOff, int yOff) {
        this.showAsDropDown(anchor, xOff, yOff);
    }


    /**
     * 子类别点击事件
     */
    private AdapterView.OnItemClickListener childrenItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategory(parentCategoryAdapter.getPos(), position);
            }
            dismiss();
        }
    };
    /**
     * 父类别点击事件
     */
    private AdapterView.OnItemClickListener parentItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            childrenCategoryAdapter.setDatas(childrenStrings[position]);
            childrenCategoryAdapter.notifyDataSetChanged();

            parentCategoryAdapter.setSelectedPosition(position);
            parentCategoryAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 选择成功回调
     *
     * @author apple
     */
    public interface SelectCategory {
        /**
         * 把选中的下标通过方法回调回来
         *
         * @param parentSelectposition   父类别选中下标
         * @param childrenSelectposition 子类别选中下标
         */
        public void selectCategory(int parentSelectposition, int childrenSelectposition);
    }

}
