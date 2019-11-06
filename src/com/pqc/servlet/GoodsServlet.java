package com.pqc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.pqc.entity.Category;
import com.pqc.entity.Goods;
import com.pqc.service.CateService;
import com.pqc.service.GoodsService;
import com.pqc.utils.DateTool;
import com.pqc.utils.PageTool;
import com.pqc.utils.UploadUtils;
@WebServlet("/goods")
@MultipartConfig
public class GoodsServlet  extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private GoodsService goodsService = new GoodsService();
	private CateService cateService = new CateService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String func = request.getParameter("func");
		switch (func) {
		case "findAllCate":
			findAllCate(request,response);
			break;
		case "insertGoods":
			insertGoods(request,response);
			break;
		case "findAllGoods":
			findAllGoods(request,response);
			break;
		case "findGoodsById":
			findGoodsById(request,response);
			break;
		case "updateGoods":
			updateGoods(request,response);
			break;
		default:
			break;
		}
	}
	//�޸���Ʒ����
	private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String gid = request.getParameter("gid");
		String cid = request.getParameter("cid");
		String gname = request.getParameter("gname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		String pic=null;
		//��ȡpart�������д洢�Ź����ϴ�����������
		Part part = request.getPart("pic");
		//�ж�part���Ƿ�����ϴ�������
		if(part.getSize()==0) {
			pic = request.getParameter("oldPic");
		}else {
			pic = UploadUtils.upload("goods?func=findGoodsById&gid="+gid, part, request, response);
			//���photo�ʹ������Ͳ�ƥ��
			if(pic.equals("")) {
				return;
			}
		}
		Goods goods = new Goods(Integer.valueOf(gid),Integer.valueOf(cid), gname, color, size, Double.valueOf(price), description, full_description, pic, Integer.valueOf(state), version, DateTool.stringToDate(product_date));
		System.out.println(goods);
		boolean isSuccess = goodsService.updateGoods(goods);
		System.out.println(isSuccess);
		if(isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}
	}
	//ͨ��id��ȡ��Ʒ��Ϣ
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		Goods goods = goodsService.findGoodsById(gid);
		request.setAttribute("goods", goods);
		List<Category> cates = cateService. findAllCate();
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/goods_update.jsp").forward(request, response);
	}
	//չʾ������Ʒ����
	private void findAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ҳ������
		String currentPage = request.getParameter("currentPage");
		int totalCount = goodsService.findGoodsCount();
		PageTool pageTool = new PageTool(totalCount, currentPage);
		//����service��ȡ������Ʒ
		List<Goods> goodses =  goodsService.findAllGoods(pageTool);
		//����Ʒ�ͷ�ҳ�������ŵ�����
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("goodses", goodses);
		request.getRequestDispatcher("admin/goods_list.jsp").forward(request, response);
	}
	//�����Ʒ����
	private void insertGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cid = request.getParameter("cid");
		String gname = request.getParameter("gname");
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String full_description = request.getParameter("full_description");
		String state = request.getParameter("state");
		String version = request.getParameter("version");
		String product_date = request.getParameter("product_date");
		String pic=null;
		//��ȡpart�������д洢�Ź����ϴ�����������
		Part part = request.getPart("pic");
		System.out.println(part);
		//�ж�part���Ƿ�����ϴ�������
		if(part.getSize()==0) {
			request.setAttribute("msg", "����ѡ���ϴ�ͼƬ");
			findAllCate(request, response);
			return;
		}else {
			pic = UploadUtils.upload("goods?func=findAllCAte",part, request, response);
			//���photo�ʹ������Ͳ�ƥ��
			if(pic.equals("")) {
				return;
			}
		}
		Goods goods = new Goods(Integer.valueOf(cid), gname, color, size, Double.valueOf(price), description, full_description, pic, Integer.valueOf(state), version, DateTool.stringToDate(product_date));
		boolean isSuccess = goodsService.insertGoods(goods);
		if(isSuccess) {
			response.sendRedirect("goods?func=findAllGoods");
		}else {
			response.sendRedirect("admin/error.jsp");
		}
	}
	//��ת��Ʒ���ҳ��,��ȡ��Ʒ��Ϣ
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> cates = cateService. findAllCate();
		request.setAttribute("cates", cates);
		request.getRequestDispatcher("admin/goods_add.jsp").forward(request, response);
		
	}
	
	
}
