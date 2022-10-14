package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.SupBrand;

import java.util.List;

public interface SupBrandService {

    /**
     * Search all
     * @return
     */
    List<SupBrand> selectAll();

    /**
     * Add Data
     * @param brand
     */
    void add(SupBrand brand);
}
