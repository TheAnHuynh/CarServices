package com.example.hoangthang.timkiemxekhach;

/**
 * Created by hoangthang on 3/18/18.
 */

public class ChuyenXe {
    private String tennhaxe;
    private int soCho;
    private  String Time;

    public ChuyenXe(String tennhaxe, int soCho, String time) {
        this.tennhaxe = tennhaxe;
        this.soCho = soCho;
        Time = time;
    }

    public String getTennhaxe() {
        return tennhaxe;
    }

    public void setTennhaxe(String tennhaxe) {
        this.tennhaxe = tennhaxe;
    }

    public int getSoCho() {
        return soCho;
    }

    public void setSoCho(int soCho) {
        this.soCho = soCho;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
