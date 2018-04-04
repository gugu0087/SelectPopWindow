package com.xyc.selectpop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xyc.selectpop.inteface.TabSelectListener;
import com.xyc.selectpop.model.FirstLevelModel;
import com.xyc.selectpop.model.SelectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/4/2.
 */

public class SelectPopupWindow extends PopupWindow implements View.OnClickListener {
    private SelectCategory selectCategory;
    private FirstSelectCategory firstSelectCategory;

    private ListView lvSecondCategory = null;
    private ListView lvThirdCategory = null;
    private SelectCategoryAdapter secondCategoryAdapter = null;
    private SelectCategoryAdapter thirdCategoryAdapter = null;
    private Activity activity;


    private View contentView;
    private TextView tvCompany;
    private TextView tvUserItem;
    public final int FIRST_TAB = 1;
    public final int SECOND_TAB = 2;
    private TabSelectListener tabSelectListener;
    private List<SelectModel> firstLevelList = new ArrayList<>();
    private List<SelectModel> secondLevelList = new ArrayList<>();
    private List<SelectModel> thirdLevelList = new ArrayList<>();
    private List<FirstLevelModel> firstLevelModels;
    private ListView lvFirstCategory;
    private final SelectCategoryAdapter firstCategoryAdapter;


    public void setTabSelectListener(TabSelectListener tabSelectListener) {
        this.tabSelectListener = tabSelectListener;
    }

    public void setSelectCategoryData(List<FirstLevelModel> firstLevelModels) {
        if (firstLevelModels == null) {
            return;
        }
        this.firstLevelModels = firstLevelModels;

    }


    public SelectPopupWindow(final Activity activity, SelectCategory selectCategory, FirstSelectCategory firstSelectCategory) {
        this.selectCategory = selectCategory;
        this.firstSelectCategory = firstSelectCategory;
        this.activity = activity;
        initPopOptions();
        initView();
        // 一级适配器
        firstCategoryAdapter = new SelectCategoryAdapter(activity);
        firstCategoryAdapter.setSelectDataList(firstLevelList);

        //二级适配器
        secondCategoryAdapter = new SelectCategoryAdapter(activity);
        secondCategoryAdapter.setSelectDataList(secondLevelList);
        lvSecondCategory.setAdapter(secondCategoryAdapter);
        secondCategoryAdapter.setSelectedPosition(0);

        // 三级适配器
        thirdCategoryAdapter = new SelectCategoryAdapter(activity);
        lvThirdCategory.setAdapter(thirdCategoryAdapter);
        thirdCategoryAdapter.setSelectDataList(thirdLevelList);
        thirdCategoryAdapter.notifyDataSetChanged();

    }

    private void initView() {

        lvFirstCategory = contentView.findViewById(R.id.lvFirstCategory);
        lvSecondCategory = contentView.findViewById(R.id.lvSecondCategory);
        lvThirdCategory = contentView.findViewById(R.id.lvThirdCategory);

        tvCompany = contentView.findViewById(R.id.tvCompany);
        tvUserItem = contentView.findViewById(R.id.tvUserItem);

        lvFirstCategory.setOnItemClickListener(firstItemClickListener);
        lvSecondCategory.setOnItemClickListener(secondItemClickListener);
        lvThirdCategory.setOnItemClickListener(thirdItemClickListener);

        tvCompany.setOnClickListener(this);
        tvUserItem.setOnClickListener(this);

        if (tvCompany.getVisibility() == View.VISIBLE) {
            tvCompany.setSelected(true);
        } else {
            tvUserItem.setSelected(true);
        }
    }

    private void initPopOptions() {
        contentView = LayoutInflater.from(activity).inflate(R.layout.layout_quyu_choose_view, null);
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
    }

    public void setLvChildrenCategoryVisible(int visible) {
        if (lvThirdCategory != null) {
            lvThirdCategory.setVisibility(visible);
        }
    }

    /**
     * set the visibility of the first tab
     *
     * @param visible
     */
    public void setTabFirstVisible(int visible) {
        if (tvCompany == null) {
            return;
        }
        tvCompany.setVisibility(visible);
    }

    /**
     * set the text of the first tab
     *
     * @param tabText
     */
    public void setTabFirstText(String tabText) {
        if (tvCompany == null) {
            return;
        }
        if (tvCompany.getVisibility() == View.INVISIBLE) {
            tvCompany.setVisibility(View.VISIBLE);
        }
        tvCompany.setText(tabText);
    }

    /**
     * set the visibility of the second tab
     *
     * @param visible
     */
    public void setTabSecondVisible(int visible) {
        if (tvUserItem == null) {
            return;
        }
        tvUserItem.setVisibility(visible);
    }

    /**
     * set the text of the second tab
     *
     * @param tabText
     */
    public void setTabSecondText(String tabText) {
        if (tvUserItem == null) {
            return;
        }
        if (tvUserItem.getVisibility() == View.INVISIBLE) {
            tvUserItem.setVisibility(View.VISIBLE);
        }
        tvUserItem.setText(tabText);
    }

    public void showPopAsDropDown(View anchor, int xOff, int yOff) {
        this.showAsDropDown(anchor, xOff, yOff);
    }


    /**
     * 子类别点击事件
     */
    private AdapterView.OnItemClickListener thirdItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategory(secondCategoryAdapter.getPos(), position);
            }
            dismiss();
        }
    };

    /**
     * 父类别点击事件
     */
    private AdapterView.OnItemClickListener secondItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            secondCategoryAdapter.setSelectedPosition(position);


        }
    };

    private AdapterView.OnItemClickListener firstItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            firstCategoryAdapter.setSelectedPosition(position);
            updateUserSelectUI(position);

            Toast.makeText(activity, position, Toast.LENGTH_SHORT).show();
        }
    };

    private void updateUserSelectUI(int position) {
        lvFirstCategory.setVisibility(View.GONE);
        lvSecondCategory.setVisibility(View.VISIBLE);
        lvThirdCategory.setVisibility(View.VISIBLE);

    }

    private void updateSecondTabUI() {
        lvFirstCategory.setVisibility(View.VISIBLE);
        lvSecondCategory.setVisibility(View.GONE);
        lvThirdCategory.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvCompany) {
            tvCompany.setSelected(true);
            tvUserItem.setSelected(false);
            lvFirstCategory.setVisibility(View.VISIBLE);
            lvSecondCategory.setVisibility(View.GONE);
            lvThirdCategory.setVisibility(View.GONE);
            tabSelectListener.selectItem(FIRST_TAB);

        } else if (view.getId() == R.id.tvUserItem) {
            tvCompany.setSelected(false);
            tvUserItem.setSelected(true);
            lvFirstCategory.setVisibility(View.GONE);
            lvSecondCategory.setVisibility(View.VISIBLE);
            lvThirdCategory.setVisibility(View.VISIBLE);
            tabSelectListener.selectItem(SECOND_TAB);
        } else {

        }
    }

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
        void selectCategory(int parentSelectposition, int childrenSelectposition);
    }

    public interface FirstSelectCategory {
        void selectedItem(int position);
    }
}
