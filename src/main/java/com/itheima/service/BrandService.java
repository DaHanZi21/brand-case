package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加操作
     */
    void add(Brand brand);

    /**
     * 修改操作
     */
    void updateById(Brand brand);

    /**
     * 删除操作
     * @param id
     */
    void deleteById(int id);

    /**
     * 批量删除
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    /**
     * 分页条件查询
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
