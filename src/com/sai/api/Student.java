package com.sai.api;

public class Student {

    private int rollNO;

    private String name;

    private String address;

    @Override
    public String toString() {
        return "Student{" +
                "roolNo=" + rollNO +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getRollNO() {
        return rollNO;
    }

    public void setRollNO(int rollNO) {
        this.rollNO = rollNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
