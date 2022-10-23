package com.itheima.service;
import com.itheima.pojo.SupCart;
import java.util.List;

public interface SupCartService {

    List<SupCart> selectAll(Integer userId);


    void add(SupCart cart);
    //void delete(Integer id);
    void delete(Integer id);

}
