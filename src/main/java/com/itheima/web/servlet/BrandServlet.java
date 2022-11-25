package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceimpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service层的方法
        List<Brand> brands = brandService.selectAll();
        //2.把数据转成JSON
        String jsonString = JSON.toJSONString(brands);
        //3.发送数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取传过来的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.讲json数据转成brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //3.调用service方法
        brandService.add(brand);

        //4.返回响应数据
        response.getWriter().write("success");
    }

    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取传过来的数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2.讲json数据转成brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //3.调用service方法
        brandService.updateById(brand);

        //4.返回响应数据
        response.getWriter().write("success");
    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int
        int id = JSON.parseObject(params,int.class);


        //2. 调用service添加
        brandService.deleteById(id);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 int[]数组
        int[] ids = JSON.parseObject(params,int[].class);


        //2. 调用service添加
        brandService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受当前页码和每页显示条数的数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        //2.转成int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //3.调用service方法
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2.把数据转成JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3.发送数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 分页条件查询
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.接受当前页码和每页显示条数的数据
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        //2.转成int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //3.获取JSON数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //4.转为 Brand数组
        Brand brand = JSON.parseObject(params, Brand.class);
        //5.调用service方法
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //6.把数据转成JSON
        String jsonString = JSON.toJSONString(pageBean);
        //7.发送数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
