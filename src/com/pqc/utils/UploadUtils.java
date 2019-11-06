package com.pqc.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadUtils {
	public static String upload(String errorPath, Part part,HttpServletRequest request, HttpServletResponse response) {
		String photo="";
		//��ȡ�ļ���
		photo = part.getSubmittedFileName();
		//���ͼƬͬ������
		photo = UUID.randomUUID()+photo;
		//�����ж�
		String type = photo.substring(photo.lastIndexOf(".")+1);
		if(!("jpeg".equalsIgnoreCase(type) || "jpg".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type))) {
			request.setAttribute("msg", "��ѡ��ͼƬ�����ļ�");
			try {
				request.getRequestDispatcher(errorPath).forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			photo ="";
		}
		
		//�������ش洢�ϴ���Ŀ¼·��
		String realPath= "E:/upload";//������ļ���ʧ����
		//�ж��ļ��Ƿ����
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		//��part�е�����д���ļ�
		try {
			part.write(realPath+"/"+photo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photo;
	}

}