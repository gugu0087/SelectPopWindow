package com.xyc.selectpopwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xyc.selectpop.inteface.TabSelectListener;
import com.xyc.selectpop.model.SelectModel;
import com.xyc.selectpop.SelectPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<SelectModel> firstList = new ArrayList<>();
    private List<SelectModel> secondList = new ArrayList<>();
    private List<SelectModel> thirdList = new ArrayList<>();



    private SelectPopupWindow mPopupWindow;
    private TextView tvPosition;

    private NetDataModel netDataModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setdata();
            }
        });
    }

    private void setdata() {
        initData();

        if (mPopupWindow == null) {
            // mPopupWindow = new SelectPopupWindow(parentStrings, childrenStrings, this, selectCategory);
            mPopupWindow = new SelectPopupWindow(this, selectCategory,firstSelectCategory);
        }

        mPopupWindow.setLvChildrenCategoryVisible(View.GONE);
        mPopupWindow.showPopAsDropDown(tvPosition, -5, 5);
        mPopupWindow.setTabSelectListener(new TabSelectListener() {

            @Override
            public void selectItem(int selectId) {

                if (selectId == mPopupWindow.FIRST_TAB) {

                    mPopupWindow.setLvChildrenCategoryVisible(View.GONE);
                } else if (selectId == mPopupWindow.SECOND_TAB) {

                    mPopupWindow.setLvChildrenCategoryVisible(View.VISIBLE);
                }

            }

            @Override
            public void selectItem(int selectId, String content) {

            }
        });
    }




    private void initData() {
      netDataModel = JsonData.getNetData();

    }

    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {
        @Override
        public void selectCategory(int parentSelectposition, int childrenSelectposition) {


            Toast.makeText(MainActivity.this, "父类别:" + parentSelectposition + "  子类别:" + childrenSelectposition, Toast.LENGTH_SHORT).show();
        }
    };
    private SelectPopupWindow.FirstSelectCategory firstSelectCategory = new SelectPopupWindow.FirstSelectCategory() {
        @Override
        public void selectedItem(int position) {

            Toast.makeText(MainActivity.this, "父类别:" + position , Toast.LENGTH_SHORT).show();
        }
    };
}
