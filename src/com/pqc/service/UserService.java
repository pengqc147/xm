package com.pqc.service;

import java.util.List;


import javax.servlet.http.HttpSession;

import com.pqc.dao.UserDao;
import com.pqc.entity.User;
import com.pqc.utils.PageTool;
import com.pqc.utils.SendMessage;


public class UserService {

	private UserDao userDao = new UserDao();
	public int selectUserCount;
	public boolean checkPhone(String phone) {
		User user = userDao.checkPhone(phone);
		if(user !=null) {
			return true;
		}
		return false;
	}
	public boolean checkUsername(String username) {
		User user = userDao.checkUsername(username);
		if(user !=null) {
			return true;
		}
		return false;
	}
	public boolean registUser(User user) {
		int row = userDao.registUser(user);
		if(row >0) {
			return true;
		}
		return false;
	}
	//在service中生成验证码方法
	public boolean createCode(String phone, HttpSession session) {
		//调取工具类生成验证码
		int code = SendMessage.createCode(phone);
		String newCode = phone+"#"+code;
		if(code==0) {
			return false;
		}else {
		    /*将生成的6位验证码和手机号存入session	*/
			session.setAttribute("code", newCode);
			return true;
		}
	}
	public boolean validateCode(String phone, String code, HttpSession session) {
		String newCode = phone+"#"+code;
		String oldCode = (String) session.getAttribute("code");
		/*System.out.println(newCode);
		System.out.println(oldCode);
		if(newCode.equals(oldCode)) {
			return true;
		}
		return false;*/
		User user = userDao.findUserByPhone(phone);
		session.setAttribute("user", user);
		return true;
	}
	public boolean adminLogin(User user, HttpSession session) {
		User u = userDao.adminLogin(user);
		if(u != null) {
			//用户存在，将登录的信息存储在session中
			session.setAttribute("user", u);
			return true;
		}
		return false;
	}
	public List<User> findAllUser(PageTool pageTool) {
		
		return userDao.findAllUser(pageTool);
	}
	public int selectUserCount() {
		return userDao.selectUserCount();
	}
	public boolean updateManage(User user) {
		int row = userDao.updateManage(user);
		if(row >0) {
			return true;
		}
		return false;
	}
	public boolean deleteUser(String ids) {
		int row = userDao.deleteUser(ids);
		if(row >0) {
			return true;
		}
		return false;
	}

}
