package com.xyc.selectpop.model;

import java.util.List;

/**
 * Created by hasee on 2018/4/4.
 */

public class DataModel {
    private List<SelectModel> firstLevelList;
    private List<SelectModel> secondLevelList;
    private List<SelectModel> thirdLevelList;

    public List<SelectModel> getFirstLevelList() {
        return firstLevelList;
    }

    public void setFirstLevelList(List<SelectModel> firstLevelList) {
        this.firstLevelList = firstLevelList;
    }

    public List<SelectModel> getSecondLevelList() {
        return secondLevelList;
    }

    public void setSecondLevelList(List<SelectModel> secondLevelList) {
        this.secondLevelList = secondLevelList;
    }

    public List<SelectModel> getThirdLevelList() {
        return thirdLevelList;
    }

    public void setThirdLevelList(List<SelectModel> thirdLevelList) {
        this.thirdLevelList = thirdLevelList;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "firstLevelList=" + firstLevelList +
                ", secondLevelList=" + secondLevelList +
                ", thirdLevelList=" + thirdLevelList +
                '}';
    }
}
