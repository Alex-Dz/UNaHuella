package com.unal.una_huella.UNaHuellaLauncher.util;

public class OrderPair {
    int orderBy;
    int view;
    int userType;

    public OrderPair(int orderby, int view) {
        this.orderBy = orderby;
        this.view = view;
        this.userType = 0;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
