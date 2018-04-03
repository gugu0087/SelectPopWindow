package com.xyc.selectpopwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xyc.selectpop.SelectModel;
import com.xyc.selectpop.SelectPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<SelectModel> parentList = new ArrayList<>();
    private List<List<SelectModel>> childrenList = new ArrayList<>();
    private List<SelectModel> childInList = new ArrayList<>();
    private String[] parentStrings = {"全城", "中原区", "二七区", "管城区", "金水区", "上街区", "惠济区", "郑东新区", "高新区", "经开区", "郑州周边"};
    private String[][] childrenStrings = {{},
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"二七1", "二七2", "二七3", "二七4", "二七5", "二七6", "二七7", "二七8", "二七9", "二七10", "二七11", "二七12", "二七13", "二七14", "二七15"},
            {"管城1", "管城2", "管城3", "管城4", "管城5", "管城6", "管城7", "管城8", "管城9", "管城10", "管城11", "管城12", "管城13", "管城14", "管城15"},
            {"金水1", "金水2", "金水3", "金水4", "金水5", "金水6", "金水7", "金水8", "金水9", "金水10", "金水11", "金水12", "金水13", "金水14", "金水15"},
            {"上街1", "上街2", "上街3", "上街4", "上街5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"中原1", "中原2", "中原3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"郑东新区1", "郑东新区2", "郑东新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"高新区1", "高新区2", "高新区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"经开区1", "经开区2", "经开区3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
            {"周边1", "周边2", "周边3", "中原4", "中原5", "中原6", "中原7", "中原8", "中原9", "中原10", "中原11", "中原12", "中原13", "中原14", "中原15"},
    };
    private SelectPopupWindow mPopupWindow;
    private TextView tvPosition;

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
            mPopupWindow = new SelectPopupWindow(parentStrings, childrenStrings, this, selectCategory);
        }
        mPopupWindow.showPopAsDropDown(tvPosition, -5, 5);
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            SelectModel selectModel = new SelectModel();
            selectModel.setSelectId(i + 1);
            selectModel.setItemContent("item" + (i + 1));
            parentList.add(selectModel);
        }
        for (int j = 0; j < 15; j++) {
            for (int k = 0; k < 7; k++) {
                SelectModel selectModel = new SelectModel();
                selectModel.setSelectId(k + 1);
                selectModel.setItemContent("item" + (k + 1));
                childInList.add(selectModel);
            }
            childrenList.add(childInList);
        }
    }

    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {
        @Override
        public void selectCategory(int parentSelectposition, int childrenSelectposition) {
            String parentStr = parentStrings[parentSelectposition];
            String childrenStr = childrenStrings[parentSelectposition][childrenSelectposition];
            Toast.makeText(MainActivity.this, "父类别:" + parentStr + "  子类别:" + childrenStr, Toast.LENGTH_SHORT).show();
        }
    };
}
