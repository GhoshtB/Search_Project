package com.hfad.myassignment.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "data")
public class Datas {
    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "fundname")
    String fundname;

    @ColumnInfo(name = "minsipamount")
     int minsipamount;
    @ColumnInfo(name = "minsipmultiple")
      int minsipmultiple;
//    @ColumnInfo(name = "sipdates")
//    List<Integer> sipdates;

    public Datas(String fundname, int minsipamount, int minsipmultiple, List<Integer> sipdates) {
        this.fundname = fundname;
        this.minsipamount = minsipamount;
        this.minsipmultiple = minsipmultiple;
//        this.sipdates = sipdates;
    }

    public Datas(String fundname, int minsipamount, int minsipmultiple) {
        this.fundname = fundname;
        this.minsipamount = minsipamount;
        this.minsipmultiple = minsipmultiple;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public List<Integer> getSipdates() {
//        return sipdates;
//    }
//
//    public void setSipdates(List<Integer> sipdates) {
//        this.sipdates = sipdates;
//    }
}
