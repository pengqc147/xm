package com.pqc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	/*
 	生成Cookie的方法
 	search参数：搜索的内容
 	request参数：为了获取Cookie中的内容
 	response参数：为了将生成的Cookie响应给浏览器保存
 */
	public static void addCookie(String search,HttpServletRequest request, HttpServletResponse response) {
		/*
	 	先获取之前存储搜索历史Cookie中的信息
	 	拼接上当前的搜索内容(已存在的不拼接)
	 	将处理完之后的搜索内容存入Cookie
	 */
		String info = getCookieInfo(request);
		/*此时info有两种情况，
		 * info为空字符串，就代表是第一次搜索内容，
		 * info中存在关键字，就代表不是第一次搜索
		*/
		if("".equals(info)) {
			info = search;
		}else {
			//判断是否包含search
			if(!info.contains(search)) {
				//判断是否包含#号
				boolean isContains = info.contains("#");
				if(isContains) {
					String[] str = info.split("#");
					if(str.length==3) {
						info= str[1]+"#"+str[2];
					}
					
				}
				info = info +"#"+ search;
			}
		}
		
		Cookie cookie = new Cookie("search", info);
		response.addCookie(cookie);
		
	}

	//获取cookie内容的方法
	public static String getCookieInfo(HttpServletRequest request) {
		String info="";//定义一个空字符串存储cookie
		Cookie[] cookies = request.getCookies();
		if(!(cookies==null || cookies.length==0)) {
			for (Cookie cookie : cookies) {
				//判断cookie中的关键字，取出cookies的内容
				if("search".equals(cookie.getName())) {
					info = cookie.getValue();
				}
			}
		}
		return info;
	}
	
}
