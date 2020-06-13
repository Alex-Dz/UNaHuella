package com.unal.una_huella.UNaHuellaLauncher.util;

public class SortParams {
    private String param;
    private int value;

    public SortParams(String param, int value) {
        this.param = param;
        this.value = value;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
