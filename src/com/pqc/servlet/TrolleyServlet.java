package com.pqc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pqc.entity.Trolley;
import com.pqc.entity.User;
import com.pqc.service.TrolleyService;

@WebServlet("/trolley")
public class TrolleyServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private TrolleyService trolleyService = new TrolleyService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String func = request.getParameter("func");
		switch (func) {
			case "addTrolley":
				addTrolley(request,response);
				break;
			case "selectTrolleyCount":
				selectTrolleyCount(request,response);
				break;
			case "findAllTrolley":
				findAllTrolley(request,response);
				break;
			case "addOrDeleteNumber":
				addOrDeleteNumber(request,response);
				break;
			case "deleteTrolley":
				deleteTrolley(request,response);
			default:
				break;
		}
	}
	//删除购物车记录方法
	private void deleteTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		boolean isSuccess = trolleyService.deleteTrolley(tid);
		if(isSuccess) {
			response.sendRedirect("trolley?func=findAllTrolley");
		}
	}
	//商品数量的加减
	private void addOrDeleteNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		String number = request.getParameter("number");
		boolean isSuccess = trolleyService.addOrDeleteNumber(tid,number);
		if(isSuccess) {
			response.sendRedirect("trolley?func=findAllTrolley");
		}
	}
	//获取购物车所有商品方法
	private void findAllTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先获取当前用户
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Trolley> trolleys = trolleyService.findAllTrolley(user);
		request.setAttribute("trolleys", trolleys);
		request.getRequestDispatcher("trolley.jsp").forward(request, response);
		
	}
	//查询当前用户的购物车商品数量
	private void selectTrolleyCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//先获取当前用户
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int number = trolleyService.selectTrolleyCount(user.getUid());
		response.getWriter().write(number+"");
	}
	//添加购物车方法
	private void addTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取gid
		String gid = request.getParameter("gid");
		//获取uid
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//构建购物车对象
		Trolley trolley = new Trolley(user.getUid(),Integer.valueOf(gid));
		boolean isRepeat = trolleyService.addTrolley(trolley);
		/*System.out.println(trolley);*/
		response.getWriter().print(isRepeat);
		
	}

}
