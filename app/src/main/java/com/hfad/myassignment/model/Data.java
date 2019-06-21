package com.hfad.myassignment.model;

import java.util.List;

public class Data {


    String fundname;

    int minsipamount;

    int minsipmultiple;

    List<Integer> sipdates;

    public Data(String fundname, int minsipamount, int minsipmultiple) {
        this.fundname = fundname;
        this.minsipamount = minsipamount;
        this.minsipmultiple = minsipmultiple;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public int getMinsipamount() {
        return minsipamount;
    }

    public void setMinsipamount(int minsipamount) {
        this.minsipamount = minsipamount;
    }

    public int getMinsipmultiple() {
        return minsipmultiple;
    }

    public void setMinsipmultiple(int minsipmultiple) {
        this.minsipmultiple = minsipmultiple;
    }

    public List<Integer> getSipdates() {
        return sipdates;
    }

    public void setSipdates(List<Integer> sipdates) {
        this.sipdates = sipdates;
    }
}
