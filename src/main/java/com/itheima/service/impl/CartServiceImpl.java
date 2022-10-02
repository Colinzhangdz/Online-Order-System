package com.itheima.service.impl;

import com.itheima.mapper.CartMapper;
import com.itheima.pojo.Cart;
import com.itheima.service.CartService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CartServiceImpl implements CartService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Cart> selectAll(Integer uid) {
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);

        //4. 调用方法
        List<Cart> carts = mapper.selectAll(uid);

        //5. 释放资源
        sqlSession.close();

        return carts;
    }
    @Override
    public void add(Cart cart) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        CartMapper mapper = sqlSession.getMapper(CartMapper.class);

        //4. 调用方法
        mapper.add(cart);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }
}
