package com.itheima.mapper;

import com.itheima.pojo.SupBrand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SupBrandMapper {

    /**
     *
     * @return
     */
    @Select("select * from sp_brand")
    @ResultMap("supbrandResultMap")
    List<SupBrand> selectAll();

    /**
     * add data
     * @param brand
     */
    @Insert("insert into sp_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(SupBrand brand);
}
