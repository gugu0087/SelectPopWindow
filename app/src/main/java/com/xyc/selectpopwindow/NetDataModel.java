package com.xyc.selectpopwindow;

import java.util.List;

/**
 * Created by hasee on 2018/4/4.
 */

public class NetDataModel {
    private List<FirstLevelModel> firstLevel;

    public List<FirstLevelModel> getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(List<FirstLevelModel> firstLevel) {
        this.firstLevel = firstLevel;
    }

    @Override
    public String toString() {
        return "NetDataModel{" +
                "firstLevel=" + firstLevel +
                '}';
    }
}
