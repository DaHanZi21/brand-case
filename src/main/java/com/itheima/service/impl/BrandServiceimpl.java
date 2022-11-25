package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceimpl implements BrandService {
    //1.获取sqlsessionFectory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.关闭资源
        sqlSession.close();
        return brands;
    }

    /**
     * 添加数据
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        sqlSession.commit();//添加数据要提交一下事务
        //5.关闭资源
        sqlSession.close();
    }

    /**
     * 修改操作
     *
     * @param brand
     */
    @Override
    public void updateById(Brand brand) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.updateById(brand);
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    /**
     * 根据id删除数据
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    /**
     * 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页显示条数
     * @return PageBean
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引和每页显示条数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //5.调用方法
        List<Brand> rows = mapper.selectByPage(begin, size);
        int totalCount = mapper.selectTotalCount();
        //6.封装javaBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //7.释放资源
        sqlSession.close();

        return pageBean;
    }

    /**
     * 根据条件分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引和每页显示条数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //5.把brandName和companyName转为模糊查询
        if (brand.getBrandName() != null && brand.getBrandName().length() > 0) {
            brand.setBrandName("%" + brand.getBrandName() + "%");
        }
        if (brand.getCompanyName() != null && brand.getCompanyName().length() > 0) {
            brand.setCompanyName("%" + brand.getCompanyName() + "%");
        }

        //6.调用方法
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
        int totalCount = mapper.selectTotalCountByCondition(brand);
        //7.封装javaBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //8.释放资源
        sqlSession.close();

        return pageBean;
    }
}
