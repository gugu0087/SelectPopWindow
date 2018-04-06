package com.xyc.selectpopwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xyc.selectpop.inteface.TabSelectListener;
import com.xyc.selectpop.model.FirstLevelModel;
import com.xyc.selectpop.model.SelectModel;
import com.xyc.selectpop.SelectPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



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
        List<FirstLevelModel> firstLevel = netDataModel.getFirstLevel();
        mPopupWindow.setSelectCategoryData(firstLevel);

        mPopupWindow.showPopAsDropDown(tvPosition, -5, 5);

        mPopupWindow.setTabSelectListener(new TabSelectListener() {

            @Override
            public void selectItem(int selectId) {

                if (selectId == mPopupWindow.FIRST_TAB) {


                } else if (selectId == mPopupWindow.SECOND_TAB) {


                }

            }

            @Override
            public void selectItem(int selectId, String content) {

            }
        });
    }




    private void initData() {
      netDataModel = JsonData.getNetData();
        Log.d("xyc", "initData: netDataModel="+netDataModel);
    }

    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {
        @Override
        public void selectCategoryPos(int secondSelectPos, int thirdSelectPos) {
            Log.d("xyc", "selectCategoryPos: secondSelectPos="+secondSelectPos+"thirdSelectPos="+thirdSelectPos);
        }

        @Override
        public void selectCateGoryId(long secondSelectId, long thirdSelectId) {
            Log.d("xyc", "selectCategoryPos: secondSelectId="+secondSelectId+"thirdSelectId="+thirdSelectId);

            Toast.makeText(MainActivity.this, "父类别:Id" + secondSelectId + "  子类别Id:" + thirdSelectId, Toast.LENGTH_SHORT).show();

        }

    };

    private SelectPopupWindow.FirstSelectCategory firstSelectCategory = new SelectPopupWindow.FirstSelectCategory() {
        @Override
        public void selectedItem(int position, long id) {
            Toast.makeText(MainActivity.this, "父类别:" + position+"id="+id , Toast.LENGTH_SHORT).show();

        }

    };
}
