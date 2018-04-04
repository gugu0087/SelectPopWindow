package com.xyc.selectpop.model;

import java.util.List;

/**
 * Created by hasee on 2018/4/4.
 */

public class FirstLevelModel {
    private Integer id;
    private String name;
    private List<SecondLevelModel> secondLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondLevelModel> getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(List<SecondLevelModel> secondLevel) {
        this.secondLevel = secondLevel;
    }

    @Override
    public String toString() {
        return "FirstLevelModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondLevel=" + secondLevel +
                '}';
    }
}
