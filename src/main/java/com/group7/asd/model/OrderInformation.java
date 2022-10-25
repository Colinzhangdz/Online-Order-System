package com.group7.asd.model;


import java.util.List;

public class OrderInformation {
    private String orderNo;
    private Double totalMoney;
    private String user_id;
    private String address;
    private String phone;
    private String idcard;
    private String password;
    private int paytype;

    private List<OrderDetail> orderDetailList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPaytype() {
        return paytype;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    @Override
    public String toString() {
        return "OrderInformation{" +
                "orderNo='" + orderNo + '\'' +
                ", totalMoney=" + totalMoney +
                ", user_id='" + user_id + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", password='" + password + '\'' +
                ", paytype=" + paytype +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
