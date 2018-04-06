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
import com.xyc.selectpop.model.SecondLevelModel;
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
    private TextView tvFirstTab;
    private TextView tvSecondTab;
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
        updateFirstCategoryUI();
        tvFirstTab.setSelected(true);
        tvSecondTab.setSelected(false);
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
         lvFirstCategory.setAdapter(firstCategoryAdapter);

        //二级适配器
        secondCategoryAdapter = new SelectCategoryAdapter(activity);
        secondCategoryAdapter.setSelectDataList(secondLevelList);
        lvSecondCategory.setAdapter(secondCategoryAdapter);
        secondCategoryAdapter.setSelectedPosition(0);

        // 三级适配器
        thirdCategoryAdapter = new SelectCategoryAdapter(activity);
        thirdCategoryAdapter.setSelectDataList(thirdLevelList);
        lvThirdCategory.setAdapter(thirdCategoryAdapter);


    }

    private void initView() {

        lvFirstCategory = contentView.findViewById(R.id.lvFirstCategory);
        lvSecondCategory = contentView.findViewById(R.id.lvSecondCategory);
        lvThirdCategory = contentView.findViewById(R.id.lvThirdCategory);

        tvFirstTab = contentView.findViewById(R.id.tvFirstTab);
        tvSecondTab = contentView.findViewById(R.id.tvSecondTab);

        lvFirstCategory.setOnItemClickListener(firstItemClickListener);
        lvSecondCategory.setOnItemClickListener(secondItemClickListener);
        lvThirdCategory.setOnItemClickListener(thirdItemClickListener);

        tvFirstTab.setOnClickListener(this);
        tvSecondTab.setOnClickListener(this);

        if (tvFirstTab.getVisibility() == View.VISIBLE) {
            tvFirstTab.setSelected(true);
        } else {
            tvSecondTab.setSelected(true);
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


    /**
     * set the visibility of the first tab
     *
     * @param visible
     */
    public void setTabFirstVisible(int visible) {
        if (tvFirstTab == null) {
            return;
        }
        tvFirstTab.setVisibility(visible);
    }

    /**
     * set the text of the first tab
     *
     * @param tabText
     */
    public void setTabFirstText(String tabText) {
        if (tvFirstTab == null) {
            return;
        }
        if (tvFirstTab.getVisibility() == View.INVISIBLE) {
            tvFirstTab.setVisibility(View.VISIBLE);
        }
        tvFirstTab.setText(tabText);
    }

    /**
     * set the visibility of the second tab
     *
     * @param visible
     */
    public void setTabSecondVisible(int visible) {
        if (tvSecondTab == null) {
            return;
        }
        tvSecondTab.setVisibility(visible);
    }

    /**
     * set the text of the second tab
     *
     * @param tabText
     */
    public void setTabSecondText(String tabText) {
        if (tvSecondTab == null) {
            return;
        }
        if (tvSecondTab.getVisibility() == View.INVISIBLE) {
            tvSecondTab.setVisibility(View.VISIBLE);
        }
        tvSecondTab.setText(tabText);
    }

    public void showPopAsDropDown(View anchor, int xOff, int yOff) {
        this.showAsDropDown(anchor, xOff, yOff);
    }


    /**
     * 三级类目点击事件
     */
    private AdapterView.OnItemClickListener thirdItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategoryPos(secondCategoryAdapter.getPos(), position);
                selectCategory.selectCateGoryId(secondLevelList.get(secondCategoryAdapter.getPos()).getId(),thirdLevelList.get(position).getId());
            }
            dismiss();
        }
    };

    /**
     * 二级类目点击事件
     */
    private AdapterView.OnItemClickListener secondItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            secondCategoryAdapter.setSelectedPosition(position);

            thirdLevelList.clear();
            updateThirdCategoryUI(position);
            secondCategoryAdapter.notifyDataSetChanged();
        }
    };
    /**
     *一级类目点击事件
     *
     */
    private AdapterView.OnItemClickListener firstItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            if(firstSelectCategory!=null){
                firstSelectCategory.selectedItem(position,firstLevelList.get(position).getId());
            }
            firstCategoryAdapter.setSelectedPosition(position);
            secondLevelList.clear();
            tvFirstTab.setSelected(false);//切换到二级类目
            tvSecondTab.setSelected(true);

            updateSecondCategoryUI(position);

            updateThirdCategoryUI(0);
        }
    };

    public void updateFirstCategoryUI(){
        lvFirstCategory.setVisibility(View.VISIBLE);
        lvSecondCategory.setVisibility(View.GONE);
        lvThirdCategory.setVisibility(View.GONE);
        firstLevelList.clear();
         for(int i=0;i<firstLevelModels.size();i++){
             SelectModel selectModel = new SelectModel();
             selectModel.setId(firstLevelModels.get(i).getId());
             selectModel.setName(firstLevelModels.get(i).getName());
             firstLevelList.add(selectModel);
         }
        firstCategoryAdapter.notifyDataSetChanged();
    }

    public void updateSecondCategoryUI(int position){
        lvFirstCategory.setVisibility(View.GONE);
        lvSecondCategory.setVisibility(View.VISIBLE);
        lvThirdCategory.setVisibility(View.VISIBLE);
        if(position>=firstLevelModels.size()){
            position = firstLevelModels.size();
        }
        secondLevelList.clear();
        FirstLevelModel firstLevelModel = firstLevelModels.get(position);
        List<SecondLevelModel> secondLevel = firstLevelModel.getSecondLevel();
        for(int i=0;i<secondLevel.size();i++){
            SelectModel selectModel = new SelectModel();
            selectModel.setId(secondLevel.get(i).getId());
            selectModel.setName(secondLevel.get(i).getName());
            secondLevelList.add(selectModel);
        }
        secondCategoryAdapter.notifyDataSetChanged();
    }

    public void updateThirdCategoryUI(int position){
        lvFirstCategory.setVisibility(View.GONE);
        lvSecondCategory.setVisibility(View.VISIBLE);
        lvThirdCategory.setVisibility(View.VISIBLE);
        int firstSelectPos = firstCategoryAdapter.getPos();
        thirdLevelList.clear();
        FirstLevelModel firstLevelModel = firstLevelModels.get(firstSelectPos);
        SecondLevelModel secondLevelModel = firstLevelModel.getSecondLevel().get(position);
        List<SelectModel> thirdLevel = secondLevelModel.getThirdLevel();
        if(thirdLevel==null||thirdLevel.size()==0){
            return;
        }
        thirdLevelList.addAll(thirdLevel);
        thirdCategoryAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvFirstTab) {
            tvFirstTab.setSelected(true);
            tvSecondTab.setSelected(false);
            lvFirstCategory.setVisibility(View.VISIBLE);
            lvSecondCategory.setVisibility(View.GONE);
            lvThirdCategory.setVisibility(View.GONE);
            tabSelectListener.selectItem(FIRST_TAB);
            updateFirstCategoryUI();
        } else if (view.getId() == R.id.tvSecondTab) {
            tvFirstTab.setSelected(false);
            tvSecondTab.setSelected(true);
            lvFirstCategory.setVisibility(View.GONE);
            lvSecondCategory.setVisibility(View.VISIBLE);
            lvThirdCategory.setVisibility(View.VISIBLE);
            tabSelectListener.selectItem(SECOND_TAB);
            int firstPos = firstCategoryAdapter.getPos();
            updateSecondCategoryUI(firstPos);
            int secondPos= secondCategoryAdapter.getPos();
            updateThirdCategoryUI(secondPos);
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
         * @param secondSelectPos   二级类别选中下标
         * @param thirdSelectPos 三级类别选中下标
         */
        void selectCategoryPos(int secondSelectPos, int thirdSelectPos);
        void selectCateGoryId(long secondSelectId,long thirdSelectId);
    }

    public interface FirstSelectCategory {

        void selectedItem(int position,long id);

    }
}
