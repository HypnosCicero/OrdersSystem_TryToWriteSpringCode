package com.atguigu.model.pojo;

import java.sql.Date;

public class Orders {
    private int oid;
    private String uname;
    private String tel;
    private String address;
    private int payway;
    private String sender;
    private String phone;
    private String idcard;
    private Date overtime;
    private int isover;
    private int tid;
    private String tname;

    public Orders() {
    }

    public Orders(int oid, String uname, String tel, String address, int payway, String sender, String phone, String idcard, String overtime, int isover, int tid, String tname) {
        this.oid = oid;
        this.uname = uname;
        this.tel = tel;
        this.address = address;
        this.payway = payway;
        this.sender = sender;
        this.phone = phone;
        this.idcard = idcard;
        java.util.Date date = new java.util.Date(overtime);
        this.overtime = new Date(date.getTime());
        this.isover = isover;
        this.tid = tid;
        this.tname = tname;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTel() {
        return tel.substring(0,7)+"****";
    }
    public String getTel(int flag){
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPayway() {
        return payway;
    }

    public void setPayway(int payway) {
        this.payway = payway;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        java.util.Date date = new java.util.Date(overtime);
        this.overtime = new Date(date.getTime());
    }

    public int getIsover() {
        return isover;
    }

    public void setIsover(int isover) {
        this.isover = isover;
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
        return "Orders{" +
                "oid=" + oid +
                ", uname='" + uname + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", payway=" + payway +
                ", sender='" + sender + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", overtime=" + overtime +
                ", isover=" + isover +
                ", tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }
}
