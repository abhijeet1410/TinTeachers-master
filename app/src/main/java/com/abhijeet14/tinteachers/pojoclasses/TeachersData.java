package com.abhijeet14.tinteachers.pojoclasses;

public class TeachersData {
    private String name,dp;

    public TeachersData() {
    }

    public TeachersData(String name, String dp) {

        this.name = name;
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }
}
