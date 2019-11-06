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
	//ɾ���û�����
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		boolean isSuccess = userService.deleteUser(ids);
		if(isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
	}
	//�޸Ĺ���ԱȨ��
	private void updateManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String manager = request.getParameter("manager");
		User user = new User(Integer.valueOf(uid), Integer.valueOf(manager));
		boolean isSuccess = userService.updateManage(user);
		if(isSuccess) {
			response.sendRedirect("user?func=findAllUser");
		}
	}
	//��ѯ�����û��ķ���
	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ҳ���߶���
		String currentPage = request.getParameter("currentPage");
		int totalCount = userService.selectUserCount();
		PageTool pageTool = new PageTool(totalCount, currentPage);
		List<User> users = userService.findAllUser(pageTool);
		//���õ��Ĺ�����Ͷ��������������
		request.setAttribute("users", users);
		request.setAttribute("pageTool", pageTool);
		request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
	}
	//��̨�ǳ�����
	private void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���session�е�����
		HttpSession session = request.getSession();
		session.invalidate();
		//��ת�ص�¼ҳ��
		response.sendRedirect("admin/login.jsp");
	}
	//��̨��¼����
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		HttpSession session = request.getSession();
		boolean isSuccess = userService.adminLogin(user,session);
		if(isSuccess) {
			response.sendRedirect("admin/main.jsp");
		}else {
			request.setAttribute("msg", "�û���������������󣬻�����û��Ȩ�ޣ�����������");
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
		}
	}
	//��¼У�鷽��
	private void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		String code = request.getParameter("code");
		System.out.println(phone+code);
		//��service��ִ��У��
		HttpSession session = request.getSession();
		boolean isSuccess = userService.validateCode(phone,code,session);
		response.getWriter().print(isSuccess);
	}
	//������֤��ķ���
	private void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
	    /*���ɵ���֤��Ӧ����service�����
	     * �����������ڵ�¼ʱ����Ҫ���˶�У�飬���������ھ��ڻỰ�д��ڼ���
	     * ����Ҫ����֤��洢��session��
	    * */
		HttpSession session = request.getSession();
		boolean isSuccess = userService.createCode(phone,session);
		response.getWriter().print(isSuccess);
	}
	private void checkPhoneRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkPhone(request, response);
	}
	//ע����Ϣ
	private void registUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡ����Ϣ
		String uname = request.getParameter("uname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String area = request.getParameter("area");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String photo = request.getParameter("photo");
		String photo="";
		//��ȡpart�������д洢�Ź����ϴ�����������
		Part part = request.getPart("photo");
		//�ж�part���Ƿ�����ϴ�������
		if(part.getSize()==0) {
			request.setAttribute("msg", "����ѡ��ͼ��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}else {
			photo = UploadUtils.upload("register.jsp",part, request, response);
			//���photo�ʹ������Ͳ�ƥ��
			if("".equals(photo)) {
				return;
			}
		}
		
		//����User����
		User user = new User(uname, Integer.valueOf(gender), phone, area, username, password, photo,new Date());
		//����service����
		boolean isSuccess = userService.registUser(user);
		if(isSuccess) {
			response.sendRedirect("login.jsp");
		}else {
			request.setAttribute("msg", "ע��ʧ�ܣ�������ע��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}
	//У���û���Ψһ�ķ���
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ�û���
		String username = request.getParameter("username");
		//����service����
		boolean isRegist = userService.checkUsername(username);
		//��Ӧ��ҳ������
		response.getWriter().print(isRegist);
	}
	//У���ֻ���Ψһ�ķ���
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ�ֻ���
		String phone = request.getParameter("phone");
		//����service����
		boolean isRegist = userService.checkPhone(phone);
		//��Ӧ��ҳ������
		response.getWriter().print(isRegist);
	}

}









