package com.pqc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	/*
 	����Cookie�ķ���
 	search����������������
 	request������Ϊ�˻�ȡCookie�е�����
 	response������Ϊ�˽����ɵ�Cookie��Ӧ�����������
 */
	public static void addCookie(String search,HttpServletRequest request, HttpServletResponse response) {
		/*
	 	�Ȼ�ȡ֮ǰ�洢������ʷCookie�е���Ϣ
	 	ƴ���ϵ�ǰ����������(�Ѵ��ڵĲ�ƴ��)
	 	��������֮����������ݴ���Cookie
	 */
		String info = getCookieInfo(request);
		/*��ʱinfo�����������
		 * infoΪ���ַ������ʹ����ǵ�һ���������ݣ�
		 * info�д��ڹؼ��֣��ʹ����ǵ�һ������
		*/
		if("".equals(info)) {
			info = search;
		}else {
			//�ж��Ƿ����search
			if(!info.contains(search)) {
				//�ж��Ƿ����#��
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

	//��ȡcookie���ݵķ���
	public static String getCookieInfo(HttpServletRequest request) {
		String info="";//����һ�����ַ����洢cookie
		Cookie[] cookies = request.getCookies();
		if(!(cookies==null || cookies.length==0)) {
			for (Cookie cookie : cookies) {
				//�ж�cookie�еĹؼ��֣�ȡ��cookies������
				if("search".equals(cookie.getName())) {
					info = cookie.getValue();
				}
			}
		}
		return info;
	}
	
}
