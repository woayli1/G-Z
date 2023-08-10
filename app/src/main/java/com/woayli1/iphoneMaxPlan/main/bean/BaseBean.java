package com.woayli1.iphoneMaxPlan.main.bean;

public class BaseBean {

    private String data;
    private String money;
    private Integer AllMoney;

    public BaseBean(String data, String money, Integer AllMoney) {
        this.data = data;
        this.money = money;
        this.AllMoney = AllMoney;
    }

    public Integer getAllMoney() {
        return AllMoney;
    }

    public void setAllMoney(Integer allMoney) {
        AllMoney = allMoney;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


}
