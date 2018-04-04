package com.xyc.selectpopwindow;

/**
 * Created by hasee on 2018/4/3.
 */

public class SelectDataModel {
    private int selectId;// 选项id
    private String itemContent;// 选项内容
    private String itemId;// 预留一个String 类型的id
    private String extraTip;//其他内容提示

    public SelectDataModel() {
    }

    public SelectDataModel(int selectId, String itemContent) {
        this.selectId = selectId;
        this.itemContent = itemContent;
    }

    public SelectDataModel(String itemContent, String itemId) {
        this.itemContent = itemContent;
        this.itemId = itemId;
    }

    public SelectDataModel(int selectId, String itemContent, String itemId, String extraTip) {
        this.selectId = selectId;
        this.itemContent = itemContent;
        this.itemId = itemId;
        this.extraTip = extraTip;
    }

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getExtraTip() {
        return extraTip;
    }

    public void setExtraTip(String extraTip) {
        this.extraTip = extraTip;
    }

    @Override
    public String toString() {
        return "SelectModel{" +
                "selectId=" + selectId +
                ", itemContent='" + itemContent + '\'' +
                ", itemId='" + itemId + '\'' +
                ", extraTip='" + extraTip + '\'' +
                '}';
    }
}
