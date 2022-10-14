package com.itheima.mapper;

import com.itheima.pojo.SupCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SupCartMapper {

    /**
     *
     * @return
     */
    @Select("select * from sp_cart where uid = #{uid}")
    //@Select("select * from sp_cart")
    @ResultMap("supcartResultMap")
    List<SupCart> selectAll(Integer uid);

    /**
     * add Data
     * @param cart
     */
    @Insert("insert into sp_cart values(#{id},#{uid},#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(SupCart cart);

    @Delete("delete from sp_cart where id = #{id}")
    //void delete(Integer id);
    void delete(Integer id);
}
