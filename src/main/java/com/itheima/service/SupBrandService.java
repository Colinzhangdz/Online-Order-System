package com.itheima.service;

import com.itheima.pojo.Brand;

import java.util.List;

public interface SupBrandService {

    /**
     * Search all
     * @return
     */
    List<Brand> selectAll();

    /**
     * Add Data
     * @param brand
     */
    void add(Brand brand);
}
