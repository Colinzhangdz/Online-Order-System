package com.itheima.mapper;

import com.itheima.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_cart where uid = #{uid}")
    @ResultMap("cartResultMap")
    List<Cart> selectAll(Integer uid);

    /**
     * 添加数据
     * @param cart
     */
    @Insert("insert into tb_cart values(#{id},#{uid},#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Cart cart);

    @Delete("delete from tb_cart where id = #{id}")
    //void delete(Integer id);
    void delete(Integer id);
}
