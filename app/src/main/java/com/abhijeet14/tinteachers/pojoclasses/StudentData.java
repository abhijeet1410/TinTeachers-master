package com.abhijeet14.tinteachers.pojoclasses;

public class StudentData {
    String name,dp,roll;

    public StudentData(String name, String dp, String roll) {
        this.name = name;
        this.dp = dp;
        this.roll = roll;
    }

    public StudentData() {
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

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
