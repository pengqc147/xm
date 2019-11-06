package com.pqc.servlet;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.pqc.entity.User;
import com.pqc.service.UserService;
import com.pqc.utils.PageTool;
import com.pqc.utils.UploadUtils;


@WebServlet("/user")
@MultipartConfig
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String func = request.getParameter("func");
		switch (func) {
		case "checkPhone":
			checkPhone(request,response);
			break;
		case "checkUsername":
			checkUsername(request,response);
			break;
		case "registUser":
			registUser(request,response);
			break;
		case "checkPhoneRegist":
			checkPhoneRegist(request,response);
			break;
		case "createCode":
			createCode(request,response);
			break;
		case "validateCode":
			validateCode(request,response);
			break;
		case "adminLogin":
			adminLogin(request,response);
			break;
		case "adminLogout":
			adminLogout(request,response);
			break;
		case "findAllUser":
			findAllUser(request,response);
			break;
		case "updateManage":
			updateManage(request,response);
			break;
		case "deleteUser":
			deleteUser(request,response);
			break;
		default:
			break;
		}
	}
	//删除用户方法
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		boolean isSuccess = userService.deleteUser(ids);
		if(isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
	}
	//修改管理员权限
	private void updateManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String manager = request.getParameter("manager");
		User user = new User(Integer.valueOf(uid), Integer.valueOf(manager));
		boolean isSuccess = userService.updateManage(user);
		if(isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
	}
	//查询所有用户的方法
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//构建分页工具对象
		String currentPage = request.getParameter("currentPage");
		int totalCount = userService.selectUserCount();
		PageTool pageTool = new PageTool(totalCount, currentPage);
		List<User> users = userService.findAllUser(pageTool);
		//将得到的工具类和对象存放在域对象中
		request.setAttribute("users", users);
		request.setAttribute("pageTool", pageTool);
		request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
	}
	//后台登出代码
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//清空session中的数据
		HttpSession session = request.getSession();
		session.invalidate();
		//跳转回登录页面
		response.sendRedirect("admin/login.jsp");
	}
	//后台登录方法
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		HttpSession session = request.getSession();
		boolean isSuccess = userService.adminLogin(user,session);
		if(isSuccess) {
			response.sendRedirect("admin/main.jsp");
		}else {
			request.setAttribute("msg", "用户名、密码输入错误，或者您没有权限，请重新输入");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
	}
	//登录校验方法
	private void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		System.out.println(phone+code);
		//在service中执行校验
		HttpSession session = request.getSession();
		boolean isSuccess = userService.validateCode(phone,code,session);
		response.getWriter().print(isSuccess);
	}
	//生成验证码的方法
	private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
	    /*生成的验证码应该在service中完成
	     * 保存起来，在登录时还需要做核对校验，而生命周期就在会话中存在即可
	     * 所以要将验证码存储在session中
	    * */
		HttpSession session = request.getSession();
		boolean isSuccess = userService.createCode(phone,session);
		response.getWriter().print(isSuccess);
	}
	private void checkPhoneRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkPhone(request, response);
	}
	//注册信息
	private void registUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取表单信息
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String photo = request.getParameter("photo");
		String photo="";
		//获取part对象，其中存储着关于上传的所有内容
		Part part = request.getPart("photo");
		//判断part中是否存在上传的内容
		if(part.getSize()==0) {
			request.setAttribute("msg", "请先选择图像");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}else {
			photo = UploadUtils.upload("register.jsp",part, request, response);
			//如果photo就代表类型不匹配
			if("".equals(photo)) {
				return;
			}
		}
		
		//构建User对象
		User user = new User(uname, Integer.valueOf(gender), phone, area, username, password, photo,new Date());
		//调用service方法
		boolean isSuccess = userService.registUser(user);
		if(isSuccess) {
			response.sendRedirect("login.jsp");
		}else {
			request.setAttribute("msg", "注册失败，请重新注册");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}
	//校验用户名唯一的方法
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名
		String username = request.getParameter("username");
		//调用service方法
		boolean isRegist = userService.checkUsername(username);
		//响应给页面请求
		response.getWriter().print(isRegist);
	}
	//校验手机号唯一的方法
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取手机号
		String phone = request.getParameter("phone");
		//调用service方法
		boolean isRegist = userService.checkPhone(phone);
		//响应给页面请求
		response.getWriter().print(isRegist);
	}

}









