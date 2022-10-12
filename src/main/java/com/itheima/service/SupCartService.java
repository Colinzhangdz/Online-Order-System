package com.itheima.service;

import com.itheima.pojo.Cart;

import java.util.List;

public interface SupCartService {

    List<Cart> selectAll(Integer userId);


    void add(Cart cart);
    //void delete(Integer id);
    void delete(Integer id);

}
