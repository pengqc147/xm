
package com.pqc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pqc.entity.Category;
import com.pqc.service.CateService;
import com.pqc.utils.DateTool;
import com.pqc.utils.PageTool;
@WebServlet("/cate")
public class CateServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private CateService cateService = new CateService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String func = request.getParameter("func");
    	switch (func) {
		case "findAllCate":
			findAllCate(request,response);
			break;
		case "insertCate":
			insertCate(request,response);
			break;
		case "findCateById":
			findCateById(request,response);
			break;
		case "updateCate":
			updateCate(request,response);
			break;
		case "deleteCate":
			deleteCate(request,response);
			break;
		default:
			break;
		}
    }
    //商品类别批量删除
    private void deleteCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		boolean isSuccess = cateService.deleteCate(ids);
		if(isSuccess) {
			response.sendRedirect("cate?func=findAllCate");
		}
    	
	}
	//修改分类
    private void updateCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String cid = request.getParameter("cid");
    	String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		Category category = new Category(Integer.valueOf(cid),cname, Integer.valueOf(state), Integer.valueOf(order_number), description, DateTool.stringToDate(create_time));
		boolean isSuccess = cateService.updateCate(category);
		if(isSuccess) {
			response.sendRedirect("cate?func=findAllCate");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
	}
	//通过id查找商品分类
    private void findCateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		Category category = cateService.findCateById(cid);
		request.setAttribute("cate", category);
		request.getRequestDispatcher("admin/category_update.jsp").forward(request, response);
	}
	//添加类别的方法
    private void insertCate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cname = request.getParameter("cname");
		String state = request.getParameter("state");
		String order_number = request.getParameter("order_number");
		String description = request.getParameter("description");
		String create_time = request.getParameter("create_time");
		Category category = new Category(cname, Integer.valueOf(state), 
				            Integer.valueOf(order_number), description, DateTool.stringToDate(create_time));
		boolean isSuccess = cateService.insertCate(category);
		if(isSuccess) {
			response.sendRedirect("cate?func=findAllCate");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
	}
	//查询所有商品信息(分页查询)
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		int totalCount = cateService.selectCateCount();//查询数据库得到分类总数量
		PageTool pageTool = new PageTool(totalCount, currentPage);
		//得到所有分类信息
		List<Category> cates = cateService.findAllCate(pageTool);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/category_list.jsp").forward(request, response);
	}
	
	
	
}






