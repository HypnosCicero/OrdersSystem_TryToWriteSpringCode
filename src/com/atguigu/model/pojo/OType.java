package com.atguigu.model.pojo;

public class OType {
    private int tid;
    private String tname;

    public OType() {
    }

    public OType(int tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "OType{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }
}
