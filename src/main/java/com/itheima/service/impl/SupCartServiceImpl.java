package com.itheima.service.impl;

import com.itheima.mapper.SupCartMapper;
import com.itheima.pojo.Cart;
import com.itheima.service.SupCartService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SupCartServiceImpl implements SupCartService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Cart> selectAll(Integer uid) {
        SqlSession sqlSession = factory.openSession();
        //3. Get BrandMapper

        SupCartMapper mapper = sqlSession.getMapper(SupCartMapper.class);

        //4. Call
        List<Cart> carts = mapper.selectAll(uid);

        //5. Close
        sqlSession.close();

        return carts;
    }

    @Override
    public void add(Cart cart) {
        //2. get SqlSession obj
        SqlSession sqlSession = factory.openSession();
        //3. Get BrandMapper
        SupCartMapper mapper = sqlSession.getMapper(SupCartMapper.class);

        //4. Call
        mapper.add(cart);
        sqlSession.commit();//comit

        //5. Close
        sqlSession.close();
    }

    @Override
    public void delete(Integer id) {
        //2. get SqlSession obj
        SqlSession sqlSession = factory.openSession();
        //3. get BrandMapper
        SupCartMapper mapper = sqlSession.getMapper(SupCartMapper.class);

        //4. call
        mapper.delete(id);
        sqlSession.commit();//comit

        //5. close
        sqlSession.close();
    }
}
