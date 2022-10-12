package com.itheima.service.impl;


import com.itheima.mapper.SupBrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.service.SupBrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SupBrandServiceImpl implements SupBrandService {
    //1. create SqlSessionFactory obj
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //2. get SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. get BrandMapper
        SupBrandMapper mapper = sqlSession.getMapper(SupBrandMapper.class);

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
        SupBrandMapper mapper = sqlSession.getMapper(SupBrandMapper.class);

        //4. call
        mapper.add(brand);
        sqlSession.commit();//commit

        //5. close
        sqlSession.close();
    }
}
