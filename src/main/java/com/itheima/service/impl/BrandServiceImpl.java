package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1. create SqlSessionFactory obj
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //2. get SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. get BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. call
        List<Brand> brands = mapper.selectAll();

        //5. close
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2. get SqlSession obj
        SqlSession sqlSession = factory.openSession();
        //3. get BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. call
        mapper.add(brand);
        sqlSession.commit();//comit

        //5. close
        sqlSession.close();
    }
}
