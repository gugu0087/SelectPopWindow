package com.xyc.selectpop.model;

/**
 * Created by hasee on 2018/4/3.
 */

public class SelectModel {
    private Integer id;// 选项id
    private String name;// 选项内容

    public SelectModel() {
    }

    public SelectModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "SelectModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
