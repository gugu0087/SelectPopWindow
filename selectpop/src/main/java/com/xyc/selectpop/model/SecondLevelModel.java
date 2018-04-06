package com.xyc.selectpop.model;

import java.util.List;

/**
 * Created by hasee on 2018/4/4.
 */

public class SecondLevelModel {
    private long id;
    private String name;
    private List<SelectModel> thirdLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SelectModel> getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(List<SelectModel> thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    @Override
    public String toString() {
        return "SecondLevelModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thirdLevel=" + thirdLevel +
                '}';
    }
}
