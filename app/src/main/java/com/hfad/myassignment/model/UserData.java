package com.hfad.myassignment.model;

import com.google.gson.annotations.SerializedName;

public class UserData {

    /**
     * keyword : sbi blue
     */

    private CharSequence keyword;

    public CharSequence getKeyword() {
        return keyword;
    }

    @SerializedName("keyword")
    public void setKeyword(CharSequence keyword) {
        this.keyword = keyword;
    }
}
